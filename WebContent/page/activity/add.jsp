<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>相关人员</title>

</head>
<body>
    <div data-ng-app="fcms">

	<div data-ng-view class="container text-center"></div>
	<script src="../js/angularjs/lib/angular.min.js"></script>
	<script src="../js/angularjs/lib/angular-route.js"></script>
	<script src="../js/angularjs/lib/angular-resource.js"></script>
	<script src="../js/angularjs/lib/angular-file-upload.js"></script>
	<script src="../js/angularjs/content/modules/app.js"></script>
	<script src="../js/angularjs/content/directive/main.js"></script>
	<script src="../js/angularjs/content/controllers/main.js"></script>
	<script src="../js/angularjs/content/modules/resource.js"></script>
        <div data-ng-controller="articleEditCtl">
	    <div class="row container">
		    <div class="col-xs-4  col-md-3"  ng-repeat="img in imgs">
			    <upload img-index="img.index" on-success="onSuccess" on-delete="onDelete"></upload>
		    </div>
	    </div>
	    </div>
 	

   </div>