package com.miku.advs.system;

import com.miku.advs.base.BaseJunit;
import com.miku.advs.modular.system.mapper.system.DictMapper;
import com.miku.advs.modular.system.service.system.DictService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DictTest extends BaseJunit {

    @Resource
    DictService dictService;

    @Resource
    DictMapper dictMapper;

    @Test
    public void deleteTest() {
        this.dictService.delteDict(16L);
    }
}
