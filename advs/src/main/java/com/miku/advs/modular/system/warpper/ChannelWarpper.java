package com.miku.advs.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/8.
 */
public class ChannelWarpper extends BaseControllerWrapper {

    public ChannelWarpper(Map<String, Object> single) {
        super(single);
    }

    public ChannelWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ChannelWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ChannelWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }


    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }


}
