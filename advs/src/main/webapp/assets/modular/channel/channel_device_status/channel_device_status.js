layui.use(['layer', 'form', 'table','laydate','admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var $ax = layui.ax;

    var Adv = {
        tableId: "advTable",    //表格id
        condition: {      //参数
            uuid :""
        }
    };

    /**
     * 初始化表格的列
     */
    Adv.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'uuid', sort: true, title: 'UUID', minWidth: 290 },
            {field: 'status',templet: '#status', sort: true, title: '状态', minWidth: 150 },
            {field: 'remark', sort: true, title: '备注',minWidth: 100},
            {field: 'createTime', sort: true, title: '创建时间',width: 200},
            {align: 'center', toolbar: '#tableBar', title: '操作', width: 150  }
        ]];
    };

    /**
     * 点击查询按钮
     */
    Adv.search = function () {
        var queryData = {};
        queryData['uuid'] = $("#uuid").val().trim();
        table.reload(Adv.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    Adv.openAddHost = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '新增设备状态',
            area:["350px","350px"],
            content: Feng.ctxPath + '/channel_device_status/add_view',
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
            title: '编辑设备状态',
            area:["350px","350px"],
            content: Feng.ctxPath + '/channel_device_status/edit_view?id='+data.id,
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
            var ajax = new $ax(Feng.ctxPath + "/channel_device_status/delete", function () {
                table.reload(Adv.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除渠道" + data.uuid + " 的记录?", operation);
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Adv.tableId,
        url: Feng.ctxPath + '/channel_device_status/list',
        page: true,
        totalRow: true,
        limit:20,
        limits:[10,20,30,50,100],
        height: "full-158",
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
