Ext.ux.grid.filter.BooleanFilter=Ext.extend(Ext.ux.grid.filter.Filter,{defaultValue:false,yesText:'Yes',noText:'No',init:function(config){var gId=Ext.id();this.options=[new Ext.menu.CheckItem({text:this.yesText,group:gId,checked:this.defaultValue===true}),new Ext.menu.CheckItem({text:this.noText,group:gId,checked:this.defaultValue===false})];this.menu.add(this.options[0],this.options[1]);for(var i=0;i<this.options.length;i++){this.options[i].on('click',this.fireUpdate,this);this.options[i].on('checkchange',this.fireUpdate,this);}},getValue:function(){return this.options[0].checked;},setValue:function(value){this.options[value?0:1].setChecked(true);},getSerialArgs:function(){var args={type:'boolean',value:this.getValue()};return args;},validateRecord:function(record){return record.get(this.dataIndex)==this.getValue();}});