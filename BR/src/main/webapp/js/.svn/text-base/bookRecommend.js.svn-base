/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-4-18
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {
    $(function () {
        $("#main").delegate("h1","click",function(){
            $("#my-class").show();
            $("#follow_majors").show();
            $("#submitBtn").show();
            $("#article").hide();
            $("#main").hide();
            $("#pagi").hide();
            $("div.glide_content ul li").unbind();

        });
        $("#aside-content").delegate("h3,h", "click", function () {
            //$(this).parent(".folder").addClass("folder_selected").siblings().removeClass("folder_selected");
            $("#my-class").hide();
            $("#follow_majors").hide();
            $("#submitBtn").hide();
            $("#article").show();
            $("#main").show();
            openFolder();

        });

        var openFolder = function () {

            $.get("user/get_book_info.json", function(data){
                data = data.bookList;
                var index = 0, $detail = {}, position, top = 0, left = 0,index_ul=0;
                $("#pagi").html("");
                for(var key in data){
                    $("#pagi").append(
                        '<div id="'+key+'" class="glide_content" align="center" >' +
                            '<h4 align="left">'+key+'课程 图书推荐</h4>'+
                            '<ul >' +
                                '<li><img width="100px" height="147px" src="'+data[key][0].mediumPic+'"/><p align="center"><a href="'+data[key][0].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                                '<li><img width="100px" height="147px" src="'+data[key][1].mediumPic+'"/><p align="center"><a href="'+data[key][1].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                                '<li><img width="100px" height="147px" src="'+data[key][2].mediumPic+'"/><p align="center"><a href="'+data[key][2].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                                '<li><img width="100px" height="147px" src="'+data[key][3].mediumPic+'"/><p align="center"><a href="'+data[key][3].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                                '<li><img width="100px" height="147px" src="'+data[key][4].mediumPic+'"/><p align="center"><a href="'+data[key][4].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                                '<li><img width="100px" height="147px" src="'+data[key][5].mediumPic+'"/><p align="center"><a href="'+data[key][5].alt+'" target="_blank" style="cursor: pointer">去豆瓣看看>></a></p></li>' +
                            '</ul>' +
                        '</div>'
                    );


                }
                $("#pagi").show();

                $("div.glide_content ul li").hover(function () {
                    index_ul = $(this).parent().parent().index();
                    index = $(this).index();
                    position = $(this).offset();
                    if(index > 3)
                        left = position.left -360;
                    else
                        left = position.left + 120;
                    index = index + index_ul * 6;

                    top = position.top - 10;
                    $detail = $("div.hotResrc_detail").find("div:eq(" + index + ")");
                    $detail.css({"top":top + "px", "left":left + "px"});
                    $detail.removeClass("detail_hidden").addClass("detail_show").fadeIn();

                },function () {
                    $detail.removeClass("detail_show").addClass("detail_hidden").fadeOut();
                });
            });
        };
    });

})(jQuery, window);