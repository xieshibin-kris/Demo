package com.miku.advs.core.common.constant.factory;

import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.system.Country;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by hp on 2019/5/16.
 */
public interface IBeanFactory {
    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.COUNTRY_CODE+"'+#code")
    Country getCountryByCode(String code);

    AdvRule getAdvRuleById(Integer ruleid);
}
