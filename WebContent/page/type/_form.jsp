<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<table class="form-table" style="width:98%">

<!-- 	<tr>	 -->
<!-- 		<th style="width:100px">类型</th> -->
<%-- 		<td colspan="3"><input name="type" value="${obj.type}" id="BaseInfoMaintain_type"  class="easyui-combobox" data-options="required:true,url:'<c:url value="/sys/const/options/BaseInfoMaintain/type"/>'"></td> --%>
<!-- 	</tr>	 -->
	
    <tr>
		<th style="width:60px">类型名称</th>
		<td><input name="typeName" id="typeName" class="easyui-validatebox" value="${obj.typeName}" style="width:95%" data-options="required:true,validType:'blength[1,200]'"/></td>
	</tr>

	<tr>
		<th style="width:60px">备注</th>
		<td><input name="notes"  value="${obj.notes}" maxlength="50" style="width:95%" class="easyui-validatebox" /></td>
	</tr>
    <tr>
		<th style="width:60px">类型名称拼音首字母</th>
		<td><input name="type" id="type" class="easyui-validatebox" value="${obj.type}" style="width:95%" data-options="required:true,validType:'blength[1,10]'"/></td>
	</tr>
	

	
</table>

<input type="hidden" name="id" value="${obj.id}"/>
