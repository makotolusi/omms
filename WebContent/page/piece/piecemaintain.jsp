<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>规则定义</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-piecemaintain">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true">ID</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="text">类型名称</th>
					<th sortable="true"  width="60"  halign="center" align="center" field="notes">备注</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="username" >录入人</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="entertime" >录入时间</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-piecemaintain" style="padding-left:20px">
<table width="100%"><tr>
	<td style="float:left;">
	
		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-piecemaintain','#s-mlog')"/>
		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合检索公寓，小区等名称" onclick="WX.omms.fullQuery('#dg-piecemaintain','#s-mlog')">检索</a>
	</td>
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-piecemaintain-add" title="新增" onclick="$('#dg-piecemaintain').wxgrid('add')">新增</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-piecemaintain-edit" title="修改" onclick="$('#dg-piecemaintain').wxgrid('update')">修改</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-piecemaintain-del" title="删除" onclick="$('#dg-piecemaintain').wxgrid('del')">删除</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-tip'" disabled="true"  id="bt-dg-piecemaintain-rule" title="定制规则" onclick="javascript:toRuleInfo()">定制规则</a>

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-piecemaintain')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-piecemaintain').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-piecemaintain"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
<script type="text/javascript">
var wxGridId = '#dg-piecemaintain';
var row = "";
$(function(){
	$('#dg-piecemaintain').wxgrid({
		url       :'<c:url value="/piecemaintain/items"/>',
		addUrl    :'<c:url value="/piecemaintain/save"/>',
		updateUrl :'<c:url value="/piecemaintain/save"/>',
		delUrl    :'<c:url value="/piecemaintain/delete"/>',
		lookUrl   :'<c:url value="/piecemaintain/viewstartbidrecord"/>',
		editDlg   :'#dlg-piecemaintain',
		editForm  :'#fm-piecemaintain',
		toolbar : '#tb-dg-piecemaintain',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/piecemaintain/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-piecemaintain-edit').linkbutton('enable');
			$('#bt-dg-piecemaintain-del').linkbutton('enable');
			$('#bt-dg-piecemaintain-rule').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-piecemaintain-edit').linkbutton('disable');
			$('#bt-dg-piecemaintain-del').linkbutton('disable');
			$('#bt-dg-piecemaintain-rule').linkbutton('disable');
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