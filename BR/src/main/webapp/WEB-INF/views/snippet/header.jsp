<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map" %>
<%

    String q = request.getParameter("q");
    if (q == null) {
        q = "搜索资源、社区、图书";
    } else {
        q = new String(q.getBytes("ISO-8859-1"), "UTF-8");
    }
%>
<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-12
  Time: 下午1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <div id="logo">
        <div id="logo_img"><a href="#"><img src="img/logo.PNG"/></a></div>
        <div id="logo_title">
            <a href="#"><p>知识资源共享系统</p></a>
        </div>
    </div>

    <div id="nav">
        <ul>
            <%-- <li><a href="">全部资源分类</a></li>
           <li><img src="img/nav_split.jpg"/></li>--%>
            <c:if test="${isTeacher!=2}">
                <li><a href="index1.html">首&nbsp;&nbsp;页</a></li>
                <li><img src="img/nav_split.jpg"/></li>
            </c:if>
            <c:if test="${isTeacher==2}">
                <li><a href="index1.html">发帖审查</a></li>
                <li><img src="img/nav_split.jpg"/></li>
            </c:if>
            <%-- <li><a href="elec_resrc/resrc_sys_rec.html">资源</a></li>
           <li><img src="img/nav_split.jpg"/></li>--%>
            <li><a href="user/my_configure.html">
                <c:if test="${isTeacher==1}">
                    我教的课
                </c:if>
                <c:if test="${isTeacher==0}">
                    我上的课
                </c:if>
                <c:if test="${isTeacher==2}">
                    待审资源
                </c:if>

            </a></li>
            <li id="mr"><img src="img/nav_split.jpg"/></li>
            <li><a href="elec_resrc/my_resrc.html">
                <c:choose>
                    <c:when test="${isTeacher==2}">
                        通过资源
                    </c:when>
                    <c:otherwise>
                        我的资源
                    </c:otherwise>
                </c:choose>
            </a></li>

            <c:choose>
            <c:when test="${isTeacher==2}">
            <li><img src="img/nav_split.jpg"/></li>
            <li><a href="elec_resrc/upload.html">未过资源</a></li>
            </c:when>
            <%-- <c:otherwise>
                 上传资源
            </c:otherwise>--%>
            </c:choose>

            <c:if test="${isTeacher!=2}">
                <li><img src="img/nav_split.jpg"/></li>
                <li><a href="community/my_community.html">我的社区</a></li>
            </c:if>
            <c:if test="${isTeacher==2}">
                <li><img src="img/nav_split.jpg"/></li>
                <li><a href="community/my_community.html">检索关键词</a></li>
            </c:if>
            <li><img src="img/nav_split.jpg"/></li>
            <li><a href="user/p_info.html">个人管理</a></li>
            <li><img src="img/nav_split.jpg"/></li>
            <li><a href="user/guide.html">使用向导</a></li>
            <%-- <li><img src="img/nav_split.jpg"/></li>
          <li><a href="javascript:void(0)" id="user_guide">使用向导</a></li>--%>
            <li id="login_reg">
                <ul id="login_info">
                    <c:choose>
                        <c:when test="${requestScope.user!=null}">
                            <li><a href="#">${requestScope.user.userName}</a></li>
                            <li>|</li>
                            <li><a href="user/logout.html?c_p=${requestScope.c_p}" id="logout">注销</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="javascript:void(0)" id="login">登录</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <div id="message"></div>
            </li>

        </ul>
        <div class="bg_position bg_positionBorder"></div>
    </div>
    <c:if test="${fn:containsIgnoreCase(pageContext.request.requestURL,'community') or fn:containsIgnoreCase(pageContext.request.requestURL,'topic')}">
        <%
            LinkedHashMap<String, String> pathMap = new LinkedHashMap<String, String>();
            pathMap.put("myCommunity.jsp", "社区动态*community/my_community.html");
            pathMap.put("communityDetail.jsp", (String) request.getAttribute("communityName") + "*community/community_detail.html?c_id=" + request.getAttribute("c_id"));
            pathMap.put("topicDetail.jsp", "发表话题*topic/detail.html?t_id=" + request.getAttribute("t_id"));
            String url = request.getRequestURL().toString();
        %>
        ${pageScope.url}
        <div id="breadchrumb">
            <%
                for (Map.Entry<String, String> entry : pathMap.entrySet()) {
                    String[] array = entry.getValue().split("\\*");
            %>
                <%--<c:forEach items="${pathMap}" var="entry">
                          <c:if test="${fn:containsIgnoreCase(url,entry.key)}"></c:if>
                </c:forEach>--%>
            <a href="<%=array[1]%>"><%=array[0]%>
            </a>
            <span class="breadchrumb-split">></span>
            <% if (url.contains(entry.getKey()))
                break;
            }%>
                <%-- <a href="">发表话题</a>--%>
        </div>
    </c:if>
    <div id="search">
        <img src="img/icon_search.png"/>

        <form method="get" action="search.html" style="float:left">
            <input x-webkit-speech type="text" id="srch_txt" value="<%=q%>" lang="zh-CN" name="q"/>
            <input type="submit" id="srch_btn" value="搜&nbsp;&nbsp;索"/>
        </form>
    </div>
</div>
<div class="clr"></div>

