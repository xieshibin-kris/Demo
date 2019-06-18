package com.miku.advs.modular.system.service.advertise;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.core.util.CacheUtil;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.mapper.advertise.AdvertiseMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/8.
 */
@Service
public class AdvertiseService extends ServiceImpl<AdvertiseMapper, Advertise> {
    public Page<Map<String, Object>> selectAdvertises(String id, String name) {

        Page page = LayuiPageFactory.defaultPage();
        return baseMapper.selectAdvertises(page,id,name);
    }

    public int update(Advertise advertise) {
        int row = baseMapper.updateByPrimaryKey(advertise);
        if(row > 0){
            updateCache(advertise.getId());
        }
        return row;
    }

    public Advertise getAdvertiseById(String id) {
        Advertise advertise = CacheUtil.get(Cache.CONSTANT,CacheKey.ADVERTISE_BEAN+id);
        if(advertise == null){
            advertise = baseMapper.selectByPrimaryKey(id);
            if(advertise!= null){
                CacheUtil.put(Cache.CONSTANT,CacheKey.ADVERTISE_BEAN+id,advertise);
            }
        }
        return advertise;

    }

    public List<Advertise> getListByCid(int cid) {
        List<Advertise> ads = baseMapper.getListByCid(cid);

        return ads;

    }

    @CacheEvict(value = Cache.CONSTANT,key = "'"+CacheKey.ADVERTISE_BEAN+"'+#id")
    public void delete(String id) {
        baseMapper.deleteById(id);
    }

    public void updateCache(String id){
        Advertise advertise = baseMapper.selectByPrimaryKey(id);
        CacheUtil.put(Cache.CONSTANT,CacheKey.ADVERTISE_BEAN+id,advertise);
    }

}
