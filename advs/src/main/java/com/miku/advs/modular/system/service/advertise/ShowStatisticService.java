package com.miku.advs.modular.system.service.advertise;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.util.CacheUtil;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.api.ShowStatistic;
import com.miku.advs.modular.system.mapper.api.ShowStatisticMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by hp on 2019/4/18.
 */
@Service
public class ShowStatisticService extends ServiceImpl<ShowStatisticMapper, ShowStatistic> {


    /**
     * 判断bean是否存在
     * @param date
     * @param channelid
     * @param advertiseid
     * @return
     */
    public boolean selectByPrimaryKey(Date date, Integer channelid, String advertiseid ) {

        String key = DateUtils.getDateFormat("yyyyMMdd",date)+channelid+advertiseid+"";

        System.out.println("key:"+key);
        ShowStatistic bean =CacheUtil.get(Cache.CONSTANT,key);

        if(bean != null ){
            return true;
        }

        bean = baseMapper.selectByPrimaryKey(date, channelid, advertiseid );

        if(bean != null){
            CacheUtil.put(Cache.CONSTANT,key,bean);
            return true;
        }
        return false;
    }


    public int updateBySum(ShowStatistic show) {
        return baseMapper.updateBySum(show);
    }

    public int insert(ShowStatistic show) {
        return baseMapper.insert(show);
    }

    public Page<Map<String, Object>> selectAll(Page page, String dateParam, Integer channelId, String advertiseId) {
        String startDate = "";
        String endDate = "";
        if(!ToolUtil.isEmpty(dateParam)){
            String[] arrs = dateParam.split(" - ");
            startDate = arrs[0];
            endDate = arrs[1];
        }
        return baseMapper.selectAll(page,startDate,endDate,channelId,advertiseId);
    }
}
