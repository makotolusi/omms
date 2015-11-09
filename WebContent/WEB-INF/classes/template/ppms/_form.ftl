<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<input type="hidden" name="id" value="${'$'}{obj.id}"/>
<table class="form-table" width="98%">
<#list domainFields?keys as field>
<#if field != 'id'>
	<tr>
		<th style="width:60px">${domainFields[field]}</th>
		<td><input name="${field}" value="${'$'}{obj.${field}}" style="width:95%" class="easyui-validatebox"/></td>
	</tr>
</#if>
</#list>
</table>