<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="snippet/meta.jsp" %>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        dl {
            margin: 0px;
        }

        dt {
            text-align: left;
        }

        #tagsList {
            position: relative;
            height: 230px;
            border: none;
            left: -61px;
            width: 317px;
            margin: 16px 0px;
            visibility: hidden;
        }

        #tagsList a {
            position: absolute;
            top: 0px;
            left: 0px;
            font-family: Microsoft YaHei;
            font-size: 10px;
            font-weight: normal;
            text-decoration: none;
            padding: 3px 6px;
            color: #07BFE3;
        }

        #tagsList a:hover {
            color: #FF0000;
            letter-spacing: 2px;
        }
    </style>
</head>
<body>
<div></div>
<div id="wrapper">
<%@include file="snippet/header.jsp" %>
<div id="content">
<div class="article">
    <div class="glide_wrapper">
        <a href="#" class="glide_title_img">图书借阅排行榜</a>
        <a href="javascript:void(0)" class="btn_next"></a> <a href="javascript:void(0)" class="btn_prev"></a>

        <div id="glide_1" class="glide_content">
            <div class="glide_content_list" pageSize="10" page="1">
                <ul>
                    <c:forEach var="hotBook" items="${hotBooks}" varStatus="i">
                        <li <c:if test="${i.index>4}">class="dashed"</c:if>">
                        <a href="javascript:void(0)"><img src="${hotBook.imgUrl}" width="91px" height="121px"/></a>
                        <dl>
                            <dt><a href="http://211.68.68.197/opac_two/${hotBook.labHref}" target="_blank">>>图书馆详情</a>
                            </dt>
                            <dt><a href="${hotBook.doubanHref}" target="_blank">>>豆瓣详情</a></dt>
                        </dl>
                        </li>
                    </c:forEach>
                    <div class="clr"></div>
                </ul>
            </div>
        </div>
    </div>
    <div class="glide_wrapper">
        <a href="#" class="glide_title_img">你可能想看的书</a>
        <a href="javascript:void(0)" class="btn_next"></a> <a href="javascript:void(0)" class="btn_prev"></a>

        <div id="glide_2" class="glide_content">
            <div class="glide_content_list" pageSize="6" page="1">
                <ul>
                    <c:forEach items="${hotWordBooks}" var="hotWordBook" varStatus="i">
                        <li <c:if test="${i.index>1}">class="dashed"</c:if>>
                            <div class="info">
                                <a href="${hotWordBook.doubanHref}" target="_blank"><img src="${hotWordBook.imgUrl}"
                                                                                         width="92px" height="126px"
                                                                                         title="${hotWordBook.chineseName}"
                                                                                         alt="${hotWordBook.chineseName}"/></a>

                                <p class="info_1">
                                    书名：${hotWordBook.chineseName}<br/>
                                    作者：${hotWordBook.author}<br/>
                                    类别：编程<br/>
                                    评分：${hotWordBook.rating}<br/>
                                    <a href="${hotWordBook.doubanHref}" target="_blank"><b style="color: red">>></b>豆瓣详情</a>
                                        <%-- <ul class="star_rating">
                                            <li><img src=""/></li>
                                        </ul>--%>
                                </p>

                                <p class="info_2">
                                    <span class="arrow">></span>${fn:substring(hotWordBook.description,0,55)}...
                                </p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="clr"></div>
        </div>
    </div>
    <%-- <div class="glide_wrapper">
        <a href="#" class="glide_title_img">新书速递</a>
        <a href="javascript:void(0)" class="btn_next"></a> <a href="javascript:void(0)" class="btn_prev"></a>

        <div id="glide_3" class="glide_content">
            <div class="glide_content_list">
                <ul>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_1.jpg"/></a>
                    </li>
                    <li class="dashed">
                        <a href="#"><img src="img/book_2.jpg"/></a>
                    </li>
                </ul>
            </div>
            <div class="clr"></div>
        </div>
    </div>--%>
