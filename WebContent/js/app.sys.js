WX.ns('WX.sys');

WX.sys.changePosition = function(record){
	var pid = record.id;
	var pname = '';
	if(pid.indexOf('PS-')==0){
		pid = pid.substr(3);
		pname = record.attributes.fullName;
	}else{
		pname = record.name;
	}
	$.messager.progress({
		title: '切换岗位',
		msg  : '正在将岗位切换为：' + pname
	});
	$.post(base + '/position',{positionId : pid, positionName : pname}, function(result){
		if(result.success){
			document.location.replace(base + '/index');
		}else{
			$.messager.alert('岗位切换失败','岗位切换失败','error');
			$.messager.progress('close');
		}
	},'json');
}

/**
 * tableId 附件表绑定的ID
 * attasId 附件表对应主记录中的附件ID记录字段
 * 注意：attasId的值在增加或删除附件后直接更新，但需要手动保存到主记录中去
 */
var IINFO;
WX.sys.fileattas = function(tableId, attasId, editable){
	var url = base + '/sys/file/attas/';
	if($(attasId).val()){
		url += $(attasId).val();
	}

	var _toolbar = [{
			iconCls: 'icon-add',
			text : '增加附件',
			handler: function(){
				var dg = $(tableId);
				var now = (new Date()).getTime();
				var html = [];
				html.push('<form action="'+base+'/sys/file/xupload/file'+'" id="form-sys-fileupload-'+now+'" method="post" enctype="multipart/form-data">');
				html.push('<table width="100%">');
				html.push('<tr>');
				html.push('<td>上传进度</td>');
				html.push('<td><div id="progres-'+now+'" style="width:500px;"></div></td>');
				html.push('</tr>');
				html.push('<tr>');
				html.push('<td colspan="2"><div id="info-'+now+'" style="width:500px;text-align:center"></div></td>');
				html.push('</tr>');
//				html.push('<tr>');
//				html.push('<td>文件说明</td>');
//				html.push('<td><input type="text" name="notes" style="width:500px"/></td>');
//				html.push('</tr>');
				html.push('<tr>');
				html.push('<td nowrap>选择文件</td>');
				html.push('<td><div class="div-sys-fileupload"><input type="file" name="file" style="width:500px"/></div></td>');
				html.push('</tr>');
				html.push('</table>');
				html.push('</form>');
				var dialogContent = html.join('');
				
				var uploadDialog = $('<div></div>').dialog({
					width : 600,
					height : 350,
					title : '文件上传',
					modal : true,
					cache : false,
					content : dialogContent,
					toolbar : [{
						id   :  'bt-sys-file-add-'+now,
						text : '增加',
						iconCls : 'icon-add',
						handler : function(){
							var files = $('#form-sys-fileupload-'+now + ' input[type=file]');
							if(files.length < 10){
								files.last().after('<div class="div-sys-fileupload"><input type="file" name="file" style="width:500px"/></div>');
							}else{
								$.messager.alert('错误','一次最多上传10个文件！');
							}
						}
					},{
						id   :  'bt-sys-file-remove-'+now,
						text : '减少',
						iconCls : 'icon-remove',
						handler : function(){
							var fileDivs = $('#form-sys-fileupload-'+now + ' div.div-sys-fileupload');
							if(fileDivs.length > 1){
								fileDivs.last().remove();
							}else{
								$.messager.alert('错误','至少要上传一个文件！');
							}
						}
					},{text :'|'},{
						id   :  'bt-sys-file-empty-'+now,
						text : '清空',
						iconCls : 'icon-bin',
						handler : function(){
							$('#form-sys-fileupload-'+now)[0].reset();
						}
					},{text :'|'},{
						id   :  'bt-sys-file-upload-'+now,
						text : '上传',
						iconCls : 'icon-upload',
						handler : function(){
							var hasFile = false;
							$.each($('#form-sys-fileupload-'+now + ' input[type=file]'), function(i, f){
								f = $(f);
								if(f.val()){
									hasFile = true;
								}
							});
							if(!hasFile){
								$.messager.alert('错误','请至少选择一个文件再上传！');
								return;
							}
							$('#bt-sys-file-add-'+now).linkbutton('disable');
							$('#bt-sys-file-remove-'+now).linkbutton('disable');
							$('#bt-sys-file-empty-'+now).linkbutton('disable');
							$('#bt-sys-file-upload-'+now).linkbutton('disable');
							$('#bt-sys-file-stop-'+now).linkbutton('enable');
							$('#tr-progres-'+now).css('display','block');
							$('#progres-'+now).progressbar({value:0});
							checkInfo();
							var startTime = now;
							function checkInfo() {
								// 清除回调
								if (IINFO) {
									window.clearInterval(IINFO);
									IINFO = 0;
								}
								$.get(base+'/sys/file/info',{},function(data){
									if (data && data.sum){
										var value = 0;
										var nowTime = (new Date()).getTime();
										value = data.current * 100 / data.sum;
										if (value > 99)
											value = 99;
										value = Math.round(value);
										var info = '文件大小：' + Math.round(data.sum/1024) + 'KB　　已上传：' + Math.round(data.current/1024) + 'KB　　速度：'  + Math.round(data.current/(nowTime-startTime)) + 'K/s';
										$('#progres-'+now).progressbar('setValue', value);
										$('#info-'+now).text(info);
										IINFO = window.setInterval(checkInfo, 500);
									}
								},'json');
							}
							$('#form-sys-fileupload-'+now).form('submit',{
								success:function(result){//appendRow
									window.clearInterval(IINFO);
									uploadDialog.dialog('close');
									result = $.parseJSON(result);
									if(result.success){
										$.each(result.data, function(i,row){
											dg.datagrid('appendRow', row);
										});
										updateAttaIdsValue();
										$.messager.show({
											title   : '<span class="green">附件上传成功</span>',
											msg     : result.msg+'保存主记录后附件列表生效。',
											timeout : 3000,
											showType: 'fade'
										});
									}else{
										$.messager.alert('附件上传失败',result.msg || '附件上传失败');
									}
								}
							});
						}
					},{
						id   :  'bt-sys-file-stop-'+now,
						text : '停止',
						disabled : true,
						iconCls : 'icon-stop',
						handler : function(){
							$.post(base+'/sys/file/stop',{},function(result){
								window.clearInterval(IINFO);
								if(result.success){
									uploadDialog.dialog('close');
									$.messager.show({
										title   : '<span class="green">上传停止成功</span>',
										msg     : result.msg || '上传停止成功',
										timeout : 3000,
										showType: 'fade'
									});
								}else{
									$.messager.alert('上传停止失败',result.msg || '上传停止失败');
								}
							},'json');
						}
					}]
				});
			}
		},{
			iconCls: 'icon-remove',
			text : '删除附件',
			handler: function(){
				$.messager.confirm('<span class="red">警告</span>','附件将直接被删除，是否继续？',function(r){
					var dg = $(tableId);
					var rows = dg.datagrid('getChecked');
					if(rows.length==0){
						$.messager.show({
							title:'<span class="red">请先选择要删除的附件</span>',
							msg: '请先选择要删除的附件'
						});
						return;
					}
					var ids = [];
					$.each(rows, function(i,row){
						ids.push(row.id);
					});
					$.post(base + '/sys/file/xdelete',
							{attaIds : ids.join(',')},
							function(data){
								if(data.success){
									$.each(rows, function(i,row){
										var index = dg.datagrid('getRowIndex', row);
										dg.datagrid('deleteRow', index);
									});
									updateAttaIdsValue();
									$.messager.show({
										title   : '<span class="green">删除附件成功</span>',
										msg     : '删除附件成功，请保存主记录来更新附件列表。',
										timeout : 3000,
										showType: 'fade'
									});
								}else{
									$.messager.show({
										title:'<span class="red">删除附件失败</span>',
										msg: '删除附件失败'
									});
								}
							},
							'json'
						);
				});
			}
		}];

	var _options = {
		url : url,
		fitColumns:true,
		fit:false,
		singleSelect:false,
		columns:[[
			{title:'选择', checkbox:true},
			{field:'id',         title:'ID',         width:30,  hidden:true},
			{field:'fileType',   title:'fileType',   width:50,  hidden:true},
			{field:'rawName',    title:'附件名称',     width:200},
			{field:'notes',      title:'附件描述',     width:400,  hidden:true},
			{field:'length',     title:'附件大小',     width:50,  align:'right'},
			{field:'fileSize',   title:'附件大小',     width:50,  hidden:true},
			{field:'filePath',   title:'filePath',   width:300, hidden:true},
			{field:'extName',    title:'extName',    width:50,  hidden:true},
			{field:'mime',       title:'mime',       width:50,  hidden:true},
			{field:'entityName', title:'entityName', width:50,  hidden:true},
			{field:'entityId',   title:'entityId',   width:50,  hidden:true},
			{field:'userId',     title:'userId',     width:50,  hidden:true},
			{field:'userName',   title:'userName',   width:50,  hidden:true},
			{field:'onTime',     title:'onTime',     width:50,  hidden:true},
			{field:'_down',     title:'下载',     width:50,  halign:'center', align:'center',
				formatter: function(value,row,index){
					return '<a target="_blank" href="' + base + '/sys/file/download/' + row.id + '">下载</a>';
				}
			}
	      ]],
		onDblClickRow : function(index, row){
			window.open(base + '/sys/file/download/' + row.id);
		}
	 };

	var _fullOptios = _options;

	if(editable){
		_fullOptios = $.extend({}, _options, {toolbar: _toolbar});
	}

	$(tableId).datagrid(_fullOptios);

	function updateAttaIdsValue(){
		var dg = $(tableId);
		var rows = dg.datagrid('getRows');
		var ids = [];
		$.each(rows, function(i,row){
			ids.push(row.id);
		});
		$(attasId).val(ids.join(','));
	}
};

