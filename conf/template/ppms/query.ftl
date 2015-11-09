<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<div>
	<table class="form-table">
<#list domainFields?keys as field>
		<tr>
			<th style="width:60px">${domainFields[field]}</th>
			<td><input name="lk_${field}" style="width:120px" class="query"></input></td>
		</tr>
</#list>
	</table>
</div>