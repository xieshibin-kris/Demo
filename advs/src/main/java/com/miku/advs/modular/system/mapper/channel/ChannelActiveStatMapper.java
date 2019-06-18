package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.ChannelActiveStat;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface ChannelActiveStatMapper extends BaseMapper<ChannelActiveStat> {
    int deleteByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid);

    int insert(ChannelActiveStat record);

    ChannelActiveStat selectByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid);

    Page<Map<String,Object>> selectAll(Page page, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("channelId")  Integer channelId, @Param("parentId") Integer parentId,@Param("packageId") Integer packageId);

    int updateByPrimaryKey(ChannelActiveStat record);

    int updateActiveUserCount(@Param("date") Date date, @Param("channelId") Integer channelId);

    int updateNewUserCount(@Param("date") Date date, @Param("channelId") Integer channelId);

}