Ext.onReady(function() {

			Ext.BLANK_IMAGE_URL = '/extjs-tsuyu-servlet/ext-3.3.0/resources/images/default/s.gif';

			var tabPanel = new Ext.TabPanel({
				region : 'center',
				id : 'centerTabs',
				deferredRender : true,
				activeTab : 0,
				defaults : {
					closable : true
				},
				autoScroll : true,
				enableTabScroll : true,
				plugins : new Ext.ux.TabCloseMenu(),

				items : [ {
					id : 'tab1',
					title : 'Introduction',
					xtype : 'iframepanel',
					defaultSrc : './welcome.php'
				} ]
			});

			var treePanel;

			function AddCenterTabIF(tabTitle, tabUrl) {
				var tab = tabPanel.add({
					xtype : 'iframepanel',
					title : tabTitle,
					defaultSrc : tabUrl,
					loadMask : false,
					closable : true,
					autoScroll : true

				});
				tabPanel.rendered && tabPanel.doLayout();
				tabPanel.setActiveTab(tab);
				return tab;
			}
			new Ext.Viewport({
						id : 'screenPage',
						layout : 'border',
						items : [{
									region : 'north',
									tbar : [{
												xtype : 'label',
												text : 'Welcome ',
												iconCls : 'user'
											},
											'->',
											{
												xtype : 'button',
												text : 'Log Out',
												iconCls : 'house',
												handler : function() {
													window.location.replace("../index.php");
												}
											}]
								},{
									region : 'west',
									id : 'west-panel',
									title : 'Menu',
									width : 200,
									minSize : 200,
									maxSize : 400,
									collapsible : true,
									layout : 'accordion',
									iconCls : 'house',
									items : [{
												title : 'Ketetapan',
												border : false,
												layout : 'border',
												iconCls : 'cog',
												items : [  new Ext.tree.TreePanel({
															id : 'tree-panel',
															autoScroll : true,
															width : 200,
															region : 'center',
															minSize : 200,
															maxSize : 400,
															rootVisible : false,
															lines : false,
															singleExpand : true,
															useArrows : true,
															root : new Ext.tree.AsyncTreeNode({
																		expanded : true,
																		children : [{
																					expanded : true,
																					text : 'Maklumat Am',
																					iconCls : 'folder_explore',
																					children : [{
																						text : 'Agama\n',
																						leaf : true,
																						iconCls : 'application_form',
																						listeners : {
																							click : function() {
																								Ext.getCmp('west-panel').collapse();
																							}
																						}
																					}]
																				},{
																					expanded : true,
																					text : 'Akaun',
																					iconCls : 'cog',
																					children : [{
																						text : 'Dokumen Turutan\n',
																						leaf : true,
																						iconCls : ' ',
																						listeners : {
																							click : function() {
																								Ext.getCmp('west-panel').collapse();
																								AddCenterTabIF('Dokumen Turutan','../ketetapan/trx_seq.php');

																							}
																						}
																					}]
																		    }]
																	})
														})
												   ]
											},{
												title : 'Profil Anggota',
												border : false,
												layout : 'border',
												iconCls : 'user_edit',
												items : [ new Ext.tree.TreePanel({
															id : 'tree-panel',
															autoScroll : true,
															width : 200,
															region : 'center',
															minSize : 200,
															maxSize : 400,
															rootVisible : false,
															lines : false,
															singleExpand : true,
															useArrows : true,
															root : new Ext.tree.AsyncTreeNode({
																		expanded : true,
																		children : [{
																			expanded : true,
																			text : 'Maklumat Ahli',
																			iconCls : '',
																			children : [{
																						text : 'Kemaskini Profil',
																						leaf : true,
																						iconCls : '',
																						listeners : {
																							click : function() {
																								Ext.getCmp('west-panel').collapse();
																								AddCenterTabIF('Kemaskini Profil','../web/cop_mbr.php');
																							}
																						}
																					},{
																						text : 'Penyata Akaun',
																						leaf : true,
																						iconCls : '',
																						listeners : {
																							click : function() {
																								Ext.getCmp('west-panel').collapse();
																								AddCenterTabIF('Penyata Akaun','../web/account.php');
																						    }
																						}
																					}]
																		  }]
																})
													})]
										}]
								},{
									region : 'center',
									layout : 'border',
									items : [ tabPanel ]
								}]
					});

		});