WX.sys.openChpwd = function(){
	$('#dlg-chpwd').dialog('open');
};

WX.sys.chpwd = function(){
	$('#fm-chpwd').form('submit', {
		onSubmit:function(){
			if(!$('#chpwd_old_password').val()){
				$.messager.alert('请输入旧密码','请输入旧密码!');
				$('#chpwd_old_password').focus();
				return false;
			}
			if(!$('#chpwd_new_password').val()){
				$.messager.alert('请输入新密码','请输入新密码!');
				$('#chpwd_new_password').focus();
				return false;
			}
			if(!$('#chpwd_re_password').val()){
				$.messager.alert('请输入确认密码','请输入确认密码!');
				$('#chpwd_re_password').focus();
				return false;
			}
			if($('#chpwd_new_password').val() != $('#chpwd_re_password').val()){
				$.messager.alert('密码不一致','新密码与确认密码不一致!');
				$('#chpwd_re_password').focus();
				return false;
			}
			$.messager.progress({text:'正在修改密码，请稍候...'});
		},
		success:function(result){
			$.messager.progress('close');
			if(result != null && result.isJSON()) {
				result = $.parseJSON(result);
			}
			if (result.success){
				$.messager.show({
					title   : '<span class="green">密码修改成功</span>',
					msg     : result.msg,
					timeout : 3000,
					showType: 'fade'
				});
				WX.sys.closeChpwd();
			}else{
				$.messager.alert('密码修改失败',result.msg);
			}
		}
	});
};

