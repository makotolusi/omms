/**
 * $('#dg').wxgrid({
 *   autoToolbar:true  是否自动生成常见的按钮
 *   addUrl:'',        增加用的地址
 *   updateUrl:'',     修改用的地址
 *   delUrl:'',        删除用的地址
 *   lookUrl:'',       查看用的地址
 *   editDlg:'#dlg-xx' 编辑对话框
 *   editDlgOpts:{}    编辑对话框的配置，可选
 *   editForm:'#fm-xx' 编辑数据的Form
 *   searcher:'#s-xx'  绑定的查询器
 *   xtoolbar:         要屏蔽的默认表格工具类按钮ID数组
 *   toolbarx:         要扩展的表格工具类按钮数组
 *   xdlgtoolbar:      要屏蔽的默认对话框工具类按钮ID数组
 *   dlgtoolbarx:      要扩展的对话框工具类按钮数组
 *   preValidate:func  数据验证成功前执行的回调
 *   postValidate:func 数据验证成功后执行的回调
 *   onSuccess:func    提交数据成功后执行的回调
 *   onFail:func       提交数据失败后执行的回调
 * });
 */
(function($){
	function buildGrid(target, options){
		var wxgid = target.id;
		var opts = $.extend({}, {
			idField      : 'id',
			sortName     : 'id',
			sortOrder    : 'desc',
			pagination   : true,//bug页面中必须有这一项
			fit          : true,
			fitColumns   : true,
			autoRowHeight: false,
			nowrap       : false,//default:true
			singleSelect : true,
			rownumbers   : true,
			border       : false,
			pageSize     : 20,
			pageList     : [10,20,30,40,50,100,200],
			onDblClickRow:function(){
				$(target).wxgrid('look');
			},
			onHeaderContextMenu: function(e, field){
				WX.defaultDataGridHeaderContextMenu(wxgid, e, field);
			},
			toolbar : [
				{
					id:wxgid+'-look',
					text:'查看',
					iconCls:'icon-dglook',
					handler:function(){ $(target).wxgrid('look');}
				},{
					id:wxgid+'-query',
					text:'查询',
					iconCls:'icon-search',
					handler:function(){ $(target).wxgrid('query');}
				},{
					id:wxgid+'-excel',
					text:'导出',
					iconCls:'icon-excel',
					handler:function(){ excel('#'+wxgid);}
				},{
					id:wxgid+'-add',
					text:'新增',
					iconCls:'icon-dgadd',
					handler:function(){ $(target).wxgrid('add'); }
				},{
					id:wxgid+'-update',
					text:'修改',
					iconCls:'icon-dgedit',
					handler:function(){ $(target).wxgrid('update');}
				},{
					id:wxgid+'-del',
					text:'删除',
					iconCls:'icon-dgdelete',
					handler:function(){ $(target).wxgrid('del');}
				}
			]
		}, options);
		if(options.autoToolbar==undefined || options.autoToolbar){
			opts.toolbar = buildToolbar(opts.toolbar, options.xtoolbar, options.toolbarx);
		}else{
			opts.toolbar = options.toolbar;
		}
		$(target).datagrid(opts);
		var searcher = options.searcher;
		if(searcher){
			var sopts = $(searcher).searchbox().searchbox('options');
			if(!sopts.searcher || (sopts.searcher.toString().length < 25)){
				sopts = $.extend({},sopts,{searcher : function(value, name){
					$(target).wxgrid('load', eval("({" + name + " : '" + value + "'})"));
				}});
				$(searcher).searchbox(sopts);
			}
		}
	}

	function buildToolbar(toolbar, xtoolbar, toolbarx){
		if(!toolbar) toolbar = [];
		if(toolbar && xtoolbar && typeof(toolbar)=='object'){
			var len = toolbar.length;
			for(var i=len-1; i>=0; i--){
				var idx = $.inArray(toolbar[i].id, xtoolbar);
				if(idx >= 0) toolbar.removeAt(i);
			}
		}
		if(toolbar && toolbarx){
			toolbar = $.merge(toolbar,toolbarx);
		}
		return toolbar;
	}
	
	function getDlgButton(wxgrid){
		return [
			{
				id:wxgrid+'-dlg-save1',
				text:'保存',
				iconCls:'icon-save1',
				handler:function(){
					$('#'+wxgrid).wxgrid('submitForm',1);
				}
			}
//			{
//				id:wxgrid+'-dlg-save1',
//				text:'保存并关闭',
//				iconCls:'icon-save1',
//				handler:function(){
//					$('#'+wxgrid).wxgrid('submitForm',1);
//				}
//			},{
//				id:wxgrid+'-dlg-save2',
//				text:'保存并编辑',
//				iconCls:'icon-save2',
//				handler:function(){
//					$('#'+wxgrid).wxgrid('submitForm',2);
//				}
//			},{
//				id:wxgrid+'-dlg-save3',
//				text:'保存并新建',
//				iconCls:'icon-save3',
//				handler:function(){
//					$('#'+wxgrid).wxgrid('submitForm',3);
//				}
//			}
		];
	}


	
	var methods = {
		add: function(jq, param){
			return jq.each(function(){
				var editDlgOpts = null;
				var opts = $(this).datagrid('options');
				var _editDlgOpts = opts.editDlgOpts || {};
				if(!editDlgOpts){
					editDlgOpts = $.extend({},
						$(opts.editDlg).dialog('options'), _editDlgOpts,
						{toolbar:buildToolbar(getDlgButton(opts.id), opts.xdlgtoolbar, opts.dlgtoolbarx)}
					);
				}
				opts.editForm
				$(opts.editDlg).dialog(editDlgOpts).dialog('open').dialog('refresh', opts.addUrl);
			});
		},//add
		addauth: function(jq, param){
			return jq.each(function(){
				var editDlgOpts = null;
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					var _editDlgOpts = opts.editDlgOpts || {};
					if(!editDlgOpts){
						editDlgOpts = $.extend({},
							$(opts.editDlg).dialog('options'), _editDlgOpts,
							{toolbar:buildToolbar(getDlgButton(opts.id), opts.xdlgtoolbar, opts.dlgtoolbarx)}
						);
					}
					$(opts.editDlg).dialog(editDlgOpts).dialog('open').dialog('refresh', opts.addAuthUrl+'/'+row.id);
				} else {
					$.messager.show({
						title:'<span class="blue">提示</span>',
						msg:'请先<span class="blue">选择记录</span>后再打开。'
					});
				}
			});
		},//add
		addguide: function(jq, param){
			return jq.each(function(){
				var editDlgOpts = null;
				var opts = $(this).datagrid('options');
				var _editDlgOpts = opts.editDlgOpts || {};
				if(!editDlgOpts){

				}
				$(opts.editDlg).dialog(editDlgOpts).dialog('open').dialog('refresh', opts.addGuide);
			});

		},//addguide
		update: function(jq, param){
			return jq.each(function(){
				var editDlgOpts = null;
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					var _editDlgOpts = opts.editDlgOpts || {};
					if(!editDlgOpts){
						editDlgOpts = $.extend({},
							$(opts.editDlg).dialog('options'), _editDlgOpts,
							{toolbar:buildToolbar(getDlgButton(opts.id), opts.xdlgtoolbar, opts.dlgtoolbarx)}
						);
					}
					$(opts.editDlg).dialog(editDlgOpts).dialog('open').dialog('refresh', opts.updateUrl+'/'+row.id);
				} else {
					$.messager.show({
						title:'<span class="blue">提示</span>',
						msg:'请先<span class="blue">选择记录</span>后再打开。'
					});
				}
			});
		},//update
		query: function(jq, param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var dg = $(this);
				showQueryDialog({
					qid  :opts.query.qid,
					title:opts.query.title,
					width:opts.query.width,
					height:opts.query.height,
					form:opts.query.form,
					callback:function(data){
						dg.datagrid('load', data);
						if (opts.query.callback){
							opts.query.callback();
						}
					}
				});
			});
		},//query
		del: function(jq, param){
			return jq.each(function(){
				var dg = $(this);
				var opts = dg.datagrid('options');
				var row = dg.datagrid('getSelected');
				if (row){
					$.messager.confirm('<span class="red">警告</span>','是否真的<span class="red">删除</span>该记录？',function(r){
						if (r){
							$.delete_(opts.delUrl+'/'+row.id, {}, function(result){
								if (result.success){
									dg.datagrid('reload');
								} else {
									$.messager.show({
										title:'<span class="red">警告</span>',
										msg:result.msg
									});
								}
							},'json');
						}
					});
				} else {
					$.messager.show({
						title:'<span class="red">提示</span>',
						msg:'请先<span class="red">选择记录</span>后再进行删除。'
					});
				}
			});
		},//del
		look: function(jq, param){
			return jq.each(function(){
				var viewDlgOpts = null;
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					if(opts.editDlg){
						if(opts.editDlg.indexOf(0)=='#'){
							var dlgOpts = $(opts.editDlg).dialog('options');
							viewDlgOpts = $.extend({},dlgOpts,{toolbar:[]});
							$(opts.editDlg).dialog(viewDlgOpts).dialog('open').dialog('refresh', opts.lookUrl+'/'+row[opts.idField]);
						}else{
							window.open(opts.lookUrl+'/'+row[opts.idField]);
						}
					}
				} else {
					$.messager.show({
						title:'<span class="blue">提示</span>',
						msg:'请先<span class="blue">选择记录</span>后再查看。或<span class="red">双击</span>某条记录进行查看。'
					});
				}
			});
		},//look
		titleLook: function(jq, param){
			return jq.each(function(){
				var viewDlgOpts = null;
				var opts = $(this).datagrid('options');
				var row = $(this).datagrid('getSelected');
				if (row){
					if(opts.editDlg){
						if(opts.editDlg.indexOf(0)=='#'){
							var dlgOpts = $(opts.editDlg).dialog('options');
							viewDlgOpts = $.extend({},dlgOpts,{toolbar:[]});
							$(opts.editDlg).dialog(viewDlgOpts).dialog('open').dialog('refresh', opts.titleLookUrl+'/'+row[opts.idField]);
						}else{
							window.open(opts.titleLookUrl+'/'+row[opts.idField]);
						}
					}
				} else {
					$.messager.show({
						title:'<span class="blue">提示</span>',
						msg:'请先<span class="blue">选择记录</span>后再查看。或<span class="red">双击</span>某条记录进行查看。'
					});
				}
			});
		},//look
		submitForm : function(jq, param){
			var action = null;
			if(param){
				if(typeof(param)=='string' || typeof(param)=='number'){
					action = param;
				}else if(typeof(param)=='object' && param.action){
					action = param.action;
				}
			}else{
				action = 1;
				param = {};
			}
			return jq.each(function(){
				var dg = $(this);
				var opts = dg.datagrid('options');
				if(opts.preValidate){
					opts.preValidate();
				}
				if(param.preValidate){
					param.preValidate();
				}
				$(opts.editForm).form('submit', {
					onSubmit:function(){
						WX.kesync();
						var isValid = $(this).form('validate');
						if (isValid){
							$.messager.progress({text:'正在提交数据，请稍候...'});
							if(opts.postValidate){
								opts.postValidate();
							}
							if(param.postValidate){
								param.postValidate();
							}
						}
						return isValid;
					},
					success:function(result){
						$.messager.progress('close');

						if(result != null && result.isJSON()) {
							result = $.parseJSON(result);
						} else {
							$.messager.show({title : '<span class="red">警告</span>', msg : result});
							return false;
						}
						var msg = result.msg || result.detailMessage || '保存数据发生错误';
						if (result.success){
							if(opts.onSuccess){
								opts.onSuccess(result);
							}
							if(param.onSuccess){
								param.onSuccess(result);
							}
							$.messager.show({
								title   : '<span class="green">保存成功</span>',
								msg     : result.msg || '数据保存成功',
								timeout : 3000,
								showType: 'fade'
							});
							dg.datagrid('reload');
							if(1==action){//保存关闭
								$(opts.editDlg).dialog('close');
							}else if(2==action){//保存编辑
								$(opts.editDlg).dialog('refresh', opts.updateUrl+'/'+result.attr.id);
							}else if(3==action){//保存新建
								$(opts.editDlg).dialog('refresh', opts.addUrl);
							}
						} else {
							if(opts.onFail){
								opts.onFail(result);
							}
							if(param.onFail){
								param.onFail(result);
							}
							$.messager.show({
								title   :'<span class="red">保存错误</span>',
								msg     : msg,
								timeout : 10000
							});
						}
					}
				});
			});
		}//submitForm
	};
	$.fn.wxgrid = function(options, param){
		if (typeof options == 'string'){
			var method = methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.datagrid(options, param);
			}
		}
		
		options = options || {};
		return this.each(function(){
			buildGrid(this, options);
		});
	};
})(jQuery);