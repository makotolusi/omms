<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>规则定义</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-rule">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					
					<th sortable="true"  width="100"  halign="center" align="center" field="apartmentName">名称</th>
					<th sortable="true"  width="40"  halign="center" align="center" field="networkTypeText">带宽种类</th>
					<th sortable="true"  width="40"  halign="center" align="center" field="businessTypeText">业务类型</th>
<!-- 					<th sortable="true"  width="40"  halign="center" align="center" formatter="WX.sys.money.getmoney" field="receivableAmount">应收金额</th> -->
<!-- 					<th sortable="true"  width="30"  halign="center" align="center" field="continueToPayDay">续费日期</th> -->
<!-- 					<th sortable="true"  width="30"  halign="center" align="center" field="beginDay">开通日期</th> -->
					<th sortable="true"  width="30"  halign="center" align="center" field="receivableAmount" formatter="WX.sys.money.getmoney">应收金额</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="entertime" >录入时间</th>
					<th sortable="true"  width="30"  halign="center" align="center" field="username" >录入人</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-rule" style="padding-left:20px">
<table width="100%"><tr>
<!-- 	<td style="float:left;"> -->
	
<!-- 		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-rule','#s-mlog')"/> -->
<!-- 		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合检索名称、带宽种类和业务类型" onclick="WX.omms.fullQuery('#dg-rule','#s-mlog')">检索</a> -->
<!-- 	</td> -->
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-rule-add" title="新增" onclick="$('#dg-rule').wxgrid('add')">新增</a>
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-rule-edit" title="修改" onclick="$('#dg-rule').wxgrid('update')">修改</a> -->
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-rule-del" title="删除" onclick="$('#dg-rule').wxgrid('del')">删除</a>
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-rule-rule" title="定制规则" onclick="javascript:toRuleInfo()">定制规则</a> -->

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-rule')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-rule').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-rule"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
<script type="text/javascript">
var wxGridId = '#dg-rule';
var rowData = "";
$(function(){
	$('#dg-rule').wxgrid({
		url       :'<c:url value="/rule/ruleList/${obj}"/>',
		addUrl    :'<c:url value="/rule/add/${obj}"/>',
		updateUrl :'<c:url value="/rule/add"/>',
		delUrl    :'<c:url value="/rule/delete"/>',
		lookUrl   :'<c:url value="/rule/viewstartbidrecord"/>',
		editDlg   :'#dlg-rule',
		editForm  :'#fm-rule',
		toolbar : '#tb-dg-rule',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/rule/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			rowData = rowData;
			$('#bt-dg-rule-edit').linkbutton('enable');
			$('#bt-dg-rule-del').linkbutton('enable');
// 			$('#bt-dg-rule-rule').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-rule-edit').linkbutton('disable');
			$('#bt-dg-rule-del').linkbutton('disable');
// 			$('#bt-dg-rule-rule').linkbutton('disable');
		}
	});
});

toRuleInfo = function() {

	var id = rowData.id;
// 	var onDate = $("#onDate").combobox('getValue');	
// 	var endDate = $("#endDate").combobox('getValue');
// alert($("#endDate").combobox('getValue'));
	var sFeatures='dialogTop:0;dialogWidth:'+(window.screen.availWidth)+'px;DialogHeight='+(window.screen.availHeight)+'px;help=0;center=1;status:yes;scroll=1';

	windowName = window.showModalDialog(base + '/rule/ruleInfo/'+id,"",sFeatures);
	if(windowName == "success"){
// 		$('#backBox').panel('refresh');
	}
};
</script>
</body>
</html>