package com.miku.advs.modular.system.mapper.advertise;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.advertise.AdvertiserHost;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdvertiserHostMapper extends BaseMapper<AdvertiserHost> {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertiserHost record);

    AdvertiserHost selectByPrimaryKey(Integer id);

    List<AdvertiserHost> selectAll();

    int updateByPrimaryKey(AdvertiserHost record);

    Page<Map<String, Object>> selectAdvertisers( Page page, @Param("id") Integer id,@Param("name") String name);
}