WX.sys.closeChpwd = function(){
	$('#dlg-chpwd').dialog('close');
	$('#fm-chpwd').form('clear');
};

WX.ns('WX.sys.admin');
WX.sys.admin.formatter = function(value,row,index){
	return value ? "是" : "否";
};
WX.sys.admin.styler = function(value,row,index){
	return value ? "color:red" : "";
};

WX.ns('WX.sys.enable');
WX.sys.enable.formatter = function(value,row,index){
	return value ? "是" : "否";
};
WX.sys.enable.styler = function(value,row,index){
	return value ? "color:green" : "color:red";
};

WX.ns('WX.sys.gender');
WX.sys.gender.formatter = function(value,row,index){
	return value ? "男" : "女";
};
WX.sys.gender.styler = function(value,row,index){
	return value ? "color:#800080" : "color:#ff00ff";
};

WX.ns('WX.sys.dict');
WX.sys.dict.reload = function(callback){
	$.messager.progress({text:'正在刷新字典缓存，请稍候...'});
	$.get(base+'/sys/dict/reload',null,function(data){
		$.messager.progress('close');
		$.messager.show({
			title   : '<span class="green">成功刷新字典缓存</span>',
			msg     : '成功刷新字典缓存',
			timeout : 3000,
			showType: 'fade'
		});
		if(callback) callback();
	});
};

