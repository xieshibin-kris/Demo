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
            {field: 'id',  sort: true, title: '广告ID', minWidth: 130 },
            {field: 'name', sort: true, title: '广告名称', minWidth: 110},
            {field: 'channelName', sort: true, title: '所属渠道（子）', minWidth: 135},
            {field: 'hostName', sort: true, title: '广告主名称', minWidth: 110},
            {field: 'ruleId', sort: true, title: '规则ID', minWidth: 110},
            {field: 'status',templet: '#status', sort: true, title: '状态' ,width:80},
            {field: 'adPosition', sort: true, title: '展示位置',width:105 },
            {field: 'adType', sort: true, title: '广告类型',width:105},
            {field: 'weight', sort: true, title: '权重',width:80},
            {field: 'url', sort: true, title: '链接',minWidth:80},
            {field: 'remark', sort: true, title: '备注',minWidth: 70},
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
        queryData['name'] = $("#name").val();
        table.reload(Adv.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    Adv.openAddHost = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '新增广告主',
            content: Feng.ctxPath + '/adv/add_view',
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
            title: '编辑广告主',
            content: Feng.ctxPath + '/adv/edit_view?id=' + data.id,
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
            var ajax = new $ax(Feng.ctxPath + "/adv/delete", function () {
                table.reload(Adv.tableId);
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
        elem: '#' + Adv.tableId,
        url: Feng.ctxPath + '/adv/list',
        page: true,
        // height: "full-158",
        //cellMinWidth: 100,
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
    // 修改user状态
    form.on('switch(status)', function (obj) {

        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        MgrUser.changeUserStatus(userId, checked);
    });

});
