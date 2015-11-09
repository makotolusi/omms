<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>录入</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" border="false">
		<table id="dg-apartmenthistory">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="username">用户名</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="OperationText">操作类型</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="entertime">操作时间</th>
					<th sortable="true"  width="100"  halign="center" align="center" field="apartmentName">名称</th>

				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-apartmenthistory" style="padding-left:20px">
<table width="100%"><tr>
<!-- 	<td style="float:left;"> -->
	
<!-- 		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-apartmenthistory','#s-mlog')"/> -->
<!-- 		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合检索标题、联系人和正文" onclick="WX.omms.fullQuery('#dg-apartmenthistory','#s-mlog')">检索</a> -->
<!-- 	</td> -->
	<td align="right" style="float:right; padding-right:20px">
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-apartmenthistory-add" title="新增" onclick="$('#dg-apartmenthistory').wxgrid('add')">新增</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-apartmenthistory-edit" title="修改" onclick="$('#dg-apartmenthistory').wxgrid('update')">修改</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-apartmenthistory-del" title="删除" onclick="$('#dg-apartmenthistory').wxgrid('del')">删除</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-apartmenthistory-reply" title="提交" onclick="javascript:WX.omms.apply.reply()">提交</a> -->

<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-apartmenthistory')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-apartmenthistory').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-apartmenthistory"
	title="信息录入"
	style="width:800px;height:650px;position:relative"></div>
<script type="text/javascript">
var wxGridId = '#dg-apartmenthistory';
var row = null;
$(function(){
	$('#dg-apartmenthistory').wxgrid({
		url       :'<c:url value="/apartment/viewhistorygriddata/${obj}"/>',
		addUrl    :'<c:url value="/apartment/add/${obj}"/>',
		updateUrl :'<c:url value="/apartment/save"/>',
		delUrl    :'<c:url value="/apartment/del"/>',
		lookUrl   :'<c:url value="/apartment/viewhistorydetail"/>',
		editDlg   :'#dlg-apartmenthistory',
		editForm  :'#fm-apartmenthistory',
		toolbar : '#tb-dg-apartmenthistory',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/apartment/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-apartmenthistory-edit').linkbutton('enable');
			$('#bt-dg-apartmenthistory-del').linkbutton('enable');
			$('#bt-dg-apartmenthistory-reply').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#bt-dg-apartmenthistory-edit').linkbutton('disable');
			$('#bt-dg-apartmenthistory-del').linkbutton('disable');
			$('#bt-dg-apartmenthistory-reply').linkbutton('disable');
		}
	});
});




</script>
</body>
</html>