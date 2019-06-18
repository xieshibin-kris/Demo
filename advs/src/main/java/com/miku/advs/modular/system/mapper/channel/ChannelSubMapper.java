package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelSubMapper extends BaseMapper<ChannelSub> {
    int deleteByPrimaryKey(Integer id);

    int insert(ChannelSub record);

    ChannelSub selectByPrimaryKey(Integer id);

    Page<Map<String, Object>> selectAll(Page<Map<String, Object>> page, @Param("channelId") Integer channelId,@Param("channelName") String channelName);

    int updateByPrimaryKey(ChannelSub record);

    List<ChannelSub> selectActiveChannels();
}