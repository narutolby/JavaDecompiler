<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 13-5-20
  Time: 上午9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        #reviewList
        {
            font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
            width:100%;
            border-collapse:collapse;
        }

        #reviewList td, #reviewList th
        {
            font-size:1em;
            border:1px solid #98bf21;
            padding:3px 7px 2px 7px;
        }

        #reviewList th
        {
            font-size:1.1em;
            text-align:center;
            padding-top:5px;
            padding-bottom:4px;
            background-color:#A7C942;
            color:#ffffff;
        }

        #reviewList tr.alt td
        {
            color:#000000;
            background-color:#EAF2D3;
        }
        #demo {
            margin: 50px auto;
            min-height: 600px;

        }
    </style>
</head>

<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="demo">
        <input type="hidden" id="topicPageCount" value="${topics.pageCount}"/>
        <input type="hidden" id="topicPageNo" value="${topics.pageNo}"/>


        <table width="100%" id="reviewList">
            <thead>
            <tr>
                <th>社区名称</th>
                <th>帖子名称</th>
                <th>发帖人</th>
                <th>内容</th>
                <th>原帖链接</th>
                <th>审查</th>


            </tr>
            </thead>
            <tbody>
            <c:forEach items="${topics.domainList}" var="topic">
                <tr class="folder folder_nodes" id="resourceTable">
                    <td id="${topic.id}">${topic.community.name}</td>
                    <td id="userId">${topic.topicTitle}</td>
                    <td>${topic.author.userName}</td>
                    <td>${fn:substring(topic.topicContent,0,13)}...</td>
                    <td><a href='topic/detail.html?t_id=${topic.id}' target="_blank"><img src='img/icon_subfolder.png'/></a></td>
                    <td><input type="submit" value="删帖" id="removeBtn" ></td></tr>
            </c:forEach>
            </tbody>
        </table>

        <div id="dialog-confirm" title="审核提示">
            <p id="msg" style="display: none;">
                <span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
                确定审核结果吗？</p>
        </div>


        <!--<div class="page" id="topicPage">
          <ul>
              <li><a href="#first">首页</a></li>
              <%-- <li><a href="#prev">上一页</a></li>--%>
              <li><ul class="main-page" id="s_p">
                  <c:forEach begin="1" end="${topics.pageCount}" var="i">
                      <li class="btn_page ${topics.pageNo==i?"current":""}"><a href="#${i}">${i}</a>
                      </li>
                  </c:forEach>
              </ul>
              </li>
              <%-- <li><a href="#next">下一页</a></li>--%>
              <li><a href="#last">尾页</a></li>
          </ul>
      </div>  -->

        <div id="switchPage" class="page">
            <input type="button" value="上一页" id="prev"  />
            <li id="pageNo">
                ${topics.pageNo}/${topics.pageCount}
            </li>
            <input type="button" value="下一页" id="next"  />
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp" %>
</div>

</body>


<script type="text/javascript">
    $L.wait().script("js/checkTopic.js");
</script>
</html>