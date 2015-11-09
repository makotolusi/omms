if(!WX.app) WX.app = {};

if(!WX.app.proinfo) WX.app.proinfo = {};

WX.app.proinfo.sync = function(){
	var node = $('#ztjs-sso-projectTree').tree('getSelected');
	$.post('/wx/ztjs/ppms/projectinfo/sync/',{nodeId:node.id},
			function(data){
				if(data.success){
					$.messager.show({
						title   : '<span class="green">工程基础信息同步成功</span>',
						msg     : '工程基础信息同步成功。',
						timeout : 3000,
						showType: 'fade'
					});
					$("#dg-projectinfo").datagrid('reload');
				}else{
					$.messager.show({
						title   : '<span class="red">工程基础信息同步失败</span>',
						msg     : '工程基础信息同步失败。',
						timeout : 3000,
						showType: 'fade'
					});
				}
			},'json'
		);
};

WX.app.proinfo.formatterdate = function(val, row) {
    var date = new Date(val);
    return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
};

WX.app.proinfo.ppmsState = function(value,row,index){
	if(value=='01')
		return '<span class="green">在施</span>';
	else if(value=='02')
		return '<span class="blue">停工</span>';
	else if(value=='05')
		return '<span class="red">竣工未维保</span>';
	else if(value=='07')
		return '<span class="red"><b>竣工维保</b></span>';
	else
		return '未设置';
};

WX.app.proinfo.ssoState = function(value,row,index){
	if(value=='01')
		return "在施";
	else if(value=='02')
		return "停工-未结算";
	else if(value=='03')
		return "停工-已结算";
	else if(value=='04')
		return "停工-已封账";
	else if(value=='05')
		return "竣工-未结算";
	else if(value=='06')
		return "竣工-已结算";
	else
		return "竣工已封账";
};

WX.app.proinfo.isFocus = function(value,row,index){
	if(value == 1){
		return '<span class="red"><b>是</b></span>';
	}else{
		return '<span class="gray">否</span>';
	}
};

if(!WX.app.notice) WX.app.notice = {};

WX.app.notice.stateFormatter = function(value,row,index){
	if(value == 1){
		return '<span class="green">已提交</span>';
	}else{
		return '<span class="gray">草稿</span>';
	}
};

if(!WX.app.complaint) WX.app.complaint = {};

