package com.miku.advs.modular.system.service.advertise;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.core.util.CacheUtil;
import com.miku.advs.modular.system.entity.advertise.AdvertiserHost;
import com.miku.advs.modular.system.mapper.advertise.AdvertiserHostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by hp on 2019/4/2.
 */

@Service
public class AdvertiserHostService extends ServiceImpl<AdvertiserHostMapper, AdvertiserHost> {

    @Resource
    private AdvertiserHostMapper advertiserMapper;

    public Page<Map<String, Object>> selectAdvertisers(Integer id, String name) {

        Page page = LayuiPageFactory.defaultPage();
        return baseMapper.selectAdvertisers(page, id,name);
    }


    public int add(AdvertiserHost advertiser) {
        return baseMapper.insert(advertiser);
    }

    public int edit(AdvertiserHost advertiser) {
        int row = baseMapper.updateByPrimaryKey(advertiser);
        if( row > 0 ){
            CacheUtil.put(Cache.CONSTANT,CacheKey.ADVERTISER_HOSE_NAME+advertiser.getId(),advertiser.getName());
        }
        return row;
    }

    public int delete(Integer id) {
        int row = baseMapper.deleteByPrimaryKey(id);
        if( row > 0 ){
            CacheUtil.remove(Cache.CONSTANT,CacheKey.ADVERTISER_HOSE_NAME+id);
        }
        return row;
    }
}
