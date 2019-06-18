package com.miku.advs.modular.system.service.channel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.modular.system.entity.channel.DeviceStatus;
import com.miku.advs.modular.system.mapper.channel.DeviceStatusMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/26.
 */
@Service
public class ChannelDeviceStatusService extends ServiceImpl<DeviceStatusMapper, DeviceStatus> {
    public Page<Map<String, Object>> selectAllDeviceStatus(Page page, String uuid) {
        return baseMapper.selectAll(page,uuid);
    }

    public int insert(DeviceStatus deviceStatus) {
        deviceStatus.setCreatetime(System.currentTimeMillis());
        return baseMapper.insert(deviceStatus);
    }

    public int update(DeviceStatus deviceStatus) {
        return baseMapper.updateByPrimaryKey(deviceStatus);
    }
}
