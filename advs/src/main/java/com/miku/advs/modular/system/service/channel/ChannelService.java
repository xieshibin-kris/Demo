package com.miku.advs.modular.system.service.channel;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.mapper.channel.ChannelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/12.
 */
@Service
public class ChannelService extends ServiceImpl<ChannelMapper, Channel> {


    public List<Channel> selectAll() {
       return baseMapper.selectAll();
    }

    public Page<Map<String, Object>> selectAll(Page page, Integer channelId, String channelName) {
        return baseMapper.selectAlls(page,channelId,channelName);
    }

    public int updateStatus(Integer channelId, Integer status) {
        return baseMapper.updateStatus(channelId,status);
    }

    public int update(Channel channel) {
        UpdateWrapper<Channel> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",channel.getId());
        wrapper.set("remark",channel.getRemark());
        wrapper.set("name",channel.getName());
        wrapper.set("active", channel.getActive());
        int row = baseMapper.update(channel,wrapper);
        if(row > 0 ){
            updateChannelName(channel.getId(),channel.getName());
        }
        return row;
    }

    @CachePut(value = Cache.CONSTANT, key = "'" + CacheKey.CHANNEL_NAME + "'+#channelId")
    public String updateChannelName(Integer channelId,String channelName) {
        return channelName;
    }
}