WX.app.complaint.reply = function(tableId, type){
	var pid = $('#complaint_id').val();
	var _options = {
		url : base+'/ztjs/ppms/complaintreply/reply/'+pid,
		fitColumns:true,
		fit:false,
		singleSelect:true,
		columns:[[
	          {field:'reply',    title:'回复内容',     width:500},
	          {field:'inputName',      title:'回复人',     width:50},
	          {field:'onTime',     title:'回复时间',     width:100},
	          {field:'state',     title:'状态',     width:50, formatter:function(val,rec){
	        	  if(val==0)
	        		  return '<span class="blue">待确认</span>';
	        	  else
	        		  return '<span class="green">已确认</span>';
	          }}
	      ]],
		onDblClickRow : function(index, row){
			$('#dlg-complaintreply-view').dialog(null).dialog('open').dialog('refresh', base+'/ztjs/ppms/complaintreply/view/'+row.id);
		},
		onSelect : function(index, row){
			$('#bt-reply-remove').linkbutton('disable');
			$('#bt-confirm-update').linkbutton('disable');
			if(row.state==0){
				$('#bt-reply-remove').linkbutton('enable');
				$('#bt-confirm-update').linkbutton('enable');
			}else if(row.state==9){
				$('#bt-reply-remove').linkbutton('enable');
			}
		}
	 };
	

	var _toolbar = [{
		id   :  'bt-reply-add',
		text : '添加',
		iconCls : 'icon-add',
		handler : function(){
			$('#reply').val('');
			$('#dlg-complaintreply-detail').dialog({
				cache : false,
				title : '投诉回复',
				toolbar : [{
					text : '保存',
					iconCls : 'icon-save',
					handler  :function(){
						$('#dg-complaintreply').wxgrid('submitForm',{
							onSuccess : function(result){
								$.messager.show({
									title   : '<span class="green">保存成功</span>',
									msg     : result.msg || '数据保存成功',
									timeout : 3000,
									showType: 'fade'
								});
								$(tableId).datagrid('reload');
								$('#dlg-complaintreply-detail').dialog('close');
							}
						});
					}
				}]
			}).dialog('open');
		}
	},{
		id   :  'bt-reply-remove',
		text : '删除',
		iconCls : 'icon-remove',
		handler : function(){
			$.messager.confirm('<span class="red">警告</span>','确认删除该条回复吗？',function(r){
				var dg = $(tableId);
				var rows = dg.datagrid('getSelected');
				if(rows.length==0){
					$.messager.show({
						title:'<span class="red">请先选择要删除的记录</span>',
						msg: '请先选择要删除的记录'
					});
					return;
				}
				$.delete_(base+'/ztjs/ppms/complaintreply/delete/'+rows.id,{},
						function(data){
							if(data.success){
								$.messager.show({
									title   : '<span class="green">删除记录成功</span>',
									msg     : '删除记录成功。',
									timeout : 3000,
									showType: 'fade'
								});
								$(tableId).datagrid('reload');
							}else{
								$.messager.show({
									title:'<span class="red">删除记录失败</span>',
									msg: '删除记录失败'
								});
							}
						},'json'
					);
			});
		}
	}
	];
	
	var _toolbar1 = [{
		id   :  'bt-confirm-update',
		text : '确认',
		iconCls : 'icon-edit',
		handler : function(){
			var editDlgOpts = null;
			var opts = $(tableId).datagrid('options');
			var row = $(tableId).datagrid('getSelected');
			if (row){
				var dlgtoolbarx = [
								{
									id:tableId+'-submit1',
									text:'保存',
									iconCls:'icon-save1',
									handler:function(){
										$("#fm-complaint-confirm").form('submit',{
									        url:base+'/ztjs/ppms/complaintreply/update',
									        success : function(result){
												$.messager.show({
													title   : '<span class="green">保存成功</span>',
													msg     : result.msg || '数据保存成功',
													timeout : 3000,
													showType: 'fade'
												});
												$(tableId).datagrid('reload');
												$('#dlg-complaint-confirm').dialog('close');
											}
										});
									}
			 					}];
				editDlgOpts = $.extend({},
					{toolbar:dlgtoolbarx}
				);
				$('#dlg-complaint-confirm').dialog(editDlgOpts).dialog('open').dialog('refresh', base+'/ztjs/ppms/complaint/confirm/'+row.id);
			} else {
				$.messager.show({
					title:'<span class="blue">提示</span>',
					msg:'请先<span class="blue">选择记录</span>后再打开。'
				});
			}
		}
	}
	];
	
	var _fullOptios = _options;

	switch(type){
		case 1:_fullOptios = $.extend({}, _options, {toolbar: _toolbar});break;
		case 2:_fullOptios = $.extend({}, _options, {toolbar: _toolbar1});break;
		default:break;
	}
		

	$(tableId).datagrid(_fullOptios);

};

WX.app.complaint.stateFormatter = function(value,row,index){
	if(value == 1){
		return '<span class="green">已回复</span>';
	}else if(value == 7){
		return '<span class="red">待回复</span>';
	}else if(value == 9){
		return '<span class="red"><b>重点关注</b></span>';
	}else{
		return '<span class="gray">草稿</span>';
	}
};

WX.app.complaint.dateValidate = function(){
	var content = $('#complaint-content').val();
	if(''==content||null==content){
		$.messager.show({
			title:'<span class="red">警告</span>',
			msg:'请上传附件'
		});
		return false;
	}else{
		$('#state').val('7');
		return true;
	}
};

if(!WX.app.planmain) WX.app.planmain = {};

