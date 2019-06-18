package com.miku.advs.modular.system.mapper.advertise;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.advertise.AdvertiseIncome;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface AdvertiseIncomeMapper extends BaseMapper<AdvertiseIncome> {
    int deleteByPrimaryKey(Long id);

    int insert(AdvertiseIncome record);

    AdvertiseIncome selectByPrimaryKey(Long id);

    Page<Map<String, Object>> selectAll(Page page, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("channelId") Integer channelId, @Param("advertiseId") String advertiseId);

    int updateByPrimaryKey(AdvertiseIncome record);
}