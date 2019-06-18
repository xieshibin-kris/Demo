package com.miku.advs.modular.system.warpper;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.channel.ChannelSub;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/8.
 */
public class AdvertiseWarpper extends BaseControllerWrapper {

    public AdvertiseWarpper(Map<String, Object> single) {
        super(single);
    }

    public AdvertiseWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public AdvertiseWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public AdvertiseWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }


    @Override
    protected void wrapTheMap(Map<String, Object> map) {

        map.put("hostName", ConstantFactory.me().getHostName((Integer) map.get("advertiserId")));
        ChannelSub channelSub = ConstantFactory.me().getChannelSubById((Integer) map.get("channelId"));
        if(channelSub != null){
            map.put("channelName",channelSub.getName());
        }
        map.put("createTime", DateUtils.getTimeFormat((Long) map.get("createTime")));
        map.put("adPosition",ConstantFactory.me().getDictsByName("广告展示位置", StrUtil.toString(map.get("adPosition"))) );

        map.put("adType", ConstantFactory.me().getDictsByName("广告类型", StrUtil.toString( map.get("adType"))));

    }

    public static String getAdposition(Integer position){
        switch (position){
            case 1:
                return "banner";
            case 2:
                return "插屏";
            default:
                return "";
        }
    }

    public static String getAdFormat(String format){
        StringBuffer formatStr = new StringBuffer("");

        String[] arr = format.split(",");

        if(arr.length>0){
            for(int i=0;i<arr.length;i++){
                switch (arr[i]){
                    case "1":
                        formatStr.append("应用内"+"，");
                        break;
                    case "2":
                        formatStr.append("应用外"+"，");
                        break;
                    case "3":
                        formatStr.append("桌面"+"，");
                        break;
                    default:
                        return "";
                }
            }
        }

        return formatStr.substring(0,formatStr.lastIndexOf("，"));

    }
}
