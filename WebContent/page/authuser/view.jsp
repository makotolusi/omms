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
		<td >${obj.username}</td>
	</tr>
    <tr>
		<th style="width:60px">性别</th>
		<td>男<input type="radio" name="gender" value="true" ${wx:checked(obj.gender)}/> 女<input type="radio" name="gender" value="false" ${wx:checked(!obj.gender)}/></td>
	</tr>
	<tr>
		<th>身份证号</th>
		<td>${obj.idNumber}</td>
	</tr>
	<tr>
		<th>电子邮件</th>
		<td>${obj.email}</td>
	</tr>
</table>

<input type="hidden" name="id" value="${obj.id}"/>


