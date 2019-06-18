

layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    var laydate = layui.laydate;


    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    laydate.render({
        elem: '#date'
    });

    //获取用户信息getAdvIncomeInfo
    var ajax = new $ax(Feng.ctxPath + "/adv_income/getAdvIncomeInfo?id=" + Feng.getUrlParam("id") );
    var result = ajax.start();
    form.val('advForm', result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/adv_income/edit", function (data) {
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