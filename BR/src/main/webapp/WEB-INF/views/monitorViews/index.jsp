<%--
  Created by IntelliJ IDEA.
  User: liboyang01
  Date: 12-9-24
  Time: 下午9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <%@include file="snippet/meta.jsp"%>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" title="North Title" style="height:100px;padding:10px;">
    <p>The north content.</p>
</div>
<div data-options="region:'south',split:true" title="South Title" style="height:100px;padding:10px;background:#efefef;">
    <div class="easyui-layout" data-options="fit:true" style="background:#ccc;">
        <div data-options="region:'center'">sub center</div>
        <div data-options="region:'east',split:true" style="width:200px;">sub center</div>
    </div>
</div>
<div data-options="region:'west',split:true" title="菜单" style="width:280px;padding1:1px;overflow:hidden;">
    <div class="easyui-accordion" data-options="fit:true,border:false,selected:true">
        <div title="日志监控" style="padding:10px;overflow:auto;">
            <p><a href="javascript:void(0)" id="catlina">catlina日志</a></p>
        </div>
        <div title="Title2" data-options="" style="padding:10px;">
            content2
        </div>
        <div title="Title3" style="padding:10px">
            content3
        </div>
    </div>
</div>
<div data-options="region:'center'" title="面板" style="overflow:hidden;" id="dashboard">
    <iframe frameborder="0" src="" id="main_iframe" width="100%" height="100%" style="overflow: hidden;"></iframe>
   <%-- <div class="easyui-tabs" data-options="fit:true,border:false">
        <div title="Tab1" style="padding:20px;overflow:hidden;">
            <div style="margin-top:20px;">
                <h3>jQuery EasyUI framework help you build your web page easily.</h3>
                <ul>
                    <li>easyui is a collection of user-interface plugin based on jQuery.</li>
                    <li>using easyui you don't write many javascript code, instead you defines user-interface by writing some HTML markup.</li>
                    <li>easyui is very easy but powerful.</li>
                </ul>
            </div>
        </div>
        <div title="Tab2" data-options="closable:true" style="padding:20px;">This is Tab2 width close button.</div>
        <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="overflow:hidden;padding:5px;">
            <table id="tt2"></table>
        </div>
    </div>--%>
</div>
 <script type="text/javascript">
     $(function(){
         $("#catlina").click(function(){
              $("#main_iframe").attr("src","monitor/log.html");
         });
     });
 </script>
</body>

</html>