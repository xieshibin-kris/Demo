

layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    var laydate = layui.laydate;

    laydate.render({
        elem: '#date'
    });

    //获取用户信息
    var ajax = new $ax(Feng.ctxPath + "/channel_income_stat/getChannelIncomeStatInfo?date="
        + Feng.getUrlParam("date")+"&channelId="+Feng.getUrlParam("channelId"));
    var result = ajax.start();
    form.val('advForm', result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        result.data.predictincome = $("#predictincome").val();
        result.data.remark = $("#remark").val();
        var url = Feng.ctxPath + "/channel_income_stat/edit";
        $.ajax({
            type: "POST",
            url: url,
            async:false,
            data: result.data,
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