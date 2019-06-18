package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.channel.ChannelSub;

import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
public class ChannelIncomeStatWrapper extends BaseControllerWrapper {
    public ChannelIncomeStatWrapper(Page<Map<String, Object>> stats) {
        super(stats);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createTime", DateUtils.getTimeFormat((Long) map.get("createTime")));
        map.put("date",map.get("date").toString().substring(0,10));
        ChannelSub channel = ConstantFactory.me().getChannelSubById((Integer) map.get("channelId"));
        if(channel != null){
            map.put("channelName",channel.getName());
            map.put("parentName",ConstantFactory.me().getChannelName(channel.getParentid()));
            map.put("packageName",ConstantFactory.me().getPackageName(channel.getPackageid()));
        }
    }
}
