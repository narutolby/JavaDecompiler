<%response.sendRedirect("user/my_configure.html");%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html dir="ltr" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <style type="text/css">
        #editor{
            float:left; width:380px;background:#D5F3F4;padding-left:10px;
        }
        #editor span.button{
            display:block;float:left;border: 1px solid  #CCC;margin:1px 5px 1px 0px ;color: #000;
            background: #D0E8FC;width:40px;height:20px;text-align:center;
        }
        #editor select{ display:block; float:left;height:24px;width:60px;margin-right:5px;}
        #textarea {width:382px;height:100px;}

    </style>
<script type="text/javascript">
    window.onload = function(){
        var editor = document.getElementById("editor");
        var buttons = editor.getElementsByTagName("span");
        var selects = editor.getElementsByTagName("select");
        var textarea = document.getElementById("textarea");
        textarea.style.display = "none";
        var iframe = document.createElement("iframe");
        iframe.style.width = "390px";
        iframe.style.height = "100px";
        iframe.frameBorder=0;
        textarea.parentNode.insertBefore(iframe,textarea);
        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
        iframeDocument.designMode = "on";
        iframeDocument.open();
        iframeDocument.write('<html><head><style type="text/css">body{ font-family:arial; font-size:13px;background:#DDF3FF }</style></head></html>');
        iframeDocument.close();
        for(var i = 0,l= buttons.length;i<l;i++){
            buttons[i].onclick = new function(){
                var command=buttons[i].getAttribute("title");
                return function(){
                    if(command == 'createlink' || command == 'insertimage'){
                        var value = prompt('请输入超链接:', 'http://');
                        iframeDocument.execCommand(command,false,value);
                    }else{
                        iframeDocument.execCommand(command,false,'');
                    }
                }
            };
        };
        for(i=0,l=selects.length;i<l;i++){
            selects[i].onchange = new function(){
                var select = selects[i];
                return function(){
                    var command = select.getAttribute("title"),
                            value = select.options[select.selectedIndex].value;
                    if(command == 'backcolor' && /a/[-1]=='a'){
                        iframeDocument.execCommand('hilitecolor',false,value);
                    }else{
                        iframeDocument.execCommand(command,false,value);
                    }
                };
            };
        };
    }

</script>
<title>富文本编辑器</title>
</head>
<body>
<div id="editor">
    <span class="button" title="bold" unselectable="on">加粗</span>
    <span class="button" title="italic" unselectable="on">斜体</span>
    <span class="button" title="underline" unselectable="on">下划</span>
    <span class="button" title="justifycenter" unselectable="on">居中</span>
    <span class="button" title="justifyleft" unselectable="on">居左</span>
    <span class="button" title="justifyright" unselectable="on">居右</span>
    <span class="button" title="outdent" unselectable="on">悬挂</span>
    <span class="button" title="indent" unselectable="on">缩进</span>
    <span class="button" title="insertunorderedlist" unselectable="on">无序</span>
    <span class="button" title="insertorderedlist" unselectable="on">有序</span>
    <span class="button" title="createlink" unselectable="on">链接</span>
    <span class="button" title="insertimage" unselectable="on">图片</span>
    <select title="fontname">
        <option value="SimSun">宋体</option>
        <option value="LiSu">隶书</option>
        <option value="KaiTi_GB2312">楷体</option>
        <option value="YouYuan">幼圆</option>
        <option value="SimHei">黑体</option>
        <option value="Microsoft YaHei">雅黑</option>
        <option value="Comic Sans MS">Comic Sans MS</option>
    </select>
    <select title="fontsize">
        <option value="1">特小</option>
        <option value="2">很小</option>
        <option value="3">小</option>
        <option value="4">普通</option>
        <option value="5">大</option>
        <option value="6">很大</option>
        <option value="7">特大</option>
    </select>
    <select title="forecolor">
        <option style="color:#000000" value="#000000">■■</option>
        <option style="color:#FF8080" value="#FF8080">■■</option>
        <option style="color:#FFFF00" value="#FFFF00">■■</option>
        <option style="color:#80FF00" value="#80FF00">■■</option>
        <option style="color:#00FFFF" value="#00FFFF">■■</option>
        <option style="color:#0000FF" value="#0000FF">■■</option>
        <option style="color:#FF00FF" value="#FF00FF">■■</option>
    </select>
    <select title="backcolor">
        <option style="color:#000000" value="#000000">■■</option>
        <option style="color:#FF8080" value="#FF8080">■■</option>
        <option style="color:#FFFF00" value="#FFFF00">■■</option>
        <option style="color:#80FF00" value="#80FF00">■■</option>
        <option style="color:#00FFFF" value="#00FFFF">■■</option>
        <option style="color:#0000FF" value="#0000FF">■■</option>
        <option style="color:#FF00FF" value="#FF00FF">■■</option>
    </select>
</div>
<br style="clear:both"/>
<textarea id="textarea"></textarea>
</body>
</html>--%>

<%--<script type="text/javascript " >
    Function.prototype.method = function (name, func) {
        this.prototype[name] = func;
        return this;
    };
    Function.method('inherits', function (parent) {
        this.prototype = new parent();
        var d = {},
                p = this.prototype;
        this.prototype.constructor = parent;
        this.method('uber', function uber(name) {
            if (!(name in d)) {
                d[name] = 0;
            }
            var f, r, t = d[name], v = parent.prototype;
            if (t) {
                while (t) {
                    v = v.constructor.prototype;
                    t -= 1;
                }
                f = v[name];
            } else {
                f = p[name];
                if (f == this[name]) {
                    f = v[name];
                }
            }
            d[name] += 1;
            r = f.apply(this, Array.prototype.slice.apply(arguments, [1]));
            d[name] -= 1;
            return r;
        });
        return this;
    });
    function A() { }
    A.prototype.getName = function () { return 'A'; }; // @1

    function B() { }
    B.inherits(A);
    B.prototype.getName = function () { return this.uber('getName') + ',B'; }; // @2

    function C() { }
    C.inherits(B);
    //C.prototype.getName = function () { return this.uber('getName') + ',C'; }; // @3

    function D() { }
    D.inherits(C);
    D.prototype.getName = function () { return this.uber('getName') + ',D'; }; // @3

    function E() { }
    E.inherits(D);
    E.prototype.getName = function () { return this.uber('getName') + ',E'; }; // @3

    var E = new E();
    E.getName();
    console.log(E.getName());
    console.log(E.uber('getName'))
</script>--%>
<%--<%response.sendRedirect("index1.html");>%--%>
<%--<!DOCTYPE html>

<html>
<head>
    <style>p { background:yellow; margin:6px 0; } p.off { background: black; }</style>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>
<p>Hello</p>
how are
<p>you?</p>
<button>Attach/detach paragraphs</button>
<script>
    $("p").click(function(){
        $(this).toggleClass("off");
    });
    var p;
    $("button").click(function(){
        if ( p ) {
            document.body.appendChild(p);
            p = null;
        } else {
            p = $("p").get(0).parentNode.removeChild($("p").get(0))
        }
    });</script>

</body>
</html>--%>
