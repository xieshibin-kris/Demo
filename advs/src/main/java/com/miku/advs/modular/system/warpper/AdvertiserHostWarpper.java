package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * Created by hp on 2019/4/2.
 */
public class AdvertiserHostWarpper extends BaseControllerWrapper {

    public AdvertiserHostWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

        map.put("typeName",getNameByType((Integer) map.get("type"))); //广告主类型
        map.put("modeName",getNameByMode((Integer)map.get("mode")));  //合作方式

    }

    public static String getNameByType(Integer type){
        switch (type){
            case 1:
                return "admob";
            case 2:
                return "adsense";
            case 3:
                return "接码";
            case 4:
                return "网盟";
            case 5:
                return "应用";
            default:
                return "其他";
        }
    }

    public static String getNameByMode(Integer mode) {
        if(mode == 1){
            return "CPA";
        } else {
            return "CPS";
        }
    }
}
