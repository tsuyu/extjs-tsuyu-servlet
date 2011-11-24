Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'under';
			
			Ext.BLANK_IMAGE_URL = '/extjs-tsuyu-servlet/ext/resources/images/default/s.gif';
			var encode = false;
			Ext.Ajax.timeout = 90000;
			
			var store = new Ext.data.JsonStore( {
				autoDestroy : true,
				url : 'leaf',
				remoteSort : true,
				storeId : 'myStore',
				root : 'data',
				totalProperty : 'total',
				baseParams : {
					mode : 'readl',
					action : 'viewl'
				},
				fields : [ {
					name        :   'leafId',
			        type        :   'int'
				}, {
					name        :   'leafSequence',
					type        :   'int'
				}, {
					name        :   'leafName',
					type        :   'string'
				}, {
					name        :   'leafDescription',
					type        :   'string'
				}, {
					name        :   'leafMapper',
					type        :   'string'
				},{
					name        :   'leafIcon',
					type        :   'string'
				},{
					name        :   'childrenName',
					type        :   'string'
				} ],
			listeners: {
            	exception: function(DataProxy, type, action, options, response, arg){
                	var serverMessage = Ext.util.JSON.decode(response.responseText);
                		if (serverMessage.success == false || serverMessage.success1 == false) {
                    		Ext.MessageBox.alert("Error", serverMessage.message);
                		}
            		}
        		}
			});
			
			this.action = new Ext.ux.grid.RowActions(
					{
						header : 'Actions',
						dataIndex : 'leafId',
						actions : [{
							iconCls : 'application_edit',
							tooltip : 'Update Record',
							callback : function(grid, record, action, row, col) {
								formPanel.form.load( {
									url : 'leaf',
									method : 'POST',
									waitMsg : 'Loading...',
									params : {
										mode : 'readl',
										action :'updatel',
										leafId : record.data.leafId
									},
									success : function(form,action) {
                                         Ext.Msg.alert('Success?', action.result.success );
                                         Ext.Msg.alert('Data returned.', action.result.data.data );
										 viewport.items.get(1).expand();
									},
									failure : function(form,action) {
										Ext.MessageBox.alert('Failed',action.result.message);
									}
								});
							}
						},
						{
							iconCls : 'cancel',
							tooltip : 'Delete Record',
							callback : function(grid, record, action,
									row, col) {
								Ext.Msg.show( {
                                                                    title : 'Delete record?',
                                                                    msg : 'Do you really want to delete </b><br/>There is no undo.',
                                                                    icon : Ext.Msg.QUESTION,
                                                                    buttons : Ext.Msg.YESNO,
                                                                    scope : this,
                                                                    fn : function(response) {
                                                                            if ('yes' == response) {
                                                                                    Ext.Ajax.request( {
                                                                                            url : 'leaf',
                                                                                            params : {
                                                                                                    mode : 'destroyl',
                                                                                                    action :'deletel',
                                                                                                    leafId : record.data.leafId
                                                                                            },
                                                                                            success : function(resp) {
                                                                                                    var data;
                                                                                                    data = Ext.decode(resp.responseText);
                                                                                                    if (data.success === true) {
                                                                                                        Ext.MessageBox.alert('Message', data.msg);
                                                                                                    } else {
                                                                                                        Ext.MessageBox.alert('Error', 'Some problem occurred');
                                                                                                    }
                                                                                                    store.reload();
                                                                                            },
                                                                                            failure : function() {
                                                                                                     Ext.MessageBox.alert('Error', 'Some problem occurred');
                                                                                            }
                                                                                    });
                                                                            }
                                                                    }
                                                            });
							}
						} ]
					});
			
			var columnModel = [ new Ext.grid.RowNumberer(), this.action, {
				dataIndex : 'leafId',
				header : 'Id',
				sortable : true,
				hidden : false
			}, {
				dataIndex : 'leafSequence',
				header : 'Sequence',
				sortable : true,
				hidden :  false
			}, {
				dataIndex : 'leafName',
				header : 'Name',
				sortable : true,
				hidden :  false
			}, {
				dataIndex : 'leafDescription',
				header : 'Description',
				sortable : true,
				hidden :  false
			}, {
				dataIndex : 'leafMapper',
				header : 'Mapper',
				sortable : true,
				hidden :  false
			}, {
				dataIndex : 'leafIcon',
				header : 'Icon',
				sortable : true,
				hidden :  false
			}, {
				dataIndex : 'childrenName',
				header : 'Children Name',
				sortable : true,
				hidden :  false
			} ];
			
			var filters = new Ext.ux.grid.GridFilters({
				encode : true,
				local : false,
				filters : [ {
					type : "int",
					dataIndex : "leafId",
					table : "leaf"
				}, {
					type : "int",
					dataIndex : "leafSequence",
					table : "leaf"
				}, {
					type : "string",
					dataIndex : "leafName",
					table : "leaf"
				}, {
					type : "string",
					dataIndex : "leafDescription",
					table : "leaf"
				}, {
					type : "string",
					dataIndex : "leafMapper",
					table : "leaf"
				}, {
					type : "string",
					dataIndex : "leafIcon",
					table : "leaf"
				},{
					type : "string",
					dataIndex : "childrenName",
					table : "children"
				}]
			});
			
			
			var grid = new Ext.grid.GridPanel( {
				border : false,
				store : store,
				autoHeight : false,
				height : 400,
				columns : columnModel,
				loadMask : true,
				plugins : [this.action, filters],
				sm : new Ext.grid.RowSelectionModel( {
					singleSelect : true
				}),
				viewConfig : {
					forceFit : true,
					emptyText : 'No rows to display'
				},
				iconCls : 'application_view_detail',
				listeners : {
					render : {
						fn : function() {
							store.load( {
								params : {
									mode : 'readl',
									action : 'viewl',
									plugin : [ filters ]
								}
							});
						}
					}
				},
				bbar : new Ext.PagingToolbar( {
					store : store
				})
			});
			
			var toolbarPanel = new Ext.Toolbar({
						items : [
								{
									text : 'Reload',
									iconCls : 'database_refresh',
									id : 'page_reload',
									handler : function() {
										store.reload();
									}
								},
								{
									text : 'New Record',
									iconCls : 'add',
									id : 'page_create',
									handler : function() {
										viewport.items.get(1).expand();
									}
								},
								{
									text : 'Printer',
									iconCls : 'printer',
									id : 'page_printer',
									handler : function() {
										Ext.ux.GridPrinter.print(grid);
									}
								}]
					});
			
			var gridPanel = new Ext.Panel( {
				title : 'List',
				height : 50,
				layout : 'fit',
				iconCls : 'application_view_detail',
				tbar    : [toolbarPanel],
				items : [grid]
			}); 
			
			var children_reader	= new Ext.data.JsonReader({ root:'child',id:'childrenId' }, [ 'childrenId', 'childrenName']);
			var children_store 		= 	new Ext.data.Store({
				proxy		: 	new Ext.data.HttpProxy({
	        			url	: 	'leaf',
						method:'GET'
					}),
				reader		:	children_reader,
				remoteSort	:	false,
				baseParams	:	{action:'readl',mode:'chainedl'}

			});
			
			//children_store.load();
			
			var childrenId = new Ext.ux.form.ComboBoxMatch({
				labelAlign : 'left',
				fieldLabel : 'Children <span style="color: red;">*</span>',
				name : 'childrenId',
				valueField : 'childrenId',
				hiddenName : 'childrenId',
				displayField : 'childrenName',
				typeAhead : false,
				triggerAction : 'all',
				mode :	'remote',
				store : children_store,
				anchor : '95%',
				selectOnFocus : true,
				allowBlank : false,
				blankText : 'Please Choose Children',
				createValueMatcher : function(value) {
					value = String(value).replace(/\s*/g, '');
					if (Ext.isEmpty(value, false)) {
						return new RegExp('^');
					}
					value = Ext.escapeRe(value.split('').join('\\s*')).replace(
							/\\\\s\\\*/g, '\\s*');
					return new RegExp('\\b(' + value + ')', 'i');
				}
			});

			
			
			var leafSequence = new Ext.form.TextField({
				labelAlign : 'left',
				fieldLabel : 'Leaf Sequence <span style="color: red;">*</span>',
				hiddenName : 'leafSequence',
				name : 'leafSequence',
				id : 'leafSequence',
				allowBlank : false,
				blankText : 'Please fill the field',
				anchor : '95%'
			});
			
			var leafName = new Ext.form.TextField({
				labelAlign : 'left',
				fieldLabel : 'Leaf Name <span style="color: red;">*</span>',
				hiddenName : 'leafName',
				name : 'leafName',
				id : 'leafName',
				allowBlank : false,
				blankText : 'Please fill the field',
				anchor : '95%'
			});
			
			var leafDescription = new Ext.form.TextField({
				labelAlign : 'left',
				fieldLabel : 'Leaf Description',
				hiddenName : 'leafDescription',
				name : 'leafDescription',
				id : 'leafDescription',
				blankText : 'Please fill the field',
				anchor : '95%'
			});
			
			var leafMapper = new Ext.form.TextField({
				labelAlign : 'left',
				fieldLabel : 'Leaf Mapper <span style="color: red;">*</span>',
				hiddenName : 'leafMapper',
				name : 'leafMapper',
				id : 'leafMapper',
				allowBlank : false,
				blankText : 'Please fill the field',
				anchor : '95%'
			});
			

			var icon_reader	= new Ext.data.JsonReader({ root:'icon',id:'iconId' }, [ 'iconId', 'iconName']);
			var icon_store 		= 	new Ext.data.Store({
				proxy		: 	new Ext.data.HttpProxy({
	        			url	: 	'leaf',
						method:'GET'
					}),
				reader		:	icon_reader,
				remoteSort	:	false,
				baseParams	:	{mode:'readl',action:'iconl' }

			});
			
			var leafIcon = new Ext.ux.form.IconCombo({ 
					name			:	'leafIcon',
					hiddenName		:	'leafIcon',
					hiddenId		:	'leafIcon',
					store			:	 icon_store,
					emptyText		:	'Please Choose Icon',
					fieldLabel		:	'Icon',
					anchor			: 	'95%',
					triggerAction	: 	'all',
					valueField		: 	'iconName',
					displayField	: 	'iconName',
					iconClsTpl		:	'{iconName}',
					mode			:	'remote'	
			});
			
			var leafId = new Ext.form.Hidden( {
				name : 'leafId'
			});
			var formPanel = new Ext.form.FormPanel({
						url : 'leaf',
						id : 'formPanel',
						method : 'post',
						frame : true,
						title : 'Form',
						border : false,
						width : 600,
						bodyStyle : 'padding:5px',
						items : [leafId,childrenId,leafSequence,leafName,leafDescription,leafMapper,leafIcon],
						buttonVAlign : 'top',
						buttonAlign : 'left',
						iconCls : 'application_form',
						bbar : new Ext.ux.StatusBar( {
							id : 'form-statusbar',
							defaultText : 'Ready',
							plugins : new Ext.ux.ValidationStatus( {
								form : 'formPanel'
							})
						}),
						buttons : [
								{
									text : 'Save',
									iconCls : 'bullet_disk',
									handler : function() {
										if (formPanel.getForm().isValid()) {
											formPanel.getForm().submit({
												waitMsg : 'Saving',
												params : {
													action : 'savel',
													mode   : 'leafl'
												},
												success : function(
														form,
														action) {
													var title='Message success';
													Ext.MessageBox.alert(title,action.result.message);
													formPanel.getForm().reset();
													store.reload();
													//alert("sss");
												},
												failure : function(form,action) {
													var title='Message Failure';
													if (action.failureType === Ext.form.Action.LOAD_FAILURE){
														alert("Client ada Error 1 ");
													}
													else if (action.failureType === Ext.form.Action.CLIENT_INVALID){
													// here will be error if duplicate code
													//	alert("Client ada Error 2");
													}
													else if (action.failureType === Ext.form.Action.CONNECT_FAILURE){
                    									Ext.Msg.alert('Failure', 'Server reported:'+form.response.status+' '+form.response.statusText);
                									}
                									else if (action.failureType === Ext.form.Action.SERVER_INVALID){
                    									Ext.Msg.alert(title, action.result.message);
                									}
												}
										   });
										}
									}
								}, {
									text : 'New Record',
									type : 'button',
									iconCls : 'add',
									handler : function() {
										formPanel.getForm().reset();
									}
								}, {
									text : 'Reset',
									type : 'reset',
									iconCls : 'table_refresh',
									handler : function() {
										formPanel.getForm().reset();
										icon_store.load();
									}
								},{
									text : 'Cancel',
									type : 'button',
									iconCls : 'delete',
									handler : function() {
										formPanel.getForm().reset();
										store.reload();
										viewport.items.get(0).expand();
									}
								} ]
					});
			
			var viewport = new Ext.Viewport( {
				id : 'viewport',
				region : 'center',
				layout : 'accordion',
				//defaults: {autoScroll: true},
				layoutConfig : {
					titleCollapse : true,
					animate : false,
					activeOnTop : true
				},
				items : [gridPanel, formPanel]
			});
		});
