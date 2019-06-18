package com.miku.advs.core.common.constant.factory;

import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.system.Country;
import com.miku.advs.modular.system.mapper.advertise.AdvRuleMapper;
import com.miku.advs.modular.system.mapper.system.CountryMapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Created by hp on 2019/5/16.
 */
@Component
@DependsOn("springContextHolder")
public class BeanFactory implements IBeanFactory{
    public static IBeanFactory me() {
        return SpringContextHolder.getBean("beanFactory");
    }

    private CountryMapper countryMapper = SpringContextHolder.getBean(CountryMapper.class);
    private AdvRuleMapper advRuleMapper = SpringContextHolder.getBean(AdvRuleMapper.class);

    @Override
    public Country getCountryByCode(String code) {
        return countryMapper.selectByCode(code);
    }

    @Override
    public AdvRule getAdvRuleById(Integer ruleid) {
        return advRuleMapper.selectById(ruleid);
    }
}
