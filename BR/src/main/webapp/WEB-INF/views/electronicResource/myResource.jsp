<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-12
  Time: 上午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
        <div id="aside">
            <div id="aside-content" style="min-height: 300px">
                <div id="folder_parent" class="folder folder_selected"><p id="root" class="folder-parent-icon">我的文件夹：</p></div>
            </div>
            <div class="aside-content" style="min-height: 300px;margin-top: 15px;">
                <div class="folder folder_selected folder_parent"><p class="folder-parent-icon root">我的标签：</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon">已关注标签</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon">标签推荐</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon">添加标签</p></div>
            </div>
        </div>
        <div id="content-right">
            <div class="doc_type" id="doc_type" style="display: none">
                <ul>
                    <li class="selected_type">
                        <a href="javascript:void(0)" id="all">全部
                        </a></li>
                    <li><a href="javascript:void(0)" id="pdf">
                        PDF
                    </a></li>
                    <li><a href="javascript:void(0)" id="doc|docx">
                        Word
                    </a></li>
                    <li><a href="javascript:void(0)" id="ppt|pptx|pps">
                        PPT
                    </a></li>
                    <li><a href="javascript:void(0)" id="txt">
                        Txt
                    </a></li>
                    <li><a href="javascript:void(0)" id="other">
                        其他
                    </a></li>
                </ul>
                <div class="clr"></div>
            </div>
        </div>
        <div id="article">
            <div class="doc_sort" id="doc_sort" style="display: none">
                <p class="flt_l">排序：</p>
                <input type="button" class="btn_kind btn_sortDesc" id="uploadTime" value="发布日期&nbsp;&nbsp;"/>
                <input type="button" class="btn_kind " id="resourceSize" value="文件大小&nbsp;&nbsp;"/>
                <input type="button" class="btn_kind" id="downloadTimes" value="下载次数&nbsp;&nbsp;"/>
            </div>
            <div id="upload-resource">
                <a href="elec_resrc/upload.html" class="btn_uploadS" id="upload">上传资源</a>
            </div>
            <div class="clr"></div>
            <%--<div class="folder_operation" id="folder_operation">
                <p>文件夹操作：</p>
                <input type="button" value="上一级"/>
                &lt;%&ndash;<input type="button" value="新建文件夹"/>&ndash;%&gt;
                <input type="button" value="移动"/>
                <input type="button" value="删除"/>
            </div>--%>
            <div class="clr"></div>
            <ul class="bigFolder" id="bidFolder"></ul>
            <ul class="myResrcList" id="resrc_list">
                <%-- <c:forEach items="${requestScope.pager.domainList}" var="model">
                    <%@include file="../template/myResourceTmpl.jsp"%>
                </c:forEach>--%>
            </ul>
            <div id="destination" class="detail_hidden"><img src="img/icon_trash.jpg"/></div>
            <%@include file="../snippet/pagination.jsp" %>
        </div>
    </div>
    <div class="clr"></div>
    <%@include file="../snippet/footer.jsp" %>

</div>
<div class="myResrc_detail">
</div>
<div id="downloadArrow" class="detail_hidden"><img src="img/icon_download.png"/></div>
</body>
<script id="myResourceTemplate" type="text/x-jquery-tmpl"></script>
<script type="text/javascript">
    $L.script("js/myResource.js").wait(function () {
                $.importTemplate("tmpl/myResource.tmpl", "myResourceTemplate");
                var _t;
                $("#resrc_list").bind("mouseover",function(event){
                    var $t = $(event.target);
                    var _li = $t.closest("li");
                    if(!_t){
                        _t = _li;
                    }else{
                       if(_t.is(_li)){
                           return;
                       }else{
                           _t.find(".resc_tip").stop(true,true).hide().end().find(".resc_tip_div").hide();
                           _t = _li;
                       }
                    }
                    var _len = _li.size();
                    if(_len==1){
                        _li.find(".resc_tip").stop(true,true).show().end().find(".resc_tip_div").show();
                    }else{
                        _li.parent("ul").find()
                    }
                });
            }
    );
</script>
<script id="resourceDetailTemplate" type="text/x-jquery-tmpl"></script>
</html>