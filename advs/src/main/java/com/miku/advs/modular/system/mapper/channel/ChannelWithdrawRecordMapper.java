package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.ChannelWithdrawRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ChannelWithdrawRecordMapper extends BaseMapper<ChannelWithdrawRecord> {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelWithdrawRecord record);

    ChannelWithdrawRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ChannelWithdrawRecord record);

    Page<Map<String, Object>> selectAll(Page page, @Param("startTime") Long startTime, @Param("endTime") Long endTime,
                                        @Param("account")  String account);}