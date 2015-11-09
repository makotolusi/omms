/**
 * This example demonstrates use of Ext.form.field.File, a file upload field
 * with custom rendering, and error handling.
 */
Ext.define(
				'util.FileUploads',
				{
					extend : 'Ext.form.Panel',
					xtype : 'form-fileuploads',
//					controller : 'form-fileuploads',
					id:'upload-form',
				    autoScroll:true,
					// <example>
					// requires: [
					// 'KitchenSink.view.form.FileUploadsController'
					// ],

//					exampleTitle : 'File Upload fields',
					// otherContent: [{
					// type: 'ViewController',
					// path: 'app/view/form/FileUploadsController.js'
					// }],
					// </example>

					width : 300,	
					height:450,
					initComponent : function() {
						var me=this;
						var wrappedImage = Ext.create('Ext.Img', {
						    src:me.url,
							width : 300,
							height:300
						});
						Ext.apply(this, {
							items : [ 
{
    margin: '0 0 20 0',
    xtype: 'component',
    html: [
        '文件地址： ',
        ''+me.url
    ]
},wrappedImage,
					{
						xtype : 'filefield',
						emptyText : '请选择文件',
						fieldLabel : '文件',
						name : 'imgFile',
						buttonText : '',
						buttonConfig : {
							iconCls : 'icon-upload'
						}
					} 
					],

					buttons : [ {
						text : '上传',
						handler :  function() {
					        var form = Ext.getCmp('upload-form');
					       
					        if (form.isValid()) {
					            form.submit({
					                url: ROOT_URL +  'sys/file/upload',
					                waitMsg: '正在上传中，请耐心等待...',
					                success: function(fp,o) {
					                	 console.log(o);
					                	 form.tf.setValue(o.result.url);
					                	
					                    var tpl = new Ext.XTemplate(
					                        '上传成功.<br />',
					                        'key: '+o.result.key+'<br />',
					                        '地址: '+o.result.url
					                    );
					                    
					                    Ext.MessageBox.alert('Success', tpl.apply(o.result), function() {
					                    	Ext.getCmp('upload-win').close();
										}, this);
					                },	failure : function(response) {
										var text = response.responseText;
										Ext.MessageBox.alert('提示', '创建失败:' + text, function() {
											Ext.getCmp('upload-win').close();
										}, this);
									}
					            });
					        }
					        
					    }
					}, {
						text : '删除',
						handler :  function() {
						       var form = Ext.getCmp('upload-form');
						       var url=form.tf.getValue();
						       if( Ext.String.trim(url)==''){
						    		Ext.MessageBox.alert('提示', '图片地址为空！', function() {
									}, this);
						       }else{
						    	   url=url.split('?')[0];
						    	   console.log(url);
						    	   var key=url.substring(url.indexOf('.com/')+'.com/'.length);
									 console.log(key);
									Ext.Ajax.request({
										url : ROOT_URL + '/qiniu/delete',
										method : 'POST',
										params : {key:key},
										success : function(response) {
											var text = response.responseText;
											Ext.MessageBox.alert('提示', '返回成功，状态码'+Ext.decode(text).status, function() {
												form.tf.setValue('');
												Ext.getCmp('upload-win').close();

											}, this);

										},
										failure : function(response) {
											var text = response.responseText;
											Ext.MessageBox.alert('提示', '失败:' + text, function() {
												Ext.getCmp('upload-win').close();
											}, this);
										}
									});
						       }
						     
						}
					
					}]
						});
						this.callParent();
					},

					
				});
