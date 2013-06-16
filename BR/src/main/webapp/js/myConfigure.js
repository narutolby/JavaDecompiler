/**
 * Created with IntelliJ IDEA.
 * User: zhaomeng
 * Date: 12-9-7
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {

    var pop=new Pop("",
        "",
        "您被老师邀请加入社区，请在右侧“加入社区”弹出菜单中查看详细信息");
    var myConfigureController = {
        createCache:function (maxLength) {
            var ArrayHashMap = {
                maxLength:maxLength,
                array:[],
                hashMap:{},
                push:function (key, value) {
                    var array = this.array;
                    var hashMap = this.hashMap;
                    if (array.length == maxLength) {
                        var _key = array.shift();
                        delete hashMap[_key];
                    }
                    array.push(key);
                    hashMap[key] = value;
                },
                exist:function (key) {
                    return (key in this.hashMap);
                },
                get:function (key) {
                    return this.hashMap[key];
                }
            };
            return ArrayHashMap;
        },
        elements:{
            "academy":"#academy",
            "major":"#major",
            "course":"#course",
            "term":"#term",
            "submitBtn":"#submitBtn",
            "follow_majors_list":"#follow_majors_list",
            "pop_window":"#pop_window"
        },
        events:{
            "major_term_share":function (eventType) {
                var _isTeacher = isTeacher;
                if (_isTeacher == "1" && eventType == "major") {
                    var _term = this.term;
                    var options = "<option value='1'>第一学期</option><option value='2'>第二学期</option><option value='3'>第三学期</option><option value='4'>第四学期</option><option value='5'>第五学期</option><option value='6'>第六学期</option><option value='7'>第七学期</option><option value='8'>第八学期</option>";
                    _term.html(options);
                    return;
                }
                var _coursesCache = this.coursesCache;
                var _majorValue = this.major.val();
                _isTeacher == "1" && (_majorValue = _majorValue + "_" + this.term.val());
                if (_coursesCache.exist(_majorValue)) {
                    var courses = _coursesCache.get(_majorValue);
                    var options = "";
                    for (var index in courses) {
                        options += "<option value='" + courses[index].id + "'>" + courses[index].name + "</option>"
                    }
                    this.course.html(options);
                    var $majorList = this["follow_majors_list"].find("#" + this.major.val() + " .list span");
                    var majorIdList = $.map($majorList, function (dom, index) {
                        return dom.id
                    });
                    this.course.val(majorIdList);
                    return;
                }
                $.post("academy/major/courses.json", {majorId:_majorValue}, $.proxy(function (data) {
                    var courses = data.courses;
                    var options = "";
                    for (var index in courses) {
                        options += "<option value='" + courses[index].id + "'>" + courses[index].name + "</option>"
                    }
                    this.course.html(options);
                    var $majorList = this["follow_majors_list"].find("#" + this.major.val() + " .list span");
                    var majorIdList = $.map($majorList, function (dom, index) {
                        return dom.id
                    });
                    this.course.val(majorIdList);
                    _coursesCache.push(_majorValue, courses);
                }, this));
            },
            "academy":{change:function () {
                this.course.empty();
                var _majorsCache = this.majorsCache;
                if (_majorsCache.exist(this.academy.val())) {
                    var majors = _majorsCache.get(this.academy.val());
                    var options = "";
                    for (var index in majors) {
                        options += "<option value='" + majors[index].id + "'>" + majors[index].name + "</option>"
                    }
                    this.major.html(options);
                    return;
                }
                $.post("academy/majors.json", {academyId:this.academy.val()}, $.proxy(function (data) {
                    var majors = data.majors;
                    var options = "";
                    for (var index in majors) {
                        options += "<option value='" + majors[index].id + "'>" + majors[index].name + "</option>"
                    }
                    this.major.html(options);
                    _majorsCache.push(this.academy.val(), majors);
                }, this));

            }
            },
            "major":{change:function () {
                this.course.html("");
                this.events.major_term_share.call(this, "major");
            }
            },
            "term":{change:function () {
                this.events.major_term_share.call(this, "term");
            }
            },
            "course":{ change:function () {
                var followMajorIdArray = this.course.val();
                var followMajorsHtml = "";
                for (var index in followMajorIdArray) {
                    followMajorsHtml += "<span id='" + followMajorIdArray[index] + "'>" + this.course.find("option[value=" + followMajorIdArray[index] + "]").text() + "<img src='img/01_condition.png'/>" + "</span>";
                }
                var $follow_major_list = this["follow_majors_list"];
                var _majorValue = this.major.val();
                if (!$follow_major_list.find("#" + _majorValue).size()) {
                    followMajorsHtml = "<fieldset id='" + _majorValue + "'><legend>" + this.major.find("option[value=" + _majorValue + "]").text() + "</legend><div class='list'>" + followMajorsHtml + "</div></fieldset></fieldset>"
                    $follow_major_list.append(followMajorsHtml);
                } else {
                    var $fieldset = $follow_major_list.find("#" + _majorValue);
                    if ($.trim(followMajorsHtml) == "") {
                        $fieldset.remove();
                        return;
                    }
                    $fieldset.find(".list").html(followMajorsHtml);
                }
            }
            },
            "submitBtn":{click:function () {
                var _this = this;
                var param = {};
                var $fieldset = _this["follow_majors_list"].find("fieldset");
                /*if ($fieldset.size() == 0) {
                 alert("亲，先设置课程在保存哦～");
                 return;
                 }*/
                $.each($fieldset, function () {
                    var $this = $(this);
                    var _id = $this.attr("id");
                    var majorArray = $.map($this.find(".list span"), function (dom, index) {
                        return dom.id
                    });
                    param[_id] = majorArray.join(",");
                });
                $.post("academy/major/set_course.json", param, function (data) {
                    if (typeof data.response == "undefined") {
                        var $folder_parent = $("#folder_parent");
                        $folder_parent.nextAll().remove();
                        var folder_nodes = "";
                        $folder_parent.parent("div").append('<table id="table" cellspacing="10">');
                        for(var key in data.recommendBooks) {

                            for(var value in data.recommendBooks[key][0]) {
                                if(value < 2){
                                    $("#table").append(
                                        '<tr>'+
                                            '<td><img width="70px" height="94px" src="'+data.recommendBooks[key][0][value].smallPic+'"/></td>'+
                                            '<td>'+
                                                '<table style="text-align: left">'+
                                                    '<tr><p style="color:rgb(0, 75, 145) " >'+data.recommendBooks[key][0][value].title+'</p></tr>'+
                                                    '<tr><p>'+data.recommendBooks[key][0][value].authors+'(作者)</p></tr>'+
                                                    '<tr><a href="'+data.recommendBooks[key][0][value].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></tr>'+
                                                '</table>'+
                                            '</td>'+
                                        '</tr>'
                                    );
                                }
                            }
                            $folder_parent.parent("div").append('</table>');

                        }
                        $folder_parent.parent("div").append('<div  align="left" style="color: rgb(0, 75, 145);"><h3 style="cursor: pointer">>看更多推荐图书</h3></div>');

                        var $detail = $("#hotResrc");

                        $detail.html('');
                        for(var key1 in data.bookList) {
                            for(var value1 in data.bookList[key1]) {
                                if(value1 < 6){
                                    $detail.append('<div id="detail" class="detail_hidden">' +
                                        '<h2 id="title" class="detail_title">'+data.bookList[key1][value1].title+'</h2>' +
                                        '<p id="subtitle" class="sub_title">'+data.bookList[key1][value1].authors + ' / '+ data.bookList[key1][value1].pubdate+' / '+data.bookList[key1][value1].publisher+' / '+data.bookList[key1][value1].price+'</p>' +
                                        '<p id="describe">'+data.bookList[key1][value1].summary+'</p>' +
                                        '</div>'
                                    );
                                }
                            }
                        }

                        var communities = data.communities;
                        var btn_str = "";
                        if (isTeacher == "1") {
                            btn_str = "创建";
                            $.messager.alert("亲，保存成功了，快去上传资源吧～");
                        } else {
                            btn_str = "加入";
                            $.messager.alert("亲，保存成功了，快去我的资源看看吧～");
                        }
                        var _new_commu_list = "";
                        for (var index in communities) {
                            var _community = communities[index];
                            _new_commu_list += "<li id='" + _community.id + "'><span>" + _community.name + "</span><span style='float:right'><button style='cursor:pointer' id='active' class='active'>" + btn_str + "</button></span></li>"
                        }
                        var _pop_window = _this.pop_window;
                        _pop_window.find("#new_cmu_list").html(_new_commu_list).end().css({right:0 - _pop_window.width(), top:500}).animate({right:0}, 2000);
                        setTimeout(function () {
                            _pop_window.animate({right:0 - _pop_window.width(), top:300}, 2000);
                        }, 5000);

                    } else {
                        $.messager.alert("亲，报错了哦～");
                    }
                });
            }
            },
            /*"commu_active":{hover:function(){
             this.pop_window.animate({right:0},2000);
             }
             },*/
            "follow_majors_list":{click:function (event) {
                var _target = event.target;
                if (_target.tagName.toLowerCase() === "img") {
                    var $target = $(_target);
                    var $span = $target.parent("span[id]");
                    if ($span.siblings().size() != 0) {
                        $span.remove();
                    } else {
                        $span.parents("fieldset").remove();
                    }
                    var $majorList = this["follow_majors_list"].find("#" + this.major.val() + " .list span");
                    var majorIdList = $.map($majorList, function (dom, index) {
                        return dom.id
                    });
                    this.course.val(majorIdList);
                } else {
                    return;
                }
            }
            },
            "pop_window":{click:function (event) {
                var _target = event.target;
                var _id = _target.id;

                if (_id == "close") {
                    var _pop_window = this.pop_window;
                    _pop_window.animate({bottom:0 - _pop_window.height() - 20}, 1000);
                } else if (_id == "active") {
                    var $target = $(_target);
                    $.get("community/active.json", {communityId:$target.parents("li").attr("id")}).success(function () {
                        $target.attr("disabled", true).attr("class", "unactive");


                    });
                } else if (_id == "active_invite") {
                    var $target = $(_target);
                    var userId = $target.parents("li").attr("id");
                    var courseId = $target.parents("span").attr("id");
                    var communityId = $target.parents("span").prev().attr("id");
                    $.post("community/active_invite.json",{user_id:userId,course_id:courseId,community_id:communityId},function() {
                        $target.attr("disabled", true).attr("class", "unactive");
                    });
                }

            },
                mouseenter:function (event) {
                    var _target = event.target;
                    var _id = _target.id;
                    var _pop_window = this.pop_window;
                    if (_id == "commu_active" || _target.tagName.toLowerCase() == "img") {
                        if (_pop_window.is(":animated")) {
                            return;
                        }
                        _pop_window.animate({right:0}, 300);
                    }
                },
                mouseleave:function () {
                    var _pop_window = this.pop_window;
                    if (_pop_window.is(":animated")) {
                        return;
                    }
                    _pop_window.animate({right:0 - _pop_window.width()}, 300);
                }

            }
        },
        init:function () {

            this.majorsCache = this.createCache(5);
            this.coursesCache = this.createCache(5);


            $($.proxy(function () {
                for (var el in this.elements) {
                    var val = this.elements[el];
                    var ele = myConfigureController[el] = $(val);
                    for (var event in this.events[el]) {
                        ele.bind(event, $.proxy(this.events[el][event], this));
                    }
                }

                (function (_this) {
                    var counter = 0;
                    var timer = "";
                    var ele = document.getElementById("commu_active");
                    var ele1 = $("#new_cmu_list");
                    var length = ele1.children("li").size();
                    if (length != 0) {
                        function show() {
                            ele.style.visibility = (ele.style.visibility == "hidden") ? "visible" : "hidden";
                            if (counter <= 10) {
                                if (_this.pop_window.is(":animated")) {
                                    clearTimeout(timer);
                                    ele.style.visibility = "visible";
                                    return;
                                }
                                timer = setTimeout(show, 500);
                                counter += 1;
                            }
                        }

                        show();
                    }
                })(this);
            }, this)
            );
            $.get("academy/all.json", $.proxy(function (data) {
                var academies = data.academies;
                var options = "";
                for (var index in academies) {
                    options += "<option value='" + academies[index].id + "'>" + academies[index].name + "</option>"
                }
                this.academy.html(options);

            }, this));

        }
    };
    if (user != "") {
        $(function () {

            myConfigureController.init();

        });
    }




})(jQuery, window);

