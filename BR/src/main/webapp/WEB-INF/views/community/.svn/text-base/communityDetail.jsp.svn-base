<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-10-17
  Time: 上午9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <link href="js/uploadify/uploadify.css" rel="stylesheet" type="text/css"/>
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
   <%-- <script type="text/javascript" src="js/jquery.dimensions.js"></script>
    <script type="text/javascript" src="js/jquery.accordion.js"></script>--%>
    <style type="text/css">
        .over{
            border: 1px dotted #569EDB !important;
        }
        .x{
           position: absolute;
           width: 10px;
           height: 10px;
           background-size: 10px 10px;
           background:url(img/x.png) no-repeat;
            cursor:pointer;
            cursor:hand;
        }
        #resc_list li {
            text-align: left;
            padding-left: 3px;
            color: black;
            line-height: 30px;
            height: 30px;
            position: relative;
        }

        #resc_list li a {
            float: right;
            height: 15px;
            line-height: 15px
        }

        #topic {
            font-size: 13px;
            border-collapse: collapse;
        }

        #topic thead {
            height: 20px;
            line-height: 20px;
            border-bottom: 1px solid #666
        }

        #topic th, #topic td {
            font-size: 13px;
            text-align: left;
            padding: 10px;
/*        border-bottom: 1px dotted #666*/
        }

        #topic td a {
            color: black;
        }

        #new_mem li {
            font-size: 13px;
            line-height: 16px;
        }

        #uploadBtn {
            position: absolute;
            bottom: 27px;
            left: 21px;
            width: 157px;
            height: 45px;
        }

        #Filedata_mirror {
            background: url("img/btn_upload.jpg") no-repeat;
            width: 157px;
            height: 45px;
            border: none;
            cursor: pointer;
            position: absolute;
            bottom: 1px;
        }

        #Filedata object {
            left: 0px;
        }
        .edit_state{
            border:1px dotted #DDE9F4;
        }
        .wysiwyg,#topicContentIFrame{
            width: 424px!important;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
        <div id="aside">
            <div id="aside-content" style="border:none;overflow: hidden;">
                <div style="height:320px;border:1px solid #CCC;position:relative;">
                    <div id="folder_parent" class="folder folder_selected"><p class="commu-parent-icon"
                                                                              style="font-size:14px">社区资源(<span id="count" style="color: red">${resourcesPager.totalCount}</span>)：</p>

                        <div style="float: right;padding-top: 5px" id="resc_page">
                            <a href="javascript:void(0)"><img src="img/re_l.png" style="opacity:0.5"
                                                              id="resc_prev"/></a>
                            <a href="javascript:void(0)"><img src="img/re_r.png" id="resc_next"/></a>
                        </div>
                    </div>
                    <div style="text-align: left;padding:8px 8px 0px;font-size: 13px;font-weight: bold;color:black">
                        已上传资源：
                    </div>
                    <input type="hidden" id="resourcePageCount" value="${resourcesPager.pageCount}"/>
                    <input type="hidden" id="resourcePageNo" value="${resourcesPager.pageNo}"/>

                    <div id="resource_body" style="width:${198*resourcesPager.pageCount}px;overflow: hidden;position: relative;left:0px">
                        <ul id="resc_list" style="width:198px;float: left">
                            <c:forEach items="${resourcesPager.domainList}" var="resource">
                                <li><span title="${resource.resourceRealName}">${fn:substring(resource.resourceRealName,0,13)}</span><a
                                        href="resourceOperation/download.html?resourceId=${resource.id}"
                                        class="btn_myResrc resrcDown">下载</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <c:if test="${user.role==1}">
                        <div id="uploadBtn">
                            <input type="file" id="Filedata" name="Filedata" style="display: none;"/>

                            <div id="Filedata_mirror"></div>
                        </div>
                        <div style="position: absolute;bottom:5px;left:48px"/><input type="checkbox" id="mail1" style="vertical-align: bottom">是否邮件提醒</div>
                    </c:if>
                </div>
                <div style="height: 15px"></div>
                <div style="height:260px;border:1px solid #CCC" >
                    <dl id="${c_id}" style="position: relative;top: -3px">
                        <dt><a href="#">
                                <div class="folder folder_selected" style="text-indent: 11px"><p class="commu-parent-icon" style="font-size:14px;"><b>社区成员：</b></p></div>
                        </a></dt>
                        <dd>
                            <div style="text-align: left;padding:8px;font-size: 13px;font-weight: bold;color:black">最近加入...</div>
                                <ul id="new_mem" style="height: 164px">
                                    <c:forEach items="${newMembers}" var="user" begin="0" end="9">
                                        <li>${user.userName}${user.role==1?"老师":"同学"}</li>

                                    </c:forEach>
                                </ul>
                        </dd>
                        <c:if test="${isTeacher==1}">
                        <dt><a href="#">
                            <div class="folder folder_selected" style="text-indent: 11px"><p class="commu-parent-icon" style="font-size:14px;"><b>邀请加入：</b></p></div>
                        </a></dt>
                        <dd style="height:195px;overflow-y:auto">
                            <ul id="invite" style="padding: 2px" >
                                <c:forEach items="${student}" var="student" begin="0" end="9">
                                    <li style="padding: 18px;position: relative;left:0px"><span id="${student.id}" style="float:left" >${student.userName}</span>
                                    <span class="btn_myResrc resrcDown" style="float:right" ><font color=blue>邀请</font></span></li>
                                </c:forEach>
                            </ul>
                        </dd>
                        </c:if>
                    </dl>
            </div>

        </div>
    </div>
    <div id="commu-article">
        <div id="articleL">
            <div id="articleL-content" style="border: none">
                <div id="commu-dynamics" class=" ">
                    <p class="hot-commu-icon"
                       style="text-align: left;font-size: 15px; ">发表话题
                        <c:if test="${isAdmin==1}">
                            <button id="edit_topic"
                                    style="float: right;cursor: pointer;border:#2492CF;color:#FFFFFF;background: #2492CF;padding:5px 10px;border-radius:6px;" state="0">
                                编辑
                            </button>
                        </c:if>
                        <button id="pub_topic"
                                style="float: right;cursor: pointer;border:#2492CF;color:#FFFFFF;background: #2492CF;padding:5px 10px;border-radius:6px;">
                            发表<c:if test="${isAdmin!=1}">话题</c:if>
                        </button>
                    </p>
                </div>
                <table width="100%" id="topic">
                    <thead>
                    <tr>
                        <th width="43%">话题</th>
                        <th width="15%">作者</th>
                        <th width="15%">回应</th>
                        <th width="27%">最后回应</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${subTopicPager.domainList}" var="topic">
                        <%@include file="/tmpl/topic.tmpl" %>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%@ include file="../snippet/topicPage.jsp" %>
        </div>
        <div id="articleR">
            <div id="articleR-content">
                <div id="hot-commu" class="folder folder_selected"><p class="hot-commu-icon">历史公告：</p></div>
                <ul class="hot-commu-list" id="bull_list" style="text-align: left;text-indent: 1em">
                    <c:forEach items="${bulletins}" var="bulletin">
                        <li>
                                ${bulletin.content}【${bulletin.pubDate}】
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${user.role==1}">
                    <div>
                        <textarea rows="4" style="border: 1px solid #B6C3DA" id="bull_content"> </textarea>
                        <button id="pub_bull"
                                style="float: right;cursor: pointer;border:#2492CF;color:#FFFFFF;background: #2492CF;padding:2px 5px;border-radius:6px;margin-right: 8px;">
                            发表公告
                        </button>
                        <input type="checkbox" id="mail" style="vertical-align: bottom;"/>是否邮件通知
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="clr"></div>
<%@include file="../snippet/footer.jsp" %>
</div>
<div id="pubTopicDialog" style="display: none;overflow: hidden;" title="发表话题">
    <div id="uploadWindow">
        <form id="topicForm">
            <input type="hidden" value="${param["c_id"]}" name="communityId" id="communityId"/>

            <div>
                <span>标题:&nbsp;</span>
                <input id="topicTitle" type="text" value="" style="border: 1px solid #B6C3DA;margin-left:1px"
                       name="topicTitle"/>
            </div>
            <div>
                <span>内容:&nbsp;</span>
                <textarea
                        style="width:358px;height:155px;vertical-align: middle;border: 1px solid #B6C3DA;margin-left:1px"
                        id="topicContent" name="topicContent"></textarea>
            </div>
        </form>
    </div>
