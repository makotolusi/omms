<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title><%=App.getAppName()%></title>
</head>
<body>
<div align="center" style="width:100%;height:100%;padding-top:100px;background-color:yellow;font-weight:bold;color:red;font-size:24px;" class="err">${obj}</div>
<script type="text/javascript">
$.messager.alert('<span class="red">警告</span>','${obj}');
</script>
</body>
</html>