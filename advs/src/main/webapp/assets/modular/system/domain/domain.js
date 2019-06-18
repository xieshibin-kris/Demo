layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     * 系统管理--用户管理
     */
    var Domain = {
        tableId: "domainTable",    //表格id
    };

    /**
     * 初始化表格的列
     */
    Domain.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: '用户id'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'url', sort: true, title: '域名'},
            {field: 'active', sort: true, title: '状态',templet: '#status', },
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 180}
        ]];
    };


    /**
     * 点击查询按钮
     */
    Domain.search = function () {
        var queryData = {};
        table.reload(Domain.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    Domain.openAddUser = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加域名',
            content: Feng.ctxPath + '/domain/add_view',
            end: function () {
                admin.getTempData('formOk') && table.reload(Domain.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Domain.exportExcel = function () {
        var checkRows = table.checkStatus(Domain.tableId);
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
    Domain.onEditUser = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑域名',
            content: Feng.ctxPath + '/domain/edit_view?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Domain.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Domain.onDeleteUser = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/domain/delete", function () {
                table.reload(Domain.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除域名" + data.url + "?", operation);
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Domain.tableId,
        url: Feng.ctxPath + '/domain/list',
        page: true,
        cols: Domain.initColumn()
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Domain.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Domain.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Domain.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Domain.onEditUser(data);
        } else if (layEvent === 'delete') {
            Domain.onDeleteUser(data);
        }
    });


});
