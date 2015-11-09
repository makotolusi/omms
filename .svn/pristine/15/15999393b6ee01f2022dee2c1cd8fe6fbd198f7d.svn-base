<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">
	$(function(){
		$('#dg_activity_product_list').edatagrid({
			title     : '<span style="color:red;font-size:18px">${obj.activityName}的活动商品维护</span><span style="float:right">操作提示：双击<span style="color:red;">商品</span>进行编辑，光标移开后自动保存。F5刷新页面</span>',
			url       : '<c:url value="/activity/getactivityproducts/${obj.id}"/>',
			updateUrl : '<c:url value="/activity/updactivityproduct/${obj.id}"/>',
			onError: function(index,row){
				alert(row.msg);
			},
			toolbar : [{
				iconCls: 'icon-no',
				text : '全部<span class="red">停用</span>',
				handler: function(){
					$.messager.progress({title:'正在停用', msg:'正在停用...'});
					$.post("<c:url value='/activity/closeactivityproducts/" + ${obj.id} + "'/>",{},function(result){
						$.messager.progress('close');
						var result_obj = JSON.parse(result);
						if(result_obj.success){
							$('#dg_activity_product_list').edatagrid('reload');
						}else{
							$.messager.alert('停用失败','停用失败');
						}
					});
				}
			},{
				iconCls: 'icon-ok',
				text : '全部<span class="green">启用</span>',
				handler: function(){
					$.messager.progress({title:'正在启用', msg:'正在启用...'});
					$.post("<c:url value='/activity/openallactivityproducts/" + ${obj.id} + "'/>",{},function(result){
						$.messager.progress('close');
						var result_obj = JSON.parse(result);
						if(result_obj.success){
							$('#dg_activity_product_list').edatagrid('reload');
						}else{
							$.messager.alert('启用失败','启用失败');
						}
					});
				}
			},'-',{
				iconCls: 'icon-excel',
				text : '导出',
				title: '将列表内容导出为Excel文件',
				handler: function(){
					excel('#dg_activity_product_list');
				}
			},{
				iconCls: 'icon-reload',
				text : '刷新',
				handler: function(){
					$('#dg_activity_product_list').edatagrid('reload');
				}
			}
			           ]
			
		});
	});
	
	function enableStyler(value,row,index){
		if('启用'==value){
			return 'color:green';
		}else{
			return 'color:red';
		}
	}

	var formatterOptions = {groupSeparator:',', decimalSeparator:'.', prefix:'￥', suffix:''};
	function numberFormatter (v,row,index) {
		if(v== null){
			return '';
		}
		if(v=='' || v==0){
			return '￥0.00';
		}
		v = parseFloat(v).toFixed(2) + "";
		var foptions = formatterOptions;
		var s1 = v,
		s2 = "";
		var _24 = v.indexOf(".");
		if (_24 >= 0) {
			s1 = v.substring(0, _24);
			s2 = v.substring(_24 + 1, v.length);
		}
		if (foptions.groupSeparator) {
			var p = /(\d+)(\d{3})/;
			while (p.test(s1)) {
				s1 = s1.replace(p, "$1" + foptions.groupSeparator + "$2");
			}
		}
		if (s2) {
			return foptions.prefix + s1 + foptions.decimalSeparator + s2 + foptions.suffix;
		} else {
			return foptions.prefix + s1 + foptions.suffix;
		}
	}
	
</script>

<div region="center">
        <table id="dg_activity_product_list" style="width:1260px;height:600px" data-options="pagination:true, pageNumber:1, pageSize:10, striped:true, fitColumns:false, rownumbers:true, idField:'id', autoSave:true, singleSelect:true">
		<thead data-options="frozen:true">
			<tr>
				<th halign="center" field="productCode" fixed="true" align="left" width="100">商品code</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th halign="center" field="productName"			width="100" align="center">商品名称</th>
				<th halign="center" field="rushQuantity"		width="100" align="right" editor="{type:'numberbox',options:{required:true, min:0, groupSeparator:','}}">抢购数量</th>
				<th halign="center" field="rushPrice"       	width="100" align="right" formatter="numberFormatter" editor="{type:'numberbox',options:{required:true, min:0, precision:2, groupSeparator:',', prefix:'￥'}}">价格</th>
				<th halign="center" field="bargainPrice"		width="100"  align="right" formatter="numberFormatter" editor="{type:'numberbox',options:{required:true, min:0, precision:2, groupSeparator:',', prefix:'￥'}}">优惠额度</th>
				<th halign="center" field="sortNum"				width="100"  align="right" editor="{type:'numberbox',options:{required:true, min:0, groupSeparator:','}}">排序</th>
				<th halign="center" field="status"				width="100"  align="center" styler="enableStyler" editor="{type:'checkbox',options:{on:'启用',off:'停用'}}">status</th>
				                                                  <!-- sample http://stworthy.iteye.com/blog/689080 -->
				
			</tr>
		</thead>
	</table>
</div>
