Ext.define('order.OrderGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'grid-order',
	 id:"orderGrid",  
	// <example>
	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action','Ext.state.*','Ext.form.*','Ext.toolbar.*'],
	columnLines : true,
	multiSelect : true,
	
	initComponent : function() {
		var me = this;
		var store = Ext.create('Ext.data.Store', {
			fields : [ 'id', 'totalCount', 'totalPrice', 'orderCode', 'orderTime',
					'submitPerson', 'reviewerName', 'receiverInfo', 'receiverId',
					'receiverPhone', 'status', 'orderType', 'payType', 'payCode' ],
			autoLoad : true,
			pageSize : 20,
			proxy : {
				type : 'ajax',
				url : ROOT_URL + '../order/items',
				actionMethods : {
					read : "POST"
				},
				extraParams : {'agentId':me.agentId},
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
			text : '订单明细',
			disabled : true,
			handler : function(widget, event) {
				var rec = me.getSelectionModel().getSelection()[0];
					var updateActivityWin = Ext.create('Ext.window.Window', {
						title : '订单明细',
						height : 500,
						width : 600,
						layout : 'fit',
						modal : true,
						id:'orderdetail-grid-win',
						items : {
							xtype : 'grid-orderdetail',
							orderId : rec.get('id')
						}
					}).show();
			}
		});
		
		Ext.apply(this, {
			store:store,
			title : '订单管理',
			id : 'orderGrid',
			columns : [ {
				text : '订单代码',
				flex : 1,
				sortable : false,
				dataIndex : 'orderCode'
			}, {
				text : '收件人',
				flex : 1,
				sortable : false,
				dataIndex : 'reviewerName'
			}, {
				text : '收件人电话',
				flex : 1,
				sortable : false,
				dataIndex : 'receiverPhone'
			}, {
				text : '经销商',
				flex : 1,
				sortable : false,
				dataIndex : 'submitPerson'
			}, {
				text : '订单状态',
				flex : 1,
				sortable : false,
				dataIndex : 'status',
				renderer : function(value, cellMeta, record) {
				if(value=='WAITING_PAYMENT')
					return '待支付';
				else if(value=='PAID')
					return '已支付';
				else if(value=='CANCEL')
					return '取消支付';
				else
					return value;
				}
			}, {
				text : '支付方式',
				flex : 1,
				sortable : false,
				dataIndex : 'payType',
				renderer : function(value, cellMeta, record) {
					if(value=='WEIXIN')
						return '微信';
					else if(value=='ZHIFUBAO')
						return '支付宝';
					else
						return value;
					}
			}, {
				text : '支付流水',
				flex : 1,
				sortable : false,
				dataIndex : 'payCode'
			}, {
				text : '订单数量',
				flex : 1,
				sortable : false,
				dataIndex : 'totalCount'
			}, {
				text : '订单价格',
				flex : 1,
				sortable : false,
				dataIndex : 'totalPrice'
			// renderer: function(value, cellMeta, record) {
			// if(record.data.currency=='JPY')
			// return "JPY "+Ext.util.Format.number(value,"0.00");
			// else
			// if(record.data.currency=='RMB')
			// return "RMB "+Ext.util.Format.number(value,"0.00");
			// else
			// if(record.data.currency=='USD')
			// return "USD "+Ext.util.Format.number(value,"0.00");
			// else
			// return "RMB "+Ext.util.Format.number(value,"0.00");
			// }
			} ],
			dockedItems : [ {
				xtype : 'toolbar',

				items : [ {
					xtype : 'textfield',
					name : 'name',
					id : 'orderCode',
					fieldLabel : '订单号'
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
				} , '->',detailAction]
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
		
		this.callParent();
		me.getSelectionModel().on({
			selectionchange : function(sm, selections) {
				 if (selections.length) {
					detailAction.enable();
				} else {
					detailAction.disable();
				}
			}
		});
	}
	
	
});


