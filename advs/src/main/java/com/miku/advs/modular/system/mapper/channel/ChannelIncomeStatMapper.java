package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.ChannelIncomeStat;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface ChannelIncomeStatMapper extends BaseMapper<ChannelIncomeStat> {
    int deleteByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid);

    int insert(ChannelIncomeStat record);

    ChannelIncomeStat selectByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid);

    int updateByPrimaryKey(ChannelIncomeStat record);

    Page<Map<String, Object>> selectAll(Page page,@Param("startDate")  String startDate, @Param("endDate") String endDate,
                                        @Param("channelId")  Integer channelId,@Param("parentId")  Integer parentId,@Param("packageId")  Integer packageId);
}