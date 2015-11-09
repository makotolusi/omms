<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>

	
<script type="text/javascript">
	function productTreeFormatter(node){
		if(node.id.charAt(0)!='T' && node.attributes.price){
			return '【<a href="javascript:getProductDetail(' + node.id + ',\'' + node.attributes.productCode + '\')">查看'+ node.id + '号商品' + node.attributes.productCode + '</a>】&nbsp;&nbsp;' + node.text;
		}else{
			return node.text;
		}
	}
	
	function getProductDetail(productId, productCode){
		$('#productDetailDlg').dialog({title : '商品详细'})
		.dialog('open').dialog('refresh', base + '/productMaintenance/viewstartbidrecord/' + productId);
	}
</script>
<div class="easyui-dialog" id="productDetailDlg" style="width:600px;height:600px;position:relative" cache="false" closed="true"></div>
<form id="guanlian_form" method="post">
<table class="form-table">
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
		<th style="width:60px">关联商品</th>
		<td><ul id="tree-activity-product" class="easyui-tree" data-options="cascadeCheck:false, onlyLeafCheck:true, checkbox:true, lines:true, formatter: productTreeFormatter, onCheck:WX.sys.role.onBeforeCheck, url:'<c:url value='/mg/sso/common/productTree/M/PS'/>'"></ul></td>
					
	</tr>
</table>
<input type="hidden" id="activityId" name="activityId" value="${obj.id}"/>
</form>




