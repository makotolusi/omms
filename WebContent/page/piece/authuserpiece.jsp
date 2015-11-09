<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<script type="text/javascript">

</script>
<form id="fm-authuser" method="post" action="<c:url value='/sys/user/addPieces'/>">

	<table class="form-table" style="width: 98%">
	
	<c:forEach var="piece" items="${obj.plist}" varStatus="status">
		<tr>
	
			
				<td><input type="checkbox" name="pieces" value="${piece.id}">${piece.text}
				</td>

		</tr>
	</c:forEach>
	</table>
	<input type="hidden" name="id" value="${obj.id}" />
</form>

