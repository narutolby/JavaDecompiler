<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-26
  Time: 下午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp"%>

    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>

    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/yanue.pop.js"></script>
    <style type="text/css">
        *{margin:0;padding:0;}
        #pop{background:#FFFFFF;width:260px;height: 150px;border:1px solid #e0e0e0;font-size:12px;position:
            fixed;right:10px;bottom:10px;}
        #popHead{line-height:32px;background:#3299CC;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
        #popHead h2{font-size:14px;color:#FFF;line-height:32px;height:32px;}
        #popHead #popClose{position:absolute;right:10px;top:1px;}
        #popHead a#popClose:hover{color:#fff;cursor:pointer;}
        #popContent{padding:5px 10px;}
        #popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#fff;font-weight:bold;text-decoration:none;}
        #popTitle a:hover{color:#f60;}
        #popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
        #popMore{text-align:right;border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
        #popMore a{color:#f60;}
        #popMore a:hover{color:#f00;}
        table{
            border-collapse: collapse;
        }
        table td{
            padding:2px;
            word-break: break-all;
            word-wrap: break-word;
        }
    </style>

</head>

<body>

<div id="wrapper">
    <%@include file="../snippet/header.jsp"%>

    <div id="content">
        <div id="aside">
            <div id="aside-content" style="position:relative">
                <div id="folder_parent" class="folder folder_selected"><p id="root" class="folder-parent-icon">所选课程图书推荐：</p><h style="color: red;cursor: pointer">更多</h></div>
                <table  align="left" cellspacing="10" style="text-align: left;width: 200px;">
                    <c:forEach items="${recommendBooks}" var="entry" >
                        <c:forEach items="${entry.value[0]}" end="1" var="book" >
                            <tr>
                                <td><img src="${book.smallPic}" width="70px" height="94px"/></td>
                                <td>
                                    <table style="text-align: left">
                                        <tr><p style="color:rgb(0, 75, 145) " >${book.title}</p></tr>
                                        <tr><p>${book.authors}(作者)</p></tr>
                                        <tr style="bottom: 0"><a href="${book.alt}" target="_blank" style="cursor: pointer">去豆瓣看看>></a></tr>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
                <div align="left" style="color: rgb(0, 75, 145)"><h3 style="cursor: pointer">>看更多推荐图书</h3></div>
            </div>
        </div>
        <div id="content-right">
            <div id="my-class" class="my-config">
                <span class="config-title" style="float: left;margin-left: 10px;">选择我的课程:</span>
                <select id="academy"  size="10">
                </select>
                <select id="major"  size="10"></select>
                <c:if test="${isTeacher==1}">
                    <select id="term"  size="10"></select>
                </c:if>
                <select id="course" multiple="multiple" size="10"></select>
            </div>
            <div id="follow_majors">
                <span class="config-title">我关注的课程:</span>
                <div id="follow_majors_list">
                    <c:forEach items="${majorMap}" var="entry">
                        <fieldset id="${entry.key.id}">
                            <legend>${entry.key.name}</legend>
                            <div class="list">
                                <c:forEach items="${entry.value}" var="course">
                                    <span id="${course.id}">${course.name}<img src="img/01_condition.png"/></span>
                                </c:forEach>
                            </div>
                        </fieldset>

                    </c:forEach>
                </div>
            </div>
            <%--<table border=0 width="710" class="tb1">
            <TR>
                <TD width=340px align=center valign=top>
                    <div class="zTreeDemoBackground">
                        <ul id="majorTree" class="tree"></ul>
                    </div>
                </TD>
                <TD width=30px align="center" valign="middle" class="operate">
                    <button class="ico moveNode" onfocus="this.blur();" title="移动节点 右->左" onclick="moveTreeL2R();"></button>
                    <br/><br/><br/><br/><br/><br/><br/><br/>
                    <button class="ico moveNodeR" onfocus="this.blur();" title="移动节点 左->右" onclick="moveTreeR2L();"></button>
                    <br/><br/><br/><br/><br/><br/><br/><br/>
                    <button class="ico refresh" onfocus="this.blur();" title="恢复初始状态" onclick="reloadTree();"></button>
                    <br/><br/><br/><br/><br/><br/><br/><br/>
                </TD>
                <TD width=340px align=center valign=top>
                    <div class="zTreeDemoBackground">
                        <ul id="selectMajorTree" class="tree"></ul>
                    </div>
                </TD>
            </TR>
            </table>--%>
            <input type="submit" value="提交" class="input-button hover" id="submitBtn">
            <div id="main" align="left" style="cursor:pointer;display: none"><h1>返回继续选课</h1></div>
            <div id="article">
                <ul class="bigFolder" id="bidFolder"></ul>
                <ul class="myResrcList" id="resrc_list"></ul>
            </div>

            <div id="pagi" style="display: none">
                hello
            </div>
        </div>



    </div>

    <%@include file="../snippet/footer.jsp"%>

</div>
<script type="text/javascript">

    $L.script("js/myConfigure.js").wait().script("js/bookRecommend.js").wait();
