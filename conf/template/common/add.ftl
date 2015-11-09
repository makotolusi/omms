<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<form id="fm-${lowerDomainName}" method="post" action="<c:url value='${uriPrefix}/add'/>">
	<jsp:include page="_form.jsp"/>
</form>