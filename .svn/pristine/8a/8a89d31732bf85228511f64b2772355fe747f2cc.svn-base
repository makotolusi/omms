<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<style type="text/css">
p.tip span {
	font-weight:bold;
	color:#ff9955;
	}
</style>
<script type="text/javascript">
 
</script>
<table class="form-table" style="width:98%">

	<tr>	
		<th style="width:60px">姓名</th>
		<td ><input name="username" id="username" value="${obj.username}" style="width:180px" data-options="required:true,validType:'blength[1,50]'"/></td>
	</tr>
    <tr>
		<th style="width:60px">性别</th>
		<td>男<input type="radio" name="gender" value="true" ${wx:checked(obj.gender)}/> 女<input type="radio" name="gender" value="false" ${wx:checked(!obj.gender)}/></td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td><input type="text" name="idNumber" value="${obj.idNumber}" style="width:180px" class="easyui-validatebox" data-options="validType:'blength[18,18]'"/></td>
	</tr>
	<tr>
		<th>电子邮件</th>
		<td><input type="text" name="email" value="${obj.email}" style="width:180px" class="easyui-validatebox" data-options="validType:'blength[1,50]'"/></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<th style="width:60px">是否是编辑</th> -->
<%-- 		<td colspan="3"><input  name="editor" id="user_editor" value="${obj.editor}" class="easyui-combobox" data-options="required:true,url:'<c:url value="/sys/const/options/User/editor"/>'"></td> --%>
<!-- 	</tr> -->
</table>

<input type="hidden" name="id" value="${obj.id}"/>


