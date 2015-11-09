<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>人员</title>
</head>
<body>
<div class="easyui-layout" fit="true">

	<div region="center" border="false">
		<table id="dg-authuser">
			<thead>
				<tr>
					<th sortable="true"  width="30"  halign="center" align="center" field="id" hidden="true" hidden="true">ID</th>
					
					<th sortable="true"  width="50"  halign="center" align="center" field="username">姓名</th>
					<th sortable="true"  width="40"  align="center" field="gender" formatter="WX.sys.gender.formatter" styler="WX.sys.gender.styler">性别</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="email">邮件</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="idNumber">身份证号</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="roleNames">角色</th>
					<th sortable="true"  width="50"  halign="center" align="center" field="roleEditPieces">角色管理板块范围</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<div id="tb-dg-authuser" style="padding-left:20px">
<table width="100%"><tr>
<!-- 	<td style="float:left;"> -->
	
<!-- 		关键词：<input id="s-mlog" name="ct_*" style="width:200px" onkeydown="WX.omms.fullKeyDown(event,'#dg-authuser','#s-mlog')"/> -->
<!-- 		<a class="easyui-linkbutton easyui-tooltip" plain="true" data-options="iconCls:'icon-searchpp'" title="综合检索标题、联系人和正文" onclick="WX.omms.fullQuery('#dg-authuser','#s-mlog')">检索</a> -->
<!-- 	</td> -->
	<td align="right" style="float:right; padding-right:20px">
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" id="bt-dg-authuser-add" title="新增" onclick="$('#dg-authuser').wxgrid('add')">新增</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" disabled="true"  id="bt-dg-authuser-edit" title="修改" onclick="$('#dg-authuser').wxgrid('update')">修改</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" disabled="true"  id="bt-dg-authuser-del" title="删除" onclick="$('#dg-authuser').wxgrid('del')">删除</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-role'" disabled="true"  id="bt-dg-authuser-reply" title="授权角色" onclick="javascript:WX.omms.apply.auth()">授权角色</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" href="javascript:void(0)" plain="true" id="bt-dg-authuser-delAuth" onclick="javascript:WX.omms.apply.delAuth()">取消授权角色</a>
		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-role'" disabled="true"  id="bt-dg-authuser-query" title="授权角色板块范围" onclick="$('#dg-authuser').wxgrid('addauth')">授权角色板块范围</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" href="javascript:void(0)" plain="true" id="bt-dg-authuser-delQuery" onclick="javascript:WX.omms.apply.delAuthQuery()">取消授权角色板块范围</a>
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-excel'" title="将本页列表内容导出为Excel文件" onclick="excel('#dg-authuser')">导出</a> -->
<!-- 		<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search'" title="更多复合查询条件的高级查询" onclick="$('#dg-authuser').wxgrid('query')">高级查询</a> -->
	</td>
</tr></table>
</div>

<div class="easyui-dialog" closed="true" data-options="iconCls:'icon-mlog',maximizable:true,resizable:true,modal:true"
	id="dlg-authuser"
	title="信息录入"
	style="width:800px;height:500px;position:relative"></div>
	
<div class="easyui-dialog" closed="true" cache="false"
	id="dlg-invoice-auth" toolbar="#tb-dlg-invoice-auth"
	style="width:600px;height:200px;position:relative">
	<div class="easyui-layout" fit="true">
		<div region="north" style="height:25px" align="center" id="div-ck-invoice-auth">
			<input type="checkbox" id="roleCK" name="roleNames" value="板块编辑">板块编辑
			<input type="checkbox" id="roleCK" name="roleNames" value="商品发布管理员">商品发布管理员
			<input type="checkbox" id="roleCK" name="roleNames" value="审批员">审批员
			<input type="checkbox" id="roleCK" name="roleNames" value="维护员">维护员
		</div>

	</div>
</div>
<div class="easyui-dialog" closed="true" cache="false"
	id="dlg-invoice-query" toolbar="#tb-dlg-invoice-query"
	style="width:600px;height:200px;position:relative">
	<div class="easyui-layout" fit="true">
		<div region="north" style="height:25px" align="center" id="div-ck-invoice-query">

		</div>
	</div>

</div>
<div id="tb-dlg-invoice-auth">
	<a class="easyui-linkbutton" iconCls="icon-save" href="javascript:void(0)" plain="true" onclick="javascript:WX.omms.apply.addAuth()">授权</a>
</div>
<div id="tb-dlg-invoice-query">
	<a class="easyui-linkbutton" iconCls="icon-save" href="javascript:void(0)" plain="true" onclick="javascript:WX.omms.apply.addAuthQuery()">授权角色板块范围</a>
