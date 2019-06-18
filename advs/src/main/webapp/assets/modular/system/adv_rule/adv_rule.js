layui.use(['layer', 'form', 'table','admin', 'ax'], function () {
    var table = layui.table;
    var admin = layui.admin;
    var $ax = layui.ax;
    var AdvRule = {
        tableId: "ruleTable",    //表格id
        condition: {      //参数
            id: "",
            name: "",
        }
    };

    /**
     * 初始化表格的列
     */
    AdvRule.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id',  sort: true, title: '规则ID' ,minWidth: 120},
            {field: 'dayShowCount', sort: true, title: '日展示次数',minWidth: 110},
            {field: 'timeRange', sort: true, title: '展示时间（当天0~23小时）',minWidth: 210},
            {field: 'country', sort: true, title: '排除国家',minWidth: 190},
            {field: 'version', sort: true, title: '排除系统版本',minWidth: 190},
            {field: 'remark', title: '备注' ,minWidth: 90},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 80  }
        ]];
    };


    /**
     * 点击查询按钮
     */
    AdvRule.search = function () {
        var queryData = {};
        queryData['id'] = $("#id").val();
        table.reload(AdvRule.tableId, {where: queryData});
    };


    /**
     * 导出excel按钮
     */
    AdvRule.exportExcel = function () {
        var checkRows = table.checkStatus(AdvRule.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data );
        }
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    AdvRule.onEditHost = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑规则',
            area:['700px','700px'],
            resize:true,
            content: Feng.ctxPath + '/adv_rule/edit_view?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(AdvRule.tableId);
            }
        });
    };

    /**
     * 弹出添加
     */
    AdvRule.openAddRule = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加规则',
            area:['720px','610px'],
            content: Feng.ctxPath + '/adv_rule/add_view',
            end: function () {
                admin.getTempData('formOk') && table.reload(AdvRule.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    AdvRule.onDeleteRule = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/adv_rule/delete", function () {
                Feng.success("删除成功!");
                table.reload(AdvRule.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删规则 " + data.id + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + AdvRule.tableId,
        url: Feng.ctxPath + '/adv_rule/list',
        page: true,
        cols: AdvRule.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        AdvRule.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        AdvRule.openAddRule();
    });

    // 导出excel
    $('#btnExp').click(function () {
        AdvRule.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + AdvRule.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            AdvRule.onEditHost(data);
        } else if (layEvent === 'delete') {
            AdvRule.onDeleteRule(data);
        }
    });


});
