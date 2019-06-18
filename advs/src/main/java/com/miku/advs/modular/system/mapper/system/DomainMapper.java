package com.miku.advs.modular.system.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.system.Domain;
import org.apache.ibatis.annotations.Param;

import javax.naming.Name;
import java.util.Map;

public interface DomainMapper extends BaseMapper<Domain> {
    int deleteByPrimaryKey(Integer id);

    int insert(Domain record);

    Domain selectByPrimaryKey(Integer id);

    Page<Map<String, Object>> selectAll(Page page,@Param("status") Integer status);

    int updateByPrimaryKey(Domain record);

    Page<Domain> selectDomainList(Page page,@Param("status") Integer status);
}