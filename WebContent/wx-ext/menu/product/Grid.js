/**
 * This example shows examples of the various supported form field types.
 */
Ext.require([ 'product.ProductStore']);
Ext.define('product.Grid', {
	extend : 'Ext.grid.Panel',
	xtype : 'grid-product',
	 id:"productGrid",  
	// <example>
	requires : [ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.Action',
			'Ext.data.*', 'Ext.toolbar.*', 'activity.ActivityForm' ],



	columnLines : true,
	multiSelect : true,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			columns : [ {
				text : '产品代码',
				flex:1,
				sortable : false,
				dataIndex : 'productCode'
			}, {
				text : '产品名称',
				flex:1,
				sortable : false,
				dataIndex : 'name'
			}, {
				text : '价格',
				flex:1,
				sortable : false,
				dataIndex : 'price',
				renderer: function(value, cellMeta, record) {
					if(record.data.currency=='JPY')
		            return "JPY "+Ext.util.Format.number(value,"0.00");
					else
					if(record.data.currency=='RMB')
			            return "RMB "+Ext.util.Format.number(value,"0.00");
					else
					if(record.data.currency=='USD')
			            return "USD "+Ext.util.Format.number(value,"0.00");
					else
						  return "RMB "+Ext.util.Format.number(value,"0.00");
		        }
			}, {
				text : '描述',
				flex:1,
				sortable : false,
				dataIndex : 'description'
			}, {
				text : '数量',
				flex:1,
				sortable : false,
				dataIndex : 'quantity'
			}, {
				text : '系统名称',
				flex:1,
				sortable : false,
				dataIndex : 'systemName'
			}, {
				text : '创建时间',
				flex:1,
				sortable : false,
				dataIndex : 'entertime'
			} ]
			
		});
		this.callParent();
	},
	
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


