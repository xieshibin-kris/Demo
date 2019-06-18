package com.miku.advs.modular.system.service.advertise;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.core.util.CacheUtil;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.mapper.advertise.AdvRuleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/5/15.
 */
@Service
public class AdvertiseRuleService extends ServiceImpl<AdvRuleMapper, AdvRule> {

    public Page<Map<String, Object>> selectAdvRule(Page<Map<String, Object>> page, Integer id) {
        return baseMapper.selectAll(page,id);
    }

    public int updateDate(AdvRule rule) {
        return baseMapper.updateByPrimaryKey(rule);
    }

    public List<AdvRule> selectAllRule() {
        return baseMapper.selectAllRules();
    }
    public AdvRule selectByPrimaryKey(int id){
        return baseMapper.selectByPrimaryKey(id);
    }

}
