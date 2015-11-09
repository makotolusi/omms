<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
    <head>
<jsp:include page="_meta.jsp"/>
<title><decorator:title default="公共关系管理系统" /></title>
<jsp:include page="_link.jsp"/>
        <decorator:head />
    </head>
    <body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
   		<div class="mainwrap">
   			<div id="mainlayout" class="easyui-layout" fit="true">
<div region="center" border="false" style="overflow:hidden"><decorator:body /></div>
   			</div>
   		</div>
    </body>
</html>
