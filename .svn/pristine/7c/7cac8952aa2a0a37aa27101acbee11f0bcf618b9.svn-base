<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/_includes.jsp"%>
<html style="height:100%" lang='zh-CN' xml:lang='zh-CN'
	xmlns='http://www.w3.org/1999/xhtml'>
<script type="text/javascript" src="js/easyui/extension/portal/jquery.portal.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style type="text/css">
.pheader1,.pheader2,.pheader3,.pheader4 {
	background-image: none;
	color: white;
	margin-top: 10px;
}

.pbody1,.pbody2,.pbody3,.pbody4 {
	line-height: 30px;
	color: black;
}

.pheader1 {
/* 	background-color: #99FF99; */
	background-color: #95b8e7;
}

.pbody1 {
	background-color: #EEFFEE;
	
}

.pheader2 {
/* 	background-color: #CCCC99; */
	background-color: #95b8e7;
}

.pbody2 {
/* 	background-color: #FFFFEE; */
	background-color: #EEFFEE;
}

.pheader3 {
/* 	background-color: #CC9999; */
	background-color: #95b8e7;
}

.pbody3 {
/* 	background-color: #FFEEEE; */
	background-color: #EEFFEE;
}

.pheader4 {
	background-color: #9999CC;
}

.pbody4 {
	background-color: #EEEEFF;
}
</style>
<!-- 公共关系 -->
<div class="easyui-layout" fit="true"  >
	<div data-options="region:'center'" style="padding-left: 5px;padding-right:10px;overflow-x:hidden;overflow-y:auto;">
		<div id="workspace" style="vertical-align:top;" >
			<!-- 公告区 -->
			<div style="width: 45%;padding: 10px;"></div>
			<!-- 待定 -->
			<div style="width: 45%;"></div>
		</div>
	</div>

</div>
<div id="daiban">

</div>

<div id="gonggao" >

</div>

<div id="xinwen" >

</div>
<div id="popWin" title=" "></div>
<script type="text/javascript">

	var h = 300;
	$('#workspace').portal({
		border : false,
		fit : true
	});
	$('#daiban').panel({
		iconCls : 'icon-bell',
		headerCls : 'pheader1',
		bodyCls : 'pbody1',
		height : h,
		title : '待办事项'
	});
	$('#workspace').portal('add', {
		panel : $('#daiban'),
		columnIndex : 0,
		prepend : true
	});
	$('#gonggao').panel({
		iconCls : 'icon-role',
		headerCls : 'pheader2',
		bodyCls : 'pbody2',
		height : h,
		title : '公告'
	});
// 	$('#workspace').portal('add', {
// 		panel : $('#gonggao'),
// 		columnIndex : 0,
// 		prepend : true
// 	});
	$('#xinwen').panel({
		iconCls : 'icon-define',
		headerCls : 'pheader3',
		bodyCls : 'pbody3',
		height : h * 2 + 10,
		title : '新闻'
	});
// 	$('#workspace').portal('add', {
// 		panel : $('#xinwen'),
// 		columnIndex : 1,
// 		prepend : true,
// 	});
	// 新闻
	$('#workspace').portal('add', {
		panel : $('#xinwen').panel({
// 			href:'<c:url value="/cms/noteList/news"/>',
			collapsible : false,
			tools:[{
				iconCls:'icon-reload',
				title : '刷新',
				handler:function(){
					$('#xinwen').panel('refresh');
					}
			},{
				iconCls:'icon-list',
				title : '所有',
				handler:function(e){
					$('#popWin').window({
						title:"全数据",
						width:800,
						height:600,
						collapsible:false,
						minimizable:false,
						cache:false,
				 	    closable: true,
// 						href:'cms/noteList/news-all'
					});
				}
			}]
		}),
		columnIndex : 1
	});
	// 公告
	$('#workspace').portal('add', {
		panel : $('#gonggao').panel({
// 			href:'<c:url value="/cms/noteList/knows"/>',
			collapsible : false,
			tools:[{
				iconCls:'icon-reload',
				title : '刷新',
				handler:function(){
					$('#gonggao').panel('refresh');
					}
			},{
				iconCls:'icon-list',
				title : '所有',
				handler:function(e){
					$('#popWin').window({
						title:"全数据",
						width:800,
						height:600,
						collapsible:false,
						minimizable:false,
						cache:false,
				 	    closable: true,
// 						href:'cms/noteList/knows-all'
					});
				}
			}]
		}),
		columnIndex : 0
	});
	// 待办
	$('#workspace').portal('add', {
		panel : $('#daiban').panel({
			href:'<c:url value="/cms/daibanlistshowcount"/>',
			collapsible : false,
			tools:[{
				iconCls:'icon-reload',
				title : '刷新',
				handler:function(){
					$('#daiban').panel('refresh');
					}
			}]
		}),
		columnIndex : 0
	});
</script>


