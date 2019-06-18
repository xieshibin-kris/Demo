

layui.use([  'form', 'admin',   'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;


    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取用户信息
    var ajax = new $ax(Feng.ctxPath + "/channel/getChannelInfo?id=" + Feng.getUrlParam("id") );
    var result = ajax.start();
    form.val('advForm', result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/channel/edit", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});