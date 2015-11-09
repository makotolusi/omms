WX.ns('WX.omms');

WX.omms.fullQuery = function(wxId, titleId){
	$(wxId).datagrid('reload',{'ct_*' : $(titleId).val()});
};

WX.omms.fullKeyDown = function(e, wxId, titleId){
	var ev= window.event||e;
	if(ev.keyCode == 13){
		WX.omms.fullQuery(wxId, titleId);
	}
};

WX.omms.allolqQuery = function(){
	$('#dg-allolq').datagrid('reload',{'ct_*' : $('#s-allolq').val(), 'in_state' : $('#s-allolq-state').combobox('getValue')});
};

WX.omms.allolqKeyDown = function(e){
	var ev= window.event||e;
	if(ev.keyCode == 13){
		WX.omms.allolqQuery();
	}
};


WX.omms.titleQuery = function(wxId, titleId){
	$(wxId).datagrid('reload',{lk_thetitle : $(titleId).val()});
};

WX.omms.titleKeyDown = function(e, wxId, titleId){
	var ev= window.event||e;
	if(ev.keyCode == 13){
		WX.omms.titleQuery(wxId, titleId);
	}
};

WX.omms.textQuery = function(wxId, titleId){
	$(wxId).datagrid('reload',{lk_text : $(titleId).val()});
};

WX.omms.textKeyDown = function(e, wxId, titleId){
	var ev= window.event||e;
	if(ev.keyCode == 13){
		WX.omms.textQuery(wxId, titleId);
	}
};

WX.omms.positionNameQuery = function(wxId, titleId){
	$(wxId).datagrid('reload',{lk_positionName : $(titleId).val()});
};

WX.omms.positionNameKeyDown = function(e, wxId, titleId){
	var ev= window.event||e;
	if(ev.keyCode == 13){
		WX.omms.positionNameQuery(wxId, titleId);
	}
};

WX.ns('WX.omms.index');
WX.omms.index.onTabSelect = function(title,index){
//	var treeOptions = $('#infotree-index').tree('options');
//	if('信息系统导航'==title){
//		treeOptions.url = base + '/ztjs/omms/infotype/tree';
//	}else{
//		treeOptions.url = base + '/ztjs/omms/infosys/posinfo';
//	}
//	if('我的提问'!=title){
//		$('#infotree-index').tree(treeOptions);
//	}else{
//		var root = $('#infotree-index').tree('getRoot');
//		if(root){
//			$('#infotree-index').tree('remove', root.target);
//		}
//	}
};