WX.app.planmain.dlgOpenCheck = function(){
	if(isProjectSelected){
		$('#dg-plan-main-dlg-save3').linkbutton('enable');
		$('#dg-plan-main-submit3').linkbutton('enable');
	}else{
		$('#dg-plan-main-dlg-save3').linkbutton('disable');
		$('#dg-plan-main-submit3').linkbutton('disable');
	}
};

WX.app.planmain.stateFormatter = function(value,row,index){
	if(value == 1){
		return '<span class="green">已提交</span>';
	}else{
		return '<span class="gray">草稿</span>';
	}
};

WX.app.planmain.dateValidate = function(){
	var content = $('#plan-main-content').val();
	if(''==content||null==content){
		$.messager.show({
			title:'<span class="red">警告</span>',
			msg:'请上传附件'
		});
		return false;
	}else{
		$('#state').val('1');
		return true;
	}
};

if(!WX.app.planmajor) WX.app.planmajor = {};

WX.app.planmajor.dlgOpenCheck = function(){
	if(isProjectSelected){
		$('#dg-plan-major-dlg-save3').linkbutton('enable');
		$('#dg-plan-major-submit3').linkbutton('enable');
	}else{
		$('#dg-plan-major-dlg-save3').linkbutton('disable');
		$('#dg-plan-major-submit3').linkbutton('disable');
	}
};

WX.app.planmajor.stateFormatter = function(value,row,index){
	if(value == 3){
		return '<span class="green">消除预警</span>';
	}if(value == 9){
		return '<span class="red">预警</span>';
	}else if(value == 7){
		return '<span class="blue">滞后</span>';
	}else if(value == 1){
		return '<span class="green">已提交</span>';
	}else{
		return '<span class="gray">草稿</span>';
	}
};

WX.app.planmajor.flowStateFormatter = function(value,row,index){
	switch(value){
		case 'Init':return '<span class="green">未启动</span>';
		case 'Runing':return '<span class="blue">进行中</span>';
		case 'Accepted':return '<span class="green">同意</span>';
		case 'Denied':return '<span class="red">拒绝</span>';
		case 'Rejected':return '<span class="red">驳回</span>';
		case 'Canceled':return '<span class="gray">取消</span>';
	}
};

WX.app.planmajor.getLagDays = function(date){
	var plan = $('#planDate').datebox('getValue');
	var actual = $('#actualDate').datebox('getValue');
	if(null!=plan&&null!=actual){
		plan = plan.replace(/-/g,"/"); 
		actual = actual.replace(/-/g,"/"); 
		if(Date.parse(actual)-Date.parse(plan)>0){
			$('#lagDays').val((Date.parse(actual)-Date.parse(plan))/(1000*60*60*24));
		}else{
			$('#lagDays').val(0);
		}
	}else{
		$('#lagDays').val(0);
	}
};

WX.app.planmajor.dateValidate = function(){
	var actual = $('#actualDate').datebox('getValue');
	if(null==actual||''==actual){
		$.messager.show({
			title:'<span class="red">警告</span>',
			msg:'请填写实际完成时间'
		});
		$('#actualDate').focus();
		return false;
	}
	var d = $('#lagDays').val();
	if(d>0){
		var cause = $('#cause').val();
		if(null==cause||''==cause){
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:'滞后原因未填写'
			});
			$('#cause').focus();
			return false;
		}
		
		var affect = $('#affect').val();
		if(''==affect||null==affect){
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:'产生后果未填写'
			});
			$('#affect').focus();
			return false;
		}
		
		var measure = $('#measure').val();
		if(''==measure||null==measure){
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:'纠偏措施未填写'
			});
			$('#measure').focus();
			return false;
		}
		
		var coordination = $('#coordination').val();
		if(''==coordination||null==coordination){
			$.messager.show({
				title:'<span class="red">警告</span>',
				msg:'需要分公司协调事宜未填写'
			});
			$('#coordination').focus();
			return false;
		}

		if(d>=15){
			$('#state').val('9');
		}else{
			$('#state').val('7');
		}
		return true;
	}else{
		$('#state').val('1');
		return true;
	}
};


