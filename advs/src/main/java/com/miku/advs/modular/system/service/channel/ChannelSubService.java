package com.miku.advs.modular.system.service.channel;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.mapper.channel.ChannelSubMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/5/13.
 */
@Service
public class ChannelSubService extends ServiceImpl<ChannelSubMapper, ChannelSub> {

    public Page<Map<String, Object>> selectAll(Page<Map<String, Object>> page, Integer id, String name) {
        return baseMapper.selectAll(page,id,name);
    }

    public int update(ChannelSub channelSub) {
        UpdateWrapper<ChannelSub> wrapper = new UpdateWrapper<>();
        wrapper.set("name",channelSub.getName());
        wrapper.set("active",channelSub.getActive());
        wrapper.set("remark",channelSub.getRemark());
        wrapper.eq("id",channelSub.getId());
        return baseMapper.update(channelSub,wrapper);
    }

    public List<ChannelSub> selectActiveChannels() {
        return baseMapper.selectActiveChannels();
    }
}