WX.omms.index.onMyTabSelect = function(title,index){
	var treeOptions = $('#infotree-index').tree('options');
	if('所有提问'==title){
		wxGridId = '#dg-allolq';
		treeOptions.url = base + '/ztjs/omms/infotype/tree';
		$('#infotree-index').tree(treeOptions);
	}else{
		wxGridId = '#dg-myolq';
		var root = $('#infotree-index').tree('getRoot');
		if(root){
			$('#infotree-index').tree('remove', root.target);
		}
	}
};
var nid = "";
WX.omms.index.onTreeSelect = function(node){
	
	if(node.id.indexOf("type") >= 0)
		return;
	
	var dgOptions = $('#dg-queryapartment').wxgrid('options');
	dgOptions.url =  base + '/apartment/apartmentinfobytree/' + node.id;

	
	if(node.id == '56' || node.id == '57'){
		$('#dg-queryapartment').datagrid('hideColumn','paySubject');
		$('#dg-queryapartment').datagrid('hideColumn','projectName');
		$('#dg-queryapartment').datagrid('hideColumn','receivedAmount');
		$('#dg-queryapartment').datagrid('hideColumn','businessType');
		
		$('#dg-queryapartment').datagrid('showColumn','networkTypeText');
		$('#dg-queryapartment').datagrid('showColumn','apartmentNo');
		$('#dg-queryapartment').datagrid('showColumn','continueToPayDay');
		$('#dg-queryapartment').datagrid('showColumn','notes');
		$('#dg-queryapartment').datagrid('showColumn','businessTypeText');
		
		var col = $('#dg-queryapartment').datagrid('getColumnOption', 'networkTypeText');
		col.width = 80;
		
//		$('#dg-queryapartment').wxgrid(dgOptions);
		$('#dg-queryapartment').datagrid();
		$('#bDay').text('开通日期');
		$('#aName').text('小区名称');
		$('#rAmount').text('应收金额');
	}
	if(node.id == '80' || node.id == '79'){
		// 代理 自主运营
//		$('#dg-queryapartment').datagrid('showColumn','apartmentName');
		$('#dg-queryapartment').datagrid('showColumn','projectName');
		$('#dg-queryapartment').datagrid('showColumn','receivedAmount');
		$('#dg-queryapartment').datagrid('showColumn','notes');
		$('#dg-queryapartment').datagrid('showColumn','businessType');
		
		$('#dg-queryapartment').datagrid('hideColumn','businessTypeText');
		$('#dg-queryapartment').datagrid('hideColumn','paySubject');
		$('#dg-queryapartment').datagrid('hideColumn','networkTypeText');
		$('#dg-queryapartment').datagrid('hideColumn','apartmentNo');
		$('#dg-queryapartment').datagrid('hideColumn','continueToPayDay');		
		
//		$('#dg-queryapartment').wxgrid(dgOptions);
		$('#dg-queryapartment').datagrid();
		$('#bDay').text('计费日期');
		$('#aName').text('用户名称');
		if(node.id == '80'){
			$('#pName').text('所属运营商');
		}else{
			$('#pName').text('项目名称');
		}
		$('#rAmount').text('应收金额');
	}
	if(node.id == '77'){
		
		$('#dg-queryapartment').datagrid('hideColumn','notes');
		$('#dg-queryapartment').datagrid('hideColumn','networkTypeText');
		$('#dg-queryapartment').datagrid('hideColumn','businessTypeText');
		$('#dg-queryapartment').datagrid('hideColumn','apartmentNo');
		$('#dg-queryapartment').datagrid('hideColumn','continueToPayDay');
		$('#dg-queryapartment').datagrid('hideColumn','receivedAmount');
		$('#dg-queryapartment').datagrid('hideColumn','businessType');
//		$('#dg-queryapartment').datagrid('hideColumn','apartmentName');
				
		$('#dg-queryapartment').datagrid('showColumn','paySubject');
//		$('#dg-queryapartment').datagrid('showColumn','projectName');
		
		
		var col = $('#dg-queryapartment').datagrid('getColumnOption', 'paySubject');
		col.width = 100;

//		$('#dg-queryapartment').wxgrid(dgOptions);
		$('#dg-queryapartment').datagrid();
		$('#rAmount').text('金额');
	}

};

WX.ns('WX.omms.common');
WX.omms.common.beforeInfoSelect = function(node){
	if(node.attributes && node.attributes.level==2){
		return true;
	}else{
		return false;
	}
};

WX.ns('WX.omms.infotype');
WX.omms.infotype.reload = function(callback){
	$.messager.progress({text:'正在刷信息系统分类缓存，请稍候...'});
	$.get(base+'/ztjs/omms/infotype/reload',null,function(data){
		$.messager.progress('close');
		$.messager.show({
			title   : '<span class="green">成功刷信息系统分类</span>',
			msg     : '成功刷信息系统分类缓存',
			timeout : 3000,
			showType: 'fade'
		});
		if(callback) callback();
	});
};

WX.ns('WX.omms.olq');

WX.omms.olq.openReadd = function(){
	var row = $('#dg-myolq').datagrid('getSelected');
	if (row){
		$('#dlg-myolq-readd').dialog('open').dialog('refresh', base + '/olq/update/' + row.id);
	}else{
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择记录</span>后再进行追问。'
		});
	}
};

WX.omms.olq.readd = function(){
	$('#theanswer_form').form('submit', {
	    url: base + '/olqdetail/add/readd',
	    onSubmit:function(){  
	    	ke_solvefunction.sync();
	    	if(ke_solvefunction.isEmpty()){
	    		$.messager.alert('请填写追问内容','请填写追问内容','info');
	    		return false;
	    	}
	    },
	    success:function(data){
	    	data=$.parseJSON(data);
	    	if(data.success){
	    		$('#dg-myolq').datagrid('clearSelections').datagrid('reload');
				$('#dlg-myolq-readd').dialog('close');
				$('#dlg-myolq').dialog('close');
           	 	$.messager.show({
					title:'<span class="black">追问成功</span>',
					msg: data.msg
				});
	    	}else{
	    		$.messager.show({
					title:'<span class="red">追问失败</span>',
					msg: data.msg
				});
	    	};
	    }   
	});
};

