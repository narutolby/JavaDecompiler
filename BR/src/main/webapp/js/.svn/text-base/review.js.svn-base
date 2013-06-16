
(function ($, exports) {
    var ReviewResource ={

        pageNo:1,
        htmlTag:$("#demo tbody").attr("id"),
        resourceReview:$.template("resourceReview","<tr class='folder folder_nodes'><td id='${id}' class='folder-nodes-icon'>{{= resourceRealName}}</td>"
            +"<td>${uploadUserId}</td>"
            +"<td>${uploadUserName}</td>"
            +"<td>${uploadTime}</td>"
            +"<td><a href='resourceOperation/download.html?resourceId=${id}'><img src='img/icon_download_small.png'/></a></td>"
            +"<td>"
            +"<input type='submit' value='审核通过' id='reviewBtn' >"
            +"<input type='submit' value='审核未过' id='refuseBtn' ></td></tr>"),

        resourcePass:$.template("resourcePass","<tr class='folder folder_nodes'><td id='${id}' class='folder-nodes-icon'>{{= resourceRealName}}</td>"
            +"<td>${uploadUserId}</td>"
            +"<td>${uploadUserName}</td>"
            +"<td>${uploadTime}</td>"
            +"<td><a href='resourceOperation/download.html?resourceId=${id}'><img src='img/icon_download_small.png'/></a></td>"
            +"<td>审核通过</td></tr>"),

        resourceRefused:$.template("resourceRefused","<tr class='folder folder_nodes'><td id='${id}' class='folder-nodes-icon'>{{= resourceRealName}}</td>"
            +"<td>${uploadUserId}</td>"
            +"<td>${uploadUserName}</td>"
            +"<td>${uploadTime}</td>"
            +"<td><a href='resourceOperation/download.html?resourceId=${id}'><img src='img/icon_download_small.png'/></a></td>"
            +"<td>审核未过</td></tr>"),

        refresh:function(){
            //var hashStr = location.hash.replace("#","");
            $.post("elec_resrc/reviewPage.json",{pageNo:this.pageNo,htmlTag:this.htmlTag}, $.proxy(function(data){
                var resource = data.pager;
                var htmlTag = $("#demo tbody").attr("id");
                var pageCount = $("#resourcePageCount").val();
                if(htmlTag == 1)
                    $("#demo tbody").html($.tmpl("resourceReview",resource.domainList));
                else if(htmlTag == 2)
                    $("#demo tbody").html($.tmpl("resourcePass",resource.domainList));
                else if(htmlTag == 3)
                    $("#demo tbody").html($.tmpl("resourceRefused",resource.domainList));
                $("#pageNo").html(resource.pageNo+"/"+pageCount);
            }));
        }

    };
    $(function(){
        var pageCount = $("#resourcePageCount").val();

        $("#switchPage :button").click(function(){
            var page = $(this).attr("id");
            var _pageNo = ReviewResource.pageNo;
            if(page == "prev"){
                _pageNo--;
                if(_pageNo < 1)
                    _pageNo++;
            }
            else if(page == "next"){
                _pageNo++;
                if(_pageNo > pageCount)
                    _pageNo--;
            }
            //location.hash = "#" + _pageNo;
//            window.onload = ReviewResource.refresh();
//            window.onhashchange = ReviewResource.refresh();
            ReviewResource.pageNo = _pageNo;
            ReviewResource.refresh();
        });
    });

    $(function () {
        $("#dialog:ui-dialog").dialog("destroy");
        $(":submit").live("click",(function () {
            var currentBtn = $(this).attr("id");
            var $this = $(this);
            $("#msg").show();

            $("#dialog-confirm").dialog({
                resizable: false,
                height: 140,
                modal: true,
                buttons: {
                    "确定": function () {
                        var mark;
                        if(currentBtn == "reviewBtn"){
                            mark = 1;
                            $this.next().attr("disabled",true);
                        }
                        else{
                            mark = 0;
                            $this.prev().attr("disabled",true);
                        }
                        $this.attr("disabled",true);

                        var _id = $this.parents("tr").children("td").attr("id");
                        //param[_id] = mark;

                        $.post("elec_resrc/reviewList.json",{resourceId:_id,mark:mark});
                        $(this).dialog("close");
                    },
                    "取消": function () {
                        $(this).dialog("close");
                    }
                }
            });

        }));
    });

})(jQuery,window);

