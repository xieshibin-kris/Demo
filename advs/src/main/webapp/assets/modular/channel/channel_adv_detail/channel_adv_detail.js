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
            {field: 'date', sort: true, title: '日期', minWidth: 110 , totalRowText: '合计'},
            {field: 'channelName', sort: true, title: '子渠道',minWidth:100 },
            {field: 'parentName', sort: true, title: '父渠道', minWidth: 110},
            {field: 'advertiseId', sort: true, title: '广告ID', minWidth: 110},
            {field: 'adPosition', sort: true, title: '展示位置',width:110 },
            {field: 'requestCount', sort: true, title: '请求次数', minWidth: 110 ,totalRow: true},
            {field: 'showCount', sort: true, title: '展示次数' , minWidth: 110 ,totalRow: true},
            {field: 'clickCount', sort: true, title: '点击次数', minWidth: 110 ,totalRow: true},
            {field: 'showRate', sort: true, title: '展示转化率' , minWidth: 110  },
            {field: 'clickRate', sort: true, title: '点击转化率', minWidth: 110  }
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
        queryData['advertiseId'] = $("#advertiseId").val().trim();
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
        url: Feng.ctxPath + '/channel_adv_detail/list',
        page: true,
        totalRow: true,
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
