/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-27
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
//发表话题
(function ($, exports) {
    var topicController = {
        elements:{
            "pub_topic":$("#pub_topic"),
            "edit_topic":$("#edit_topic"),
            "pub_bull":$("#pub_bull"),
            "resc_page":$("#resc_page"),
            "topic_page":$("#topicPage"),
            "mail":$("#mail"),
            "commu_dynamics":$("#commu-dynamics"),
            "articleL-content":$("#articleL-content")
        },
        events:{
            "topic_page":{
                click:function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    var $target = $(event.target);
                    location.hash = $target.attr("href");
                }
            },
            "pub_topic":{click:function () {
                this["pubTopicDialog"].dialog("open");
            }
            },
            "articleL-content":{
                mouseover:function (event) {
                    event.stopPropagation();
                    var $target = $(event.target);
                    if ($target.is(".x") || $target.is("tr")) {
                        var _index;
                        $target.is(".x") && (_index = $(".x").index($target));
                        $target.is(".tr") && (_index = _topic.find("tbody tr").index($target));
                        var _topic = $("#topic");
                        _topic.find("tbody tr").removeClass("over").addClass("edit_state");
                        _topic.find("tbody tr:eq(" + _index + ")").addClass("over");
                        _topic.find("tbody tr:eq(" + (_index - 1) + ")").removeClass("edit_state");
                    }
                },
                click:function (event) {
                    event.stopPropagation();
                    var $target = $(event.target);
                    if ($target.is(".x")) {
                        var _topic = $("#topic");
                        var _index = $(".x").index($target);
                        var _t_id = /.*?\?t_id=(.*)/.exec(_topic.find("tbody tr:eq(" + _index + ")").find("td:first a").attr("href"))[1];
                        if (confirm("确定删除本话题?")) {
                            $.post("community/delete_topic.json",{communityId:Topic.communityId,pageNo:Topic.pageNo,t_id:_t_id},function(data){
                               Topic.render(data);
                               $(".x:last").remove();
                               _topic.find("tbody tr").addClass("edit_state");
                            });
                        }
                    }
                }
            },
            "edit_topic":{click:function () {
                var _topic = $("#topic");
                var _edit_topic = this.edit_topic;
                var _state = _edit_topic.attr("state");
                if (_state == 0) {
                    var _frageMent = document.createDocumentFragment();
                    _topic.find("tbody tr").addClass("edit_state").each(function () {
                        var _left = $(this).offset().left + $(this).width() - 2;
                        var _top = $(this).offset().top;
                        var _x = document.createElement("span");
                        _x.style.left = _left - 7 + "px";
                        _x.style.top = _top + 12 + "px";
                        _x.className = "x";
                        _frageMent.appendChild(_x);
                    });
                    this.commu_dynamics[0].appendChild(_frageMent);
                    _edit_topic.text("保存");
                    _edit_topic.attr("state", 1);
                    _edit_topic.css({"boder":"red", "background":"red"});
                    _topic.bind("click.remove", function (event) {
                    });
                } else {
                    _topic.unbind("click.remove");
                    $(".x").remove();
                    _topic.find("tbody tr").removeClass("edit_state");
                    _edit_topic.css({"boder":"#2492CF", "background":"#2492CF"});
                    _edit_topic.text("编辑");
                    _edit_topic.attr("state", 0);
                }
            }
            },
            "pub_bull":{click:function () {
                var _bull_c = this.bull_content;
                var _content = _bull_c.val();
                if ($.trim(_content) == "") {
                    $.messager.alert("公告内容不能为空!");
                    return;
                }
                _bull_c.attr("disabled", true);
                this.pub_bull.attr("disabled", true);
                var _mail = 0;
                this.mail.is(":checked") && (_mail = 1);
                $.post("community/pub_bull.json", {communityId:this.communityId, content:_content, mail:_mail}, $.proxy(function (data) {
                    var bulletins = data.bulletins;
                    var list = "";
                    for (var index in bulletins) {
                        list += "<li>" + bulletins[index].content + "【" + bulletins[index].pubDate + "】" + "</li>"
                    }
                    this.bull_list.html(list);
                    _bull_c.attr("disabled", false);
                    _bull_c.val("");
                    this.pub_bull.attr("disabled", false);
                }, this));

            }
            },
            "resc_page":{
                click:(function ($) {
                    var pageCount = $("#resourcePageCount").val();
                    var pageNo = $("#resourcePageNo").val();
                    var r_b = $("#resource_body");
                    var li = "<li><span title='${resourceRealName}'>${$item.subStr(resourceRealName,0,13)}..</span><a href='resourceOperation/download.html?resourceId=${id}' class='btn_myResrc resrcDown'>下载</a></li>";
                    $.template("li", li);
                    var pageNoList = [];
                    var fun = function ($target) {
                        if (pageNo == pageCount || pageNo == 1) {
                            this.resc_page.find("img").css("opacity", 1);
                            $target.css("opacity", 0.5);
                        } else {
                            this.resc_page.find("img").css("opacity", 1);
                        }
                    };
                    return function (event) {
                        var $target = $(event.target);
                        var _id = $target.attr("id");
                        var offsetLeft = r_b.position().left;
                        if (_id == "resc_next") {
                            if (pageNo == pageCount) {
                                return;
                            }
                            pageNo++;
                            var exist = false;
                            for (var i in pageNoList){
                                if(pageNoList[i]==pageNo){
                                    exist = true;
                                }
                            }
                            if(exist){
                                fun.call(this,$target);
                                offsetLeft-=198;
                                r_b.stop(false, true).animate({"left":offsetLeft}, 100);
                            }else{
                                $.post("community/get_resc.json", {communityId:this.communityId, pageNo:pageNo}, $.proxy(function (data) {
                                    data = data.resourcePager;
                                    pageNo = data.pageNo;
                                    pageCount = data.pageCount;
                                    pageNoList.push(pageNo);
                                    fun.call(this,$target);
                                    offsetLeft-=198;
                                    r_b.append($("<ul style='width:198px;float:left;' id='resc_list'></ul>").append($.tmpl("li", data.domainList, {
                                        subStr:function (str, s, e) {
                                            return str.substr(s, e);
                                        }
                                    }))).stop(false, true).animate({"left":offsetLeft}, 100);
                                }, this));
                            }
                        } else if (_id == "resc_prev") {
                            if (pageNo == 1) {
                                return;
                            }
                            pageNo--;
                            fun.call(this,$target);
                            r_b.stop(false, true).animate({"left":offsetLeft+=198}, 100);
                        }
                    }
                })($)
            }
        },
        switchPage:function (_hash) {
            typeof(_hash)!='number' && (_hash = _hash.substr(1));
            var _pageNo = Topic.pageNo;

            if (_hash == "first") {
                _pageNo = 1;
            } else if (_hash == "next") {
                _pageNo != Topic.pageCount && _pageNo++;
            } else if (_hash == "last") {
                _pageNo = Topic.pageCount;
            } else if (_hash == "prev") {
                _pageNo != 1 && pageNo--;
            } else {
                _pageNo = _hash;
            }
            Topic.pageNo = _pageNo;
            Topic.refresh();

        },
        init:function () {
            var _elements = this.elements;
            var _events = this.events;
            for (var ele in _elements) {
                this[ele] = _elements[ele];
                for (var ev in _events[ele]) {
                    if (!$.isFunction(_events[ele][ev])) {
                        continue;
                    }
                    _elements[ele].bind(ev, $.proxy(_events[ele][ev], this));
                }
            }
            var $topic = $("#topic tbody");
            var _this = this;
            var pubTopicFun = function () {
                $.template("topicTmpl", exports.topicTmpl);
                (pubTopicFun = function () {
                    var _title = _this["topicTitle"];
                    var _content = _this["topicContent"];
                    _this["topicForm"].find(":input").each(function () {
                        $(this).val($.trim($(this).val()));
                    });
                    $.post("topic/pub.json", _this["topicForm"].serializeArray(), function (data) {
                        var _topics = data.topics;
                        $topic.html($.tmpl("topicTmpl", _topics));
                        $pubW.dialog("close");
                    });
                })();
            };
            var $pubW = this['pubTopicDialog'] = $("#pubTopicDialog").dialog(
                {width:440, height:280, modal:true, autoOpen:false,
                    buttons:{
                        "发表话题":function () {
                            $.proxy(pubTopicFun(), this);
                        },
                        "取消":function () {
                            $(this).dialog("close");
                        }
                    },
                    close:function () {
                        _this['topicTitle'].val("");
                        _this['topicContent'].val("");
                    }
                });
            $L.script("js/jwysiwyg/jquery.wysiwyg.js").wait(function () {
                $('#topicContent').wysiwyg({
                    controls:{
                        strikeThrough:{ visible:true},
                        underline:{ visible:true},
                        subscript:{ visible:true},
                        superscript:{ visible:true},
                        insertImage:{visible:false},
                        separator05:{ visible:false, separator:false },
                        separator06:{ visible:false, separator:false },
                        separator07:{ visible:false, separator:false },
                        separator08:{ visible:false, separator:false },
                        separator09:{ visible:false, separator:false },
                        separator10:{ visible:false, separator:false },
                        separator11:{ visible:false, separator:false },
                        separator12:{ visible:false, separator:false },
                        h1:{visible:false},
                        h2:{visible:false},
                        h3:{visible:false},
                        uploadImgSep:{ separator:false},
                        removeFormat:{visible:false},
                        increaseFontSize:{visible:false},
                        decreaseFontSize:{visible:false},
                        uploadImg:{
                            visible:true,
                            exec:function () {
                                var _target = $(".uploadImg");
                                $("#img_pop").css("left", _target.offset().left - 90).css("top", _target.offset().top + 24).show();
                            },
                            className:'uploadImg'
                        }
                    }
                });
                var _file = $("#img_form input");
                $("#df_img").click(function () {
                    _file.trigger("click");
                });
                _file.bind("change", function () {
                    var img_pat = /(\.jpeg)|(\.png)|(\.gif)|(\.jpg)/gi;
                    var _fileName = $(this).val();
                    if (img_pat.test(_fileName)) {
                        $("#img_form").submit();
                    } else {
                        $.messager.alert("您上传的文件格式不正确,请上传jpeg,jpg,gif,png图片");
                    }
                    var _v =$.trim($("#net_img").val()) ;
                    if(_v!="http://"){
                        if (img_pat.test(_v)) {
                            $.messager.alert("您上传的文件格式不正确,请上传jpeg,jpg,gif,png图片");
                        }
                    }
                    $(this).val("");
                    $("#net_img").val("http://");
                });
                $("#ok").bind("click", function () {
                    $("#img_pop").hide();
                    var _content_body = $("#topicContentIFrame").get(0).contentWindow.document.body;
                    $(_content_body).trigger("focus");
                    var _reply = $('#topicContent');
                    _reply.wysiwyg("focus");
                    if($("#df_img").attr("src")!="http://cdn-img.easyicon.net/png/5347/534765.png"){
                        _reply.wysiwyg('insertImage', basePath+$("#df_img").attr("src"));
                    }
                    if($.trim($("#net_img").val())!="http://"){
                        _reply.wysiwyg('insertImage', $.trim($("#net_img").val()));
                    }
                    $("#net_img").val("http://");
                    _reply.val(_content_body.innerHTML);
                    showImg("http://cdn-img.easyicon.net/png/5347/534765.png");
                });
                $("#topicContentIFrame").get(0).contentWindow.document.designMode="on";
            });
            var $topicForm = this["topicForm"] = $pubW.find("#topicForm");
            this["topicTitle"] = $topicForm.find("#topicTitle");
            this["topicContent"] = $topicForm.find("#topicContent");
            this["communityId"] = $("#communityId").val();
            this["bull_content"] = $("#bull_content");
            this["bull_list"] = $("#bull_list");
            ("onhashchange" in exports) && (exports.onhashchange = $.proxy(function () {
                var _hash = location.hash;
                if(_hash==""){
                    _hash=1;
                }
                this.switchPage(_hash);
            }, this));

        }
    };

    var uploadController = {
        init:function () {
            var courseIds = $("#courseId").val();
            var resourceIds = resourceIds || [];
            var $uploadDialog = $("#uploadDialog");
            var description = "";
            var mail  = $("#mail1");
            $("#Filedata").commonUpload({"width":"157px", "height":"45px", "onUploadSuccess":function (file, data, response) {
                var fileName = file.name;
                $uploadDialog.dialog("open");
                resourceIds.push(data);
                $uploadDialog.find("#file-queue ul").append("<li><a href='resourceOperation/download.html?resourceId=" + data + "'>" + fileName + "</a></li>");
                /*$("#title").val(fileName);*/
                description = fileName;
            }
            });
            $uploadDialog.dialog({width:480, height:280, modal:true, autoOpen:false,
                buttons:{
                    "提交":function () {
                        $.post("resourceOperation/save.json", {resourceIds:resourceIds.join(","), description:description, courseIds:courseIds,mail:(mail.is(":checked")?1:0)}, function (data) {
                            if (data.save == "success") {
                                location.reload();
                            } else {
                                $.messager.alert("亲，出错了，一会再试哈～");
                            }
                        });
                    },
                    "取消":function () {
                        $(this).dialog("close");
                    }
                },
                close:function () {
                    $uploadDialog.find("#file-queue ul").empty();
                }
            });
        }
    };
    var Topic = {
        pageNo:1,
        topicTmpl:$.template("topicTmpl", "<tr><td><a href='topic/detail.html?t_id=${id}' title='${topicTitle}'>${$item.substring(topicTitle,0,14)}..</a></td>"
            + "<td title='${authorName}'>${authorName}</td>"
            + "<td>${replyCount}</td>"
            + "<td>${latestReplyDate==''?pubDate:latestReplyDate}</td></tr>"),
        pageCount:$("#s_p li").size(),
        communityId:$("#communityId").val(),
        topicBody:$("#topic tbody"),
        main_page:$("#s_p"),
        refresh:function () {
            $.post("community/get_topics.json", {pageNo:this.pageNo, communityId:this.communityId}, this.render);
        },
        render:function (data) {
            data = data.topicPager;
            this.pageCount = data.pageCount;
            this.pageNo = data.pageNo;
            this.topicBody.html($.tmpl("topicTmpl", data.domainList, {substring:function (str, s, e) {
                return str.substring(s, e)
            }}));
            var li = "";
            for (var i = 1; i <= this.pageCount; i++) {
                li += '<li class="btn_page ' + (i == this.pageNo ? "current" : "") + '"><a href="#' + i + '">' + i + '</a> </li>';
            }
            this.main_page.html(li);
        }
    };
    $(function () {
        topicController.init();
        if (isTeacher == 1) {
            uploadController.init();
        }
    });

})(jQuery, window);