WX.ns('WX.sys.dict.enable');
WX.sys.dict.enable.styler = function(value,row,index){
	return value=='1' ? "color:green" : "color:red";
};


WX.ns('WX.sys.org');
WX.sys.org.reload = function(callback){
	$.messager.progress({text:'正在刷新机构，请稍候...'});
	$.get(base+'/sys/org/reload',null,function(data){
		$.messager.progress('close');
		$.messager.show({
			title   : '<span class="green">成功刷新机构</span>',
			msg     : '成功刷新机构',
			timeout : 3000,
			showType: 'fade'
		});
		if(callback) callback();
	});
};

WX.sys.org.add = function(){
	var node = $('#tree_org').tree('getSelected');
	var _title = '增加机构';
	var _pid = null;

	if(node){
		 _title += '：上级机构【<span class="red">'+node.text+'</span>】';
		 _pid = node.id;
	}else{
		 _title += '：<span class="red">顶级机构</span>';
	}
	$.messager.prompt(_title,'请输入新机构的名称',function(r){
		if(!r){
			return false;
		}
		var _org = null;
		if(_pid){
			_org = {name : r, pid : _pid, orders : 0};
		}else{
			_org = {name : r, orders : 0};
		}
		$.post(base + '/sys/org/add', _org, function(result){
			if (result.success){
				$.messager.show({
					title:'<span class="green">提示</span>',
					msg:result.msg || '添加机构成功'
				});
				$('#tree_org').tree('reload');
			} else {
				$.messager.show({
					title:'<span class="red">警告</span>',
					msg:result.msg || '添加机构失败'
				});
			}
		},'json');
	});
};


WX.sys.org.rename = function(node){
	var _title = '修改机构名称';
	var _pid = node.id;
	$.messager.prompt(_title,'当前机构名称为"'+node.text+'"请输入机构新名称',function(r){
		if(!r){
			return false;
		}
		var _org = {name : r, id : _pid};
		$.post(base + '/sys/org/rename', _org, function(result){
			if (result.success){
				$.messager.show({
					title:'<span class="green">提示</span>',
					msg:result.msg || '修改名称成功'
				});
				$('#tree_org').tree('reload');
			} else {
				$.messager.show({
					title:'<span class="red">警告</span>',
					msg:result.msg || '修改名称失败'
				});
			}
		},'json');
	});
};

WX.sys.org.del = function(){
	var node = $('#tree_org').tree('getSelected');
	if(node){
		$.messager.confirm('<span class="red">严重警告！！！</span>','机构删除后不可恢复，是否真的<span class="red">删除</span>【'+node.text+'】？',function(r){
			if (r){
				$.delete_(base + '/sys/org/delete/'+node.id, {}, function(result){
					if (result.success){
						$.messager.show({
							title:'<span class="green">提示</span>',
							msg:result.msg || '删除机构成功'
						});
						$('#tree_org').tree('reload');
					} else {
						$.messager.show({
							title:'<span class="red">警告</span>',
							msg:result.msg || '删除机构失败'
						});
					}
				},'json');
			}
		});
	}
};
WX.sys.org.move = function(target, source, point){
	var targetId = $(target).attr('node-id');
	var targetName = $(target).text();
	var sourceId = source.id;
	var sourceName = source.name;

	$.post(base + '/sys/org/move',{sourceId : sourceId, targetId : targetId, point : point},function(result){
		if (result.success){
			$.messager.show({
				title:'<span class="green">提示</span>',
				msg:result.msg || '移动机构成功'
			});
		} else {
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:result.msg || '移动机构失败'
			});
			$('#tree_org').tree('reload');
		}
	},'json');
};

