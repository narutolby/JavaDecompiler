<%--
  Created by IntelliJ IDEA.
  User: naruto
  Date: 12-11-11
  Time: 下午1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page" id="topicPage" style="margin-bottom: 3px">
    <ul>
        <li><a href="#first">首页</a></li>
       <%-- <li><a href="#prev">上一页</a></li>--%>
       <li><ul class="main-page" id="s_p">
            <c:forEach begin="1" end="${subTopicPager.pageCount}" var="i">
                <li class="btn_page ${subTopicPager.pageNo==i?"current":""}"><a href="#${i}">${i}</a>
                </li>
            </c:forEach>
        </ul>
           </li>
       <%-- <li><a href="#next">下一页</a></li>--%>
        <li><a href="#last">尾页</a></li>
    </ul>
</div>