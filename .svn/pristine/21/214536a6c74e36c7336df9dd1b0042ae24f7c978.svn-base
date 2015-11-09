namespace('WX')

/** 生成命名空间 */
WX.ns = function(ns){
    namespace(ns);
};

WX.gc = function(){
	if($.msie) CollectGarbage();
};

/** 判断是否为空 */
WX.isEmpty = function(val){
	return null == val || '' == val;
};
/** 判断是否为空白 */
WX.isBlank = function(val){
	return null == val || $.trim(val).length<1;
};
/** 获取某个直接值或字段值 */
WX.val = function(val){
	if(typeof(val) != 'string') return val;
	return val.indexOf('#') == 0 ? $(val).val() : val;
};
/** 解析字符串形式的日期 */
WX.parseDate = function(val){
	val = WX.val(val);
	var dateParts = val.split("-");
	return new Date(dateParts[0], (dateParts[1] - 1) ,dateParts[2]);
};
/** 日期形式的今天 */
WX.dtoday = function(){
	var date = new Date();
	return new Date(date.getFullYear(), (date.getMonth()+1) ,date.getDate());
};
/** 字符串形式的今天 */
WX.stoday = function(){
	var date = new Date();
	return date.getFullYear() +'-'+ (date.getMonth()+1) +'-'+ date.getDate();
};

/** 默认的DataGrid列首右键菜单  */
WX.defaultDataGridHeaderContextMenu = function(gridId, e, field){
	e.preventDefault();
	if (!$('#'+gridId+'-tmenu').length){
		createHeaderMenu(gridId);
	}
	$('#'+gridId+'-tmenu').menu('show', {
		left:e.pageX,
		top:e.pageY
	});

	function createHeaderMenu(gid){
		var tmenu = $('<div id="'+gid+'-tmenu" style="width:100px;"></div>').appendTo('body');
		var fields = $('#'+gid).datagrid('getColumnFields');
		for(var i=0; i<fields.length; i++){
			var copts = $('#'+gid).datagrid('getColumnOption', fields[i]);
			if(!copts.hidden){
				$('<div name="'+fields[i]+'" iconCls="icon-ok"/>').html(copts.title).appendTo(tmenu);
			}else{
				$('<div name="'+fields[i]+'" iconCls="icon-empty"/>').html(copts.title).appendTo(tmenu);
			}
		}
		tmenu.menu({
			onClick: function(item){
				if (item.iconCls=='icon-ok'){
					$('#'+gid).datagrid('hideColumn', item.name);
					tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				} else {
					$('#'+gid).datagrid('showColumn', item.name);
					tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
					});
				}
			}
		});
	}
};

