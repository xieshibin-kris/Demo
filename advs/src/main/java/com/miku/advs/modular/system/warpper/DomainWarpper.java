package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.util.DateUtils;

import java.util.Map;

/**
 * Created by hp on 2019/4/19.
 */
public class DomainWarpper extends BaseControllerWrapper {
    public DomainWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createTime", DateUtils.getTimeFormat((Long)map.get("createTime")));
    }
}