</div>
<div id="img_pop" style="z-index: 1000000000000000000;">
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
<c:if test="${user.role==1}">
    <div id="uploadDialog" title="上传附件">
        <input type="hidden" id="courseId" value="${courseId}"/>
        <input type="hidden" id="description" value=""/>

        <div id="file-queue">
            <ul></ul>
        </div>
    </div>
</c:if>
<script type="text/javascript">
    $L.wait(function () {
        basePath = "<%=basePath%>";
        $(function(){
            $("dd:not(:first)").hide(); //隐藏 dd不是第一个的. E:first:相当于E:eq(0)
// $("dd:not(:last)").hide();  //试试$("dd:not(:last)").hide();
            $("dt a").click(function(){
                $("dd:visible").slideUp("slow");
                $(this).parent().next().slideDown("slow");
                return false;
            });
            $("#invite span:last-child").click(function(){
                var userId = $(this).prev().attr("id");
                var communityId = $("dl").attr("id");
                $.post("community/invite.json",{UserId:userId,CommunityId:communityId},function(){

                });
                $(this).attr("class", "invited");

            });
        });

        $.get("tmpl/topic.tmpl", function (data) {
            window.topicTmpl = data.replace(/(topic\.)|(<\%@.*?\%>)/g, "").replace("fn:substring(topicTitle,0,14)", "topicTitle.substring(0,14)");
        });
    })<c:if test="${user.role==1}">.script("js/uploadify/jquery.uploadify-3.1.min.js").wait().script("js/commonUpload.js").wait()</c:if>.script("js/community.js");

</script>
<script type="text/javascript">
    function showImg(url) {
        $("#df_img").attr("src", url);
    }

</script>
</body>
</html>
