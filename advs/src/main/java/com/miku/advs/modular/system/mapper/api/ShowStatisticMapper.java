package com.miku.advs.modular.system.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.modular.system.entity.api.ShowStatistic;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface ShowStatisticMapper extends BaseMapper<ShowStatistic> {
    int deleteByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid, @Param("advertiseid") String advertiseid );

    int insert(ShowStatistic record);

    ShowStatistic selectByPrimaryKey(@Param("date") Date date, @Param("channelid") Integer channelid, @Param("advertiseid") String advertiseid );

    int updateByPrimaryKey(ShowStatistic record);

    int updateBySum(ShowStatistic show);

    Page<Map<String, Object>> selectAll(Page page,@Param("startDate") String startDate,@Param("endDate")  String endDate,
                                        @Param("channelId") Integer channelId,@Param("advertiseId") String advertiseId);

}