layui.use(['layer', 'form', 'table','laydate','admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;

    var Adv = {
        tableId: "advTable",    //表格id
        condition: {      //参数
            cahnnelId: "",
            advertiseId: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Adv.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'date',  sort: true, title: '日期', minWidth: 110 , totalRowText: '合计'},
            {field: 'channelName', sort: true, title: '子渠道', minWidth: 110},
            {field: 'packageName', sort: true, title: '包名', minWidth: 110},
            {field: 'parentName', sort: true, title: '父渠道', minWidth: 110},
            {field: 'newUserCount', sort: true, title: '新增用户',minWidth: 110,totalRow: true},
            {field: 'activeUserCount', sort: true, title: '活跃用户', minWidth: 110 ,totalRow: true}
        ]];
    };

    laydate.render({
        elem: '#dateParam'
        ,range: true
    });

    /**
     * 点击查询按钮
     */
    Adv.search = function () {
        var queryData = {};
        queryData['channelId'] = $("#channelId").val();
        queryData['packageId'] = $("#packageId").val();
        queryData['parentId'] = $("#parentId").val();
        queryData['dateParam'] = $("#dateParam").val().trim();
        table.reload(Adv.tableId, {where: queryData});
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

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Adv.tableId,
        url: Feng.ctxPath + '/channel_activeuser_stat/list',
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

    // 导出excel
    $('#btnExp').click(function () {
        Adv.exportExcel();
    });


});
