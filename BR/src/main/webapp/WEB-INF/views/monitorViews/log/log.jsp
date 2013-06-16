<%--
  Created by IntelliJ IDEA.
  User: liboyang01
  Date: 12-9-29
  Time: 上午11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>catlina.out</title>
    <base href="<c:url value="/"/>">
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <style type="text/css">
        #log_console{
            background-color: #000000;
            color: white;
            height: 93%;
            display: none;
            overflow-y: auto;
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
    <div><button id="monitor_start">监控日志</button><button id="monitor_stop">停止监控日志</button></div>
    <div id="log_console"></div>
<script type="text/javascript">
    (function($,exports){
      var stop = false;
      var $log_console = $("#log_console");
      var sendForLogWrap = function(){
          sendForLog({"first":true});
          $log_console.show();
          sendForLogWrap = sendForLog;
      };
      var sendForLog = function(param){
        $.get("monitor/log/start.json",param,function(data){
            if($.trim(data)!=""){
                $log_console.text($log_console.text()+data);
                $log_console.scrollTop($log_console[0].scrollHeight);
            }
            var timmer;
            if(!stop){
                timmer = setTimeout(function(){sendForLogWrap({});},5000);
             }
            if(stop && timmer){
                clearTimeout(timmer);
            }
        });
    }
    $("#monitor_start").click(function(){
        stop = false;
        sendForLogWrap({});
    });
    $("#monitor_stop").click(function(){
        stop = true;
    });
    })(jQuery,window);
</script>
</body>
</html>