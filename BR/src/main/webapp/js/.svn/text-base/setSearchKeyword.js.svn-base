(function ($, exports) {
//    var CourseList ={
//
//        pageNo:1,
//
//        courseList:$.template("courseList","<tr class='folder folder_nodes'><td id='${name}'>${name}</td>"
//            +"<td><input type='text' readonly='readonly' style='border-width: 0;width: 80%' value='${searchKeyword}'/></td>"
//            +"<td>"
//            +"<input type='submit' align='center' value='编   辑' id='editBtn' />"
//            +"</td></tr>"),
//
//        refresh:function(){
//            $.post("academy/coursePage.json",{pageNo:this.pageNo}, $.proxy(function(data){
//                var courses = data.pager;
//                var pageCount = $("#coursePageCount").val();
//                $("#demo tbody").html($.tmpl("courseList",courses.domainList));
//                $("#pageNo").html(courses.pageNo+"/"+pageCount);
//            }));
//        }
//    };
//    $(function(){
//        var pageCount = $("#coursePageCount").val();
//        $("#switchPage :button").click(function(){
//            var page = $(this).attr("id");
//            var _pageNo = CourseList.pageNo;
//            $(":submit").unbind();
//            if(page == "prev"){
//                _pageNo--;
//                if(_pageNo < 1)
//                    _pageNo++;
//            }
//            else if(page == "next"){
//                _pageNo++;
//                if(_pageNo > pageCount)
//                    _pageNo--;
//            }
//            CourseList.pageNo = _pageNo;
//            CourseList.refresh();
//        });
//    });

    $(function () {
        $("#dialog:ui-dialog").dialog("destroy");
        $(":submit").live('click',function(){
            $(this).toggle(
                function () {
                    $(this).val('保   存');
                    var old_key = $(this).parent().prev().find(":text").val();
                    $(this).parent().prev().html('<input type="text" style="border-width: 0;width: 80%" value="'+old_key+'" />');
                },
                function () {
                    var $this = $(this);
                    var $text = $this.parent().prev();
                    $("#msg").show();
                    $("#dialog-confirm").dialog({
                        resizable: false,
                        height: 140,
                        modal: true,
                        buttons: {
                            "确定": function () {
                                var topicId = $this.parents("tr").children("td").attr("id");
                                //param[_id] = mark;
                                //$this.attr("disabled",true);
                                var keyword = $text.find(":text").val();
                                var courseName = $this.parents("tr").children("td").attr("id");
                                $.post("academy/updateCourse.json",{keyword:keyword,course_name:courseName});
                                $(this).dialog("close");
                                $this.val('编   辑');
                                $text.html('<input type="text" style="border-width: 0;width: 80%" readonly="readonly" value="'+keyword+'"/>');
                            },
                            "取消": function () {
                                $(this).dialog("close");
                                var keyword = $text.find(":text").val();
                                $this.val('编   辑');
                                var keyword = $text.find(":text").val();
                                $text.html('<input type="text" style="border-width: 0;width: 80%" readonly="readonly" value="'+keyword+'"/>');
                            }
                        }
                    });
                }
            );
            $(this).trigger('click');
        });
    });
})(jQuery,window);

