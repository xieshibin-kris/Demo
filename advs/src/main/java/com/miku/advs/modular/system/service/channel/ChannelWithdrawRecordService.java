package com.miku.advs.modular.system.service.channel;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.channel.ChannelWithdrawRecord;
import com.miku.advs.modular.system.mapper.channel.ChannelWithdrawRecordMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
@Service
public class ChannelWithdrawRecordService extends ServiceImpl<ChannelWithdrawRecordMapper, ChannelWithdrawRecord> {

    public Page<Map<String, Object>> selectAllRecords(Page<Map<String, Object>> page, String dateParam, String account) {
        Long startTime = 0L;
        Long endTime = 0L;
        if(!ToolUtil.isEmpty(dateParam)){
            String[] arrs = dateParam.split(" - ");
            startTime = DateUtils.getDateFromStr(arrs[0]+" 00:00:00").getTime();
            endTime =  DateUtils.getDateFromStr(arrs[1]+" 23:59:59").getTime();
        }
        return baseMapper.selectAll(page,startTime,endTime, account);
    }

    public int insert(ChannelWithdrawRecord channelWithdrawRecord) {
        channelWithdrawRecord.setCreatetime(System.currentTimeMillis());
        return baseMapper.insert(channelWithdrawRecord);
    }

    public int update(ChannelWithdrawRecord channelWithdrawRecord) {
        return baseMapper.updateByPrimaryKey(channelWithdrawRecord);
    }

    public int delete(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
}
