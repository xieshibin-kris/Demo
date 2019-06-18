package com.miku.advs.modular.system.service.channel;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.modular.system.entity.channel.ChannelActiveStat;
import com.miku.advs.modular.system.mapper.channel.ChannelActiveStatMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by hp on 2019/4/28.
 */
@Service
public class ChannelActiveUserStatService extends ServiceImpl<ChannelActiveStatMapper, ChannelActiveStat> {
    public Page<Map<String, Object>> selectAll(Page page, String dateParam, Integer channelId, Integer parentId, Integer packageId) {
        String startDate = "";
        String endDate = "";
        if(!ToolUtil.isEmpty(dateParam)){
            String[] arrs = dateParam.split(" - ");
            startDate = arrs[0];
            endDate = arrs[1];
        }
        return baseMapper.selectAll(page,startDate,endDate,channelId,parentId,packageId);
    }

    public int updateActiveUser(Date date, Integer channelId) {
        return baseMapper.updateActiveUserCount(date,channelId);
    }

    public int updateNewUserCount(Date date, Integer channelid) {
        return baseMapper.updateNewUserCount(date,channelid);
    }
}
