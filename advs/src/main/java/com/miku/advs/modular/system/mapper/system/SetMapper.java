package com.miku.advs.modular.system.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.system.set.Set;

import java.util.List;

public interface SetMapper extends BaseMapper<Set> {
    int deleteByPrimaryKey(String key);

    int insert(Set record);

    Set selectByPrimaryKey(String key);

    List<Set> selectAll();

    int updateByPrimaryKey(Set record);
}