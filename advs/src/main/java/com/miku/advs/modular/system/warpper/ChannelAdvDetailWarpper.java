package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.BeanFactory;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.entity.channel.ChannelSub;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/23.
 */
public class ChannelAdvDetailWarpper extends BaseControllerWrapper {
    public ChannelAdvDetailWarpper(Map<String, Object> single) {
        super(single);
    }

    public ChannelAdvDetailWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ChannelAdvDetailWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ChannelAdvDetailWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }


    @Override
    protected void wrapTheMap(Map<String, Object> map) {

        Advertise advertise =  ConstantFactory.me().getAdvertiseById((String) map.get("advertiseId"));
        DecimalFormat df = new DecimalFormat("#0.0");
        if(advertise!=null){
            AdvRule advRule = BeanFactory.me().getAdvRuleById(advertise.getRuleid());
            map.put("adPosition",AdvertiseWarpper.getAdposition(advertise.getAdposition()));
            if(advRule!= null){
                map.put("clickRate",(df.format(((Integer)map.get("clickCount")/(double)advRule.getDayshowcount())*100)+"%"));
                map.put("showRate",(df.format(((Integer)map.get("showCount")/(double)advRule.getDayshowcount())*100)+"%"));
            }
        }
        map.put("date",map.get("date").toString().substring(0,10));

        ChannelSub channel = ConstantFactory.me().getChannelSubById((Integer) map.get("channelId"));
        if(channel != null){
            map.put("channelName",channel.getName());
            map.put("parentName",ConstantFactory.me().getChannelName(channel.getParentid()));

        }

    }
}
