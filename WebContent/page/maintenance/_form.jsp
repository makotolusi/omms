<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<table class="form-table" style="width:98%">

	<tr>	
		<th style="width:100px">系统名称</th>
		<td colspan="3">${obj.systemName}</td>
	</tr>	
	
    <tr>
		<th style="width:60px">终端名称</th>
		<td><input name="clentName" id="clentName" class="easyui-validatebox" value="${obj.clentName}" style="width:95%" data-options="required:true,validType:'blength[1,100]'"/></td>
	</tr>
    <tr>
		<th style="width:60px">页面名称</th>
		<td><input name="pageName" id="pageName" class="easyui-validatebox" value="${obj.pageName}" style="width:95%" data-options="required:true,validType:'blength[1,100]'"/></td>
	</tr>
	
	<tr>
		<th style="width:60px">头部内容</th>
		<td><input name="headerContext"  value="${obj.headerContext}" maxlength="100" style="width:95%" class="easyui-validatebox" /></td>
	</tr>
	<tr>
		<th style="width:60px">导航内容</th>
		<td><input name="navContext"  value="${obj.navContext}" maxlength="500" style="width:95%" class="easyui-validatebox" /></td>
	</tr>	

	
</table>

<input type="hidden" name="id" value="${obj.id}"/>
<input type="hidden" name="systemName" value="${obj.systemName}"/>

