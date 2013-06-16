<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-11
  Time: 下午2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <%@include file="../snippet/meta.jsp"%>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
     <%@include file="../snippet/header.jsp"%>
    <div id="content">
        <div id="aside">
            <%@include file="../snippet/menu.jsp"%>
            <div id="resrc_mayLike">
                 <div id="title_mayLike"><p>你可能感兴趣的资源：</p></div>
                 <ul>
                     <li>
                         <div class="pic_mayLike"><img src="img/book_1.jpg"/></div>
                         <div class="detail_mayLike">
                         <a href="#" class="pic_title">> Java编程</a>
                         <p>   如果时光可以重来，
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
                             <p>   如果时光可以重来，
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
                             <p>   如果时光可以重来，
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
                <a href="" ><li class="selected_type">全部</li></a>
                <a href="" > <li>PDF</li></a>
                <a href="" > <li>Word</li></a>
                <a href="" > <li>PPT</li></a>
                <a href="" > <li>Txt</li></a>
                <a href="" ><li>其他</li></a>
            </ul>
            <div class="clr"></div>
        </div>
        <div id="article">

            <div class="clr"></div>
            <div class="doc_sort">
                <p class="flt_l">排序：</p>
                <input type="button"  class="btn_kind"  id="btn_pubDate" value="发布日期&nbsp;&nbsp;"/>
                <input type="button"  class="btn_kind" id="btn_docSize" value="文件大小&nbsp;&nbsp;"/>
                <input type="button"  class="btn_kind" id="btn_downloadTimes" value="下载次数&nbsp;&nbsp;"/>
            </div>
            <div class="clr"></div>
             <ul class="resrc_list">
                 <li class="li_first">
                     <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                     <div class="detail_sysRec">
                         <p class="title_sysRec">Java 编程思想</p>
                         <div>
                              <p class="subtitle_sysRec">(美)埃克尔 著，陈浩鹏 译/2007/12/PDF</p>
                              <div class="star_ratingCur">
                                  <div>读者评价：</div>
                                  <ul><li><img src="img/rating_star.jpg"></li>
                                      <li><img src="img/rating_star.jpg"></li>
                                      <li><img src="img/rating_star.jpg"></li>
                                      <li><img src="img/rating_star.jpg"></li>
                                      <li><img src="img/rating_star.jpg"></li>
                                  </ul>
                                  <div>(已有100人评价)</div>
                              </div>
                         </div>
                         <div class="clr"></div>
                         <p class="cont_sysRec">
                             如果时光可以重来，
                             我会鼓起勇气问，我们在一起，好不好？
                             如果时光可以重来，
                             我会拿起电话说，对不起，是我太幼稚了。
                             时光可以重来吗？
                             不能。
                             一刀未剪！剧本数版原稿...
                         </p>
                         <div>
                             <input type="button" class="btn_del" value="&nbsp;&nbsp;删除"/>
                             <input type="button" class="btn_col" value="&nbsp;&nbsp;收藏"/>
                             <input type="button" class="btn_down" value="&nbsp;&nbsp;下载"/>
                         </div>
                     </div>
                 </li>
                 <li>
                     <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                     <div class="detail_sysRec">
                         <p class="title_sysRec">Java 编程思想</p>
                         <div>
                             <p class="subtitle_sysRec">(美)埃克尔 著，陈浩鹏 译/2007/12/PDF</p>
                             <div class="star_ratingCur">
                                 <div>读者评价：</div>
                                 <ul><li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                 </ul>
                                 <div>(已有100人评价)</div>
                             </div>
                         </div>
                         <div class="clr"></div>
                         <p class="cont_sysRec">
                             如果时光可以重来，
                             我会鼓起勇气问，我们在一起，好不好？
                             如果时光可以重来，
                             我会拿起电话说，对不起，是我太幼稚了。
                             时光可以重来吗？
                             不能。
                             一刀未剪！剧本数版原稿...
                         </p>
                         <div>
                             <input type="button" class="btn_del" value="&nbsp;&nbsp;删除"/>
                             <input type="button" class="btn_col" value="&nbsp;&nbsp;收藏"/>
                             <input type="button" class="btn_down" value="&nbsp;&nbsp;下载"/>
                         </div>
                     </div>
                 </li>
                 <li>
                     <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                     <div class="detail_sysRec">
                         <p class="title_sysRec">Java 编程思想</p>
                         <div>
                             <p class="subtitle_sysRec">(美)埃克尔 著，陈浩鹏 译/2007/12/PDF</p>
                             <div class="star_ratingCur">
                                 <div>读者评价：</div>
                                 <ul><li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                 </ul>
                                 <div>(已有100人评价)</div>
                             </div>
                         </div>
                         <div class="clr"></div>
                         <p class="cont_sysRec">
                             如果时光可以重来，
                             我会鼓起勇气问，我们在一起，好不好？
                             如果时光可以重来，
                             我会拿起电话说，对不起，是我太幼稚了。
                             时光可以重来吗？
                             不能。
                             一刀未剪！剧本数版原稿...
                         </p>
                         <div>
                             <input type="button" class="btn_del" value="&nbsp;&nbsp;删除"/>
                             <input type="button" class="btn_col" value="&nbsp;&nbsp;收藏"/>
                             <input type="button" class="btn_down" value="&nbsp;&nbsp;下载"/>
                         </div>
                     </div>
                 </li>
                 <li>
                     <div class="pic_sysRec"><img src="img/book_1.jpg"/></div>
                     <div class="detail_sysRec">
                         <p class="title_sysRec">Java 编程思想</p>
                         <div>
                             <p class="subtitle_sysRec">(美)埃克尔 著，陈浩鹏 译/2007/12/PDF</p>
                             <div class="star_ratingCur">
                                 <div>读者评价：</div>
                                 <ul><li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                     <li><img src="img/rating_star.jpg"></li>
                                 </ul>
                                 <div>(已有100人评价)</div>
                             </div>
                         </div>
                         <div class="clr"></div>
                         <p class="cont_sysRec">
                             如果时光可以重来，
                             我会鼓起勇气问，我们在一起，好不好？
                             如果时光可以重来，
                             我会拿起电话说，对不起，是我太幼稚了。
                             时光可以重来吗？
                             一刀未剪！剧本数版原稿...
                         </p>
                         <div>
                             <input type="button" class="btn_del" value="&nbsp;&nbsp;删除"/>
                             <input type="button" class="btn_col" value="&nbsp;&nbsp;收藏"/>
                             <input type="button" class="btn_down" value="&nbsp;&nbsp;下载"/>
                         </div>
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