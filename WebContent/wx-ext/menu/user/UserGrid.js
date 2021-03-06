Ext.define('user.UserGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'grid-user',
	 id:"userGrid",  
	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action','Ext.state.*','Ext.form.*','Ext.toolbar.*'],
	columnLines : true,
	multiSelect : true,
	initComponent : function() {
		var me = this;
		me.obj={};
		if(me.role!=undefined)
			{
			me.obj.role=me.role;
			}
		if(me.agentId!=undefined)
		{
			me.obj.agentId=me.agentId;
		}
		console.log(me.role)
		var store = Ext.create('Ext.data.Store', {
			fields : [ 'id', 'username', 'phoneNum', 'gender', 'qq',
					'weixin', 'addr', 'idNumber', 'email','role' ],
			autoLoad : true,
			pageSize : 20,
			proxy : {
				type : 'ajax',
				url : ROOT_URL + '../commerceuser/items',
				actionMethods : {
					read : "POST"
				},
				extraParams :me.obj,
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

//		var detailAction = Ext.create('Ext.Action', {
//			iconCls : 'icon-grid', // Use a URL in the icon config
//			text : '订单明细',
//			disabled : true,
//			handler : function(widget, event) {
//				var rec = me.getSelectionModel().getSelection()[0];
//					var updateActivityWin = Ext.create('Ext.window.Window', {
//						title : '订单明细',
//						height : 500,
//						width : 600,
//						modal : true,
//						id:'orderdetail-grid-win',
//						items : {
//							xtype : 'grid-orderdetail',
//							orderId : rec.get('id')
//						}
//					}).show();
//			}
//		});
		
		Ext.apply(this, {
			store:store,
			id : 'userGrid',
			columns : [ {
				text : '用户名',
				flex : 1,
				sortable : false,
				dataIndex : 'username'
			}, {
				text : '电话',
				flex : 1,
				sortable : false,
				dataIndex : 'phoneNum'
			}, {
				text : '性别',
				flex : 1,
				sortable : false,
				dataIndex : 'gender',
				renderer : function(value, cellMeta, record) {
					if(value=='0')
						return '男';
					else
						return '女';
					}
			}, {
				text : 'QQ',
				flex : 1,
				sortable : false,
				dataIndex : 'qq'
			}, {
				text : '地址',
				flex : 1,
				sortable : false,
				dataIndex : 'addr'
			
			}, {
				text : '证件',
				flex : 1,
				sortable : false,
				dataIndex : 'idNumber'
			}, {
				text : '邮箱',
				flex : 1,
				sortable : false,
				dataIndex : 'email'
			}, {
				text : '角色',
				flex : 1,
				sortable : false,
				dataIndex : 'role',
				renderer : function(value, cellMeta, record) {
					if(value=='AGENT')
						return '经销商';
					else if(value=='VIP')
						return 'VIP';
					else
						return '普通会员';
					}
			}],
			dockedItems : [ {
				xtype : 'toolbar',
				id:'user-grid-toolbar',
				items : [ {
					xtype : 'textfield',
					name : 'phoneNum',
					id : 'phoneNum',
					fieldLabel : '手机号'
				}, {
					iconCls : 'icon-searchpp',
					text : '搜索',
					scope : this,
					handler : function(widget, event) {
						 me.obj.phoneNum =Ext.getCmp('phoneNum').getValue();
						 me.getStore().load({
						
							 params : me.obj
						 });
					}
				} , '->']
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
//		me.getSelectionModel().on({
//			selectionchange : function(sm, selections) {
//				 if (selections.length) {
//					if(sm.selected.first().data.role!='AGENT')
//						detailAction.enable();
//				} else {
//						detailAction.disable();
//				}
//			}
//		});
	}
	
	
});


