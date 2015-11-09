<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>页面维护</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-pagemaintenance">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					
					<th sortable="true"  width="30"  halign="center" align="center" field="systemName">系统名称</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="clentName">终端名称</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="pageName">页面名称</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="headerContext">头部内容</th>
					<th sortable="true"  width="80"  halign="center" align="center" field="navContext">导航内容</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="entertime" >录入时间</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-pagemaintenance" style="padding-left:20px">
<table width="100%"><tr>
	<td style="float:left;">
	
		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-pagemaintenance','#s-mlog')"/>
		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合名称" onclick="WX.omms.fullQuery('#dg-pagemaintenance','#s-mlog')">检索</a>
	</td>
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-pagemaintenance-add" title="新增" onclick="$('#dg-pagemaintenance').wxgrid('add')">新增</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-pagemaintenance-edit" title="修改" onclick="$('#dg-pagemaintenance').wxgrid('update')">修改</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-pagemaintenance-del" title="删除" onclick="$('#dg-pagemaintenance').wxgrid('del')">删除</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-tip'" disabled="true"  id="bt-dg-pagemaintenance-rule" title="定制规则" onclick="javascript:toRuleInfo()">定制规则</a>

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-pagemaintenance')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-pagemaintenance').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-pagemaintenance"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
<script type="text/javascript">
var wxGridId = '#dg-pagemaintenance';
var row = "";
$(function(){
	$('#dg-pagemaintenance').wxgrid({
		url       :'<c:url value="/pageMaintenance/items"/>',
		addUrl    :'<c:url value="/pageMaintenance/add"/>',
		updateUrl :'<c:url value="/pagemaintenance/add"/>',
		delUrl    :'<c:url value="/pagemaintenance/delete"/>',
		lookUrl   :'<c:url value="/pagemaintenance/viewstartbidrecord"/>',
		editDlg   :'#dlg-pagemaintenance',
		editForm  :'#fm-pagemaintenance',
		toolbar : '#tb-dg-pagemaintenance',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/pagemaintenance/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-pagemaintenance-edit').linkbutton('enable');
			$('#bt-dg-pagemaintenance-del').linkbutton('enable');
			$('#bt-dg-pagemaintenance-rule').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-pagemaintenance-edit').linkbutton('disable');
			$('#bt-dg-pagemaintenance-del').linkbutton('disable');
			$('#bt-dg-pagemaintenance-rule').linkbutton('disable');
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