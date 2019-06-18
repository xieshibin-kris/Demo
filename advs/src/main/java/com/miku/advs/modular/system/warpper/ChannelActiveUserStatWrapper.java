package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.modular.system.entity.channel.ChannelSub;

import java.util.Map;

/**
 * Created by hp on 2019/4/28.
 */
public class ChannelActiveUserStatWrapper extends BaseControllerWrapper {
    public ChannelActiveUserStatWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        ChannelSub channelSub = ConstantFactory.me().getChannelSubById((Integer) map.get("channelId"));
        if(channelSub!=null){
            map.put("channelName",channelSub.getName());
            map.put("parentName", ConstantFactory.me().getChannelName(channelSub.getParentid()));
            map.put("packageName",ConstantFactory.me().getPackageName(channelSub.getPackageid()));
        }

        map.put("date",map.get("date").toString().substring(0,10));

    }
}