WX.ns('WX.sys.permission');
WX.sys.permission.moduleChange = function(rec){
	$('#dg-permission').edatagrid('reload',{in_module : $(this).combobox('getValues').join(',')});
};

WX.ns('WX.sys.role');
WX.sys.role.openAddPermissions = function(rid){
	$('#dlg-role-permissions'+rid).dialog('open');
};

WX.sys.role.addPermissions = function(rid){
	var rows = $('#dg-all-permissions'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要增加的权限</span>',
			msg: '请先选择要增加的权限'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/role/addPermissions/' + rid,
		{permissionIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dlg-role-permissions'+rid).dialog('close');
				$('#dg-all-permissions'+rid).datagrid('unselectAll').datagrid('clearSelections');
				$('#dg-role-permissions'+rid).datagrid('reload');
				$.messager.show({
					title   : '<span class="green">增加权限成功</span>',
					msg     : '增加权限成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">增加权限失败</span>',
					msg: '增加权限失败'
				});
			}
		},
		'json'
	);
};

WX.sys.role.delPermissions = function(rid){
	var rows = $('#dg-role-permissions'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要删除的权限</span>',
			msg: '请先选择要删除的权限'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/role/delPermissions/' + rid,
		{permissionIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dg-role-permissions'+rid).datagrid('unselectAll').datagrid('clearSelections').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">删除权限成功</span>',
					msg     : '删除权限成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">删除权限失败</span>',
					msg: '删除权限失败'
				});
			}
		},
		'json'
	);
};

WX.sys.role.openAddUsers = function(rid){
	$('#dlg-role-users'+rid).dialog('open');
};

WX.sys.role.addUsers = function(rid){
	var rows = $('#dg-all-users'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要增加的用户</span>',
			msg: '请先选择要增加的用户'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/role/addUsers/' + rid,
		{userIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dlg-role-users'+rid).dialog('close');
				$('#dg-all-users'+rid).datagrid('unselectAll').datagrid('clearSelections');
				$('#dg-role-users'+rid).datagrid('reload');
				$.messager.show({
					title   : '<span class="green">增加用户成功</span>',
					msg     : '增加用户成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">增加用户失败</span>',
					msg: '增加用户失败'
				});
			}
		},
		'json'
	);
};

WX.sys.role.delUsers = function(rid){
	var rows = $('#dg-role-users'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要删除的用户</span>',
			msg: '请先选择要删除的用户'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/role/delUsers/' + rid,
		{userIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dg-role-users'+rid).datagrid('unselectAll').datagrid('clearSelections').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">删除用户成功</span>',
					msg     : '删除用户成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">删除用户失败</span>',
					msg: '删除用户失败'
				});
			}
		},
		'json'
	);
};

WX.sys.role.onBeforeCheck = function(node, checked){
	//TODO
}

WX.sys.role.openAddPositions = function(rid){
	$('#dlg-role-positions'+rid).dialog('open');
};

