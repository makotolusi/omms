<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<head>
	<script type="text/javascript">
		$(function(){
			$('#dg-${lowerDomainName}').wxgrid({
				url       :'<c:url value="${uriPrefix}/items"/>',
				addUrl    :'<c:url value="${uriPrefix}/add"/>',
				updateUrl :'<c:url value="${uriPrefix}/update"/>',
				delUrl    :'<c:url value="${uriPrefix}/delete"/>',
				lookUrl   :'<c:url value="${uriPrefix}/view"/>',
				editDlg   :'#dlg-${lowerDomainName}',
				editForm  :'#fm-${lowerDomainName}',
				searcher  :'#s-${lowerDomainName}',
				query:{
					title:'查询${chineseName}',
					form:'<c:url value="${uriPrefix}/query"/>',
					width:450,
					height:380,
					callback:function(){$('.easyui-searchbox').searchbox('setValue','');}
				}
			});
		});
	</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="subtitle">${chineseName}列表</div>
			<div class="toolbar">
				<input id="s-${lowerDomainName}" class="easyui-searchbox" style="width:500px"
					data-options="prompt:'请输入要查询的值',menu:'#s-${lowerDomainName}-menu'"></input>
				<div id="s-${lowerDomainName}-menu" style="width:120px">
<#list domainFields?keys as field>
					<div data-options="name:'lk_${field}'">${domainFields[field]}</div>
</#list>
				</div>
			</div>
		</div>
		<div region="center" border="false" style="padding:0 5px 5px 5px">
			<table id="dg-${lowerDomainName}" title="${chineseName}列表">
				<thead>
					<tr>
<#list domainFields?keys as field>
						<th sortable="true"  width="50"  align="left" field="${field}">${domainFields[field]}</th>
</#list>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="easyui-dialog" closed="true"
		id="dlg-${lowerDomainName}"
		title="${chineseName}"
		style="width:1000px;height:600px;position:relative"></div>
</body>