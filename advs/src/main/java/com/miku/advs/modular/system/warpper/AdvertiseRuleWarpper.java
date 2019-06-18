package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * Created by hp on 2019/4/10.
 */
public class AdvertiseRuleWarpper extends BaseControllerWrapper {
    public AdvertiseRuleWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        String versionStr = (String) map.get("version");
        if(!ToolUtil.isEmpty(versionStr)){
            String[] versionArr = versionStr.split(",");
            StringBuffer versions = new StringBuffer();
            for (String version:versionArr){
                versions.append(ChannelDeviceWrapper.getSystemVersion(Integer.valueOf(version))+",");
            }
            map.put("version",versions.toString()==""?versions.toString():versions.substring(0,versions.lastIndexOf(",")));

        }

/*        String country = (String) map.get("country");
        if(!ToolUtil.isEmpty(country)) {
            String[] countryArr = country.split(",");
            StringBuffer countrys = new StringBuffer();
            for (String code:countryArr){
                Country bean = BeanFactory.me().getCountryByCode(code);
                if(bean != null){
                    countrys.append(bean.getZhName()+",");
                }
            }
            map.put("country",countrys.toString()==""?countrys.toString():countrys.substring(0,countrys.lastIndexOf(",")));
        }*/

    }
}
