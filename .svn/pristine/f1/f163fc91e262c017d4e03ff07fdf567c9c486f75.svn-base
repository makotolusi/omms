<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>

<form id="fm-getanalysisinfo" method="get" action="<c:url value='/analyse/statisticsComeAndGo'/>">
<div id="tb-dlg-baseinfo-get">
	<a class="easyui-linkbutton" iconCls="icon-next" href="#" plain="true" onclick="javascript:toAnalysisInfo()">&nbsp&nbsp数据抽取</a>

</div>
<div region="center">

<table class="form-table">
		<tr>
			<th style="width: 60px">数据类型</th>
			<td><input name="type" value="${obj.type}" id="type" style="width: 198px" class="easyui-combobox" panelHeight="120" data-options="required:true,editable:false,url:'<c:url value="/sys/const/options/analysis/type"/>'"></td>
		</tr>
		<tr>
			<th style="width: 60px">起始日期</th>
			<td><input name="onDate" id="onDate" value="${wx:date(obj.onDate)}" style="width: 198px" class="easyui-datebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th style="width: 60px">截至日期</th>
			<td><input name="endDate" id="endDate" value="${wx:date(obj.endDate)}" style="width: 198px" class="easyui-datebox" data-options="required:true" /></td>
		</tr>
	</table>
</div>
</form>

<div class="easyui-dialog" closed="true" id="dlg-baseinfo-get" toolbar="#tb-dlg-analysis-get"
 title="数据分析" style="width:600px;height:400px;position:relative">

</div>
	
	

<script type="text/javascript">

toAnalysisInfo = function() {

	var type = $("#type").combobox('getValue');
	var onDate = $("#onDate").combobox('getValue');	
	var endDate = $("#endDate").combobox('getValue');
// alert($("#endDate").combobox('getValue'));
	var sFeatures='dialogTop:0;dialogWidth:'+(window.screen.availWidth)+'px;DialogHeight='+(window.screen.availHeight)+'px;help=0;center=1;status:yes;scroll=1';

	windowName = window.showModalDialog(base + '/analyse/statisticsComeAndGo/'+type+'/'+onDate+'/'+endDate,"",sFeatures);
	if(windowName == "success"){
// 		$('#backBox').panel('refresh');
	}
};

// function toAnalysisInfo(){
// 	$('#dlg-analysis').dialog('close');
// 	$('#fm-getanalysisinfo').form('submit');
// // 	$('#fm-getanalysisinfo').form('submit',{
// // 		onSubmit:function(){
// // 			WX.kesync();
// // 			var isValid = $(this).form('validate');
// // 			if (isValid){
// // 				$.messager.progress({text:'正在提交数据，请稍候...'});
// // 			}
// // 			return isValid;
// // 		},
// // 		success:function(result){
// // 			$.messager.progress('close');

// // 			if(result != null && result.isJSON()) {
// // 				result = $.parseJSON(result);
// // 			} else {
// // 				$.messager.show({title : '<span class="red">警告</span>', msg : result});
// // 				return false;
// // 			}
// // 			$('#dlg-baseinfo-get').dialog('open').dialog('refresh', base+'/analysis/btnaddstaffByGuide/'+result.attr.data.companyid);
// // 			var msg = result.msg || result.detailMessage || '保存数据发生错误';
// // 			if (result.success){
				
// // 				alert("aaa");
// // 				$('#dlg-baseinfo-get').dialog('open').dialog('refresh', base+'/analysis/btnaddstaffByGuide/'+result.attr.data.companyid);

// // 			}else{
// // 				$.messager.show({
// // 					title   :'<span class="red">保存错误</span>',
// // 					msg     : msg,
// // 					timeout : 10000
// // 				});
// // 			}
// // 		}
// // 	});
// }	

</script>