/** 默认的Tabs右键菜单 */
WX.defaultTabsContextMenu = function(e, title, index){
	e.preventDefault();

	var self = this;
	var tabsId = this.id;
	var tabs = $(this).tabs('tabs');
	var tabsCount = tabs.length;
	var menuId = 'mm_' + tabsId;

	var _tab = '#' + tabsId;
	var _menu = '#' + menuId;

	if($(_menu).length<1) appendMenu(menuId);

	//绑定右键菜单事件
	//刷新
	$(_menu + '-tabupdate').unbind('click').click(function(){
		$(self).tabs('getSelected').panel('refresh');
		$(_menu).menu('hide');
	});

	//关闭当前
	$(_menu + '-tabclose').unbind('click').click(function(){
		if(tabs[index].panel('options').closable) $(self).tabs('close',index);
		$(_menu).menu('hide');
	});
	//全部关闭
	$(_menu + '-tabcloseall').unbind('click').click(function(){
		for(var idx = tabsCount-1; idx>=0; idx--){
			if(tabs[idx].panel('options').closable) $(self).tabs('close',idx);
		}
		$(_menu).menu('hide');
	});
	//关闭除当前之外的TAB
	$(_menu + '-tabcloseother').unbind('click').click(function(){
		$(_menu + '-tabcloseright').click();
		$(_menu + '-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$(_menu + '-tabcloseright').unbind('click').click(function(){
		for(var idx = tabsCount-1; idx>index; idx--){
			if(tabs[idx].panel('options').closable) $(self).tabs('close',idx);
		}
		$(_menu).menu('hide');
	});
	//关闭当前左侧的TAB
	$(_menu + '-tabcloseleft').unbind('click').click(function(){
		var cnt = 0;
		var max = index;
		for(var idx = 0; idx<max; idx++){
			if(tabs[idx].panel('options').closable){
				$(self).tabs('close',cnt);
				max--;
				idx--;
			}else{
				cnt++;
			}
		}
		$(_menu).menu('hide');
	});

	//退出
	$(_menu + '-exit').unbind('click').click(function(){
		$(_menu).menu('hide');
	});
	
	$(_menu).menu('show',{left : e.pageX, top : e.pageY});

	function appendMenu(menuId){
		var mm = [];
		mm.push('<div id="' + menuId + '" style="width:150px;">');
		mm.push('	<div id="' + menuId + '-tabupdate">刷新当前页</div>');
		mm.push('	<div class="menu-sep"></div>');
		mm.push('	<div id="' + menuId + '-tabcloseall">全 部 关 闭</div>');
		mm.push('	<div id="' + menuId + '-tabclose">关闭所选页</div>');
		mm.push('	<div class="menu-sep"></div>');
		mm.push('	<div id="' + menuId + '-tabcloseother">所选页之外全部关闭</div>');
		mm.push('	<div id="' + menuId + '-tabcloseright">所选页右侧全部关闭</div>');
		mm.push('	<div id="' + menuId + '-tabcloseleft">所选页左侧全部关闭</div>');
		mm.push('	<div class="menu-sep"></div>');
		mm.push('	<div id="' + menuId + '-exit">退出菜单</div>');
		mm.push('</div>');
		$(document.body).append(mm.join('\n'));
		$('#'+menuId).menu();
	}
};

//$.fn.tabs.defaults.onContextMenu = WX.defaultTabsContextMenu;

/** 导出grid表格数据为excel */
function excel(gid){
	var opts = $(gid).datagrid('options');
	//getColumnOption
	var fields = $(gid).datagrid('getColumnFields');
	var rows = $(gid).datagrid('getRows');
	var table = ["<rows profile='color'"];
	if(opts.title){
		table.push(" title='"+opts.title+"'");
	}
	table.push(">");
	table.push("<head>");
	table.push("<columns>");
	for(var i=0; i<fields.length; i++){
		var col = $(gid).datagrid('getColumnOption', fields[i]);
		if (col && !col.hidden){
			table.push("<column");
			if(col.align){
				table.push(" align='"+col.align+"'");
			}
			if(col.width){
				table.push(" width='"+col.width+"'");
			}
			table.push("><![CDATA[");
			table.push(col.title);
			table.push("]]></column>");
		}
	}
	table.push("</columns>");
	table.push("</head>");
	
	for(var i=0; i<rows.length; i++) {
		table.push("<row>");
		var row = rows[i];
		var rowIndex = $(gid).datagrid('getRowIndex', row);
		for(var j=0; j<fields.length; j++){
			var field = fields[j];
			var col = $(gid).datagrid('getColumnOption', field);
			if (col && !col.hidden){
				table.push("<cell><![CDATA[");
				if (col.formatter){
					var txt = col.formatter(row[field], row, rowIndex);
					if(txt == 0){
						table.push(txt);
					}else{
						table.push(txt.replace(/(<([^>]+)>)/ig,""));
					}
					
				} else {
					table.push(row[field]);
				}
				table.push("]]></cell>");
			}
		}
		table.push("</row>");
	}
	table.push("</rows>");

	// as long dash in post converts to some special symbols we need to convert it to simple dash by replacing using unicode \u2013
	var _xml = encodeURIComponent(table.join('').replace("\u2013", "-"));

	var url = base + '/excel';
	var win = window.open("", "_blank");
	win.document.open();
	win.document.write("<html><body>");
	win.document.write('<form id="myform" method="post" action="'+url+'" accept-charset="utf-8" enctype="text/html"><input type="hidden" name="grid_xml" id="grid_xml"/> </form>');
	win.document.getElementById("grid_xml").value = _xml;
	win.document.write("</body></html>");
	win.document.getElementById("myform").submit();
	win.document.close();
}

function showQueryDialog(options){
	var opts = options || {};
	var qid = opts.qid || 'qid';
	var dlg = $('#dlg-query-' + qid);
	if (!dlg.length){
		dlg = $('<div id="dlg-query-'+qid+'"></div>').appendTo('body');
		dlg.dialog({
			title:opts.title||'高级查询',
			width:opts.width||400,
			height:opts.height||300,
			closed:true,
			modal:true,
			href:opts.form,
			buttons:[{
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					dlg.dialog('close');
					var param = {};
					dlg.find('#'+qid+' .query').each(function(){
						var name = $(this).attr('name');
						var val = $(this).val();
						if ($(this).hasClass('easyui-datebox')){
							name = $(this).attr('comboname');
							val = $(this).datebox('getValue');
						} else if ($(this).hasClass('easyui-datetimebox')){
							name = $(this).attr('comboname');
							val = $(this).datetimebox('getValue');
						}  else if ($(this).hasClass('easyui-combogrid')){
							name = $(this).attr('comboname');
							val = $(this).combogrid('getValues').join(',');
						} else if ($(this).hasClass('easyui-combobox')){
							name = $(this).attr('comboname');
							val = $(this).combobox('getValues').join(',');
						} else if ($(this).hasClass('easyui-combotree')){
							name = $(this).attr('comboname');
							val = $(this).combotree('getValues').join(',');
						}
						param[name] = val;
					});
					opts.callback(param);
				}
			},{
				text:'重置',
				iconCls:'icon-undo',
				handler:function(){
					$('.query-form').form('reset');
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){dlg.dialog('close');}
			}]
		});
	}
	dlg.dialog('open');
}
WX.ns('WX.pub');
WX.pub.showResult = function(result, onSuccess, onFail){
	if(result.success){
		if(onSuccess){
			onSuccess.call(this, result);
		}
		$.messager.show({
			title   : '<span class="green">操作成功</span>',
			msg     : result.msg || '操作成功',
			timeout : 3000,
			showType: 'fade'
		});
	}else{
		if(onFail){
			onFail.call(this, result);
		}
		$.messager.show({
			title   :'<span class="red">操作失败</span>',
			msg     : result.msg || '操作失败',
			timeout : 0
		});
	}
};
WX.ns('WX.pub.formatter');
WX.pub.formatter.pre = function(value,row,index){
	return '<pre>' + value + '</pre>';
}