<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-26
  Time: 下午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@include file="../snippet/meta.jsp" %>
    <link href="css/resrc_rec_sys.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .form {
            overflow: hidden;
            width: 100%;
            color: #666;
        }

        .form label {
            width: 60px;
            display: inline-block;
            text-align: left;
        }

        .form p {
            line-height: 30px;
            position: relative;
        }

        .form input, .form label, .form span {
            font-size: 14px;
            vertical-align: middle;
        }

        .form .input {
            line-height: 20px;
            padding: 4px 5px;
            background-color: white;
            border: #DDD 1px solid;
            -webkit-box-shadow: 0 2px 0 whiteSmoke inset;
            -moz-box-shadow: 0 2px 0 #f5f5f5 inset;
            box-shadow: 0 2px 0 whiteSmoke inset;
            -webkit-transition: border linear .2s, box-shadow linear .2s;
            -moz-transition: border linear .2s, box-shadow linear .2s;
            transition: border linear .2s, box-shadow linear .2s;
            width: 220px;
        }

        .form input:focus {
            outline: 0;
            border: #87BE44 1px solid;
            -webkit-box-shadow: 0 2px 0 whiteSmoke inset, 0 0 4px 1px #E8F3DB;
            -moz-box-shadow: 0 2px 0 #f5f5f5 inset, 0 0 4px 1px #e8f3db;
            box-shadow: 0 2px 0 whiteSmoke inset, 0 0 4px 1px #E8F3DB;
        }

        .form .success, .form .error {
            display: inline-block;
            padding-left: 27px;
            height: 30px;
            line-height: 30px;
            background: url(/img/s_e.png) no-repeat -6px -28px;
        }

        .msgPlace {
            position: absolute;
            margin-left: 5px;
        }

        .hide {
            display: none;
        }

        .form .error {
            color: #F30;
            background-position: -7px 2px;
        }

        .form .success {
            width: 27px;
        }
        .base-info{
            border: 1px solid transparent;
            padding: 20px;
        }
        .base-info .preview-title {
            margin: 10px auto;
            width:500px;
            border-bottom: 2px solid #7DB546;
            border-radius: 0px;
            overflow: hidden;
        }
        .base-info .preview-title h3 {
            display: inline-block;
            margin: 0;
            float:left;
            padding: 5px 20px;
            width: 60px;
            text-align: center;
            color: white;
            background-color: #7DB546;
            font-size: 14px;
        }
        .base-info span{
            font-size: 12px;
            color: #7DB546;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="../snippet/header.jsp" %>
    <div id="content">
        <div id="aside">
            <div id="aside-content">
                <div id="folder_parent" class="folder folder_selected"><p id="root" class="folder-parent-icon">个人管理：</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon" block="basic-info">基本信息</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon" block="passwd-conf">密码设置</p></div>
                <div class="folder folder_nodes"><p class="folder-nodes-icon" block="integral-manage">积分管理</p></div>
            </div>
        </div>
        <div id="article">
            <div id="basic-info">
                <form action="" method="get" target="" class="form" id="form">
                    <div class="base-info">
                        <div class="preview-title"><h3>基本信息</h3></div>

                        <p><label for="userId">姓名</label>
                            <input class="input" type="text" name="userName" id="userName" disabled="disabled" value="${requestScope.user.userName}"/>
                        </p>

                        <p><label for="userId">帐号</label>
                            <input class="input" type="text" name="userId" id="userId" disabled="disabled" value="${requestScope.userId}"/>
                        </p>

                        <p><label for="email">Email</label>
                            <input class="input" type="text" name="email" id="email" value="${email}"/>
                            <span class="msgPlace hide">推荐填写,方便相关信息推送</span>
                        </p>
                    </div>
                    <p>
                        <input class="form-bt submit" type="button" title="提交" value="提交" id="submit"/>
                        <span class="msgPlace hide"></span>
                    </p>
                </form>
            </div>
            <div id="passwd-conf" style="display: none">
                <form action="" method="get" target="" class="form" id="form-pass">
                    <div class="base-info">
                        <div class="preview-title"><h3>密码设置</h3></div>

                        <p><label for="passwd">旧密码</label>
                            <input class="input" type="password" name="passwd-old" id="passwd-old" autofocus/>
                        </p>

                        <p><label for="passwd">新密码</label>
                            <input class="input" type="password" name="passwd" id="passwd" autofocus/>
                            <span class="msgPlace hide">密码4-16位任意字符（字母区分大小写）</span>
                        </p>

                        <p><label for="passwdConfirm">确认密码</label>
                            <input class="input" type="password" name="passwdConfirm" id="passwdConfirm"/>
                            <span class="msgPlace hide">请再输一遍密码</span>
                        </p>
                    </div>
                    <p>
                        <input class="form-bt submit" type="button" title="提交" value="提交" id="submit-pass"/>
                        <span class="msgPlace hide"></span>
                    </p>
                </form>
            </div>
            <div id="integral-manage" style="display: none">
               <div class="integral-status">
                   <img src="../../../img/icon_integral.jpg">
                   <ul>
                       <li><i>积分余额：</i><span>2429</span></li>
                       <li><i>当前可领取积分：</i><span>0</span></li>
                       <li><i>已使用积分：</i><span>0</span> </li>
                       <li><i>累计积分：</i><span>2429</span></li>
                   </ul>
                   <a href="elec_resrc/upload.html" class="btn_uploadS" id="upload" style="margin: 80px 0 0 100px">上传资源</a>
               </div>
                <div class="integral-rule">
                    <div><h2>积分规则</h2></div>
                    <div><h3>上传资源获赠积分</h3></div>
                    <p>
                        一份耕耘一份收获。欢迎同学们积极上传课程相关的电子资源，为建设良好的数字图书馆环境添砖加瓦，贡献者会获取相应积分以下载更多宝贵资源哦。
                    </p>
                    <div><h3>加入社区获赠积分</h3></div>
                    <p>
                        同学们选择了相应课程后，可以根据提示选择加入相应课程社区，并获得一定的积分奖励。
                    </p>
                    <div><h3>参与社区讨论获赠积分</h3></div>
                    <p>
                        欢迎大家在社区家园里积极参与讨论，与老师和同学们交流学术问题，这里是大家增进知识、收获学识的第二课堂。
                    </p>
                    <div><h3>下载资源扣除积分</h3></div>
                    <p>
                        在这里获得分享的乐趣。下载资源会根据上传者规定的积分额度扣除相应积分哦，快去上传资源共享给大家吧~
                    </p>
                </div>
            </div>

        </div>
    </div>
    <%@include file="../snippet/footer.jsp" %>
</div>
<script type="text/javascript">
    $L.script("js/personalInfo.js");
</script>
<%--社区激活列表弹出框--%>
</body>
</html>