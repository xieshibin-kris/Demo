package com.miku.advs.modular.system.mapper.advertise;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvRuleMapper extends BaseMapper<AdvRule> {
    int deleteByPrimaryKey(Integer id);

    AdvRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdvRule record);

    Page<Map<String, Object>> selectAll(Page<Map<String, Object>> page,@Param("id") Integer id);

    List<AdvRule> selectAllRules();
}