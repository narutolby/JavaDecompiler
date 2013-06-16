<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-12
  Time: 下午1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="pagi" id="pagi" style="display: none">
	<a href="javascript:void(0)" id="page_prev"><b><span>上一页</span></b></a>
	<c:forEach var="i" begin="1" end="${requestScope.pager.pageCount}">
		<a href="javascript:void(0)" id="page_${i}"> <c:choose>
				<c:when test="${i==1}">
					<span style="font-weight:bold;font-size:12px">${i}</span>
				</c:when>
				<c:otherwise>
					<b><span>${i}</span></b>
				</c:otherwise>
			</c:choose>
		</a>
	</c:forEach>
	<a href="javascript:void(0)" id="page_next"><b><span>下一页</span></b></a>
</div>