WX.sys.role.addPositions = function(rid){
	//FIXME
	var nodes = $('#tree-all-positions'+rid).tree('getChecked');
	if(nodes.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要被授予的岗位</span>',
			msg: '请先选择要被授予的岗位'
		});
		return;
	}
	var ids = [];
	var names = [];
	$.each(nodes, function(i, node){
		ids.push(node.attributes.positionId);
		names.push(node.attributes.fullName);
	});
	$.post(base + '/sys/auth/addPositions',
		{roleId : rid, positionIds : ids.join(','), positionNames : names.join(',')},
		function(data){
			if(data.success){
				$('#dlg-role-positions'+rid).dialog('close');
				$('#dg-all-positions'+rid).datagrid('unselectAll').datagrid('clearSelections');
				$('#dg-role-positions'+rid).datagrid('reload');
				$.messager.show({
					title   : '<span class="green">角色授予岗位成功</span>',
					msg     : '角色授予岗位成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">角色授予岗位失败</span>',
					msg: '角色授予岗位失败'
				});
			}
		},
		'json'
	);
};

WX.sys.role.delPositions = function(rid){
	var rows = $('#dg-role-positions'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要取消授权的角色</span>',
			msg: '请先选择要取消授权的角色'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, row){
		ids.push(row.positionId);
	});
	$.post(base + '/sys/auth/delPositions',
		{roleId : rid, positionIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dg-role-positions'+rid).datagrid('unselectAll').datagrid('clearSelections').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">取消授权成功</span>',
					msg     : '取消授权成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red"取消授权失败</span>',
					msg: '取消授权失败'
				});
			}
		},
		'json'
	);
};

WX.ns('WX.sys.user');
WX.sys.user.openAddRoles = function(rid){
	$('#dlg-user-roles'+rid).dialog('open');
};

WX.sys.user.addRoles = function(rid){
	var rows = $('#dg-all-roles'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要增加的角色</span>',
			msg: '请先选择要增加的角色'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/user/addRoles/' + rid,
		{roleIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dlg-user-roles'+rid).dialog('close');
				$('#dg-all-roles'+rid).datagrid('unselectAll').datagrid('clearSelections');
				$('#dg-user-roles'+rid).datagrid('reload');
				$.messager.show({
					title   : '<span class="green">增加角色成功</span>',
					msg     : '增加角色成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">增加角色失败</span>',
					msg: '增加角色失败'
				});
			}
		},
		'json'
	);
};

WX.sys.user.delRoles = function(rid){
	var rows = $('#dg-user-roles'+rid).datagrid('getChecked');
	if(rows.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要删除的角色</span>',
			msg: '请先选择要删除的角色'
		});
		return;
	}
	var ids = [];
	$.each(rows, function(i, v){
		ids.push(v.id);
	});
	$.post(base + '/sys/user/delRoles/' + rid,
		{roleIds : ids.join(',')},
		function(data){
			if(data.success){
				$('#dg-user-roles'+rid).datagrid('unselectAll').datagrid('clearSelections').datagrid('reload');
				$.messager.show({
					title   : '<span class="green">删除角色成功</span>',
					msg     : '删除角色成功',
					timeout : 3000,
					showType: 'fade'
				});
			}else{
				$.messager.show({
					title:'<span class="red">删除角色失败</span>',
					msg: '删除角色失败'
				});
			}
		},
		'json'
	);
};


WX.ns('WX.sys.station');
WX.sys.station.reload = function(callback){
	$.messager.progress({text:'正在刷新机构，请稍候...'});
	$.get(base+'/sys/station/reload',null,function(data){
		$.messager.progress('close');
		$.messager.show({
			title   : '<span class="green">成功刷新机构</span>',
			msg     : '成功刷新机构',
			timeout : 3000,
			showType: 'fade'
		});
		if(callback) callback();
	});
};

WX.sys.station.add = function(){
	var node = $('#tree_orgst').tree('getSelected');
	$('#dlg-service').dialog({
		cache : false,
		href : base+'/sys/station/add/'+node.id,
		toolbar:[{
			text:'保存',
			iconCls:'icon-add',
			handler:function(){
				var formAction = $('#fm-station').attr('action');
				var astation = $('#fm-station').form('toJson');
				var postData = {station : JSON.stringify(astation)};
				$.post(formAction, postData, function(result){
					if (result.success){
						$.messager.show({
							title:'<span class="green">提示</span>',
							msg:result.msg || '添加岗位成功'
						});
						WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
					} else {
						$.messager.show({
							title:'<span class="red">警告</span>',
							msg:result.msg || '添加岗位失败'
						});
					}
					
				},'json');
				$('#dlg-service').dialog('close');
			}
		}]
	}).dialog('open');
};

