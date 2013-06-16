<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-11
  Time: 下午2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <%--<%@include file="../snippet/meta.jsp" %>--%>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
        <div id="aside">
            <div id="resrc_mayLike">
               <%-- <%@include file="../snippet/menu.jsp" %>--%>
                <div id="title_mayLike"><p>你可能感兴趣的资源：</p></div>
                <ul>
                    <li>
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>

                            <p> 如果时光可以重来，
                                我会鼓起勇气问，我们在一起，好不好？
                                如果时光可以重来，
                                我会拿起电
                            </p>

                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                    <li>
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>

                            <p> 如果时光可以重来，
                                我会鼓起勇气问，我们在一起，好不好？
                                如果时光可以重来，
                                我会拿起电
                            </p>

                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                    <li>
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>

                            <p> 如果时光可以重来，
                                我会鼓起勇气问，我们在一起，好不好？
                                如果时光可以重来，
                                我会拿起电
                            </p>

                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="doc_type">
            <ul>
                    <li class="selected_type"> <a href="" style="display: block;">全部 </a></li>

                <a href="">
                    <li>PDF</li>
                </a>
                <a href="">
                    <li>Word</li>
                </a>
                <a href="">
                    <li>PPT</li>
                </a>
                <a href="">
                    <li>Txt</li>
                </a>
                <a href="">
                    <li>其他</li>
                </a>
            </ul>
            <div class="clr"></div>
        </div>
        <div id="article">

            <div class="clr"></div>
            <div class="doc_sort">
                <p class="flt_l">排序：</p>
                <input type="button" class="btn_kind" id="btn_pubDate" value="发布日期&nbsp;&nbsp;"/>
                <input type="button" class="btn_kind" id="btn_docSize" value="文件大小&nbsp;&nbsp;"/>
                <input type="button" class="btn_kind" id="btn_downloadTimes" value="下载次数&nbsp;&nbsp;"/>
            </div>
            <div class="clr"></div>
            <ul class="resrc_list" id="resrc_list" >
                <c:forEach items="${requestScope.model}" var="model">
                    <%@include file="../template/resourceSystemRecommendTmpl.jsp"%>
                </c:forEach>
            </ul>
            <%@include file="../snippet/pagination.jsp" %>
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp" %>

</div>
</body>
<script id="resrcSysRecTemplate" type="text/x-jquery-tmpl"></script>
<script type="text/javascript">
    $.importTemplate("tmpl/resourceSystemRecommend.tmpl","resrcSysRecTemplate");
</script>
<script type="text/javascript">
    $(function(){
            $("#pagi").delegate("a,span","click",function(event){
                var $target = $(event.target);
                var pageNum = $target.attr("id").split("_")[1];
                $.templateAndModel("elec_resrc/resrc_rec_tmpl.json","resrcSysRecTemplate","resrc_list") ;
            });
    });
</script>
</html>