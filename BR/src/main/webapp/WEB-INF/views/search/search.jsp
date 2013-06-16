<%@ page import="java.util.*" %>
<%@ page import="org.apache.lucene.document.Document" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-26
  Time: 下午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    Set<Document> resouceSet = (Set<Document>) request.getAttribute("resources");
    Set<Document> communitySet = (Set<Document>) request.getAttribute("communities");
%>
<html>
<head>
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        ul{
            text-align: left;
            padding-left: 30px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
        <div class="preview-title"><h3 style="text-align: left;">资源结果</h3>
            <ul>
                <%
                    for (Document d : resouceSet) {
                %>
                <li>
                    <a href="resourceOperation/download.html?resourceId=<%=d.getField("id").stringValue()%>"><%=d.getField("fileName").stringValue()%>
                    </a>
                </li>
                <%}%>
            </ul>
        </div>
        <div class="preview-title"><h3 style="text-align: left">社区结果</h3>
            <ul>
                <%
                    for (Document d : communitySet) {
                %>
                <li>
                    <a href="community/community_detail.html?c_id=<%=d.getField("id").stringValue()%>"><%=d.getField("name").stringValue()%>
                    </a>
                </li>
                <%}%>
            </ul>
        </div>
        <div class="preview-title"><h3 style="text-align: left">图书馆结果</h3>
            <ul>
              <%--  <%
                    for (Document d : communitySet) {
                %>
                <li>
                    <a href="/community/community_detail.html?c_id=<%=d.getField("id").stringValue()%>"><%=d.getField("name").stringValue()%>
                    </a>
                </li>
                <%}%>--%>
            </ul>
        </div>
        <%@include file="../snippet/footer.jsp" %>
    </div>
</body>
</html>
