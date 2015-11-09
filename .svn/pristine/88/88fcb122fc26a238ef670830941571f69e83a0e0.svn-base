<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<head>
<title>活动管理</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-activity">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					
					<th sortable="true"  width="30"  halign="center" align="center" field="activityName">活动名称</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="specialName">专场名称</th>
					<th sortable="true"  width="80"  halign="center" align="center" field="description">描述</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="rushBeginTime">抢购开始时间</th>
					<th sortable="true"  width="80"  halign="center" align="center" field="rushEndTime">抢购结束时间</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="rushStatus" >抢购状态</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="imgUrl" data-options="formatter: WX.omms.activity.imgFormatter" >图片预览</th>					
					
					
					<th sortable="true"  width="30"  halign="center" align="center" field="userName" >创建人</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="entertime" >录入时间</th>
					
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-activity" style="padding-left:20px">
<table width="100%"><tr>
	<td style="float:left;">
	
		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-activity','#s-mlog')"/>
		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合名称" onclick="WX.omms.fullQuery('#dg-activity','#s-mlog')">检索</a>
	</td>
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-activity-add" title="新增" onclick="$('#dg-activity').wxgrid('add')">新增</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-readd'" id="bt-dg-activity-to-product" title="绑定商品" onclick="WX.omms.activity.openProductList()">绑定商品</a>	
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-readd'" id="bt-dg-activity-to-product" title="编辑商品" onclick="WX.omms.activity.editProductList()">编辑商品</a>		
        <a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-activity-edit" title="修改" onclick="$('#dg-activity').wxgrid('update')">修改</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-activity-del" title="删除" onclick="$('#dg-activity').wxgrid('del')">删除</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-tip'" disabled="true"  id="bt-dg-activity-rule" title="定制规则" onclick="javascript:toRuleInfo()">定制规则</a>

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-activity')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-activity').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-activity"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
	
<!-- 关联活动商品 -->
<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-olq',maximizable:true,resizable:true,modal:true"
    id="dlg-activity-product" toolbar="#tb-dlg-activity-product"
    title="<span class='red'>关联</span>商品和活动"
    style="width:800px;height:500px;position:relative"></div>
<div id="tb-dlg-activity-product">
    <a class="easyui-linkbutton easyui-tooltip" data-options="plain:true, iconCls:'icon-save'" title="确认关联" onclick="WX.omms.activity.guanlianProduct()">确认关联</a>
</div>

<!-- 编辑关联商品 -->
<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-olq',maximizable:true,resizable:true,modal:true"
    id="dlg-edit-activity-product" 
    title="<span class='red'>编辑</span>活动关联商品"
    style="width:800px;height:500px;position:relative"></div>
<div id="tb-dlg-edit-activity-product">
    <a class="easyui-linkbutton easyui-tooltip" data-options="plain:true, iconCls:'icon-save'" title="确认关联" onclick="WX.omms.activity.guanlianProduct()">确认关联</a>
</div>
	
<script type="text/javascript">
var wxGridId = '#dg-activity';
var row = "";
$(function(){
	$('#dg-activity').wxgrid({
		url       :'<c:url value="/activity/items"/>',
		addUrl    :'<c:url value="/activity/add"/>',
		updateUrl :'<c:url value="/activity/add"/>',
		delUrl    :'<c:url value="/activity/delete"/>',
		lookUrl   :'<c:url value="/activity/viewstartbidrecord"/>',
		editDlg   :'#dlg-activity',
		editForm  :'#fm-activity',
		toolbar : '#tb-dg-activity',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/activity/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-activity-edit').linkbutton('enable');
			$('#bt-dg-activity-del').linkbutton('enable');
			$('#bt-dg-activity-rule').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-activity-edit').linkbutton('disable');
			$('#bt-dg-activity-del').linkbutton('disable');
			$('#bt-dg-activity-rule').linkbutton('disable');
		},
		onDblClickRow:function(rowIndex, rowData) {
			var opts = $(this).datagrid('options');
		    var dlgOpts = $('#dlg-activity').dialog('options');
			var viewDlgOpts = $.extend({},dlgOpts,{toolbar:[]});
			$('#dlg-activity').dialog(viewDlgOpts).dialog('open').dialog('refresh', '<c:url value="/activity/viewstartbidrecord"/>'+'/'+ row[opts.idField]);

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