WX.omms.olq.openReply = function(){
	var row = $('#dg-olq').datagrid('getSelected');
	if (row){
		$('#dlg-olq-reply').dialog('open').dialog('refresh', base + '/olq/update/' + row.id);
	}else{
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择记录</span>后再进行回复。'
		});
	}
};

WX.omms.olq.reply = function(){
	$('#theanswer_form').form('submit', {
	    url: base + '/olqdetail/add/reply',
	    onSubmit:function(){  
	    	ke_solvefunction.sync();
	    	if(ke_solvefunction.isEmpty()){
	    		$.messager.alert('请填写回复内容','请填写回复内容','info');
	    		return false;
	    	}
	    },
	    success:function(data){
	    	data=$.parseJSON(data);
	    	if(data.success){
	    		$('#dg-olq').datagrid('clearSelections').datagrid('reload');
				$('#dlg-olq-reply').dialog('close');
				$('#dlg-olq').dialog('close');
           	 	$.messager.show({
					title:'<span class="black">回复成功</span>',
					msg: data.msg
				});
	    	}else{
	    		$.messager.show({
					title:'<span class="red">回复失败</span>',
					msg: data.msg
				});
	    	};
	    }   
	});
};

WX.omms.olq.openForward = function(){
	var row = $('#dg-olq').datagrid('getSelected');
	if (row){
		$('#div-olq-title').text(row.thetitle);
		$('#div-olq-pname').text(row.pname);
		//$('#div-olq-sid').combotree('setValue',row.pid);
		$('#dlg-olq-forward').dialog('open');
	}else{
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择记录</span>后再进行转发。'
		});
	}
};

WX.omms.olq.forward = function(){
	var row = $('#dg-olq').datagrid('getSelected');
	var qid = row.id;
	var sid = $('#div-olq-sid').combotree('getValue');
	if(!sid){
		$.messager.alert('选择信息系统','请选择转发至的信息系统','info');
		return false;
	}else if(sid == row.pid){
		$.messager.alert('选择信息系统','原信息系统【'+row.pname+'】与转发至的信息系统为同一信息系统，请选择<span class="red">不同的信息系统</span>。','info');
		return false;
	}
	$.post(
		base + '/olq/forward',
		{id:qid, sid:sid, notes:$('#olq-forward-notes').val()},
		function(data){
	    	if(data.success){
	    		$('#dg-olq').datagrid('clearSelections').datagrid('reload');
				$('#dlg-olq-forward').dialog('close');
				$('#dlg-olq').dialog('close');
				$('#olq-forward-notes').val('');
           	 	$.messager.show({
					title:'<span class="black">转发成功</span>',
					msg: data.msg
				});
	    	}else{
	    		$.messager.show({
					title:'<span class="red">转发失败</span>',
					msg: data.msg
				});
	    	};
		},
		'json'
	);
};

WX.omms.olq.stateStyler = function(value,row,index){
	if(row.state == 0){
		return 'color:red;';
	}else{
		return 'color:blue;';
	}
};

WX.omms.olq.titleClick = function(rowIndex){
	$(wxGridId).wxgrid('selectRow', rowIndex).wxgrid('titleLook');
};

WX.omms.olq.titleFormatter = function(value, row, rowIndex){
	var fmt = '';
	if(row.faqid || row.olqid){
		fmt += '<span style="padding-left:20px;background:url(\''+base+'/css/icons/star.png\') no-repeat;">&nbsp;</span>';
	}
	fmt += '<a href="javascript:WX.omms.olq.titleClick('+rowIndex+')">' + value + '</a>';
	return fmt;
};

