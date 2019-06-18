package com.miku.advs.modular.system.mapper.api;

import com.miku.advs.modular.system.entity.api.ApiLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApiLogMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ApiLog record);

    ApiLog selectByPrimaryKey(String uuid);

    List<ApiLog> selectAll();

    int updateByPrimaryKey(ApiLog record);
}