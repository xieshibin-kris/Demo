package com.miku.advs.modular.system.service.channel;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.channel.ChannelIncomeStat;
import com.miku.advs.modular.system.mapper.channel.ChannelIncomeStatMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
@Service
public class ChannelIncomeStatService extends ServiceImpl<ChannelIncomeStatMapper, ChannelIncomeStat> {

    public Page<Map<String, Object>> selectAllStats(Page page, String dateParam, Integer channelId, Integer parentId, Integer packageId) {
        String startDate = "";
        String endDate = "";
        if(!ToolUtil.isEmpty(dateParam)){
            String[] arrs = dateParam.split(" - ");
            startDate = arrs[0];
            endDate = arrs[1];
        }
        return baseMapper.selectAll(page,startDate,endDate,channelId,parentId,packageId);
    }

    public int insert(ChannelIncomeStat channelIncomeStat) {
        channelIncomeStat.setCreatetime(System.currentTimeMillis());
        return baseMapper.insert(channelIncomeStat);
    }

    public int update(ChannelIncomeStat channelIncomeStat) {
        return baseMapper.updateByPrimaryKey(channelIncomeStat);
    }

    public ChannelIncomeStat getById(String date, Integer channelId) {
        return baseMapper.selectByPrimaryKey(DateUtils.getDayFromStr(date),channelId);
    }

    public int delete(String date, Integer channelId) {
        return baseMapper.deleteByPrimaryKey(DateUtils.getDayFromStr(date),channelId);
    }
}
