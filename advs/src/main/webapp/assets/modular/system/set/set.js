layui.use(['layer', 'form'  ], function () {

    var form = layui.form;

    form.on('submit(confirm)', function (obj) {

        var param = $("#setForm").serialize() ;
        var url = Feng.ctxPath + "/set/edit";
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
            },
            error:function (data) {
                Feng.error("操作失败！" + data.responseJSON.message);
            }
        });
    });

});
