package com.miku.advs.modular.system.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.api.UuidInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UuidInfoMapper extends BaseMapper<UuidInfo> {
    int insert(UuidInfo record);

    Page<Map<String, Object>> selectAll(Page page, @Param("uuid") String uuid,@Param("channelId") Integer channelId);

    Page<Map<String, Object>> selectnewUserCount(Page page, @Param("startTime")  Long startTime, @Param("endTime")  Long endTime, @Param("channelId")  String channelId);

    UuidInfo selectByUuid( @Param("uuid") String uuid);
}