WX.sys.station.update = function(){
	var node = $('#tree_orgst').tree('getSelected');
	$('#dlg-service').dialog({
		cache : false,
		href : base+'/sys/station/update/'+node.id,
		toolbar:[{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				var formAction = $('#fm-station').attr('action');
				var astation = $('#fm-station').form('toJson');
				var postData = {station : JSON.stringify(astation)};
				$.post(formAction, postData, function(result){
					if (result.success){
						$.messager.show({
							title:'<span class="green">提示</span>',
							msg:result.msg || '修改岗位成功'
						});
						WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
					} else {
						$.messager.show({
							title:'<span class="red">警告</span>',
							msg:result.msg || '修改岗位失败'
						});
					}
					
				},'json');
				$('#dlg-service').dialog('close');
			}
		}]
	}).dialog('open');
};

WX.sys.station.view = function(){
	var node = $('#tree_orgst').tree('getSelected');
	$('#dlg-service').dialog('refresh');
	$('#dlg-service').dialog({
		cache : false,
		href : base+'/sys/station/view/'+node.id,
		toolbar:[]
	}).dialog('open');
};


WX.sys.station.rename = function(node){
	var _title = '修改岗位名称';
	var _pid = node.id;
	$.messager.prompt(_title,'当前岗位名称为"'+node.text+'"请输入岗位新名称',function(r){
		if(!r){
			return false;
		}
		var _org = {name : r, id : _pid};
		$.post(base + '/sys/station/rename', _org, function(result){
			if (result.success){
				$.messager.show({
					title:'<span class="green">提示</span>',
					msg:result.msg || '修改名称成功'
				});
				WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
			} else {
				$.messager.show({
					title:'<span class="red">警告</span>',
					msg:result.msg || '修改名称失败'
				});
			}
		},'json');
	});
};


WX.sys.station.del = function(){
	var node = $('#tree_orgst').tree('getSelected');
	if(node){
		$.messager.confirm('<span class="red">严重警告！！！</span>','机构删除后不可恢复，是否真的<span class="red">删除</span>【'+node.text+'】？',function(r){
			if (r){
				$.delete_(base + '/sys/station/delete/'+node.id, {}, function(result){
					if (result.success){
						$.messager.show({
							title:'<span class="green">提示</span>',
							msg:result.msg || '删除机构成功'
						});
						WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
					} else {
						$.messager.show({
							title:'<span class="red">警告</span>',
							msg:result.msg || '删除机构失败'
						});
					}
				},'json');
			}
		});
	}
};

WX.sys.station.move = function(target, source, point){
	var targetId = $(target).attr('node-id');
	var targetName = $(target).text();
	var sourceId = source.id;
	var sourceName = source.name;

	$.post(base + '/sys/station/move',{sourceId : sourceId, targetId : targetId, point : point},function(result){
		if (result.success){
			$.messager.show({
				title:'<span class="green">提示</span>',
				msg:result.msg || '移动岗位成功'
			});
			WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
		} else {
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:result.msg || '移动岗位失败'
			});
			WX.sys.station.reload(function(){$('#tree_orgst').tree('reload')});
		}
	},'json');
};
if(!WX.sys.money) WX.sys.money = {};

WX.sys.money.getmoney = function(value,row,index){
	if(null == value||value.length == 0||value == 0){
		return 0;
	};
    var str = value.toString();
    var length = str.length;//总长度
    var a = (length - 1) / 3;//要添加几个逗号
    var m = length % 3;//最前面几位
    m = m==0?3:m;
    var s = str.substr(0,m);//截取前面的字符串
    for(var i=1;i<=a;i++){
        s = s.concat(',');
        s = s.concat(str.substr(m+3*(i-1),3));
    }
	return s;
};