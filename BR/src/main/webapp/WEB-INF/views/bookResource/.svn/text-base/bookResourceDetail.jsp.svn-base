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
    <link href="css/tagBookResrcView.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src=""></script>
    <script>
        $(document).ready(function(){
            var score1=8;
            var score2=6;
            var score3=8;
            var score4=2;
            var score5=16;
            var tscore=40;
            if(score1==0){var per1=0} else{var per1= Math.round(score1/tscore*100);}
            if(score2==0){var per2=0} else{var per2= Math.round(score2/tscore*100);}
            if(score3==0){var per3=0} else{var per3= Math.round(score3/tscore*100);}
            if(score4==0){var per4=0} else{var per4= Math.round(score4/tscore*100);}
            if(score5==0){var per5=0} else{var per5= Math.round(score5/tscore*100);}
            $("#peR1").width(per1*3.01);
            $("#peR2").width(per2*3.01);
            $("#peR3").width(per3*3.01);
            $("#peR4").width(per4*3.01);
            $("#peR5").width(per5*3.01);
            $("#per_1").text(per1+"%");
            $("#per_2").text(per2+"%");
            $("#per_3").text(per3+"%");
            $("#per_4").text(per4+"%");
            $("#per_5").text(per5+"%");
        })

    </script>
<%--    <script>
        $(document).ready(function(){

            $("input.discuss-score").each(function(){
                var score=$(this).val();
                if(score==5){var width=145;}
                else {var width=score*30;}
                $(this).parent().find("li.current-rating").width(width);
            });

        });
    </script>--%>
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
                <a href="" ><li>电子资源</li></a>
                <a href="" ><li class="selected_type">图书资源</li></a>
            </ul>
            <div class="clr"></div>
        </div>
        <div id="article">
            <div id="bResrc">
                <div id="bPic"><img src="img/book_1.jpg"/></div>
                <div id="bMess">
                    <p id="bTitle">Java 编程思想（第四版）</p>
                    <p id="author">作&nbsp;&nbsp;者：(美)埃克尔 著，陈浩鹏 译</p>
                    <p id="puber">出版社：机械工业出版社</p>
                    <p id="pubtime">出版时间：2007年6月</p>
                    <p id="isbn">ISBN:357923867564</p>
                    <p id="kind">所属分类：计算机技术</p>
                    <p id="version">版次：4</p>
                    <p id="pg">页数：880</p>
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
                    <input type="button" class="btn_col" value="&nbsp;&nbsp;收藏"/>
                </div>
            </div>
            <div id="bResrcSimi">
                <div class="bkTitle"><div>同类推荐</div></div>
                <ul>
                     <li>
                         <img src="img/book_2.jpg"/>
                         <p> Java核心技术（卷1）：基础知识</p>
                     </li>
                     <li>
                         <img src="img/book_2.jpg"/>
                         <p> Java核心技术（卷1）：基础知识</p>
                     </li>
                     <li>
                         <img src="img/book_2.jpg"/>
                         <p> Java核心技术（卷1）：基础知识</p>
                     </li>
                     <li>
                         <img src="img/book_2.jpg"/>
                         <p> Java核心技术（卷1）：基础知识</p>
                     </li>
                     <li>
                         <img src="img/book_2.jpg"/>
                         <p> Java核心技术（卷1）：基础知识</p>
                     </li>
                 </ul>
            </div>
            <div class="bResrcC">
                <div class="bkTitle"><div>内容简介</div></div>
                <p>如果时光可以重来，
                    我会鼓起勇气问，我们在一起，好不好？
                    如果时光可以重来，
                    我会拿起电话说，对不起，是我太幼稚了。
                    时光可以重来吗？
                    不能。
                    所以有了这一个无法重来的故事。
                    ——九把刀
                    感激！电影《那些年，我们一起追的女孩》
                    勇夺香港电影金像奖，最佳两岸华语电影
                    九把刀：谢谢这个世界，包容了我的幼稚
                    《那些年，我们一起追的女孩》封笔之作
                    一刀未剪！剧本数版原稿...</p>
            </div>
            <div class="bResrcC">
                <div class="bkTitle"><div>目&nbsp;&nbsp;录</div></div>
                 <p>《快速编码：高效使用microsoft visual studio》 <br>
                     译者序    <br>
                     序        <br>
                     引言      <br>
                     第一部分　提升工作效率的各项技能   <br>
                     第1章　现在开始	2                <br>
                     1.1　同时运行不同版本的visual studio	2    <br>
                     1.2　获取visual studio 2010联机帮助中的内容列表	2    <br>
                     1.3　导出环境设置	5       <br>
                     1.4　移除最近使用的项目	7        <br>
                     1.5　自动恢复	8              <br>
                     1.6　在visual studio 2010中修改视觉体验，以提高性能	10  <br>
                     1.7　更改“工具”窗口动画	11                <br>
                     1.8　导入或更改环境设置	12 </p>
            </div>
            <div class="bResrcC">
                 <div class="bkTitle"><div>评&nbsp;&nbsp;论</div></div>
                 <div>
                     <p id="rating">已有25人打分和评论:&nbsp;&nbsp;</p>
                     <div>
                         <table id="rating_tbl">
                             <tr><td width="90px">5分5人</td> <td><div class="percent"><div id="peR5"></div></div></td> <td id="per_5"></td> <td><img src="img/star_5.jpg"/></td></tr>
                             <tr><td>4分5人</td> <td><div class="percent"><div id="peR4"></div></div></td> <td id="per_4"></td> <td><img src="img/star_4.jpg"/></td></tr>
                             <tr><td>3分5人</td> <td><div class="percent"><div id="peR3"></div></div></td> <td id="per_3"></td> <td><img src="img/star_3.jpg"/></td></tr>
                             <tr><td>2分5人</td> <td><div class="percent"><div id="peR2"></div></div></td> <td id="per_2"></td> <td><img src="img/star_2.jpg"/></td></tr>
                             <tr><td>1分5人</td> <td><div class="percent"><div id="peR1"></div></div></td> <td id="per_1"></td> <td><img src="img/star_1.jpg"/></td>
                             </tr>
                         </table>
                     </div>
                 </div>
                 <ul class="discuss-list">
                     <li>
                         <div class="disuss-pic">
                              <div style="text-align:center;padding:20px 8px 0 8px;word-break:break-all;"><a style="color:#242644;" href=javascript:openwindowtocenter('rsmRcmbookAction.do?method=detail&bookId=${rsmRcmbookdiscuss.bookId}','教育培训系统',900,900) >${rsmRcmbookdiscuss.bookName}</a></div>
                         </div>
                         <div class="disuss-body">
                             <div class="disuss-head">
                                 <span class="discuss-rating">评分：</span>
                                     <input class="discuss-score" type="hidden" value="5" />
                                     <ul class="star-rating">
                                         <li class="current-rating"></li>
                                         <li><a href="#" name="score" title="1 star out of 5" class="one-star">1</a></li>
                                         <li><a href="#" name="score" title="2 stars out of 5" class="two-stars">2</a></li>
                                         <li><a href="#" name="score" title="3 stars out of 5" class="three-stars">3</a></li>
                                         <li><a href="#" name="score" title="4 stars out of 5" class="four-stars">4</a></li>
                                         <li><a href="#" name="score" title="5 stars out of 5" class="five-stars">5</a></li>
                                     </ul>
                                 <div class="disuss-time"> <span>评论时间：2012-8-12</span></div>
                             </div>
                             <div class="disuss-content">
                                 最早知道科耶夫这个名字是在刘小枫先生的一篇文章《 施特劳斯抑或科耶夫》之中 (后来又读到一篇写灵知主义
                                 的文章里也提到了他)，科耶夫是一位极具传奇色彩的哲学天才：法国两代哲人（存在主义和后结构主义， 包括雷
                                 神之父......
                             </div>
                             <div class="discuss-arrow"></div>
                         </div>
                     </li>
                     <li>
                         <div class="disuss-pic">
                             <div style="text-align:center;padding:20px 8px 0 8px;word-break:break-all;"><a style="color:#242644;" href=javascript:openwindowtocenter('rsmRcmbookAction.do?method=detail&bookId=${rsmRcmbookdiscuss.bookId}','教育培训系统',900,900) >${rsmRcmbookdiscuss.bookName}</a></div>
                         </div>
                         <div class="disuss-body">
                             <div class="disuss-head">
                                 <span class="discuss-rating">评分：</span>
                                 <input class="discuss-score" type="hidden" value="5" />
                                 <ul class="star-rating">
                                     <li class="current-rating"></li>
                                     <li><a href="#" name="score" title="1 star out of 5" class="one-star">1</a></li>
                                     <li><a href="#" name="score" title="2 stars out of 5" class="two-stars">2</a></li>
                                     <li><a href="#" name="score" title="3 stars out of 5" class="three-stars">3</a></li>
                                     <li><a href="#" name="score" title="4 stars out of 5" class="four-stars">4</a></li>
                                     <li><a href="#" name="score" title="5 stars out of 5" class="five-stars">5</a></li>
                                 </ul>
                                 <div class="disuss-time"> <span>评论时间：2012-8-12</span></div>
                             </div>
                             <div class="disuss-content">
                                 最早知道科耶夫这个名字是在刘小枫先生的一篇文章《 施特劳斯抑或科耶夫》之中 (后来又读到一篇写灵知主义
                                 的文章里也提到了他)，科耶夫是一位极具传奇色彩的哲学天才：法国两代哲人（存在主义和后结构主义， 包括雷
                                 神之父......
                             </div>
                             <div class="discuss-arrow"></div>
                         </div>
                     </li>
                     <li>
                         <div class="disuss-pic">
                             <div style="text-align:center;padding:20px 8px 0 8px;word-break:break-all;"><a style="color:#242644;" href=javascript:openwindowtocenter('rsmRcmbookAction.do?method=detail&bookId=${rsmRcmbookdiscuss.bookId}','教育培训系统',900,900) >${rsmRcmbookdiscuss.bookName}</a></div>
                         </div>
                         <div class="disuss-body">
                             <div class="disuss-head">
                                 <span class="discuss-rating">评分：</span>
                                 <input class="discuss-score" type="hidden" value="5" />
                                 <ul class="star-rating">
                                     <li class="current-rating"></li>
                                     <li><a href="#" name="score" title="1 star out of 5" class="one-star">1</a></li>
                                     <li><a href="#" name="score" title="2 stars out of 5" class="two-stars">2</a></li>
                                     <li><a href="#" name="score" title="3 stars out of 5" class="three-stars">3</a></li>
                                     <li><a href="#" name="score" title="4 stars out of 5" class="four-stars">4</a></li>
                                     <li><a href="#" name="score" title="5 stars out of 5" class="five-stars">5</a></li>
                                 </ul>
                                 <div class="disuss-time"> <span>评论时间：2012-8-12</span></div>
                             </div>
                             <div class="disuss-content">
                                 最早知道科耶夫这个名字是在刘小枫先生的一篇文章《 施特劳斯抑或科耶夫》之中 (后来又读到一篇写灵知主义
                                 的文章里也提到了他)，科耶夫是一位极具传奇色彩的哲学天才：法国两代哲人（存在主义和后结构主义， 包括雷
                                 神之父......
                             </div>
                             <div class="discuss-arrow"></div>
                         </div>
                     </li>
                 </ul>
                <%@include file="../snippet/pagination.jsp"%>
            </div>
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp"%>
</div>
</body>
</html>