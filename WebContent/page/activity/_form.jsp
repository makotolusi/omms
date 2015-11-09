<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<table class="form-table" style="width:98%">
	<tr>	
		<th style="width:100px">活动名称</th>
		<td><input name="activityName" id="activityName" class="easyui-validatebox" value="${obj.activityName}" 
		style="width:95%" data-options="required:true,validType:'blength[1,100]'"/></td>
	</tr>	
	
	<tr>	
		<th style="width:100px">专场名称</th>
		<td><input name="specialName" id="specialName" class="easyui-validatebox" value="${obj.specialName}" 
		style="width:95%" data-options="required:true,validType:'blength[1,100]'"/></td>
	</tr>	
	
	<tr>	
		<th style="width:100px">描述</th>
		<td><input name="description" id="description" class="easyui-validatebox" value="${obj.description}" 
		style="width:95%" data-options="required:true,validType:'blength[1,200]'"/></td>
	</tr>

	<tr>	
		<th style="width:100px">抢购开始时间</th>
		<td><input name="rushBeginTime" id="rushBeginTime" class="easyui-datetimebox" value="${obj.rushBeginTime}"/></td>
	</tr>

	<tr>	
		<th style="width:100px">抢购结束时间</th>
		<td><input name="rushEndTime" id="rushEndTime" class="easyui-datetimebox" value="${obj.rushEndTime}"/></td>
	</tr>

	<tr>	
		<th style="width:100px">抢购状态 </th>
		<td><input name="rushStatus" id="rushStatus" class="easyui-validatebox" value="${obj.rushStatus}" 
		style="width:95%" data-options="required:true,validType:'blength[1,200]'"/></td>
	</tr>
	
	<tr>	
		<th style="width:100px">图片地址 </th>
		<td><input name="imgUrl" id="imgUrl" class="easyui-validatebox" value="${obj.imgUrl}" 
		style="width:95%" data-options="required:true,validType:'blength[1,200]'"/></td>
	</tr>
</table>

<input type="hidden" name="id" value="${obj.id}"/>
<input type="hidden" name="userName" value="${obj.userName}"/>
<input type="hidden" name="entertime" value="${wx:datetime(obj.entertime)}"/>


