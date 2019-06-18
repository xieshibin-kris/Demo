layui.use(['layer', 'form', 'table','admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var Adv = {
        tableId: "advTable",    //表格id
        condition: {      //参数
            id: "",
            name: "",
        }
    };

    /**
     * 初始化表格的列
     */
    Adv.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id',  sort: true, title: '渠道ID'  },
            {field: 'name', sort: true, title: '渠道名称' },
            {field: 'active',templet: '#status', sort: true, title: '状态' },
            {field: 'remark',   title: '备注'  },
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 180},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 150  }
        ]];
    };


    /**
     * 点击查询按钮
     */
    Adv.search = function () {
        var queryData = {};
        queryData['id'] = $("#id").val();
        queryData['name'] = $("#name").val().trim();
        table.reload(Adv.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Adv.openAddHost = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '新增渠道',
            content: Feng.ctxPath + '/channel/add_view',
            end: function () {
                admin.getTempData('formOk') && table.reload(Adv.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Adv.exportExcel = function () {
        var checkRows = table.checkStatus(Adv.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Adv.onEditHost = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑渠道',
            content: Feng.ctxPath + '/channel/edit_view?id='+data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Adv.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Adv.onDeleteHost = function (data) {

        var operation = function () {
            console.log(JSON.stringify(data))
            var ajax = new $ax(Feng.ctxPath + "/channel/delete", function () {
                table.reload(Adv.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除渠道" + data.name + " ?", operation);
    };


    Adv.updateChannelStatus = function (channelId,status) {
        var str = "冻结成功，该渠道不可用！";
        if (status == 0)str = "解冻成功，该渠道恢复正常！";

        var ajax = new $ax(Feng.ctxPath+"/channel/updateStatus",function (data) {
            Feng.success(str);
        },function (data) {
            Feng.error("操作失败," + data.responseJSON.message + "!");
            table.reload(Adv.tableId);
        });

        ajax.set("channelId",channelId);
        ajax.set("status",status);

        ajax.start();
    }

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Adv.tableId,
        url: Feng.ctxPath + '/channel/list',
        page: true,
        cols: Adv.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Adv.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Adv.openAddHost();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Adv.exportExcel();
    });

    // 修改channel状态
    form.on('switch(status)', function (obj) {

        var channelId = obj.elem.value;
        var status = obj.elem.checked ? 0 : 1; //0恢复正常，1冻结

        Adv.updateChannelStatus(channelId, status);
    });

    // 工具条点击事件
    table.on('tool(' + Adv.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Adv.onEditHost(data);
        } else if (layEvent === 'delete') {
            Adv.onDeleteHost(data);
        }
    });

});
