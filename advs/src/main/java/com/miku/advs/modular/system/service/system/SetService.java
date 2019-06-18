package com.miku.advs.modular.system.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.modular.system.mapper.system.SetMapper;
import com.miku.advs.modular.system.entity.system.set.Set;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/5/7.
 */
@Service
public class SetService extends ServiceImpl<SetMapper, Set> {

    public int updateSet(Set set) {
        return baseMapper.updateByPrimaryKey(set);
    }

    public String getSetValueByKey(String key) {
        Map<String,Object> setsMap = getSetMapsByCache();
        if(setsMap!=null){
           return (String) setsMap.get(key);
        }
        return "";
    }

    @Cacheable(value = Cache.CONSTANT,key = "'"+CacheKey.SETMAPKEY+"'")
    public Map<String, Object> getSetMapsByCache() {

        return getSetMaps();
    }

    @CachePut(value = Cache.CONSTANT,key = "'"+CacheKey.SETMAPKEY+"'")
    public Map<String, Object> updateSetMapsCache() {

        return getSetMaps();
    }

    public Map<String, Object> getSetMaps() {
        Map<String, Object> map = new HashMap<>();

        List<Set> list = baseMapper.selectList(null);
        for (Set set : list) {
            if(set != null){
                map.put(set.getFiled(), set.getValue());
            }
        }
        return map;
    }
}
