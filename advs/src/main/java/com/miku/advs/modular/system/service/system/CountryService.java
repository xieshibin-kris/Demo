package com.miku.advs.modular.system.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.modular.system.entity.system.Country;
import com.miku.advs.modular.system.mapper.system.CountryMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hp on 2019/5/16.
 */
@Service
public class CountryService extends ServiceImpl<CountryMapper, Country> {

    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.COUNTRY_LIST+"'")
    public List<Country> selectAll() {
        return baseMapper.selectAll();
    }
}
