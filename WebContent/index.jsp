<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN'
	xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title><%=App.getAppName()%></title>

<style type="text/css">
.bigbtn {
	font-size: 14px;
	width: 180px;
	height: 17px;
	/*color:#3388AA;*/
	font-weight: bold;
	text-align: center;
	padding: 21px 0px 21px 0px;
	cursor: pointer;
	background-repeat: no-repeat;
	background-position: center left;
}

.bigbtnborder {
	padding: 5px;
	padding-left: 15px;
}

#bt-dg-search {
	background-image: url('css/images/search.png');
}

#bt-dg-editbaseinfo-add {
	background-image: url('css/images/baseinfo.png');
}

#bt-dg-editactivityinfo-add {
	background-image: url('css/images/baseinfo.png');
}

#bt-dg-editcomeandgo-add {
	background-image: url('css/images/comego.png');
}

#bt-dg-edithistory-add {
	background-image: url('css/images/history.png');
}

#bt-dg-editquote-add {
	background-image: url('css/images/quate.png');
}

#bt-dg-editevaluation-add {
	background-image: url('css/images/voute.png');
}

#bt-dg-editstaff-add {
	background-image: url('css/images/staff.png');
}

#bt-dg-editanalysis-add {
	background-image: url('css/images/analysis_1.png');
}
</style>

<script type="text/javascript">
	var winEvaluation;
	var winComeAndGo;
	var winQuote;
	var winBaseInfo;
	var winHistory;
	var winStaff;
	var winActivityInfo;
	var winProductInfo;
	var winAgent;
	var winCommerceUser;
	WX.ns('WX.omms.customer');
	// 
	WX.omms.customer.independent = function(){
		if (!isOpeningWindow(winEvaluation))
		{		
			winEvaluation = window.open('<c:url value="/pageMaintenance/index"/>');
		}
	};
	// 
	WX.omms.customer.btneditcomeandgo = function(){
		if (!isOpeningWindow(winComeAndGo))
		{	
			winComeAndGo = window.open('<c:url value="/productMaintenance/index"/>');
		}
	};
	// 
	WX.omms.customer.agent = function(){
		if (!isOpeningWindow(winStaff))
		{
			winStaff = window.open('<c:url value="wx-ext/menu/order/order_grid.html"/>');
		}
	};
	// 
	WX.omms.customer.expend = function(){
		if (!isOpeningWindow(winHistory))
		{	
			winHistory = window.open('<c:url value="/apartment/apartmentinfo/expend"/>');
		}
	};
	// 规则定义
	WX.omms.customer.btneditquote = function(){
// 		$.messager.show({title : '<span class="red">警告</span>', msg : "建设中"});
		if (!isOpeningWindow(winQuote))
		{	
			winQuote = window.open('<c:url value="/typemaintain/maintain"/>');
		}
	};
	// 模块定义
	WX.omms.customer.btneditPiece = function(){
// 		$.messager.show({title : '<span class="red">警告</span>', msg : "建设中"});
		if (!isOpeningWindow(winQuote))
		{	
			winQuote = window.open('<c:url value="/piecemaintain/maintain"/>');
		}
	};
	
	// 模块定义
	WX.omms.customer.btnSend = function(){
// 		$.messager.show({title : '<span class="red">警告</span>', msg : "建设中"});
		if (!isOpeningWindow(winQuote))
		{	
			winQuote = window.open('<c:url value="/piecesend/index"/>');
		}
	};
	// 
	WX.omms.customer.btneditbaseinfo = function(){
		if (!isOpeningWindow(winBaseInfo))
		{	
			winBaseInfo = window.open('<c:url value="/pageMaintenance/index"/>');
		}
	};
	// 活动维护
	WX.omms.customer.btneditactivityinfo = function(){
		if (!isOpeningWindow(winActivityInfo))
		{	
			winActivityInfo = window.open('<c:url value="wx-ext/menu/activity-grid.html"/>');
		}
	};
	// 商品维护
	WX.omms.customer.btneditproductinfo = function(){
		if (!isOpeningWindow(winProductInfo))
		{	
			winProductInfo = window.open('<c:url value="wx-ext/menu/product_grid.html"/>');
		}
	};
	// 订单维护
	WX.omms.customer.btnagentinfo = function(){
		if (!isOpeningWindow(winAgent))
		{	
			winAgent = window.open('<c:url value="wx-ext/menu/agent/agent_grid.html"/>');
		}
	};
	WX.omms.customer.commerceuser = function(){
		if (!isOpeningWindow(winCommerceUser))
		{	
			winCommerceUser = window.open('<c:url value="wx-ext/menu/user/user_grid.html"/>');
		}
	};
	// 信息查询
	function changeUrl(url){
		window.open('<c:url value="/apartment/queryapartmentinfo"/>');
		
	}
	
	// 数据分析
	WX.omms.customer.btneditanalysis = function(){
	
// 		$('#dlg-analysis').dialog('open').dialog('refresh', base+'/analyse/openGuide');
	};
	
	

	// 判断是否已经打开画面
	function isOpeningWindow(target) {
		if (target && target.open && !target.closed)
		{
			$.messager.show({
	    		title : '<span class="red">警告</span>',
	    		msg : '页面已经打开'
	    	});
			target.focus(); // TODO 不起作用
			return true;
		}
		return false;
	}

