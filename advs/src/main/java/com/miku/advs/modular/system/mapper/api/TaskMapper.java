package com.miku.advs.modular.system.mapper.api;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hp on 2019/4/28.
 */
@Component
public interface TaskMapper {

    void createDefalutTable(@Param("sql") String sql);

    void createSysTable(@Param("sql")String sql);

    Map<String,Object> getTableName(@Param("destTableName") String destTableName);
}
