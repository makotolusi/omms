Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action', 'Ext.data.*', 'Ext.toolbar.*', 'activity.ActivityForm', 
             'product.Grid', 'activity.ActivityStore', 'activity.ActivityProductGrid']);

Ext.onReady(function() {
	Ext.QuickTips.init();


	// create the data store
	var store = Ext.create('activity.ActivityStore', {
	});

	var sellAction = Ext.create('Ext.Action', {
		iconCls : 'icon-readd', // Use a URL in the icon config
		text : '编辑商品',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				//create window
				var win = Ext.create('Ext.window.Window', {
					title : '编辑商品',
					height : 600,
					width : 700,
					layout : 'fit',
					modal : true,
					items : {
						xtype : 'grid-activity-product',
						store : Ext.create('activity.ActivityProductStore', {
							proxy : {
								url : ROOT_URL + '/activityext/getactivityproducts/'+rec.get('id')
							}
						}),
						activityId : rec.get('id')
					}
				}).show();
			}
		}
	});
	var delAction = Ext.create('Ext.Action', {
		iconCls : 'icon-delete', // Use a URL in the icon config
		text : '删除',
		disabled : true,
		handler : function(widget, event) {
			Ext.MessageBox.confirm('Confirm', '确认删除吗?', function(btn, text) {
				if (btn == 'yes') {
					var rec = grid.getSelectionModel().getSelection()[0];
					if (rec) {
						Ext.Ajax.request({
							url : ROOT_URL + '/activityext/delete/'+rec.get('id'),
							method : 'DELETE',
							success : function(response) {
								var text = response.responseText;
								Ext.MessageBox.alert('提示', '创建成功', function() {
										var p = Ext.getCmp('activityGrid');
										p.getStore().reload();

								}, this);

							},
							failure : function(response) {
								var text = response.responseText;
								Ext.MessageBox.alert('提示', '创建失败:' + text, function() {
									var p = Ext.getCmp('activityGrid');
									p.getStore().reload();
								}, this);
							}
						});
					}
				}
			});
		
		}
	});

	var updateAction = Ext.create('Ext.Action', {
		iconCls : 'icon-readd', // Use a URL in the icon config
		text : '修改',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				console.log(rec.get('rushBeginTime'));
				//create window
				var updateActivityWin = Ext.create('Ext.window.Window', {
					title : '修改活动',
					height : 350,
					width : 500,
					modal : true,
					layout : 'fit',
					id:'activity-update-win',
					items : {
						xtype : 'form-activity',
						activityId : rec.get('id'),
						activityName : rec.get('activityName'),
						specialName : rec.get('specialName'),
						description : rec.get('description'),
						rushBeginTime : rec.get('rushBeginTime'),
						rushEndTime : rec.get('rushEndTime'),
						rushStatus : rec.get('rushStatus'),
						imgUrl : rec.get('imgUrl')
					}
				}).show();
			}
		}
	});
	
	var addAction = Ext.create('Ext.Action', {
		iconCls : 'icon-add', // Use a URL in the icon config
		text : '新增',
		handler : function(widget, event) {
				var updateActivityWin = Ext.create('Ext.window.Window', {
					title : '修改活动',
					height : 600,
					width : 500,
					layout : 'fit',
					modal : true,
					id:'activity-update-win',
					items : {
						xtype : 'form-activity'
					}
				}).show();
		}
	});

	var buyAction = Ext.create('Ext.Action', {
		iconCls : 'buy-button',
		text : '绑定商品',
		disabled : true,
	
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				//create window
				var productStore=Ext.create('product.ProductStore', {
					proxy : {
						url : ROOT_URL + '/productMaintenance/itemsExcludeActivity/'+rec.get('id')
					}
				});
				var win = Ext.create('Ext.window.Window', {
					title : '绑定产品',
					height : 600,
					width : 700,
					modal : true,
					id:'avtivity-product-bind-win',
					layout : 'fit',
					items : {
						xtype : 'grid-product',
						store : productStore,
						dockedItems : [ {
							xtype : 'toolbar',
							items : [ {
								xtype : 'textfield',
								name : 'name',
								id:'productName',
								fieldLabel : '关键词'
							}, {
								iconCls : 'icon-add',
								text : '搜索',
								scope : this,
								handler : function(widget, event) {
									productStore.load({
										params : {
											lk_name : Ext.getCmp('productName').getValue()
										}
									});
							}
							}, '->',
							Ext.create('Ext.Action', {
								icon : '../shared/icons/fam/add.gif', // Use a URL in the icon
								text : '绑定',
								id:'bindBtn',
								disabled : true,
								handler : function(widget, event) {
									var obj={};
									var pGrid=Ext.getCmp('productGrid');
									var pRows=pGrid.getSelectionModel().getSelection();
									var pids='';
									var pcodes='';
											for (var i = 0; i < pRows.length; i++) {
												pids+=pRows[i].get('id')+",";
													pcodes+=pRows[i].get('productCode')+",";
											
											}
									obj.activityId=pGrid.activityId;
									obj.productIds=pids;
									obj.productCodes=pcodes;
											console.log(obj);
									Ext.Ajax.request({
										url : ROOT_URL + '/activityext/yesguanlianproduct',
										method : 'POST',
										params :   obj,
										success : function(response) {
											var text = response.responseText;
											console.log(text);
											Ext.MessageBox.alert('提示', '创建成功', function() {
												var p = Ext.getCmp('productGrid');
												p.getStore().reload();
												Ext.getCmp('avtivity-product-bind-win').close();
											}, this);

										},
										failure : function(response) {
											var text = response.responseText;
											console.log(text);
											Ext.MessageBox.alert('提示', '创建失败-' + text, function() {
												var p = Ext.getCmp('productGrid');
												p.getStore().reload();
												Ext.getCmp('avtivity-product-bind-win').close();
											}, this);
										}
									});
								}
							}) ]
						}, {
							xtype : 'pagingtoolbar',
							dock : 'bottom',
							store : productStore, // GridPanel中使用的数据
							displayInfo : true
						} ],
						activityId : rec.get('id')
					}
				}).show();
				Ext.getCmp('productGrid').getSelectionModel().on({
					selectionchange : function(sm, selections) {
						if (selections.length) {
							Ext.getCmp('bindBtn').enable();
						} else {
							Ext.getCmp('bindBtn').disable();
						}
					}
				});
				//				Ext.example.msg('Buy', 'Buy ' + rec.get('company'));
			}
		}
	});

	var contextMenu = Ext.create('Ext.menu.Menu', {
		items : [buyAction, sellAction,updateAction]
	});

	// create the Grid
	var grid = Ext.create('Ext.grid.Panel', {
		store : store,
		 id:"activityGrid",  
		 autoScroll: true,
//			height : 100%,
		columnLines : true,
		columns : [{
			text : '活动名称',
			flex:1,
			sortable : false,
			dataIndex : 'activityName'
		}, {
			text : '专场名称',
			flex:1,
			sortable : false,
			dataIndex : 'specialName'
		}, {
			text : '描述',
			flex:1,
			sortable : false,
			dataIndex : 'description'
		}, {
			text : '抢购时间',
			flex:1,
			sortable : false,
			dataIndex : 'rushBeginTime'
		}, {
			text : '结束时间',
			flex:1,
			sortable : false,
			dataIndex : 'rushEndTime'
		}, {
			text : '抢购状态',
			flex:1,
			sortable : false,
			hidden:true,
			dataIndex : 'rushStatus'
		}, {
			text : '图片预览',
			sortable : false,
			dataIndex : 'imgUrlComplete',
			 renderer : function(value) {
				    return "<img  style='width:70px; height:70px;' src='"+value+"' >";
			 }
		}, {
			text : '创建人',
			flex:1,
			sortable : false,
			dataIndex : 'userName'
		}, {
			text : '录入时间',
			flex:1,
			sortable : false,
			dataIndex : 'entertime'
		},{
			text : "操作",
			xtype : 'actioncolumn',
			width : 70,
			items : [ {
				iconCls: 'icon-ok',  // Use a URL in the icon config
                tooltip: '启停',
                getClass : function(v, meta, record) {
    				console.log(record.get('rushStatus'));
    				if (record.get('rushStatus') == '0'||record.get('rushStatus') == undefined) {
    					return 'icon-cancel';
    				} else {
    					return 'icon-ok';
    				}
    			},
                handler: function(grid, rowIndex, colIndex) {
                	var rec = grid.getStore().getAt(rowIndex);
    				var str = '';
    				var status=0;
    				console.log(rec.get('rushStatus'));
    				if (rec.get('rushStatus') == '1') {
    					str = "确认停用吗?";
    					status=0;
    				} else {
    					str = "确认启用吗?";
    					status=1;
    				}
    				
    				Ext.MessageBox.confirm('确认', str, function(btn, text) {
    					  var obj={};
    					   obj.id=rec.get('id');
    					   obj.rushStatus=status
    					   console.log(obj);
    					if (btn == 'yes') {
    						Ext.Ajax.request({
    							url : ROOT_URL + '/activityext/updActivityStatus',
    							method : 'POST',
    							params :   obj,
    							success : function(response) {
    								var text = response.responseText;
    								Ext.MessageBox.alert('提示', '操作成功', function() {
    									grid.getStore().reload();
    								}, this);

    							},
    							failure : function(response) {
    								var text = response.responseText;
    								Ext.MessageBox.alert('提示', '失败-' + text, function() {
    								}, this);
    							}
    						});
    					}
    				}, this);
                }
            }]
		}] ,
		dockedItems : [{
			xtype : 'toolbar',

			items : [{
				xtype : 'textfield',
				name : 'name',
				id:'activityName',
				fieldLabel : '关键词'
			}, {
				iconCls : 'icon-searchpp',
				text : '搜索',
				scope : this,
				handler : function(widget, event) {
					grid.getStore().load({
						params : {
							lk_activityName : Ext.getCmp('activityName').getValue()
						}
					});
			}
			}, '->',addAction, buyAction, sellAction, updateAction, delAction]
		}, {
			xtype : 'pagingtoolbar',
			store : store, // GridPanel中使用的数据
			dock : 'bottom',
			displayInfo : true
		}],
		viewConfig : {
			stripeRows : true,
			listeners : {
				itemcontextmenu : function(view, rec, node, index, e) {
					e.stopEvent();
					contextMenu.showAt(e.getXY());
					return false;
				}
			}
		},

		title : '活动管理',
		renderTo : 'grid-example',
		stateful : false
	});



	grid.getSelectionModel().on({
		selectionchange : function(sm, selections) {
			if (selections.length) {
				buyAction.enable();
				sellAction.enable();
				updateAction.enable();
				delAction.enable();
			} else {
				buyAction.disable();
				sellAction.disable();
				updateAction.disable();
				delAction.disable();
			}
		}
	});
	
});
