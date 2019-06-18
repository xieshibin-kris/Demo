package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.DateUtils;

import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
public class ChannelWithdrawRecordWrapper extends BaseControllerWrapper {
    public ChannelWithdrawRecordWrapper(Page<Map<String, Object>> records) {
        super(records);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createTime", DateUtils.getTimeFormat((Long) map.get("createTime")));
        map.put("channelName", ConstantFactory.me().getChannelName((Integer) map.get("channelId")));
    }
}
