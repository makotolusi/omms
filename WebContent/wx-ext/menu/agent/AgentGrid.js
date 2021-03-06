Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action',
		'Ext.data.*', 'Ext.toolbar.*' ]);
Ext.onReady(function() {
	Ext.QuickTips.init();
	// create the data store
	var store = Ext.create('Ext.data.Store', {
		fields : [ 'id', 'commerceUser', 'profitRatio', 'profit','status','grossIncome' ],
		autoLoad : true,
		pageSize : 20,
		proxy : {
			type : 'ajax',
			url : ROOT_URL + '../agent/items',
			actionMethods : {
				read : "POST"
			},
			extraParams : {},
			reader : {
				type : 'json',
				rootProperty : 'rows',
				totalProperty : 'total'
			},
			writer : {
				type : 'json'
			}
		}
	});
	var detailAction = Ext.create('Ext.Action', {
		iconCls : 'icon-grid', // Use a URL in the icon config
		text : '经销商订单',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
				var updateActivityWin = Ext.create('Ext.window.Window', {
					title : '经销商订单',
					height : 600,
					width : 1800,
//					layout : 'fit',
					modal : true,
					id:'order-grid-win',
					items : {
						xtype : 'grid-order',
						agentId : rec.get('commerceUser').phoneNum
					}
				}).show();
		}
	});
	var deleteAction = Ext.create('Ext.Action', {
		iconCls : 'icon-delete', // Use a URL in the icon config
		text : '删除',
		disabled : true,
		handler : function(widget, event) {
			Ext.MessageBox.confirm('Confirm', '确认删除吗?', function(btn, text) {
				if (btn == 'yes') {
					var rec = grid.getSelectionModel().getSelection()[0];
					if (rec) {
						Ext.Ajax.request({
							url : ROOT_URL + '../agent/delete/'+rec.get('agentId'),
							method : 'DELETE',
							success : function(response) {
								var text = response.responseText;
								Ext.MessageBox.alert('提示', '创建成功', function() {
										var p = Ext.getCmp('agentGrid');
										p.getStore().reload();

								}, this);

							},
							failure : function(response) {
								var text = response.responseText;
								Ext.MessageBox.alert('提示', '创建失败:' + text, function() {
									var p = Ext.getCmp('agentGrid');
									p.getStore().reload();
								}, this);
							}
						});
					}
				}
			});
		
		}
	});
	var sellAction = Ext.create('Ext.Action', {
		iconCls : 'icon-readd', // Use a URL in the icon config
		text : '经销商配置',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				//create window
				var win = Ext.create('Ext.window.Window', {
					title : '经销商配置',
					height : 130,
					width : 300,
					layout : 'fit',
					id:'agent-profitratio-win',
					modal : true,
					items : {
						xtype : 'form-agent',
						profitRatio:rec.get('profitRatio'),
						agentId : rec.get('agentId'),
						winId:'agent-profitratio-win',
						gridId:'agentGrid'
					}
				}).show();
			}
		}
	});
	
