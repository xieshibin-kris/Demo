package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.ip.CityUtils;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by hp on 2019/4/24.
 */
public class ChannelDeviceWrapper extends BaseControllerWrapper {
    public ChannelDeviceWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        ChannelSub channel = ConstantFactory.me().getChannelSubById((Integer) map.get("channelId"));
        if(channel != null){
            map.put("channelName",channel.getName());
            map.put("parentName",ConstantFactory.me().getChannelName(channel.getParentid()));

        }
        map.put("systemVersion",getSystemVersion((Integer) map.get("osversion")));
        try {
            String ip = (String) map.get("ip");
            if(StringUtils.isNotBlank(ip)){
                String country = CityUtils.getCityAddress(ip);
                map.put("country", country );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  String getSystemVersion(Integer version){
        switch (version){
            case 18:
                return "Android 4.3";
            case 19:
                return "Android 4.4";
            case 20:
                return "Android 4.4W";
            case 21:
                return "Android 5.0";
            case 22:
                return "Android 5.1";
            case 23:
                return "Android 6.0";
            case 24:
                return "Android 7.0";
            case 25:
                return "Android 7.1.1";
            case 26:
                return "Android 8.0";
            case 27:
                return "Android 8.1";
            case 28:
                return "Android 9.0";
            default:
                return "Android 4.3以下";

        }
    }
}
