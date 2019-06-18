package com.miku.advs.modular.system.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.system.Relation;

import java.util.List;

public interface RelationMapper  extends BaseMapper<Relation> {
    int deleteByPrimaryKey(Long relationId);

    int insert(Relation record);

    Relation selectByPrimaryKey(Long relationId);

    List<Relation> selectAll();

    int updateByPrimaryKey(Relation record);
}