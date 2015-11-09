<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<table class="form-table" style="width:600px;overflow-x:auto;overflow-y:hidden;">
	<tr>	
		<th style="width:100px">活动名称</th>
		<td>${obj.activityName}</td>
	</tr>	
	
	<tr>	
		<th style="width:100px">专场名称</th>
		<td>${obj.specialName}</td>
	</tr>	
	
	<tr>	
		<th style="width:100px">描述</th>
		<td>${obj.description}</td>
	</tr>

	<tr>	
		<th style="width:100px">抢购开始时间</th>
		<td>${obj.rushBeginTime}</td>
	</tr>

	<tr>	
		<th style="width:100px">抢购结束时间</th>
		<td>${obj.rushEndTime}</td>
	</tr>

	<tr>	
		<th style="width:100px">抢购状态 </th>
		<td>${obj.rushStatus}</td>
	</tr>
	
	<tr>
 		<th style="width:100px">图片预览</th>
        <td><img src="${obj.imgUrl}"></td>
	</tr>

	<tr>	
		<th style="width:100px">创建人</th>
		<td>${obj.userName}</td>
	</tr>
	
	<tr>	
		<th style="width:100px">录入时间</th>
		<td>${obj.entertime}</td>
	</tr>
</table>

<input type="hidden" name="id" value="${obj.id}"/>