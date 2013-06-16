<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../../snippet/meta.jsp" %>
    <link href="css/zr_content.css" rel="stylesheet" type="text/css">
    <link href="js/jwysiwyg/jquery.wysiwyg.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        div.wysiwyg ul.panel li a.uploadImg {
            background-position: -80px -80px;
        }

        #img_pop {
            width: 200px;
            height: 170px;
            position: absolute;
            display: none;
            border: 1px solid grey;
            background-color: #ffffff;
        }

        #img_pop input#local_img {
            border: 1px solid #999;
            width: 100px;
            height: 100px;
        }
        #arrow {

            position: absolute;
            width: 20px;
            height: 10px;
            left: 87px;
            top: -10px;
            overflow: hidden;
        }

        #arrow div {
            position: absolute;
            font-size: 0px;
            top: -10px;
            border: 10px solid #ffffff;
            border-bottom: 10px solid grey;
        }

        input:focus {
            border-color: #538DC2;
            box-shadow: 0 0 4px rgba(83, 141, 194, 0.5);
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="../../snippet/header.jsp" %>
    <div id="content">
        <div class="content_left">
            <div class="content_title">
                <h4>${topic.topicTitle}</h4>
                <c:if test="${isTopicAuthor}">
                <span style="float:right;height: 16px;position: relative;top:17px;right: 13px">
                <b style="float:left;background:url(/img/ico.gif);background-repeat: no-repeat;background-position: -213px -2px;width:16px;height:18px;">
                </b>
                <a href="javascript:void(0)"
                   style="padding-left: 2px;float: right;height: 16px;padding-top: 3px;font-size: 13px" id="topicEdit">编辑</a>
               </span>
                </c:if>
                <p>${topic.pubDate} &nbsp; 来自<b>${topic.authorName}</b>
                    ${topic.role==1?"老师":"同学"}
                </p>
            </div>
            <div class="main_content" id="main_topic" style="height:auto"><p>${topic.topicContent}</p></div>
            <div class="goback">&gt;&gt;<a href="community/my_community.html">&nbsp;返回社区</a></div>

            <div class="content_middle">
                <div class="remark"><img src="img/zr_border.jpg" style="float:left"/>
                    <%@ include file="../../snippet/topicPage.jsp" %>
                </div>
                <ul id="replyTopicBody">
                   <%-- <c:forEach items="${subTopicPager.domainList}" var="subTopic">
                        <%@ include file="/tmpl/subTopic.tmpl" %>
                    </c:forEach>--%>
                </ul>
            </div>
            <%@ include file="../../snippet/topicPage.jsp" %>
            <textarea class="reply_input" id="reply_input"></textarea>
            <input type="hidden" value="${param.t_id}" id="replyTopicId"/>

            <div>
                <button class="btn_reply" id="btn_reply"></button>
            </div>
        </div>

        <div class="sidebar">
            <h3></h3>
            <ul>
                <c:forEach items="${newTopics}" var="topic">
                    <li>
                        <a href="topic/detail.html?t_id=${topic.id}">${topic.authorName}${topic.role==1?"老师":"同学"}：${topic.topicTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../../snippet/footer.jsp" %>
</div>
<div id="img_pop">
    <iframe id="_frame" style="display: none"></iframe>
    <div id="arrow">
        <div></div>
    </div>
    <table>
        <tr>
            <td width="50%">本地上传</td>
            <td width="50%"><img src="http://cdn-img.easyicon.net/png/5347/534765.png"
                                 style="cursor: pointer;cursor: hand;width: 100px;height: 100px" id="df_img"/></td>
        </tr>
        <tr>
            <td>网络上传</td>
            <td><input type="text" id="net_img" style="width:133px;border: 1px solid grey" value="http://"/></td>
        </tr>
    </table>
    <div style="text-align: center">
        <button id="ok">确定</button>
    </div>
    <form method="post" enctype="multipart/form-data" style="display: none" action="resourceOperation/upload_img.html"
          id="img_form" target="_frame">
        <input type="file" name="img" accept="image/gif, image/jpeg,image/png"/>
    </form>
</div>
<script type="text/x-jquery-tmpl" id="subTopicTmpl">
    <li>
        <div class="info"><p class="info_left">{{= pubDate}}&nbsp; <span class="m_t">{{= authorName}} {{= role==1?"老师":"学生"}} 发言：</span>
        </p>

            <p class="info_right">
                <button class="active" p_id="{{= id}}">回复</button>
                &nbsp; |&nbsp; 举报
            </p>
        </div>
        <p class="reply">{{html content}}</p></li>
</script>
<script type="text/javascript">
    function showImg(url) {
        $("#df_img").attr("src", url);
    }
    $L.wait().script("js/topic.js").script("js/jwysiwyg/jquery.wysiwyg.js").wait(function () {
        $('#reply_input').wysiwyg({
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
            var _content_body = $("#reply_inputIFrame").get(0).contentWindow.document.body;
            $(_content_body).trigger("focus");
            var _reply = $('#reply_input');
            _reply.wysiwyg("focus");
            if($("#df_img").attr("src")!="http://cdn-img.easyicon.net/png/5347/534765.png"){
                _reply.wysiwyg('insertImage', "<%=basePath%>"+$("#df_img").attr("src"));
            }
            if($.trim($("#net_img").val())!="http://"){
                _reply.wysiwyg('insertImage', $.trim($("#net_img").val()));
            }
            $("#net_img").val("http://");
            _reply.val(_content_body.innerHTML);
            showImg("http://cdn-img.easyicon.net/png/5347/534765.png");
        });
    });
</script>
</body>
</html>