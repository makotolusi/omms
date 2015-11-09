<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<jsp:include page="decorators/_head.jsp"></jsp:include>
<script type="text/javascript">
if(top.location!==self.location){
	top.location.href=self.location.href;
}
$(function(){
	if(!$('#username').val()){
		$('#username').focus();
	}else if(!$('#password').val()){
		$('#password').focus();
	}else{
		$('#btn').focus();
	}
});

function doSubmit(){
	if(!$('#username').val()){$('#username').focus(); return false;}
	if(!$('#password').val()){$('#password').focus(); return false;}
	$.messager.progress({
		title: '系统登录',
		msg  : '<span style="color:green; font-weight:bold; padding:10px; font-size:14px">'+$('#username').val()+'</span>您好：',
		text : '您现在正在登录到<span style="color:red; font-weight:bold; padding:5px;">[<%=App.getAppName() %>]</span>，请稍等...'
	});
	return true;
}
</script>
</head>
<body style="visibility:visible">
	<noscript><div align="center" style="color:red;font-size:24px">运行本系统必须 JavaScript 的支持。<br/>请开启浏览器的 JavaScript 功能，或更换其它支持 JavaScript 的浏览器！</div></noscript>
	<div class="easyui-dialog" style="width:500px;height:300px;overflow:hidden"
			title="系统登录" closable="false" border="false">
		<div class="header" style="width:350px;height:60px;">
			
		</div>
		<div class="api-title" style="text-align:center;margin-top:10px;"><%=App.getAppName() %>登录</div>
		<div style="padding:20px 0;">
			<form id="loginForm" action="<c:url value='/dologin'/>" method="post" onsubmit="return doSubmit()">
				<div style="padding-left:150px">
					<table cellpadding="0" cellspacing="3">
						<tr>
							<td>登录帐号</td>
							<td><input tabindex="1" id="username" name="username" style="width:150px" value="${username}"></input></td>
						</tr>
						<tr>
							<td>登录密码</td>
							<td><input tabindex="2" id="password" type="password" name="password" style="width:150px" value="${password}"></input></td>
						</tr>
						<tr>
							<td>记 住 我</td>
							<td><input tabindex="3" id="rememberMe" type="checkbox" name="rememberMe" value="true"></input></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input id="btn" tabindex="3" class="login" type="submit" value="" style="width:74px;height:21px;border:0"></input>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><div class="err">${_ERR_}</div><div class="info">${obj}${_INFO_}</div></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>