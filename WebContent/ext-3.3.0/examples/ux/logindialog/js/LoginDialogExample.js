Ext.BLANK_IMAGE_URL ='/extjs-tsuyu-servlet/ext-3.3.0/resources/images/s.gif';

Ext.onReady(function() {
	Ext.QuickTips.init();

    var loginDialog = new Ext.ux.form.LoginDialog({
        modal : true,
        cancelButton: 'Close',
        basePath: '/extjs-tsuyu-servlet/ext-3.3.0/examples/ux/logindialog/img/icons'
    });

    loginDialog.show();
});