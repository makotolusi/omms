<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>产品维护</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-productmaintenance">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					
					<th sortable="true"  width="30"  halign="center" align="center" field="systemName">系统名称</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="clentName">终端名称</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="name">产品名称</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="price">价格</th>
					<th sortable="true"  width="80"  halign="center" align="center" field="productCode">产品code</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="entertime" >录入时间</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-productmaintenance" style="padding-left:20px">
<table width="100%"><tr>
	<td style="float:left;">
	
		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-productmaintenance','#s-mlog')"/>
		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合名称" onclick="WX.omms.fullQuery('#dg-productmaintenance','#s-mlog')">检索</a>
	</td>
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-productmaintenance-add" title="新增" onclick="$('#dg-productmaintenance').wxgrid('add')">新增</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-productmaintenance-edit" title="修改" onclick="$('#dg-productmaintenance').wxgrid('update')">修改</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-productmaintenance-del" title="删除" onclick="$('#dg-productmaintenance').wxgrid('del')">删除</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-tip'" disabled="true"  id="bt-dg-productmaintenance-rule" title="定制规则" onclick="javascript:toRuleInfo()">定制规则</a>

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-productmaintenance')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-productmaintenance').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-productmaintenance"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
<script type="text/javascript">
var wxGridId = '#dg-productmaintenance';
var row = "";
$(function(){
	$('#dg-productmaintenance').wxgrid({
		url       :'<c:url value="/productmaintenance/items"/>',
		addUrl    :'<c:url value="/productmaintenance/add"/>',
		updateUrl :'<c:url value="/productmaintenance/add"/>',
		delUrl    :'<c:url value="/productmaintenance/delete"/>',
		lookUrl   :'<c:url value="/productmaintenance/viewstartbidrecord"/>',
		editDlg   :'#dlg-productmaintenance',
		editForm  :'#fm-productmaintenance',
		toolbar : '#tb-dg-productmaintenance',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/productmaintenance/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-productmaintenance-edit').linkbutton('enable');
			$('#bt-dg-productmaintenance-del').linkbutton('enable');
			$('#bt-dg-productmaintenance-rule').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-productmaintenance-edit').linkbutton('disable');
			$('#bt-dg-productmaintenance-del').linkbutton('disable');
			$('#bt-dg-productmaintenance-rule').linkbutton('disable');
		},
		onDblClickRow:function(rowIndex, rowData) {
			var opts = $(this).datagrid('options');
		    var dlgOpts = $('#dlg-productmaintenance').dialog('options');
			var viewDlgOpts = $.extend({},dlgOpts,{toolbar:[]});
			$('#dlg-productmaintenance').dialog(viewDlgOpts).dialog('open').dialog('refresh', '<c:url value="/productmaintenance/viewstartbidrecord"/>'+'/'+ row[opts.idField]);
		}
	});
});

toRuleInfo = function() {
	
	var id = row.id;

	var sFeatures='dialogTop:0;dialogWidth:'+(window.screen.availWidth)+'px;DialogHeight='+(window.screen.availHeight)+'px;help=0;center=1;status:yes;scroll=1';
	
	windowName = window.open(base + '/rule/ruleInfo/'+id,"",sFeatures);
	if(windowName == "success"){
// 		$('#backBox').panel('refresh');
	}
};
</script>
</body>
</html>