//	var newAgent = Ext.create('Ext.Action', {
//		iconCls : 'icon-add', // Use a URL in the icon config
//		text : '新增',
//		handler : function(widget, event) {
//				//create window
//				var win = Ext.create('Ext.window.Window', {
//					title : '新增',
//					height : 600,
//					width : 1400,
//					id:'agent-new-win',
//					modal : true,
//					items : {
//						xtype : 'grid-user',
//						winId:'agent-new-win',
//							width : 1400,
//							
//					}
//				}).show();
//		}
//	});
 	var addOfflineAction = Ext.create('Ext.Action', {
		iconCls : 'icon-user-add', // Use a URL in the icon config
		text : '添加下线',
		id:'add-agent-action',
		disabled : true,
		handler : function(widget, event) {
			var g=Ext.getCmp('userGrid');
			var rec = g.getSelectionModel().getSelection()[0];
			if (rec) {
				var obj={};
				 obj.agentId=g.agentIdRelation;
				obj.offLineId=rec.get('id');
				console.log(obj);
				Ext.Ajax.request({
					url : ROOT_URL + '../agent/relation',
					method : 'POST',
					params : obj,
					success : function(response) {
						var text =  Ext.decode(response.responseText);
						Ext.MessageBox.alert('提示',text.msg, function() {
//							grid.getStore().reload();
						}, this);
					},
					failure : function(response) {
						var text = response.responseText;
						Ext.MessageBox.alert('提示', '失败-' + text, function() {
						}, this);
					}
				});
			}
		}
	});
 	
	var offline = Ext.create('Ext.Action', {
		iconCls : 'icon-readd', // Use a URL in the icon config
		text : '下线配置',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				var grid1=Ext.create('user.UserGrid', {
					agentIdRelation:rec.get('agentId'),
					role:"MEMBER"
		    	});
				//create window
				var win = Ext.create('Ext.window.Window', {
					title : '下线配置',
					height : 600,
					width : 1000,
					layout : 'fit',
					id:'agent-relation-win',
					modal : true,
					items :grid1
				}).show();
				Ext.getCmp('user-grid-toolbar').add(addOfflineAction);
			 	grid1.getSelectionModel().on({
		    		selectionchange : function(sm, selections) {
		    			if (selections.length) {
		    				addOfflineAction.enable();
						} else {
							addOfflineAction.disable();
						}
		    		}
		    	});
			}
		}
	});

	var queryOffline = Ext.create('Ext.Action', {
		iconCls : 'icon-readd', // Use a URL in the icon config
		text : '查看下线',
		disabled : true,
		handler : function(widget, event) {
			var rec = grid.getSelectionModel().getSelection()[0];
			if (rec) {
				var grid1=Ext.create('user.UserGrid', {
					agentId:rec.get('agentId')
		    	});
				//create window
				var win = Ext.create('Ext.window.Window', {
					title : '查看下线',
					height : 600,
					width : 1000,
					layout : 'fit',
//					id:'agent-offline-win',
					modal : true,
					items :grid1
				}).show();
			
			}
		}
	});
	
	// create the Grid
	var grid = Ext.create('Ext.grid.Panel', {
		store : store,
		title : '经销商管理',
		id : 'agentGrid',
		renderTo : 'grid-example',
		columns : [ {
			text : '经销商名称',
			flex : 1,
			sortable : false,
			dataIndex : 'commerceUser',
			renderer : function(value, cellMeta, record) {
				if(value!=undefined)
					return value.username;
				else
					return "";
				}
		}, {
			text : '经销商电话',
			flex : 1,
			sortable : false,
			dataIndex : 'commerceUser',
			renderer : function(value, cellMeta, record) {
				if(value!=undefined)
					return value.phoneNum;
				else
					return "";
				}
		}, {
			text : '经销商地址',
			flex : 1,
			sortable : false,
			dataIndex : 'commerceUser',
			renderer : function(value, cellMeta, record) {
				if(value!=undefined)
					return value.addr;
				else
					return "";
				}
		}, {
			text : '利润率',
			flex : 1,
			sortable : false,
			dataIndex : 'profitRatio',
			renderer : function(value, cellMeta, record) {
				if(value!=undefined)
					return value*100+"%";
				else
					return "";
				}
		}, {
			text : '总盈利',
			flex : 1,
			sortable : false,
			dataIndex : 'profit'
		}, {
			text : '总销售',
			flex : 1,
			sortable : false,
			dataIndex : 'grossIncome'
		} ,{
			text : "操作",
			xtype : 'actioncolumn',
			width : 70,
			items : [ {
				iconCls: 'icon-ok',  // Use a URL in the icon config
                tooltip: '启停',
                getClass : function(v, meta, record) {
    				console.log(record.get('status'));
    				if (record.get('status') == 'NORMAL'||record.get('status') == undefined) {
    					return 'icon-cancel';
    				} else {
    					return 'icon-ok';
    				}
    			},
                handler: function(grid, rowIndex, colIndex) {
                	var rec = grid.getStore().getAt(rowIndex);
    				var str = '';
    				var status=0;
    				if (rec.get('status') == 'NORMAL') {
    					str = "确认启用吗?";
    					status="STOP";
    				} else {
    					str = "确认停用吗?";
    					status="NORMAL";
    				}
    				
    				Ext.MessageBox.confirm('确认', str, function(btn, text) {
    					  var obj={};
    					   obj.agentId=rec.get('agentId');
    					   obj.status=status
    					   console.log(obj);
    					if (btn == 'yes') {
    						Ext.Ajax.request({
    							url : ROOT_URL + '../agent/updateStutus',
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
		} ],
		dockedItems : [ {
			xtype : 'toolbar',

			items : [ {
				xtype : 'textfield',
				name : 'name',
				id : 'agentName',
				fieldLabel : '经销商名称'
			}, {
				iconCls : 'icon-searchpp',
				text : '搜索',
				scope : this,
				handler : function(widget, event) {
					// grid.getStore().load({
					// params : {
					// lk_name : Ext.getCmp('productName').getValue()
					// }
					// });
				}
			} , '->',sellAction,detailAction,offline,queryOffline]//deleteAction
		}, {
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			// store : store, // GridPanel中使用的数据
			displayInfo : true
		} ],
		viewConfig : {
			stripeRows : true,
			listeners : {
				itemcontextmenu : function(view, rec, node, index, e) {
					e.stopEvent();
					contextMenu.showAt(e.getXY());
					return false;
				}
			}
		}
	});

	grid.getSelectionModel().on({
		selectionchange : function(sm, selections) {
			 if (selections.length) {
				detailAction.enable();
				sellAction.enable();
				deleteAction.enable();
				offline.enable();
				queryOffline.enable();
			} else {
				detailAction.disable();
				sellAction.disable();
				deleteAction.disable();
				offline.disable();
				queryOffline.disable();
			}
		}
	});

});
