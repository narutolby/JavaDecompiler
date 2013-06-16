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
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        #wrapper{
            position: relative;
        }
        .form {
            overflow: hidden;
            width: 100%;
            color: #666;
        }

        .form label {
            width: 60px;
            display: inline-block;
            text-align: left;
        }

        .form p {
            line-height: 30px;
            position: relative;
        }

        .form input, .form label, .form span {
            font-size: 14px;
            vertical-align: middle;
        }

        .form input {
            line-height: 20px;
            padding: 4px 5px;
            background-color: white;
            border: #DDD 1px solid;
            -webkit-box-shadow: 0 2px 0 whiteSmoke inset;
            -moz-box-shadow: 0 2px 0 #f5f5f5 inset;
            box-shadow: 0 2px 0 whiteSmoke inset;
            -webkit-transition: border linear .2s, box-shadow linear .2s;
            -moz-transition: border linear .2s, box-shadow linear .2s;
            transition: border linear .2s, box-shadow linear .2s;
            width: 220px;
        }

        .form input:focus {
            outline: 0;
            border: #87BE44 1px solid;
            -webkit-box-shadow: 0 2px 0 whiteSmoke inset, 0 0 4px 1px #E8F3DB;
            -moz-box-shadow: 0 2px 0 #f5f5f5 inset, 0 0 4px 1px #e8f3db;
            box-shadow: 0 2px 0 whiteSmoke inset, 0 0 4px 1px #E8F3DB;
        }

        .form .bg_btn {
            display: inline-block;
            text-align: center;
            width: 60px;
            _height: 18px;
            line-height: 18px;
            font-size: 14px;
            cursor: pointer;
            cursor: hand;
            font-weight: bold;
            font-size: 14px;
        }

        .form .success, .form .error {
            display: inline-block;
            padding-left: 27px;
            height: 30px;
            line-height: 30px;
            background: url(/img/s_e.png) no-repeat -6px -28px;
        }

        .msgPlace {
            position: absolute;
            margin-left: 5px;
        }

        .hide {
            display: none;
        }

        .form .error {
            color: #F30;
            background-position: -7px 2px;
        }

        .form .success {
            width: 27px;
        }
        #base-info .preview-title {
            margin: 10px auto;
            width:400px;
            border-bottom: 2px solid #7DB546;
            border-radius: 0px;
            overflow: hidden;
        }
        #base-info .preview-title h3 {
            display: inline-block;
            margin: 0;
            float:left;
            padding: 0 15px;
            width: 60px;
            text-align: center;
            color: white;
            background-color: #7DB546;
            font-size: 14px;
        }
        dt{
            font-size: 22px;
            text-align: left;
            padding-left: 94px;
            color: red;
        }
        dd{
            padding-bottom: 28px;
        }
        .biaozhu div{
            position: absolute;
            border: 2px solid red;
        }
        .biaozhu div span{
           position: relative;
           color: #000000;
           font-weight: bold;
        }

        .biaozhu b{
            font-size: 0px;
            border: 10px solid white;
            border-right: 10px solid red;
        }
        .biaozhu a{
            position: relative;
            font-size: 0px;
            border: 10px solid white;
            border-top: 10px solid red;
            top: -9px;
        }
        #g_1{
            top: 439px;
            left: 141px;
            width: 472px;
            height: 282px;
        }
        #g_2{
            top: 766px;
            left: 141px;
            width: 472px;
            height: 173px;
        }
        #g_3{
            top: 1186px;
            left: 137px;
            width: 148px;
            height: 309px;
        }

        #t_1{
            position: absolute;
            left: -166px;
            top: 58px;
        }
        #t_2{
            position: absolute;
            left: -238px;
            top: 41px;
        }
        #t_3{
            position: absolute;
            left: -106px;
            top: 41px;
        }
        #g_4{
            top: 1193px;
            left: 352px;
            width: 505px;
            height: 272px;
        }
        #t_4{
            position: absolute;
            left: 228px;
            top: 272px;
            height: 33px;
            line-height: 33px;
        }
        #g_5{
            top: 1859px;
            left: 303px;
            width: 530px;
            height: 272px;
        }
        #t_5{
            position: absolute;
            left: 228px;
            top: 272px;
            height: 33px;
            line-height: 33px;
        }
        #g_6{
            top: 3825px;
            left: 303px;
            width: 509px;
            height: 240px;
        }
        #t_6{
            position: absolute;
            left: 228px;
            top: 240px;
            height: 33px;
            line-height: 33px;
        }
       #m1{
           top: 334px;
           left: 144px;
           width: 76px;
           height: 26px;
       }
        #m2{
            top: 1075px;
            left: 222px;
            width: 76px;
            height: 26px;
        }
        #m3{
            top: 1745px;
            left: 300px;
            width: 76px;
            height: 26px;
        }
        #m4{
            top: 2440px;
            left: 381px;
            width: 76px;
            height: 26px;
        }
         #m5{
             top: 3035px;
             left: 399px;
             width: 76px;
             height: 26px;
         }
        #m6{
            top: 3738px;
            left: 541px;
            width: 76px;
            height: 26px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
      <dl>
          <dt>首页资源推荐</dt>
          <dd> <img src="img/guide/1.png" style="width: 740px"/></dd>
          <dt>课程设置及参考书推荐</dt>
          <dd><img src="img/guide/2.png" style="width: 740px"/></dd>
          <dt>我的资源</dt>
          <dd><img src="img/guide/3.png" style="width: 740px"/></dd>
          <dt>上传资源</dt>
          <dd><img src="img/guide/4.png" style="width: 740px"/></dd>
          <dt>我的社区</dt>
          <dd><img src="img/guide/5.png" style="width: 740px"/></dd>
          <dt>个人管理</dt>
          <dd><img src="img/guide/6.png" style="width: 740px"/></dd>
      </dl>
    </div>
    <%@include file="../snippet/footer.jsp" %>
    <div class="biaozhu">
        <div id="g_1"><span id="t_1"><span>图书馆借阅图书排行榜推荐</span><b></b></span></div>
        <div id="g_2"><span id="t_2"><span>根据用户在图书馆的借阅行为做图书推荐</span><b></b></span></div>
        <div id="g_3"><span id="t_3"><span>课程参考书推荐</span><b></b></span></div>
        <div id="g_4"><span id="t_4"><a></a><span>课程设置</span></span></div>
        <div id="g_5"><span id="t_5"><a></a><span>资源下载</span></span></div>
        <div id="g_6"><span id="t_6"><a></a><span>配置个人信息，邮箱可方便接受系统邮件通知</span></span></div>
        <div id="m1"></div>
        <div id="m2"></div>
        <div id="m3"></div>
        <div id="m4"></div>
        <div id="m5"></div>
        <div id="m6"></div>
    </div>
</div>
<script type="text/javascript">
    $L.script("js/personalInfo.js");
</script>
<%--社区激活列表弹出框--%>
</body>
</html>