</div>
<div class="aside">
    <div class="tabs_wrapper">
        <a href="#" class="block_title_img">标签云</a>

        <div id="tagsList" class="block_content">
            <c:forEach items="${tags}" var="tag">
                <a href="javascript:void(0)">${tag}</a>
            </c:forEach>
        </div>
    </div>
    <div class="tabs_wrapper">
        <a href="#" class="block_title_img">资源推荐</a>

        <div id="block_2" class="block_content">
            <c:forEach items="${resources}" var="model">
                <div>
                    <a href="#">
                        <img src="img/fileIcon/${model.resourceType}.png"
                             style="width: 85px;height:82px"/>

                        <p>
                            <span class="arrow">><a href="resourceOperation/download.html?resourceId=${model.id}"
                                                    title="${model.resourceRealName}">${fn:substring(model.resourceRealName,0,14)}..</a></span><br>
                            下载次数：${model.downloadTimes}<br>
                            上传者：<span>${model.uploadUserName}</span>
                        </p>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="tabs_wrapper">
        <a href="#" class="block_title_img">分享之星</a>

        <div id="block_3" class="block_content">
            <c:forEach var="i" items="${shareUser}">
                <div>
                    <a href="#">
                        <img src="img/share_star.jpg"/>

                        <p>
                            <span class="arrow">>${i.key}</span><br>
                            分享量：${i.value}<br>
                            分享是一种乐趣！
                        </p>
                    </a>
                </div>
            </c:forEach>
            <%-- <div>
                <a href="#">
                    <img src="img/share_star.jpg"/>

                    <p>
                        <span class="arrow">>小丫头</span><br>
                        分享量：532<br>
                        分享是一种乐趣！
                    </p>
                </a>
            </div>
            <div>
                <a href="#">
                    <img src="img/share_star.jpg"/>

                    <p>
                        <span class="arrow">>小丫头</span><br>
                        分享量：532<br>
                        分享是一种乐趣！
                    </p>
                </a>
            </div>--%>
        </div>
    </div>
</div>
<div class="clr"></div>
</div>
<%@include file="snippet/footer.jsp" %>
</div>
<div class="hotResrc_detail">
    <c:forEach items="${hotBooks}" var="hotBook">
        <div class="detail_hidden">
            <h2 class="detail_title">${hotBook.chineseName}</h2>

            <p class="sub_title">${hotBook.author} / ${hotBook.pubDate} / ${hotBook.publisher}/ ${hotBook.price}
                / ${hotBook.binding} </p>

            <p> ${hotBook.description}</p>
        </div>
    </c:forEach>
</div>
<form action="http://211.68.68.197/opac_two/search2/searchout.jsp" method="get" target="_blank" style="display: none"
      id="search_form" name="suchenggG">
    <input type="text" name="suchen_type" value="1"/>
    <input type="text" name="suchen_word" id="suchen_word"/>
    <input type="text" name="suchen_match" value="qx"/>
    <input type="text" name="recordtype" value="all"/>
    <input type="text" name="library_id" value="all"/>
    <input type="text" name="suchen_way" value="Ya"/>
    <input type="text" name="show_type" value="wenzi"/>
    <input type="text" name="client_id" value="opac_search"/>
   <%-- http://211.68.68.197/opac_two/search2/searchout.jsp?suchen_match=qx&recordtype=all&suchen_type=1&suchen_way=Ya&library_id=all&show_type=wenzi&client_id=opac_search&suchen_word=java--%>
</form>
<iframe src="http://211.68.68.197/opac_two/search2/search.jsp" style="display: none"></iframe>
<script type="text/javascript">
    /*$L.wait(function () {
     $.get("http://10.108.210.121:8080/BS/reader/" + userId + "/books/json?limit=4", function (data) {
     $("#block_2").find("div").each(function (i, v) {
     var _p = $(this).find("p");
     var item = data[i];
     //var _p = $(this).find("p");
     _p.find("span").each(function (i, v) {
     if (i == 0) {
     $(this).text(item.title);
     } else if (i == 1) {
     $(this).text(item.authors);
     }
     //console.log(i) ;
     });
     console.log($(this).find("img").attr("src"));
     });
     });

     }).script("js/tagCloud.js");*/
    $L.script("js/tagCloud.js").wait(function () {
        var _sf = $("#search_form");
        $("#tagsList").delegate("a", "click", function (event) {
            var tag = $(this).text();
            _sf.find("#suchen_word").val(tag);
            _sf.submit();
        });
    });
</script>
</body>
</html>
