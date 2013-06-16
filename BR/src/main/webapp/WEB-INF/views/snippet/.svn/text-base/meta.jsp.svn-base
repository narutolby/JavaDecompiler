<%-- Created by IntelliJ IDEA. User: zhaomeng Date: 12-8-12 Time: 下午1:12 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<base href="<%=basePath%>">
<title>数字图书馆</title>
<link href="css/base.css" rel="stylesheet" type="text/css">
<link href="css/pagination.css" rel="stylesheet" type="text/css">
<link href="css/jquery-ui-1.8.23.custom.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/LAB.min.js"></script>
<script type="text/javascript">
    user = "${sessionScope.user.id}";
    userId = "${sessionScope.user.userId}";
    isTeacher = "${requestScope.isTeacher}";
    $LAB.setGlobalDefaults({BasePath:"<c:url value="/"/>"});
    $L = $LAB.script("js/jquery-1.7.1.min.js").wait()
            .script("js/jquery.tmpl.js")
            .script("js/jquery-ui-1.8.23.custom.min.js").wait()
            .script("js/base.js")
</script>