</div>
<script type="text/javascript">
var wxGridId = '#dg-authuser';
var row = null;
$(function(){
	$('#dg-authuser').wxgrid({
		url       :'<c:url value="/sys/user/items"/>',
		addUrl    :'<c:url value="/sys/user/add"/>',
		addAuthUrl    :'<c:url value="/piecemaintain/addAuthPiece"/>',
		updateUrl :'<c:url value="/sys/user/update"/>',
		delUrl    :'<c:url value="/sys/user/delete"/>',
		lookUrl   :'<c:url value="/sys/user/view"/>',
		editDlg   :'#dlg-authuser',
		editForm  :'#fm-authuser',
		toolbar : '#tb-dg-authuser',
		query:{
			qid:'query_apartment',
			title:'查询',
			form:'<c:url value="/authuser/queryapartment"/>',
			width:450,
			height:250
		},
		onSelect:function(rowIndex, rowData){
			row = rowData;
			$('#bt-dg-authuser-query').linkbutton('disable');
			$('#bt-dg-authuser-delQuery').linkbutton('disable');
			
			if(row.roleNames != undefined ){

				if(row.roleNames.indexOf('录入员') < 0){
					$('#bt-dg-authuser-query').linkbutton('enable');
					$('#bt-dg-authuser-delQuery').linkbutton('enable');
				}
			}
			$('#bt-dg-authuser-edit').linkbutton('enable');
			$('#bt-dg-authuser-del').linkbutton('enable');
			$('#bt-dg-authuser-reply').linkbutton('enable');
			
			$('#bt-dg-authuser-delAuth').linkbutton('enable');
		
		},
		onLoadSuccess:function(data){
			$('#bt-dg-authuser-edit').linkbutton('disable');
			$('#bt-dg-authuser-del').linkbutton('disable');
			$('#bt-dg-authuser-reply').linkbutton('disable');
			$('#bt-dg-authuser-delAuth').linkbutton('disable');
			$('#bt-dg-authuser-query').linkbutton('disable');
			$('#bt-dg-authuser-delQuery').linkbutton('disable');
		}
	});
});

WX.ns('WX.omms.apply');
WX.omms.apply.auth = function(){
	
	$('#dlg-invoice-auth').dialog({title : '将【<span class="red">' + row.username + '</span>】授予'}).dialog('open');
	
}
WX.ns('WX.omms.apply');
WX.omms.apply.authQuery = function(){
	
	$('#dlg-invoice-query').dialog({title : '将【<span class="red">' + row.username + '</span>】授予'}).dialog('open').dialog('refresh', opts.addAuthUrl);
	
}
WX.omms.apply.addAuth = function(){

	var roles =[];
	$('input[name="roleNames"]:checked').each(function(){
		roles.push($(this).val());
	});
	if(roles.length>1){
		$.messager.show({
			title:'<span class="red">请至少选择一个角色</span>',
			msg: '请选择一个角色'
		});
		return;
	}

	$.post(base + '/sys/user/addRoles',
		{username : row.username, roleNames : roles.join(',')},
		function(data){
			if(data.success){
				$('#dlg-invoice-auth').dialog('close');
				$('#dg-authuser').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">授权成功</span>',
					msg     : '授权成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">授权失败</span>',
					msg: '授权失败'
				});
			}
		},
		'json'
	);
};

WX.omms.apply.delAuth = function(){
	
$.messager.confirm('取消申请确认','确定取消？',function(isOK){
	if(isOK){
		
		$.post('<c:url value="/sys/user/delRoles/"/>',
				{
					username   : row.username
				},
				function(result){
					if(result.success){
						$('#dg-authuser').datagrid('reload');
						$.messager.show({
							title   : '<span class="green">取消保存成功</span>',
							msg     : result.msg || '取消保存成功',
							timeout : 3000,
							showType: 'fade'
						});

						$('#dg-apartment').wxgrid("reload");
					}else{
						$.messager.show({
							title   :'<span class="red">保存错误</span>',
							msg     : msg,
							timeout : 10000
						});
					}

				},
				'json'
			);
		
	}
});
}
WX.omms.apply.addAuthQuery = function(){

	var roles =[];
	$('input[name="queryNames"]:checked').each(function(){
		roles.push($(this).val());
	});
	if(roles.length==0){
		$.messager.show({
			title:'<span class="red">请至少选择一个角色查询范围</span>',
			msg: '请至少选择一个角色查询范围'
		});
		return;
	}

	$.post(base + '/sys/user/addQuery',
		{username : row.username, roleNames : roles.join(',')},
		function(data){
			if(data.success){
				$('#dlg-invoice-query').dialog('close');
				$('#dg-authuser').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">授权成功</span>',
					msg     : '授权成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">授权失败</span>',
					msg: '授权失败'
				});
			}
		},
		'json'
	);
};
WX.omms.apply.delAuthQuery = function(){
	
	$.messager.confirm('取消申请确认','确定取消？',function(isOK){
		if(isOK){
			
			$.post('<c:url value="/sys/user/delPieces/"/>',
					{
						id   : row.id
					},
					function(result){
						if(result.success){
							$('#dg-authuser').datagrid('reload');
							$.messager.show({
								title   : '<span class="green">取消保存成功</span>',
								msg     : result.msg || '取消保存成功',
								timeout : 3000,
								showType: 'fade'
							});

							$('#dg-apartment').wxgrid("reload");
						}else{
							$.messager.show({
								title   :'<span class="red">保存错误</span>',
								msg     : msg,
								timeout : 10000
							});
						}

					},
					'json'
				);
			
		}
	});
	};


</script>
</body>
</html>