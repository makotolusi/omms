
Ext.define('order.OrderDetailsGrid', {
	extend : 'Ext.grid.Panel',
	xtype : 'grid-orderdetail',
	 id:"orderDetailGrid",  
	// <example>
	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action','Ext.state.*','Ext.form.*','Ext.toolbar.*'],
	columnLines : true,
	multiSelect : true,
	
	initComponent : function() {
		var me = this;
		var store = Ext.create('Ext.data.Store', {
			fields : [ 'id', 'orderId', 'activity', 'product', 'count',
					'status.Value', 'price'  ],
			autoLoad : true,
			pageSize : 20,
			proxy : {
				type : 'ajax',
				url : ROOT_URL + '../order/detail/items/'+me.orderId,
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
		console.log(store);
		Ext.apply(this, {
			store:store,
			columns : [ {
		text : '活动名称',
		width : 150,
		sortable : false,
		dataIndex : 'activity',
		renderer : function(value, cellMeta, record) {
			return value.activityName;
		}
	}, {
		text : '产品名称',
		width : 150,
		sortable : false,
		dataIndex : 'product',
		renderer : function(value, cellMeta, record) {
			if(value!=undefined)
				return value.name;
			else
				return "";
			}
	}, {
		text : '价格',
		sortable : false,
            width: 150,
        
            renderer: Ext.util.Format.numberRenderer('0.00'),
		dataIndex : 'price'
	}, {
		text : '数量',
		width : 150,
		sortable : false,
		dataIndex : 'count'
	}] ,
	dockedItems : [ {
		xtype : 'toolbar',

		items : [ {
			xtype : 'textfield',
			name : 'name',
			fieldLabel : '产品名称'
		}, {
			iconCls : 'icon-add',
			text : '搜索',
			scope : this,
			handler : this.onAddClick
		}
		]
	}, {
		xtype : 'pagingtoolbar',
		dock : 'bottom',
		store:me.store,
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
	},
	stateful : false
		});
		
		this.callParent();
		me.getSelectionModel().on({
			selectionchange : function(sm, selections) {
// if (selections.length) {
// delAction.enable();
// } else {
// delAction.disable();
// }
			}
		});
	}
	
	
});