//    $(document).ready(function() {
//        var index = 0, $detail = {}, position, top = 0, left = 140;
//        $("div#pagi .glide_content").hover(function () {
//            index = $(this).index();
//            position = $(this).position();
//            var tempLeft = position.left + 120 + left;    //+120
//            var tempTop = position.top + 0;      //+215
//            $detail = $("#detail");
//            $detail.css({"top":tempTop + "px", "left":tempLeft + "px"});
//            $detail.removeClass("detail_hidden").addClass("detail_show").fadeIn();
//        }, function () {
//            $detail.removeClass("detail_show").addClass("detail_hidden").fadeOut();
//        });
//    });
</script>



<!--信息弹出窗口-->
<c:if test="${invitedCommunities[0].communityId!=null}">
    <c:if test="${isTeacher==0}">
        <div id="pop" style="display:none; z-index: 3" >
            <div id="popHead">
                <a id="popClose" title="关闭">关闭</a>
                <h2>收到新消息</h2>
            </div>
            <div id="popContent">
                <dl>
                    <dt id="popTitle"></dt>
                    <dd id="popIntro"></dd>
                </dl>
            </div>
        </div>
    </c:if>
</c:if>

<%--社区激活列表弹出框--%>

<div id="pop_window" >
    <div id="commu_active">
        <c:choose>
            <c:when test="${isTeacher==1}">
                创</br>建</br>社</br>区
            </c:when>
            <c:otherwise>
                加</br>入</br>社</br>区
            </c:otherwise>
        </c:choose>
    </div>
    <div class="easyui-tabs" style="width:300px;height:200px">



        <c:if test="${isTeacher==1}"><div id="canJoinCommunity" title="可创建社区列表" style="padding: 10px"></c:if>
        <c:if test="${isTeacher==0}"><div id="canJoinCommunity" title="可加入社区列表" style="padding:10px"></c:if>
            <ul id="new_cmu_list">
                <c:forEach items="${unactiveCommunities}" var="_community">
                    <li id='${_community.id}'><span>${_community.name}</span><span style='float:right'><button style='cursor:pointer' id='active' class="active">
                        <c:choose>
                            <c:when test="${isTeacher==1}">
                                创建
                            </c:when>
                            <c:otherwise>
                                加入
                            </c:otherwise>
                        </c:choose>
                    </button></span></li>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${isTeacher==0}">
            <div title="被邀请社区列表" style="padding:10px">
                <ul id="new_cmu_list_invite">
                    <c:forEach items="${invitedCommunities}" var="invited">
                        <li id='${invited.user.id}'><span id='${invited.communityId}'>${invited.course.name}</span><span id='${invited.course.id}' style='float:right'>
                        <button style='cursor:pointer' id='active_invite' class="active">
                            加入
                        </button>
                    </span></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <%--<p>Community</p>--%>
    </div>
    </div>
    <%--引导层--%>
    <div id="guide">
        <div class="mask_layer"></div>
        <div class="pos_layer">
            <div id="guide_step1" class="mod_guide_wrap">
                <div class="mod_guide_inner">
                    <div class="step1_arrow"></div>
                    <div class="g_step"><img src="img/guideText_1.png"/> </div>
                    <div class="move_intoguide_btnBox">
                        <a href="javascript:void(0)" class="move_intoguide_try"></a>
                        <a href="javascript:void(0)" class="move_intoguide_known"></a>
                    </div>
                </div>
            </div>
            <div id="guide_step2" class="mod_guide_wrap">
                <div class="mod_guide_inner">
                    <div class="step2_arrow"></div>
                    <div class="g_step"><img src="img/guideText_2.png"/> </div>
                    <div class="move_intoguide_btnBox">
                        <a href="javascript:void(0)" class="move_intoguide_try"></a>
                        <a href="javascript:void(0)" class="move_intoguide_known"></a>
                    </div>
                </div>
            </div>
            <div id="guide_step3" class="mod_guide_wrap">
                <div class="mod_guide_inner">
                    <div class="step3_arrow"></div>
                    <div class="g_step"><img src="img/guideText_3.png"/> </div>
                    <div class="move_intoguide_btnBox">
                        <a href="javascript:void(0)" class="move_intoguide_try"></a>
                        <a href="javascript:void(0)" class="move_intoguide_known"></a>
                    </div>
                </div>
            </div>
            <div id="guide_step4" class="mod_guide_wrap">
                <div class="mod_guide_inner">
                    <div class="step4_arrow"></div>
                    <div class="g_step"><img src="img/guideText_4.png"/> </div>
                    <div class="move_intoguide_btnBox">
                        <a href="javascript:void(0)" class="move_intoguide_try"></a>
                        <a href="javascript:void(0)" class="move_intoguide_known"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="hotResrc" class="hotResrc_detail">
        <c:forEach items="${bookList}" var="entry">
            <c:forEach items="${entry.value}" end="5" var="book" >
                <div id="detail" class="detail_hidden">
                    <h2 id="title" class="detail_title">${book.title}</h2>

                    <p id="subtitle" class="sub_title">${book.authors} / ${book.pubdate} / ${book.publisher} / ${book.price}</p>

                    <p id="describe">${book.summary}</p>
                </div>
            </c:forEach>
        </c:forEach>
    </div>


</body>

</html>