WX.omms.olq.tofaq = function(){
	var row = $('#dg-myolqreply').datagrid('getSelected');
	if(!row){
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择记录</span>后再进行转发。'
		});
		return false;
	}
	$.post(
		base + '/olq/tofaq',
		{id:row.qid},
		function(data){
	    	if(data.success){
	    		$('#dg-myolqreply').datagrid('clearSelections').datagrid('reload');
           	 	$.messager.show({
					title:'<span class="black">'+data.msg+'</span>',
					msg: data.msg
				});
	    	}else{
	    		$.messager.show({
					title:'<span class="red">'+data.msg+'</span>',
					msg: data.msg
				});
	    	};
		},
		'json'
	);
};

WX.omms.olq.viewolqfaq = function(olqid){
	var dlgdiv = $('#dlg-viewolqfaq');
	if(dlgdiv.length <= 0){
		$('<div class="easyui-dialog" closed="true" iconCls="icon-olq" maximizable="true" resizable="true" modal="true" id="dlg-viewolqfaq" title="问题" style="width:800px;height:500px;position:relative"></div>').appendTo(document.body);
		$('#dlg-viewolqfaq').dialog();
	}
	$('#dlg-viewolqfaq').dialog('open').dialog('refresh', base + '/olq/view/olqfaq/' + olqid);
};

WX.ns('WX.omms.faq');
WX.omms.faq.titleClick = function(rowId, rowOlqid){
	if(rowOlqid){
		$('#dlg-allfaq').dialog('refresh', base + '/olq/view/faqOlqView/' + rowOlqid).dialog('open');
	}else{
		$('#dlg-allfaq').dialog('refresh', base + '/faq/view/' + rowId).dialog('open');
	}
};

WX.omms.faq.titleFormatter = function(value, row, rowIndex){
	var fmt = '';
	if(row.olqid){
		fmt += '<span style="padding-left:20px;background:url(\''+base+'/css/icons/star.png\') no-repeat;">&nbsp;</span>';
	}
	fmt += '<a href="javascript:WX.omms.faq.titleClick('+row.id + ',' + row.olqid +')">' + value + '</a>';
	return fmt;
};



WX.ns('WX.omms.activity')
WX.omms.activity.imgFormatter = function(value, row, rowIndex){
	if('' != value && null != value)
  	value = '<img style="width:30px; height:30px" src="' + value + '">';
  	return  value;
};

WX.omms.activity.openProductList = function(){
	var row = $('#dg-activity').datagrid('getSelected');
	if(row){
		$('#dlg-activity-product').dialog('open').dialog('refresh', base + '/activity/guanlian/' + row.id);
	}else{
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择活动记录</span>后再进行商品关联。'				
		});
	}
};

WX.omms.activity.guanlianProduct = function(){
	var activityId = document.getElementById("activityId").value;
	var nodes = $('#tree-activity-product').tree('getChecked');
	if(nodes.length==0){
		$.messager.show({
			title:'<span class="red">请先选择要关联的商品</span>',
			msg:'请先选择要关联的商品'
		});
		return;
	};
	var productIds = [];
	var productCodes = [];
	$.each(nodes, function(i,node){
		productIds.push(node.id);
		productCodes.push(node.attributes.productCode);
	});
	$.post(base + '/activity/yesguanlianproduct',
			{activityId : activityId, productIds : productIds.join(','),productCodes : productCodes.join(',')},
			function(data){
				if(data.success){
					$('#dlg-activity-product').dialog('close');
					$('#dg-activity').datagrid('reload');
					$.messager.show({
						title   : '<span class="green">活动关联商品成功</span>',
						msg     : '活动关联商品',
						timeout : 3000,
						showType: 'fade'
					});
				}else{
					$.messager.show({
						title:'<span class="red">活动关联商品失败</span>',
						msg:'活动关联商品失败'
					});
				}
			},
			'json'
	);
};

WX.omms.activity.editProductList = function(){
	var row = $('#dg-activity').datagrid('getSelected');
	if(row){
		$('#dlg-edit-activity-product').dialog('open').dialog('refresh', base + '/activity/toactivityproduct/' + row.id);
	}else{
		$.messager.show({
			title:'<span class="blue">提示</span>',
			msg:'请先<span class="blue">选择活动记录</span>后再进行商品编辑。'				
		});
	}
};

