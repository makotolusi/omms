<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<table class="form-table" style="width:98%">

	<tr>
		<th style="width:60px">名称</th>
		<td>
			${obj.apartmentName}
		</td>
	</tr>
	<tr>
		<th style="width:60px">带宽种类</th>
		<td><input name="networkType" id="networkType"  value="${obj.networkType}" style="width:95%" 
	    class="easyui-combobox" data-options="required:true,editable:false,url:'<c:url value="/rule/getNetWorkTypeList"/>'">
	    </td>
	</tr>
	<tr>
		<th style="width:60px">业务类型</th>
		<td><input name="businessType" id="businessType"  value="${obj.businessType}" style="width:95%" 
	    class="easyui-combobox" data-options="required:true,editable:false,url:'<c:url value="/rule/getBusinessTypeList"/>'">
	    </td>
	</tr>	
	<tr>
		<th style="width:60px">应收金额</th>
		<td><input id="receivableAmount" name="receivableAmount" value="${obj.receivableAmount}" style="width:20%;" class="easyui-numberbox" data-options="min:0,required:true,groupSeparator:','" formatter="WX.sys.money.getmoney" maxlength="18" />元</td>
	</tr>

	
</table>

<input type="hidden" name="id" value="${obj.id}"/>
<input type="hidden" name="apartmentId" value="${obj.apartmentId}"/>
<input type="hidden" name="apartmentName" value="${obj.apartmentName}"/>