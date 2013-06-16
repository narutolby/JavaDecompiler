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
    <h1 class="about-us-name">关于我们</h1>
    <h2 class="about-us-title">知识资源共享系统</h2>
    <p class="about-us">知识资源共享系统是由北京邮电大学PCN&CAD中心与北京邮电大学图书馆联合创建，旨为在校大学生和老师提供电子资源、图书资源的数字共享平台。提供的功能包括:数字资源共享，图书资源推荐，社区交流互动，课程参考书推荐，资源搜索，后台管理等。</p>
    <h2 class="about-us-title">北京邮电大学PCN&CAD中心</h2>
    <p class="about-us">PCN&CAD中心的前身是成立于1985年的CAD中心，2000 年成为首批部级重点实验室。</p>
    <p class="about-us">PCN&CAD中心坚持发展优势研究、开拓新兴方向，拥有27年无线通信领域科研积累的同时，积极探索服务科学和服务工程这一交叉学科研究领域。作为首批现代服务业专项任务的承担者，团队主要科研成果“现代服务业共性服务平台及典型应用”鉴定为“国际先进水平”，并获得2009年中国服务业科技创新奖特等奖，为正在开展的云计算、移动互联网、服务工程等前沿技术、方法体系的研究奠定了基础。</p>
    <h2 class="about-us-title">北京邮电大学图书馆</h2>
    <p class="about-us">北京邮电大学具有“信息黄埔”的美誉，而其图书馆馆藏资源丰富，具有鲜明的邮电通信和电子学专业特色，尤其是世界上一些享有盛誉的电信专业书刊收藏较全，在全国堪称第一。</p>
    <p class="about-us">北京邮电大学图书馆在中国图书馆学会、教育部高等学校图书北京邮电大学图书馆、情报工作指导委员会、全国通信电子类高校图工委、北京高校图工委等全国或北京地区重要的图书馆学术机构与团体中都有一席之地。全国通信电子类高校图工委秘书处、北京高校数字图书馆研究会秘书处、北京高校图书馆联合体秘书处、北京市高校文献保障系统（BALIS）馆际互借管理中心均设在这里。</p>
</div>
<%@include file="../snippet/footer.jsp"%>
</div>
</body>
</html>