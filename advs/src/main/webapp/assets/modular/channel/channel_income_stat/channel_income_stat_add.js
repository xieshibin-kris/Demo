

layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#date'
    });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var param = $("#advForm").serialize() ;
        var url = Feng.ctxPath + "/channel_income_stat/add";
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

