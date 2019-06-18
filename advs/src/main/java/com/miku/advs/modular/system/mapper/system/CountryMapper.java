package com.miku.advs.modular.system.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.system.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountryMapper extends BaseMapper<Country> {
    int deleteByPrimaryKey(Short id);

    int insert(Country record);

    Country selectByPrimaryKey(Short id);

    List<Country> selectAll();

    int updateByPrimaryKey(Country record);

    Country selectByCode(@Param("code") String code);
}