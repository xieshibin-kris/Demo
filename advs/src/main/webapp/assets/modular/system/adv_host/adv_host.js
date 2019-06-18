layui.use(['layer', 'form', 'table','admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var AdvHost = {
        tableId: "advHostTable",    //表格id
        condition: {      //参数
            id: "",
            name: "",
        }
    };

    /**
     * 初始化表格的列
     */
    AdvHost.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id',  sort: true, title: '广告主ID',minWidth: 100},
            {field: 'name', sort: true, title: '姓名',minWidth: 100},
            {field: 'typeName', sort: true, title: '广告主类型',minWidth: 110},
            {field: 'modeName', sort: true, title: '合作方式',minWidth: 100},
            {field: 'remark', sort: true, title: '备注',minWidth: 70},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 180},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 150 }
        ]];
    };

    /**
     * 点击查询按钮
     */
    AdvHost.search = function () {
        var queryData = {};
        queryData['id'] = $("#id").val();
        queryData['name'] = $("#name").val();
        table.reload(AdvHost.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    AdvHost.openAddHost = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '新增广告主',
            content: Feng.ctxPath + '/adv_host/host_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(AdvHost.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    AdvHost.exportExcel = function () {
        var checkRows = table.checkStatus(AdvHost.tableId);
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
    AdvHost.onEditHost = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑广告主',
            content: Feng.ctxPath + '/adv_host/host_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(AdvHost.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    AdvHost.onDeleteHost = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/adv_host/delete", function () {
                table.reload(AdvHost.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除广告主" + data.name + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + AdvHost.tableId,
        url: Feng.ctxPath + '/adv_host/list',
        page: true,
        cols: AdvHost.initColumn(),
        text: {
            error: '无权限'
        }
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        AdvHost.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        AdvHost.openAddHost();
    });

    // 导出excel
    $('#btnExp').click(function () {
        AdvHost.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + AdvHost.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            AdvHost.onEditHost(data);
        } else if (layEvent === 'delete') {
            AdvHost.onDeleteHost(data);
        }
    });


});
