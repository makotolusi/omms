<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<table class="form-table" style="width:98%">

<!-- 	<tr>	 -->
<!-- 		<th style="width:100px">类型</th> -->
<%-- 		<td colspan="3"><input name="type" value="${obj.type}" id="BaseInfoMaintain_type"  class="easyui-combobox" data-options="required:true,url:'<c:url value="/sys/const/options/BaseInfoMaintain/type"/>'"></td> --%>
<!-- 	</tr>	 -->
	<c:if test='${obj.imgUrl != null}'>
		<tr>
			<th style="width:60px">板块图片</th>
			<td><img src="${obj.imgUrl}" style="width: 400px; height: 300px"></img></td>
		</tr>
	</c:if>
    <tr>
		<th style="width:60px">板块名称</th>
		<td><input name="text" id="text" class="easyui-validatebox" value="${obj.text}" style="width:95%" data-options="required:true,validType:'blength[1,200]'"/></td>
	</tr>

	<tr>
		<th style="width:60px">备注</th>
		<td><input name="notes"  value="${obj.notes}" maxlength="50" style="width:95%" class="easyui-validatebox" /></td>
	</tr>
	
	<tr>
		<th style="width: 60px">板块图片</th>
		<td>

			<div>
				<input type="button" id="organizationchartImageUploadButton"
					value="上传" />
					<c:if test='${obj.imgUrl != null}'>
						<img  imgId="${obj.imgUrl}"
							id="organizationchartImage" style="width: 400px; height: 300px"></img>
					</c:if>
			</div> 
		</td>
	</tr>
	
</table>

<input type="hidden" name="id" value="${obj.id}"/>
<input type="hidden" id="imgUrl" name="imgUrl" value="${obj.imgUrl}"/>
<script type="text/javascript">
var organizationchartImageUploadButton = KindEditor.uploadbutton({
	button : KindEditor('#organizationchartImageUploadButton')[0],
	fieldName : 'imgFile',
	url : '<c:url value="/sys/file/upload/image"/>',
	afterUpload : function(result) {
		if (result.error === 0){
			var url = KindEditor.formatUrl(result.url, 'absolute');
// 			$('#organizationchart').val(url);
// 			$('#organizationchartId').val(result.data.id);
			$('#imgUrl').attr('value',result.data.imgUrl);
			$('#organizationchartImage').attr('src',url);
			$('#organizationchartImage').attr('imgId',result.data.id);
		}else{
			alert(data.message);
		}
	},
	afterError : function(str) {
		alert('上传发生错误！');
	}
});
organizationchartImageUploadButton.fileBox.change(function(e) {
	organizationchartImageUploadButton.submit();
});
</script>