</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="true">
		<div data-options="region:'west',split:true" title=" " border="false"
			style="width: 220px;">
<wx:hr name="板块编辑">
		<div class="bigbtnborder">
			<div onclick="WX.omms.customer.btnSend()"
				id="bt-dg-editquote-add" class="bigbtn">内容发布</div>
		</div>
		
		<div class="bigbtnborder">
			<div onclick="WX.omms.customer.btneditproductinfo()"
				id="bt-dg-editevaluation-add" class="bigbtn">商品管理</div>
		</div>
</wx:hr>
<wx:har name="维护员">
			<!-- 按钮区 -->
			<div class="bigbtnborder">
				<div onclick="changeUrl('')" id="bt-dg-search"
					class="bigbtn">信息查询</div>
			</div>


</wx:har>
<wx:hr name="维护员">
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btneditquote()"
					id="bt-dg-editquote-add" class="bigbtn">类型定义</div>
			</div>
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btneditPiece()"
					id="bt-dg-editquote-add" class="bigbtn">模块定义</div>
			</div>
</wx:hr>

<wx:har name="商品发布管理员 维护员">
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btneditbaseinfo()"
					id="bt-dg-editbaseinfo-add" class="bigbtn">页面管理</div>
			</div>

			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btneditcomeandgo()"
					id="bt-dg-editcomeandgo-add" class="bigbtn">
					价格管理<span class="todoCount" id="todoCount01"></span>
				</div>
			</div>
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.agent()"
					id="bt-dg-editstaff-add" class="bigbtn">订单管理</div>
			</div>
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btneditactivityinfo()"
					id="bt-dg-editactivityinfo-add" class="bigbtn">活动管理</div>
			</div>

	<div class="bigbtnborder">
				<div onclick="WX.omms.customer.btnagentinfo()"
					id="bt-dg-editactivityinfo-add" class="bigbtn">经销商管理</div>
			</div>
			
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.independent()"
					id="bt-dg-editevaluation-add" class="bigbtn"></div>
			</div>
			<div class="bigbtnborder">
				<div onclick="WX.omms.customer.expend()"
					id="bt-dg-edithistory-add" class="bigbtn">账务查询</div>
			</div>
				<div class="bigbtnborder">
				<div onclick="WX.omms.customer.commerceuser()"
					id="bt-dg-edithistory-add" class="bigbtn">商业用户</div>
			</div>
</wx:har>


<!-- 			<div class="bigbtnborder"> -->
<!-- 				<div onclick="WX.omms.customer.btneditanalysis()" -->
<!-- 					id="bt-dg-editanalysis-add" class="bigbtn">数据分析</div> -->
<!-- 			</div> -->
		</div>
		<div region="center" border="false" >
			<div id="tabs-index" class="easyui-tabs" fit="true" border="false" 
				data-options="onSelect:WX.omms.index.onTabSelect">
				<div id="tabs-index-public" title="首  页" 
					href="<c:url value='/apartment/warningTodo'/>" cache="false"></div>
			</div>
		</div>
	</div>
	<div class="easyui-dialog" closed="true"
		data-options="iconCls:'icon-olq',maximizable:true,resizable:true,modal:true"
		id="dlg-analysis" title="数据分析"
		style="width: 800px; height: 500px; position: relative; blank: none;"></div>

</body>
</html>