package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.channel.ActiveUserRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActiveUserRecordMapper extends BaseMapper<ActiveUserRecord> {
    int deleteByPrimaryKey(String uuid);

    ActiveUserRecord selectByPrimaryKey(@Param("uuid") String uuid, @Param("tableName") String tableName);

    List<ActiveUserRecord> selectAll( @Param("tableName") String tableName);

    int updateByPrimaryKey(ActiveUserRecord record, @Param("tableName") String tableName);

    int insert( ActiveUserRecord userRecord);

    Map<String,Object> getActiveUserCount(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("channelId") Integer channelId, @Param("tableName")  String tableName);
}