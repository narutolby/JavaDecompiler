/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-11-13
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
(function ($, exports) {
    var myCommunityController = {
        community:{
            pageNo:1,
            pageCount:$("#pageCount").val() - 0
        },
        elements:{
            "slide_bar":$("#slide_bar"),
            "communities":$("#communities")
        },
        events:{
            "slide_bar":{
                click:function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    var $target = $(event.target);
                    var _id = $target.attr("id");
                    if (_id == "arrowL") {
                        if (this.community.pageNo != 1) {
                            this.communities.stop(false,true).animate({"left":"+=528px"}, 150);
                            this.community.pageNo--;
                            $target.parents("#slide_bar").find("img").css("opacity", 1);
                        }
                        if (this.community.pageNo == 1) {
                            $target.css("opacity", 0.5);
                        }

                    } else if (_id == "arrowR") {
                        if (this.community.pageNo != this.community.pageCount) {
                            this.communities.stop(false,true).animate({"left":"-=528px"}, 150);
                            this.community.pageNo++;
                            $target.parents("#slide_bar").find("img").css("opacity", 1);
                        }
                        if (this.community.pageNo == this.community.pageCount) {
                            $target.css("opacity", 0.5);
                        }
                    }
                }
            }
        },
        init:function () {
            var _eles = this.elements;
            var _eves = this.events;
            for (var ele in _eles) {
                var _v = this[ele] = _eles[ele];
                for (var _eve in _eves[ele]) {
                    _v.bind(_eve, $.proxy(_eves[ele][_eve], this));
                }
            }
        }
    };
    $(function () {
        myCommunityController.init();
    });
})(jQuery, window);