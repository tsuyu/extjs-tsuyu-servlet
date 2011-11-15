<%@page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="com.tsuyu.dao.MenuDAO"%>
<%@page import="com.tsuyu.model.Accordian"%>
<%@page import="com.tsuyu.model.Children"%>
<%@page import="com.tsuyu.model.Leaf"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>tsuyu.org</title>

        <!-- ExtJS css -->
        <link rel="stylesheet" type="text/css" href="/extjs-tsuyu-servlet/ext-3.3.0/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="/extjs-tsuyu-servlet/ext-3.3.0/resources/css/icons.css" />
        <link rel="stylesheet" href="<% out.print(session.getAttribute("theme")); %>"/>
        <!-- App custom css -->
        <!-- ExtJS js -->
        <script type="text/javascript" src="/extjs-tsuyu-servlet/ext-3.3.0/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="/extjs-tsuyu-servlet/ext/ext-all.js"></script>

        <!-- Ux js -->
        <script type="text/javascript"src="/extjs-tsuyu-servlet/ext-3.3.0/examples/ux/Ext.ux.TabCloseMenu/Ext.ux.TabCloseMenu.js"></script>
        <script type="text/javascript" src="/extjs-tsuyu-servlet/ext-3.3.0/examples/ux/iframe.js"></script>
        
        <!-- App js -->
        <script type="text/javascript"><!--
        Ext.onReady(function() {

			Ext.BLANK_IMAGE_URL = '/extjs-tsuyu-servlet/ext-3.3.0/resources/images/default/s.gif';
			
			var theme_selecter = new Ext.data.SimpleStore({
				fields	: ['display', 'value'],
				data	: [	['Default (Blue)', ''], 
							['Black', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-black.css'], 
							['Blue', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-blue.css'],
							['Access', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-access.css'], 
							['Dark Gray', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-darkgray.css'], 
							['Gray', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-gray.css'], 
							['Olive', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-olive.css'], 
							['Pink', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-pink.css'], 
							['Galdaka', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-galdaka.css'],
							['Chocolate', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-chocolate.css'],
							['Silver Cherry', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-silverCherry.css'],
							['Slickness', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-slickness.css'],
							['Midnight', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-midnight.css'],
							['Indigo', '/extjs-tsuyu-servlet/ext/resources/css/xtheme-indigo.css'],
							['Pepermint','/extjs-tsuyu-servlet/ext/resources/css/xtheme-peppermint.css'],
							['Ubuntu','/extjs-tsuyu-servlet/ext/resources/css/xtheme-human.css']]
			});
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

				items : [{
					id : 'tab1',
					title : 'Introduction',
					xtype : 'iframepanel',
					defaultSrc : 'welcome.jsp'
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
												text : 'Welcome '+'<% out.print(session.getAttribute("username")); %>',
												iconCls : 'user'
											},
											'->',
											{
													xtype			:	'combo',
													fieldLabel		:	'Change Theme',
													displayField	:	'display',
													mode			: 	'local', 
													triggerAction	: 	'all', 
													selectOnFocus	:	true, 
													resizable		:	false, 
													listWidth		: 	100, 
													width			: 	100, 
													valueField		: 	'value', 
													emptyText		:	'Theme',
													store			:	theme_selecter,
													listeners		: 	{
														select: function () {
															window.location.replace("theme.jsp?theme="+this.getValue());
														} 
													}
													
											},
											'->',
											{
												xtype : 'button',
												text : 'Log Out',
												iconCls : 'house',
												handler : function() {
													window.location.replace("/extjs-tsuyu-servlet");
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
									items : [
									<%
									MenuDAO menu = new MenuDAO();
									int counter_accordian = 0;
									//int count_accordian = menu.accordianList(Integer.parseInt(session.getAttribute("signinId").toString())).size();
									int count_accordian = menu.accordianList(1).size();
									if(count_accordian > 0){
									//for (Accordian acc : menu.accordianList(Integer.parseInt(session.getAttribute("signinId").toString())))
									for (Accordian accordian : menu.accordianList(1))
									{
										counter_accordian++;%>
										<% out.print("{"); %> 
												title : "<% out.print(accordian.getAccordianName());%>",
												border : false,
												layout : 'border',
												iconCls : "<% out.print(accordian.getAccordianIcon());%>",
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
																		children : [
																		            <%
																		            int counter_child = 0;
																		            int count_child = menu.childrenList(accordian.getAccordianId()).size();
																		            
																		            if(count_child > 0){
																			            	
																			            for (Children children : menu.childrenList(accordian.getAccordianId()))
																						{
																			            	counter_child++; %>
																			            	<% out.print("{");%>
																		         	expanded : true,
																					text : "<%out.print(children.getChildrenName());%>",
																					iconCls : "<%out.print(children.getChildrenIcon());%>",
																					children : [
																					<%
																					int counter_leaf = 0;
																					int count_leaf = menu.leafList(children.getChildrenId()).size();
																					
																					if(count_leaf > 0){
																						
																					for (Leaf leaf : menu.leafList(children.getChildrenId()))
																					{
																						counter_leaf++; %>
																						<% out.print("{");%>           
																					 
																						text : '<%out.print(leaf.getLeafName());%>',
																						leaf : true,
																						iconCls : '<%out.print(leaf.getLeafIcon());%>',
																						listeners : {
																							click : function() {
																								Ext.getCmp('west-panel').collapse();
																								AddCenterTabIF("<% out.print(leaf.getLeafName()); %>","<% out.print(children.getChildrenMapper()+"/"); %><% out.print(leaf.getLeafMapper()); %>") ;
																							}
																						}
																					}<%if(counter_leaf != count_leaf) {out.print(",");}else{out.print("]");}%>
																					<%} } else { out.print("{ text:'No Leaf Identify',leaf:true }]");}%>
																				}<%if( counter_child != count_child) {out.print(",");}else{out.print("]");}%>
															                    <%  } } else {out.print("{text:'No children identity'}]"); }%>
															})
														})]
											} <% if( counter_accordian != count_accordian){out.print(",");}else{out.print("]");}%>
											
											<%} }%>    
									     
								},{
									region : 'center',
									layout : 'border',
									items : [ tabPanel ]
								}]
					});

		});
        --></script>
    </head>
    <body>
    <div id="north">
        </div>
        <div id="west"></div>
        <div id="loading-mask" style=""></div>
        <div id="loading"></div>
        <div id="tree-main"></div>
    </body>
</html>