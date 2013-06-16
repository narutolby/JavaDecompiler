<%--
  Created by IntelliJ IDEA.
  User: zhaomeng
  Date: 12-8-16
  Time: 下午7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@include file="../snippet/meta.jsp"%>
		<%--<link style="upload.css" style="text/css" rel="stylesheet"/>--%>
        <link type="text/css" rel="stylesheet" href="css/uploadCom.css" />
        <link href="js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div id="wrapper">
	<%@include file="../snippet/header.jsp"%>
	<div id="content">
        <div id="uploadBtn">
              <input type="file" id="Filedata" name="Filedata" style="display: none;"/>
              <div id="Filedata_mirror"></div>
              <div id="file-queue">
                  <ul></ul>
              </div>
        </div>
        <div id="step1">
            <h3>资料上传须知</h3>
            <div id="uploadRemind">
                <p><b>上传涉及侵权内容的文档将会被移除</b></p>
                <p>每次最多上传10份文档，每份文档不超过30M</p>
                <p><b>为了保证文档能正常显示，我们支持以下格式的文档上传:</b></p>
                <table cellpadding="5px" width="600" height="140" cellspacing="0" border="0">
                <tbody>
                <tr>
                    <td class="r">MS Office文档</td>
                    <td valign="top">
                    <span class="icon doc"></span> doc,docx  &nbsp;&nbsp;
                    <span class="icon ppt"></span> ppt,pptx  &nbsp;&nbsp;
                    <span class="icon xls"></span> xls,xlsx&nbsp;&nbsp;
                    <span class="icon vsd"></span> vsd &nbsp;&nbsp;
                    <span class="icon pot"></span> pot&nbsp;&nbsp;
                    <span class="icon pps"></span> pps&nbsp;&nbsp;
                    <span class="icon rtf"></span> rtf&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                     <td class="r">WPS office系列</td>
                     <td>
                     <span class="icon wps"></span> wps  &nbsp;&nbsp;
                     <span class="icon et"></span> et  &nbsp;&nbsp;
                     <span class="icon dps"></span> dps&nbsp;&nbsp;
                     </td>
                </tr>
                <tr><td class="r">PDF</td><td><span class="icon pdf"></span> pdf</td></tr>
                <tr><td class="r">纯文本</td><td><span class="icon txt"></span> txt</td></tr>
                <tr><td class="r">EPUB</td><td><span class="icon epub"></span> epub</td></tr>
                </tbody>
                </table>
            </div>
        </div>
		<div id="step2" class="multi-upload" style="display: none">
			<h3 id="fill-one">填写上传资源信息</h3>
			<form name="uploadForm" method="get" id="uploadForm">
				<div class="doc-unit only-one">
				<div id="form" class="form-holder clearfix">
					<%--<dl class="form-title">
						<dt><span>*</span>标题</dt>
						<dd>
							<div id="event_main_title">
                                <input type="text" class="input-text" id="title" name="title" required="true"/>
							</div>
						</dd>
					</dl>--%>
					<dl class="form-sumary">
						<dt>介绍</dt>
						<dd>
							<textarea id="summary"class="input-init" name="description" style="color: #000000;"></textarea>
						</dd>
					</dl>
					<dl class="form-category">
					    <dt>
							<span>*</span>文件夹
						</dt>
						<dd>
							<div id="selectContainer">
								<select id="myDirectory" size="10" class="class-table" name="myDirectory" required="true" multiple="multiple">
								</select>
                            </div>
						</dd>
                        <dt>财富值</dt>
                        <dd>
                            <div id="selectContainer">
                                <select id="myDirectory" class="class-table" name="myDirectory" required="true">
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                            </div>
                        </dd>
					</dl>
				</div>
				</div>
			    <input type="button" value="提交" class="input-button" id="submitBtn">
			</form>
		</div>
	</div>
	<div class="clr"></div>
	<%@include file="../snippet/footer.jsp"%>
        <script type="text/javascript">
            $L.script("js/uploadify/jquery.uploadify-3.1.min.js").wait()
                    .script("js/commonUpload.js").wait()
                    .script("js/uploadResource.js");
        </script>
	</body>
</html>