package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.Channel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelMapper extends BaseMapper<Channel> {
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    List<Channel> selectAll();

    Page<Map<String, Object>> selectAlls(Page page, @Param("channelId") Integer channelId,@Param("channelName") String channelName);

    int updateStatus(@Param("channelId") Integer channelId, @Param("status") Integer status);
}