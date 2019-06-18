package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;

import java.util.Map;

/**
 * Created by hp on 2019/5/5.
 */
public class AdvIncomeWrapper extends BaseControllerWrapper {
    public AdvIncomeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("date",map.get("date").toString().substring(0,10));
        map.put("channelName", ConstantFactory.me().getChannelName((Integer) map.get("channelId")));
    }
}
