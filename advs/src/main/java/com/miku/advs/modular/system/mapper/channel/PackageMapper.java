package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.channel.Package;

import java.util.List;

public interface PackageMapper extends BaseMapper<Package> {
    int deleteByPrimaryKey(Integer id);

    int insert(Package record);

    Package selectByPrimaryKey(Integer id);

    List<Package> selectAll();

    int updateByPrimaryKey(Package record);
}