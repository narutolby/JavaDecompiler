(function ($) {
    // resource对象
    myResource = {
        root:"",
        url:"",
        tmplScriptId:"",
        tmplAttach:"",
        pageSize:"",
        currentPage:"",
        resourceType:"",
        order:"uploadTime_desc",
        courseId:"",
        courseName:"",
        init:function (url, tmplScriptId, tmplAttach, pageSize, _root) {
            this.url = url;
            this.tmplScriptId = tmplScriptId;
            this.tmplAttach = tmplAttach;
            this.pageSize = pageSize;
            this.root = _root;
        },
        refresh:function () {
            var _root = this.root;
            var _this = this;
            var callback = function (data) {
                var pageCount = data.pager.pageCount;
                _this.currentPage > pageCount
                && (_this.currentPage = pageCount);
                var pageHtml = '<a href="javascript:void(0)" style="margin:0 3px" id="page_next"><b><span>下一页</span></b></a>';
                for (var i = pageCount; i--;) {
                    var num = i + 1;
                    pageHtml = '<a href="javascript:void(0)" style="margin:0 3px" id="page_'
                        + num
                        + '"><b><span>'
                        + num
                        + '</span></b></a>'
                        + pageHtml;
                }
                pageHtml = '<a href="javascript:void(0)" style="margin:0 3px" id="page_prev"><b><span>上一页</span></b></a>'
                    + pageHtml;
                _root.html(pageHtml);
                _root.find("a[id=page_" + _this.currentPage + "] span")
                    .unwrap("b").css({
                        "font-weight":"bold",
                        "font-size":"12px"
                    });
            };
            var urlParams = {
                pageNo:_this.currentPage,
                pageSize:_this.pageSize,
                resourceType:_this.resourceType,
                order:_this.order,
                courseId:_this.courseId
            };

            $.renderTemplateWithModel(_this.url, _this.tmplScriptId,
                _this.tmplAttach, urlParams, callback);
        },
        reset:function () {
            this.currentPage = 1;
            this.resourceType = "all";
            this.order = "uploadTime_desc";
        }
    };

    // js 自定义插件
    // 模板插件
    {
        $.extend($, {
            renderTemplateWithModel:function (url, tmplId, domId, param, callback) {
                $.getJSON(url, param, function (data) {
                    $("#" + domId).empty().append(
                        $("#" + tmplId).tmpl(data.pager.domainList));
                    if (typeof (callback) === "function") {
                        callback(data);
                    }
                });
            }
        });

        $.extend($, {importTemplate:function (url, tmplId) {
            $.get(url, function (data) {
                $("#" + tmplId).html(data.replace(/(model\.)|(<%@.*?%>)/g, ""));
            });
        }});
    }
    // 分页插件
    {
        $.extend(
            $.fn,
            {
                pagination:function (selector, eventype, url, tmplScriptId, tmplAttach, pageSize) {
                    var _this = this;
                    myResource.init(url, tmplScriptId, tmplAttach, pageSize, _this);
                    myResource.currentPage = 1;
                    return _this.delegate(selector, eventype,
                        function (event, refreshPagination) {
                            var $target = $(event.target);
                            var pageNo = $target
                                .closest("[id]")
                                .attr("id").split(
                                "_")[1];
                            var pageCount = _this.find(
                                "a").size() - 2;
                            pageNo == "next"
                            && (pageNo = myResource
                                .currentPage / 1 + 1);
                            pageNo == "prev"
                            && (pageNo = myResource
                                .currentPage - 1);
                            pageNo < 1
                            && (pageNo = 1);
                            pageNo > pageCount
                            && (pageNo = pageCount);
                            myResource.currentPage = pageNo;
                            myResource.pageSize = pageSize;
                            myResource.refresh();
                        });
                }
            });
    }
    //通用提示插件
    {
        (function ($) {
            $.messager = $.messager || {};
            $.extend($.messager, {
                ele:$("#dialog-message"),
                alert:function (message) {
                    this.ele.find("#mess_content").text(message);
                    this.ele.dialog({
                        modal:true,
                        buttons:{
                            "确定":function () {
                                $(this).dialog("close");
                            }
                        }
                    });
                },
                prompt:function (message) {

                }
            });
        })(jQuery);
    }
})(jQuery);


