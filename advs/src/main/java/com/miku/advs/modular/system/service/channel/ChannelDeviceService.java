package com.miku.advs.modular.system.service.channel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.modular.system.entity.api.UuidInfo;
import com.miku.advs.modular.system.mapper.api.UuidInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/24.
 */
@Service
public class ChannelDeviceService extends ServiceImpl<UuidInfoMapper, UuidInfo> {

    public Page<Map<String, Object>> selectAll(Page page, String uuid, Integer channelId) {
        return baseMapper.selectAll(page,uuid,channelId);
    }
}
