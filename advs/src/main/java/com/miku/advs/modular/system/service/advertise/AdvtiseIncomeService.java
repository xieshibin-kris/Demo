package com.miku.advs.modular.system.service.advertise;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.modular.system.entity.advertise.AdvertiseIncome;
import com.miku.advs.modular.system.mapper.advertise.AdvertiseIncomeMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/30.
 */
@Service
public class AdvtiseIncomeService extends ServiceImpl<AdvertiseIncomeMapper, AdvertiseIncome> {
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

    public int insert(AdvertiseIncome advertiseIncome) {
        return baseMapper.insert(advertiseIncome);
    }

    public int update(AdvertiseIncome advertiseIncome) {
        return baseMapper.updateByPrimaryKey(advertiseIncome);
    }

}
