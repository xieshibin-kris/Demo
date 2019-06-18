package com.miku.advs.system;

import com.miku.advs.base.BaseJunit;
import com.miku.advs.modular.system.mapper.system.UserMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

import static javafx.scene.input.KeyCode.S;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMapper userMapper;

    @Test
    public void userTest() throws Exception {

        ArrayList<Double> arrayList = new ArrayList<Double>(){
            {
                add(3.0);
                add(2.0);
                add(5.0);
            }
        };
        String c = "";

        arrayList.forEach((e)->System.out.println(e.intValue()));
        Collections.sort(arrayList);
        arrayList.forEach((e)->System.out.println(e.intValue()));
    }

}
