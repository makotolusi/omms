/**
 * This example demonstrates use of Ext.form.field.File, a file upload field
 * with custom rendering, and error handling.
 */
Ext.define(
				'product.FileUploadsTextField',
				{
					extend : 'Ext.form.field.Text',
					xtype : 'textfield-fileuploads',
					 emptyText: '请选择上传图片',
					initComponent : function() {
						var me=this;
						Ext.apply(this,  {
					        disable:true,
					        listeners:{ 
					        	focus:function(nf, newv, oldv) {
					        		Ext.Ajax.request({
										url : ROOT_URL + '/productMaintenance/convertUrl',
										method : 'POST',
										params : {"url":nf.getValue()},
										success : function(response) {
											//create window
											console.log(response.responseText);
//											nf.setValue(nf)
											Ext.create('Ext.window.Window', {
							        			layout : 'fit',
							        			modal : true,
							        			id:'upload-win',
							        			items : {
							        				xtype : 'form-fileuploads',
							        				tf:nf,
							        				url:Ext.decode(response.responseText).url
							        			}
							        		}).show();
										},
										failure : function(response) {
										}
									});
					        	
					        	            }
					        	        },
					    	allowBlank : true
					    });
						this.callParent();
					}

					
				});
