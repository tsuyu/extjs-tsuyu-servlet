Ext.namespace('Ext.ux.grid.filter');Ext.ux.grid.filter.Filter=Ext.extend(Ext.util.Observable,{active:false,dataIndex:null,menu:null,updateBuffer:500,constructor:function(config){Ext.apply(this,config);this.addEvents('activate','deactivate','serialize','update');Ext.ux.grid.filter.Filter.superclass.constructor.call(this);this.menu=new Ext.menu.Menu();this.init(config);if(config&&config.value){this.setValue(config.value);this.setActive(config.active!==false,true);delete config.value;}},destroy:function(){if(this.menu){this.menu.destroy();}this.purgeListeners();},init:Ext.emptyFn,getValue:Ext.emptyFn,setValue:Ext.emptyFn,isActivatable:function(){return true;},getSerialArgs:Ext.emptyFn,validateRecord:function(){return true;},serialize:function(){var args=this.getSerialArgs();this.fireEvent('serialize',args,this);return args;},fireUpdate:function(){if(this.active){this.fireEvent('update',this);}this.setActive(this.isActivatable());},setActive:function(active,suppressEvent){if(this.active!=active){this.active=active;if(suppressEvent!==true){this.fireEvent(active?'activate':'deactivate',this);}}}});