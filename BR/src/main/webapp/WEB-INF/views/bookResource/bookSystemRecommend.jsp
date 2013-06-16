<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-12
  Time: 上午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp"%>
    <link href="css/book_rec_sys.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp"%>
    <div id="content">
        <div id="aside">
            <%@include file="../snippet/menu.jsp"%>
            <div id="resrc_mayLike">
                <div id="title_mayLike"><p>你可能感兴趣的图书：</p></div>
                <ul>
                    <li class="resrc_mayLike">
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>
                            <div class="star_ratingCur">
                                <div>书评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                            </div>
                            <p>(已有100人评价)</p>
                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                    <li class="resrc_mayLike">
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>
                            <div class="star_ratingCur">
                                <div>书评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                            </div>
                            <p>(已有100人评价)</p>
                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                    <li class="resrc_mayLike">
                        <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                        <div class="detail_mayLike">
                            <a href="#" class="pic_title">> Java编程</a>
                            <div class="star_ratingCur">
                                <div>书评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                            </div>
                            <p>(已有100人评价)</p>
                            <p class="align"><a class="arrow" href="#">了解更多...</a></p>
                        </div>
                        <div class="clr"></div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="doc_type">
            <ul>
                <a href="" ><li class="selected_type">新书推荐</li></a>
                <a href="" ><li>热门图书</li></a>
                <a href="" ><li>可借图书</li></a>
            </ul>
            <div class="clr"></div>
        </div>
        <div id="article">

            <div class="clr"></div>
            <div class="doc_sort">
                <p class="flt_l">排序：</p>
                <input type="button"  class="btn_kind"  id="btn_pubDate" value="出版时间&nbsp;&nbsp;"/>
                <input type="button"  class="btn_kind" id="btn_docSize" value="评价次数&nbsp;&nbsp;"/>
                <input type="button"  class="btn_kind" id="btn_downloadTimes" value="借阅次数&nbsp;&nbsp;"/>
            </div>
            <div class="clr"></div>
            <ul class="resrc_list">
                <li class="li_first">
                    <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                    <div class="detail_sysRec">
                        <p class="title_sysRec">Java 编程思想</p>
                        <div>
                            <p class="subtitle_sysRec">作&nbsp;&nbsp;者：(美)埃克尔 著，陈浩鹏 译</p>
                            <p> 出版社：机械工业出版社&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版时间：2007年6月</p>
                            <div class="star_ratingCur">
                                <div>书&nbsp;&nbsp;评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                                <div>(已有100人评价)</div>
                            </div>
                        </div>
                        <p class="lab_index">
                            图书馆索书号：TP312JA/A152-3
                        </p>
                    </div>
                </li>
                <li>
                    <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                    <div class="detail_sysRec">
                        <p class="title_sysRec">Java 编程思想</p>
                        <div>
                            <p class="subtitle_sysRec">作&nbsp;&nbsp;者：(美)埃克尔 著，陈浩鹏 译</p>
                            <p> 出版社：机械工业出版社&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版时间：2007年6月</p>
                            <div class="star_ratingCur">
                                <div>书&nbsp;&nbsp;评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                                <div>(已有100人评价)</div>
                            </div>
                        </div>
                        <p class="lab_index">
                            图书馆索书号：TP312JA/A152-3
                        </p>
                    </div>
                </li>
                <li>
                    <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                    <div class="detail_sysRec">
                        <p class="title_sysRec">Java 编程思想</p>
                        <div>
                            <p class="subtitle_sysRec">作&nbsp;&nbsp;者：(美)埃克尔 著，陈浩鹏 译</p>
                            <p> 出版社：机械工业出版社&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版时间：2007年6月</p>
                            <div class="star_ratingCur">
                                <div>书&nbsp;&nbsp;评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                                <div>(已有100人评价)</div>
                            </div>
                        </div>
                        <p class="lab_index">
                            图书馆索书号：TP312JA/A152-3
                        </p>
                    </div>
                </li>
                <li>
                    <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                    <div class="detail_sysRec">
                        <p class="title_sysRec">Java 编程思想</p>
                        <div>
                            <p class="subtitle_sysRec">作&nbsp;&nbsp;者：(美)埃克尔 著，陈浩鹏 译</p>
                            <p> 出版社：机械工业出版社&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版时间：2007年6月</p>
                            <div class="star_ratingCur">
                                <div>书&nbsp;&nbsp;评：</div>
                                <ul><li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                    <li><img src="img/rating_star.jpg"></li>
                                </ul>
                                <div>(已有100人评价)</div>
                            </div>
                        </div>
                        <p class="lab_index">
                            图书馆索书号：TP312JA/A152-3
                        </p>
                    </div>
                </li>
            </ul>
            <%@include file="../snippet/pagination.jsp"%>
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp"%>
</div>
</body>
</html>