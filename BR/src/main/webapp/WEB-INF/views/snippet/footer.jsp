<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-8-12                                                                                                      #login
  Time: 下午1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="footer">
    <div class="footer-l">
        <dl>
            <dt>合作交流</dt>
            <dd><a href="common/aboutUs.html" target="_blank">关于我们</a></dd>
            <dd><a href="common/contactUs.html" target="_blank">联系我们</a></dd>
        </dl>
        <dl class="friend-link">
            <dt>友情链接</dt>
            <dd><a href="http://www.bupt.edu.cn/" target="_blank">北京邮电大学</a></dd>
            <dd><a href="http://lib.bupt.edu.cn/" target="_blank">北京邮电大学图书馆</a></dd>
            <dd><a href="http://www.mbupt.com/" target="_blank">北京邮电大学PCN&CAD中心</a></dd>
            <dd><a href="http://bbs.byr.cn/index" target="_blank">北邮人论坛</a></dd>
        </dl>
    </div>
    <div class="footer-r">
        <p>CopyRight&nbsp;© 2013&nbsp;PCN&CAD中心&nbsp;&&nbsp;北京邮电大学图书馆</p>
        <p>京备07019532号&nbsp;All&nbsp;rights&nbsp;reserved.</p>
    </div>
</div>

<div id="loginDialog" title="Dialog Title" style="display: none;">
    <form name="loginForm" id="loginForm" action="user/login.json"method="post">
        <div id="loginContent">
            <div><span>账&nbsp;&nbsp;号：</span><input class="login" type="text" value="" name="userId"/>
            <p id="login_notice1"></p></div>                                                                    
            <div><span>密&nbsp;&nbsp;码：</span><input class="login" type="password" value="" name="passwd"/>
            <p id="login_notice2"></p></div>
        </div>
        <p id="login_note">学生账号为学号，初始密码均为“111”。</p>
    </form>
<%--   <p id="login_note" style="text-align: center;color: red;font-weight: bold;display: none;"></p>--%>
</div>
<!--通用提示模版-->
<div id="dialog-message" title="提示信息" style="display: none">
    <p style="padding-left:25px">
        <span class="ui-icon ui-icon-circle-check" style="float: left;margin: 0 7px 20px 0;"></span>
        <span id="mess_content"></span>
    </p>
</div>