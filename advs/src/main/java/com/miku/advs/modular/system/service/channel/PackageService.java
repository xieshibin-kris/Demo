package com.miku.advs.modular.system.service.channel;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.DatasourceEnum;
import com.miku.advs.modular.system.entity.channel.Package;
import com.miku.advs.modular.system.mapper.channel.PackageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hp on 2019/5/14.
 */
@Service
public class PackageService  extends ServiceImpl<PackageMapper, Package> {

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public List<Package> selectAll() {
        return baseMapper.selectAll();
    }
}
