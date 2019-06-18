package com.miku.advs.modular.system.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.modular.system.entity.system.Domain;
import com.miku.advs.modular.system.mapper.system.DomainMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hp on 2019/4/19.
 */
@Service
public class DomainService extends ServiceImpl<DomainMapper, Domain> {

    public Page<Map<String, Object>> selectDomains(Page page,Integer status) {
        return baseMapper.selectAll(page, status);
    }

    public int update(Domain domain) {
        int row = baseMapper.updateByPrimaryKey(domain);
        if(row > 0){
            updateCacheDomainName();
        }
        return row;
    }

    public Page<Domain> selectDomainList(Integer status) {

        Page page = new Page();
        return baseMapper.selectDomainList(page,status);
    }

    @CachePut(value = Cache.CONSTANT,key = "'"+ CacheKey.DOMAIN_ACTIVE_NAME+"'")
    public String updateCacheDomainName(){
        return (String) ConstantFactory.me().getDomainsName() ;
    }
}
