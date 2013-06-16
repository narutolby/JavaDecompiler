/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-30
 * Time: 下午6:23
 * To change this template use File | Settings | File Templates.
 */

(function ($, exports) {

    var topicController = {
        elements:{
            btnReply:$("#btn_reply"),
            replyTopicArea:$("#reply_input"),
            replyTopicBody:$("#replyTopicBody"),
            topicPage:$("[id=topicPage]"),
            topicEdit:$("#topicEdit")
        },
        events:{
            btnReply:{
                click:function () {
                    var _topic = Topic.create({content:$.trim(this.replyTopicArea.val())});
                    if (_topic.content == "") {
                        $.messager.alert("发表内容不能为空!");
                        return;
                    }
                    _topic.content = _topic.content;//.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                    Topic.pageNo = _topic.pageNo = Topic.pageCount;
                    this.replyTopic("topic/reply.json", _topic);
                }
            },
            replyTopicBody:{
                click:function (event) {
                    var $target = $(event.target);
                    if (!$target.is(".active")) {
                        return;
                    }
                    window.scrollTo(0, this.replyTopicArea.offset().top);
                    this.p_id = $target.attr("p_id");
                    var _info_right = $target.parent(".info_right");
                    var reply_message = _info_right.parent(".info").next(".reply").text().replace(/【[\S\s]*:.*?\r?\n/g, '').replace(/(^.*$)/gm, ": $1") + "\r";
                    var m_t = "【回复" + _info_right.prev(".info_left").find(".m_t").text() + "】\r" + reply_message;
                    this.replyTopicArea.val(m_t);
                }

            },
            topicPage:{click:function (event) {
                event.preventDefault();
                event.stopPropagation();
                var $target = $(event.target);
                location.hash = $target.attr("href");
            }
            },
            topicEdit:{
                click:(function ($) {
                    var _mainTopic = $("#main_topic");
                    var _text;
                    _mainTopic.delegate("button", "click", function () {
                        var _t = $.trim(_mainTopic.children("textarea").val());
                        if (_t == _text) {
                            _mainTopic.html("<p>" + _t + "</p>");
                            _text = undefined;
                            return;
                        }
                        _text = undefined;
                        topicController.modifyTopic("topic/modify.json", _t, function (data) {
                            if (data.success == 1) {
                                _mainTopic.html("<p>" + _t + "</p>");
                            }
                        });
                    });
                    return function (event) {
                        if (!_text) {
                            _text = _mainTopic.children("p").text();
                        } else {
                            _text = $.trim(_mainTopic.children("textarea").val());
                        }
                        var _area = _mainTopic.html($("<textarea class='edit_input'></textarea><button style='position: relative;top:-10px;left:5px' id='save'>保存</button>").val(_text)).children("textarea")[0];
                        var l = _text.length;
                        if (_area.createTextRange) {//IE浏览器
                            var range = _area.createTextRange();
                            range.moveEnd("character", l);
                            range.moveStart("character", l);
                            range.select();
                        } else {
                            _area.setSelectionRange(l, l);
                            _area.focus();
                        }
                    }
                })($)
            }


        },
        switchPage:function (_hash) {
            typeof(_hash) != 'number' && (_hash = _hash.substr(1));
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
        replyTopic:function (url, _topic) {
            $("#subTopicTmpl").template("subTopicTmpl");
            var _replyTopicArea = this.replyTopicArea;
            var _replyTopicBody = this.replyTopicBody;
            var _btnReply = this.btnReply;
            var _mainPage = this.topicPage.find("#s_p");
            (this.replyTopic = function (_u, _t) {
                _replyTopicArea.attr("disabled", true);
                _btnReply.attr("disabled", true);
                $.post(_u, _t, $.proxy(function (data) {
                    var pager = data.subTopicPager;
                    var _html = $.tmpl("subTopicTmpl", pager.domainList).each(function () {
                        $(this).html($(this).html().replace(/(&lt;)|(&gt;)|http(<font.*?>)/gi, function ($1,$2) {
                            if ($1 == "&lt;") {
                                return "<";
                            } else if($1 == "&gt;") {
                                return ">"
                            }else{
                                return "http";
                            }
                        }));
                    });
                    _replyTopicBody.html(_html);
                    _replyTopicArea.attr("disabled", false).val("");
                    _replyTopicArea.wysiwyg('clear');
                    _btnReply.attr("disabled", false);
                    Topic.pageCount = pager.pageCount;
                    var li = "";
                    for (var i = 1; i <= pager.pageCount; i++) {
                        li += '<li class="btn_page ' + (i == Topic.pageNo ? "current" : "") + '"><a href="#' + i + '">' + i + '</a> </li>';
                    }
                    _mainPage.html(li);
                }, this));
            })(url, _topic);
        },
        modifyTopic:function (url, content, fun) {
            var param = {
                t_id:Topic.replyTopicId,
                topicContent:content
            };
            $.post(url, param, fun);
        },
        init:function () {
            var _elements = this.elements;
            var _events = this.events;
            for (var ele in _elements) {
                this[ele] = _elements[ele];
                for (var ev in _events[ele]) {
                    _elements[ele].bind(ev, $.proxy(_events[ele][ev], this));
                }
            }
            ("onhashchange" in exports) && (exports.onhashchange = $.proxy(function () {
                var _hash = location.hash;
                if (_hash == "") {
                    _hash = 1;
                }
                this.switchPage(_hash);
            }, this));
        }
    };
    var Topic = (function () {
        var klass = function () {
        };
        var _r = {
            pageNo:1,
            pageCount:$("#topicPage #s_p li").size(),
            replyTopicId:$.trim($("#replyTopicId").val()),
            create:function (init) {
                var _topic = new klass;
                if (init instanceof Object) {
                    for (var i in init) {
                        _topic[i] = init[i];
                    }
                }
                return _topic;
            },
            refresh:function () {
                topicController.replyTopic("topic/get.json", {pageNo:this.pageNo, replyTopicId:this.replyTopicId});
            }
        };
        klass.prototype = {
            replyTopicId:_r.replyTopicId,
            content:"",
            pageNo:_r.pageNo
        };
        return _r;
    })();
    $(function () {
        topicController.init();
        topicController.switchPage("#first");
        var _hash = location.hash;
        if (_hash != "") {
            topicController.switchPage(_hash)
        }
        // Topic.refresh();
    });

})(jQuery, window);