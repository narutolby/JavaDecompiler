<%--
  Created by IntelliJ IDEA.
  User: Zhaomeng
  Date: 13-6-11
  Time: 下午3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp"%>
    <link href="css/book_rec_sys.css" rel="stylesheet" type="text/css">
    <link href="css/tagBookResrcView.css" rel="stylesheet" type="text/css">
    <style>
        .about-us-name{
            font-family: "Microsoft YaHei";
        }
        .about-us{
            text-indent: 2em;
            line-height: 1.5;
            font-size: 14px;
            text-align: left;
        }
        .about-us-title{
            text-align: left;
            font-family: "Microsoft YaHei";
            background: #DDEDE8;
            padding: 8px;
            font-size: 15px;
        }
    </style>
</head>
<body>
<div id="wrapper">
<%@include file="../snippet/header.jsp"%>
<div id="content">
    <h1 class="about-us-name">联系我们</h1>
    <h2 class="about-us-title">邮箱：</h2>
    <p class="about-us">ehaihong@gmail.com</p>
    <h2 class="about-us-title">电话：</h2>
    <p class="about-us">86-10-62282747</p>
    <h2 class="about-us-title">传真：</h2>
    <p class="about-us">86-10-62282747</p>
    <h2 class="about-us-title">地址：</h2>
    <p class="about-us">北京市海淀区西土城路10号北京邮电大学主楼1110</p>
</div>
<%@include file="../snippet/footer.jsp"%>
</div>
</body>
</html>