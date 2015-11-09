<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<table class="form-table" width="98%">
<#list domainFields?keys as field>
	<tr>
		<th style="width:60px">${domainFields[field]}</th>
		<td>${'$'}{obj.${field}}</td>
	</tr>
</#list>
</table>
