Ext.BLANK_IMAGE_URL ='./js/resources/images/s.gif';

Ext.onReady(function() {
	Ext.QuickTips.init();

    var loginDialog = new Ext.ux.form.LoginDialog({
        modal : true,
        cancelButton: 'Close',
        basePath: './js/examples/ux/logindialog/img/icons'
    });

    loginDialog.show();
});