$(function () {
    // 登录 判空验证1
    (function ($) {
        $(".login[name=userId]").blur(
            function () {
                if ($(this).val() == "") {
                    $("#login_notice1").text("账号不能为空！");
                }
            }).focus(function () {
                $("#login_notice1").text("");
            });
        $(".login[name=passwd]").blur(
            function () {
                if ($(this).val() == "") {
                    $("#login_notice2").text("密码不能为空！");
                }
            }).focus(function () {
                $("#login_notice2").text("");
            });
    })(jQuery);


    //搜索框默认文字的设置与清除
    (function ($) {
        $("#srch_txt").focus(function () {
            var txt_value = $(this).val();
            if (txt_value == "搜索资源、社区、图书") {
                $(this).val("");
            }
        });
        $("#srch_txt").blur(function () {
            var txt_value = $(this).val();
            if (txt_value == "") {
                $(this).val("搜索资源、社区、图书");
            }
        });
    })(jQuery);


    //  首页  资源详细信息的显示与隐藏
    (function ($) {
        var index = 0, $detail = {}, position, top = 0, left = 0;
        $("#glide_1 ul li").hover(function () {
            index = $(this).index();
            position = $(this).offset();
            left = position.left + 130;
            top = position.top;
            $detail = $("div.hotResrc_detail").find("div:eq(" + index + ")");
            $detail.css({"top":top + "px", "left":left + "px"});
            $detail.removeClass("detail_hidden").addClass("detail_show").fadeIn();
        }, function () {
            $detail.removeClass("detail_show").addClass("detail_hidden").fadeOut();
        });
    })(jQuery);


    //  我的资源  资源详细信息的显示与隐藏
    (function ($) {
        var index = 0, $detail = {}, position, top = 0, left = 0;
        $("ul.myResrcList li div").hover(function () {
            index = $(this).parent().index();
            position = $(this).parent().offset();
            left = position.left + 130;
            top = position.top;
            $detail = $("div.myResrc_detail").find("div:eq(" + index + ")");
            $detail.css({"top":top + "px", "left":left + "px"});
            $detail.removeClass("detail_hidden").addClass("detail_show").fadeIn();
        }, function () {
            $detail.removeClass("detail_show").addClass("detail_hidden").fadeOut();
        });
    })(jQuery);


    //   登录对话框弹出
    (function ($) {
        $('#loginDialog').dialog({
            autoOpen:false,
            width:400,
            height:260,
            modal:true,
            title:"用户登录",
            buttons:{
                "登 录":function () {
                    var $this = $(this);
                    var $loginForm = $("#loginForm");
                    if ($.trim($loginForm.find("input[name=userId]").val()) == "" || $.trim($loginForm.find("input[name=passwd]").val()) == "") {
                        return;
                    }
                    var loginUrl = $loginForm.attr("action");
                    $.post(loginUrl, $loginForm.serializeArray(), function (data) {
                        var login_info = $.parseJSON(data);
                        if (login_info.login == false) {
                            var $login_note = $("#login_note").html("用户名或密码错误，请重新登陆!").stop(true, true).fadeIn("normal");
                            if (typeof(window.login_timer) != "undefined") {
                                clearTimeout(window.login_timer);
                                $login_note.stop(true, true);
                            }
                            window.login_timer = setTimeout(function () {
                                $login_note.fadeOut("normal", function () {
                                    $login_note.html("");
                                });
                            }, 3000);
                        } else {
                            location.reload();
                            /*$this.dialog("close");
                             var loginfoHtml = "<li><a href=\"#\">" + login_info.user + "</a></li><li>|</li><li><a href=\"user/logout.html\" id=\"logout\">注销</a></li>";
                             var uploadResourceMenuItem = "<li><a href=\"elec_resrc/upload.html\">上传资源</a></li><li><img src=\"img/nav_split.jpg\"/></li>";
                             $("#nav #mr").after(uploadResourceMenuItem);
                             $("#login_info").html(loginfoHtml);*/
                        }
                    });
                },
                "取 消":function () {
                    $(this).dialog("close");
                }
            }
        });
        // Dialog Link
        $('#login').click(function () {
            var formClear = function ($form) {
                $form.find("input").val("");
            };
            formClear($('#loginDialog').dialog('open'));
            return false;
        });
        $("#nav").delegate("a[href!=index.html]", "click", function (event) {
            var $login = $("#login");
            if ($login.size() == 0) {
                return;
            }
            event.preventDefault();
            $("#login").click();
        });
    })(jQuery);


    //   上传资源对话框弹出
    (function ($) {
        if (user == "") {
            $("#login").trigger("click");
            return;
        }
        // Dialog
        if ($('#uploadDialog').size() == 0) {
            return;
        }
        $('#uploadDialog').dialog({
            autoOpen:false,
            width:700,
            height:550,
            modal:true,
            title:"上传资源",
            buttons:{
                "上 传":function () {
                    $(this).dialog("close");
                },
                "取 消":function () {
                    $(this).dialog("close");
                }
            }
        });

        // Dialog Link
        $('#upload').click(function () {
            $('#uploadDialog').dialog('open');
            return false;
        });
    })(jQuery);


    //资源的上下翻页展示
    (function ($) {
        $("a.btn_next").click(function () {
            var $parent = $(this).parents("div.glide_wrapper");
            var $v_content = $parent.find("div.glide_content");
            var $v_show = $parent.find("div.glide_content_list");
            var v_height = $v_content.height();
            var len = $v_show.find("li").length;
            var i = $v_show.attr("pageSize");
            var page = $v_show.attr("page");
            var page_count = len % i == 0 ? (len / i) : (Math.floor(len / i) + 1);
            if (!$v_show.is(":animated")) {
                if (page == page_count) {
                    $v_show.animate({top:"0px"}, "slow");
                    page=1;
                } else {
                    $v_show.animate({top:"-=" + (v_height + 20)}, "slow");
                    page++;
                }
                $v_show.attr("page",page);
            }
        });

        $("a.btn_prev").click(function () {
            var $parent = $(this).parents("div.glide_wrapper");
            var $v_content = $parent.find("div.glide_content");
            var $v_show = $parent.find("div.glide_content_list");
            var v_height = $v_content.height();
            var len = $v_show.find("li").length;
            var i = $v_show.attr("pageSize");
            var page = $v_show.attr("page");
            var page_count = len % i == 0 ? (len / i) : (Math.floor(len / i) + 1);
            if (!$v_show.is(":animated")) {
                if (page == 1) {
                    $v_show.animate({top:"-=" + (v_height + 20) * (page_count - 1)}, "slow");
                    page = page_count;
                } else {
                    $v_show.animate({top:"+=" + (v_height + 20)}, "slow");
                    page--;
                }
                $v_show.attr("page",page);
            }
        });

    })(jQuery);


    //  资源 删除动态效果
    (function ($) {
        var $resrc_list = $("#resrc_list");
        $resrc_list.delegate("a.resrcDel", "click", function () {
            var $this = $(this);
            var resourceId = $this.attr("rel");
            $.get("resourceOperation/delete.json", {resourceId:resourceId}, function () {
                myResource.refresh();
            });
            $("div#destination").removeClass("detail_hidden").fadeIn();
            var resrcTarget = $this.siblings("div").find("img");
            var resrcDel = resrcTarget.clone().css({"position":"absolute", "top":resrcTarget.offset().top, "left":resrcTarget.offset().left, "z-index":999999999}).show();
            resrcDel.appendTo("body").animate({top:$("#destination").offset().top, left:$("#destination").offset().left, width:30, height:40 },
                {duration:1000, callback:function () {
                }, complete:function () {
                    resrcDel.remove();
                } });
            var _trashTimer;
            (_trashTimer = window.trashTimer) && clearTimeout(_trashTimer);
            window.trashTimer = setTimeout(function () {
                $("div#destination").addClass("detail_hidden").stop(true, true).fadeOut();
            }, 2000);
        });
    })(jQuery);


    //  资源  下载动态效果
    (function ($) {
        $("#resrc_list").delegate("a.resrcDown", "click", function () {
            var downBtn = $(this);
            var arrow = $("#downloadArrow");
            arrow.removeClass("detail_hidden").css({ "top":downBtn.offset().top - 250, "left":downBtn.offset().left, "z-index":999999999}).show()
                .animate({top:1000, left:downBtn.offset().left, opacity:0},
                {duration:1000, callback:function () {
                }, complete:function () {
                    arrow.addClass("detail_hidden").css({"opacity":"1"});
                } });
        });
    })(jQuery);

    //引导层
    (function () {
        $("#user_guide").click(function () {
            $("#nav ul li:eq(0)").addClass("guide_target");
            $("#guide").css("display", "block");
        });


        $(".pos_layer").delegate("a.move_intoguide_known", "click", function () {
            var $dd;
            var index;
            $dd = $(this).parents(".mod_guide_wrap").css("display", "none");
            $dd.next().css("display", "block");
            index = ($(this).parents(".pos_layer").find(".mod_guide_wrap").index($dd)) * 2 + 2;
            if (index == 8) {
                $("#guide").css("display", "none");
                return;
            }
            $("#nav ul li:eq(" + index + ")").addClass("guide_target").siblings().removeClass("guide_target");

        });
    })(jQuery);


});




