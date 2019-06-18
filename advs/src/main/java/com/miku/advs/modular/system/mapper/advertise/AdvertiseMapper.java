package com.miku.advs.modular.system.mapper.advertise;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvertiseMapper extends BaseMapper<Advertise> {
    int deleteByPrimaryKey(String id);

    int insert(Advertise record);

    Advertise selectByPrimaryKey(String id);

    int updateByPrimaryKey(Advertise record);

    Page<Map<String, Object>> selectAdvertises(Page page , @Param("id") String id, @Param("name") String name);

    List<Advertise> getListByCid(@Param("cid") int cid);

}