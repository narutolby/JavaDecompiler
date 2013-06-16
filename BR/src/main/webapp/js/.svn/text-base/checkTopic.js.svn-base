
(function ($, exports) {
    var TopicList ={

        pageNo:1,

        topicList:$.template("topicList","<tr class='folder folder_nodes'><td id='${id}'>${communityName}</td>"
            +"<td>${topicTitle}</td>"
            +"<td>${authorName}</td>"
            +"<td>${$item.substring(topicContent,0,13)}...</td>"
            +"<td><a href='topic/detail.html?t_id=${id}' target='_blank'><img src='img/icon_subfolder.png'/></a></td>"
            +"<td>"
            +"<input type='submit' value='删帖' id='removeBtn' >"
            +"</td></tr>"),



        refresh:function(){
            //var hashStr = location.hash.replace("#","");
            $.post("topic/topicPage.json",{pageNo:this.pageNo}, $.proxy(function(data){
                var topics = data.pager;
                var pageCount = $("#topicPageCount").val();
                $("#demo tbody").html($.tmpl("topicList",topics.domainList,{substring:function (str, s, e) {
                    return str.substring(s, e);
                }}));
                $("#pageNo").html(topics.pageNo+"/"+pageCount);
            }));
        }

    };
    $(function(){
        var pageCount = $("#topicPageCount").val();

        $("#switchPage :button").click(function(){
            var page = $(this).attr("id");
            var _pageNo = TopicList.pageNo;
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
            TopicList.pageNo = _pageNo;
            TopicList.refresh();
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
                        var topicId = $this.parents("tr").children("td").attr("id");
                        //param[_id] = mark;
                        $this.attr("disabled",true);
                        $.post("topic/removeTopic.json",{topic_id:topicId});
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

