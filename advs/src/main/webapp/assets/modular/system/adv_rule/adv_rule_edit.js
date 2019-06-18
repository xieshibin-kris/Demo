

layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var laydate = layui.laydate;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取用户信息
    var ajax = new $ax(Feng.ctxPath + "/adv_rule/getAdvRuleInfo?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('advRuleForm', result.data);

    laydate.render({
        elem: '#date'
        ,range: true
    });

    laydate.render({
        elem: '#time',
        type: 'time'
        ,range: true
    });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var param = $("#advRuleForm").serialize() ;
        var url = Feng.ctxPath + "/adv_rule/edit";
        $.ajax({
            type: "POST",
            url: url,
            async:false,
            data: param,
            beforeSend: function(){
                layer.msg('正在保存',{
                    icon: 16
                    ,shade: 0.01
                });
            },
            success: function (data) {
                Feng.success("操作成功！");
                admin.putTempData('formOk', true);
                admin.closeThisDialog();
            },
            error:function (data) {
                Feng.error("操作失败！" + data.responseJSON.message);
            }
        });
    });
});