<%@page import="m.w.sys.util.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://wx.w.m/tags" prefix="wx"%>

<div class="header">
<div class="api-title" style="float: left; margin-top: 15px"><%=m.w.App.getAppName()%></div>
<div style="float: right; margin-top: 40px; margin-right: 20px;font-weight:bold;">
	<table cellspacing="0px" cellpadding="0px" >
<!-- 		<tr> -->
<%-- 			<td class="api-title"  style="padding-top:10px"><%=m.w.App.getAppName()%></td> --%>
<!-- 		</tr> -->
		
		<tr >
		
			<td style="padding-left:15px;padding-top:5px;" >
			<span id="welcomeWord" class="welcome-text">你好 <shiro:principal property="username" /> ，欢迎登录系统！&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span class="api-text"> 

				</span>
				<a href="<c:url value='/index'/>" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-homepage'">首页</a>
				<a href="<c:url value='/cms/index'/>" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-homepage'">CMS</a>
				<wx:hr name="审批人员">
<%-- 					<a href="<c:url value='/audit'/>" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-check'">审批</a> --%>
				</wx:hr>
				<wx:hr name="系统管理员">
					<a href="#" class="easyui-menubutton"  menu="#menu-sys" data-options="iconCls:'icon-setting'">系统管理</a>
				</wx:hr>
				<a href="manual.zip" class="easyui-linkbutton" plain="true" target="_blank" data-options="iconCls:'icon-open'">操作手册</a>
				<a href="#" onclick="WX.sys.openChpwd()" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-password'">更改密码</a>
				<a  href="<c:url value='/logout'/>" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-exit'">退出</a>
			</td>
		
		</tr>
		
	</table>
</div>
		<wx:hr name="系统管理员">
			<div id="menu-sys" class="easyui-menu" style="width: 100px">
					<a
					href="<c:url value='/sys/user/auth'/>" class="easyui-linkbutton"
					plain="true" data-options="iconCls:'icon-setting'">用户管理</a> 
<!-- 					<a -->
<%-- 					href="<c:url value='/operationhistory/queryDeletedData'/>" class="easyui-linkbutton" --%>
<!-- 					plain="true" data-options="iconCls:'icon-ifs'">删除数据查看</a> -->
			</div>
		</wx:hr>


</div>


<div id="dlg-chpwd" class="easyui-dialog"
	style="width: 300px; height: 150px"
	data-options="iconCls:'icon-password',resizable:false,modal:true, closed:true, title:'更改密码', buttons:'#dlg-chpwd-bts'">
	<div class="easyui-panel" data-options="fit:true">
		<form id="fm-chpwd" method="post"
			action="<c:url value='/sys/user/chpwd'/>">
			<table class="form-table">
				<tr>
					<td>旧 密 码</td>
					<td><input type="password" style="width: 180px"
						name="oldPassword" id="chpwd_old_password" /></td>
				</tr>
				<tr>
					<td>新 密 码</td>
					<td><input type="password" style="width: 180px"
						name="newPassword" id="chpwd_new_password" /></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" style="width: 180px"
						name="rePassword" id="chpwd_re_password" /></td>
				</tr>
			</table>
		</form>
		<div id="dlg-chpwd-bts">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				onclick="WX.sys.chpwd()">确认</a> <a class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" onclick="WX.sys.closeChpwd()">取消</a>
		</div>
	</div>
</div>

<script type="text/javascript">

var today = new Date();
var h = today.getHours();
var wString = "夜里";

if (h>=4 && h<10) {
	wString ="早上";
} 
if (h>=10 && h<12) {
	wString ="上午";
} 
if (h>=12 && h<13) {
	wString ="中午";
} 
if (h>=13 && h<18) {
	wString ="下午";
} 
if (h>=18 && h<23) {
	wString ="晚上";
} 
$("#welcomeWord").text($("#welcomeWord").text().replace("你",wString));
</script>