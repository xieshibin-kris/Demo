layui.use(['layer', 'form', 'table','laydate','admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var laydate = layui.laydate;

    var Adv = {
        tableId: "advTable",    //表格id
        condition: {      //参数
            cahnnelId: "",
            advertiseId: "",
        }
    };

    /**
     * 初始化表格的列
     */
    Adv.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'uuid', sort: true, title: 'UUID' ,width:290},
            {field: 'channelName',   title: '子渠道',minWidth:110 },
            {field: 'parentName',  title: '父渠道',minWidth:110 },
            {field: 'model',  title: '机型' ,minWidth:87  },
            {field: 'imei', sort: true, title: 'imei' ,minWidth:87 },
            {field: 'imsi', sort: true, title: 'imsi' ,minWidth:87  },
            {field: 'mac', sort: true, title: 'mac地址',minWidth:133 },
            {field: 'systemVersion', sort: true, title: '系统版本',width:133 },
            {field: 'totalSpaceSize', sort: true, title: '总内存(KB)' ,width:125  },
            {field: 'cardSpaceSize', sort: true, title: 'SD卡空间(KB)',width:140  },
            {field: 'sdkVersion', sort: true, title: 'sdk版本号',width:118 },
            {field: 'appVersion', sort: true, title: '应用版本号',width:123  },
            {field: 'country', sort: true, title: '国家/城市',minWidth:140}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Adv.search = function () {
        var queryData = {};
        queryData['channelId'] = $("#channelId").val().trim();
        queryData['uuid'] = $("#uuid").val().trim();
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
        url: Feng.ctxPath + '/channel_device/list',
        page: true,
        limit:20,
        limits:[10,20,30,50,100],
        height: "full-158",
        //cellMinWidth: 100,
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
