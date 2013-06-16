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
        <input type="hidden" id="resourcePageCount" value="${pager.pageCount}"/>
        <input type="hidden" id="resourcePageNo" value="${pager.pageNo}"/>


        <table width="100%" id="reviewList">
            <thead>
            <tr>
                <th>资源名称</th>
                <th>上传人学号</th>
                <th>上传人姓名</th>
                <th>上传时间</th>
                <th>下载资源</th>
                <c:choose>
                    <c:when test="${htmlTag == 1}">
                        <th>审核资源</th>
                    </c:when>
                    <c:otherwise>
                        <th>审核状态</th>
                    </c:otherwise>
                </c:choose>


            </tr>
            </thead>
            <tbody id="${htmlTag}">
            <c:forEach items="${pager.domainList}" var="resource">
                <c:if test="${htmlTag == 1}">
                    <%@include file="/tmpl/review.tmpl" %>
                </c:if>
                <c:if test="${htmlTag == 2}">
                    <%@include file="/tmpl/pass.tmpl" %>
                </c:if>
                <c:if test="${htmlTag == 3}">
                    <%@include file="/tmpl/refused.tmpl" %>
                </c:if>

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
                  <c:forEach begin="1" end="${pager.pageCount}" var="i">
                      <li class="btn_page ${pager.pageNo==i?"current":""}"><a href="#${i}">${i}</a>
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
                ${pager.pageNo}/${pager.pageCount}
            </li>
            <input type="button" value="下一页" id="next"  />
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp" %>
</div>

</body>


<script type="text/javascript">
    $L.wait().script("js/review.js");
</script>
</html>