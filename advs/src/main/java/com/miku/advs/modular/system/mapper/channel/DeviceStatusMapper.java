package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.channel.DeviceStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DeviceStatusMapper extends BaseMapper<DeviceStatus> {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceStatus record);

    DeviceStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceStatus record);

    Page<Map<String, Object>> selectAll(Page page,@Param("uuid") String uuid);
}