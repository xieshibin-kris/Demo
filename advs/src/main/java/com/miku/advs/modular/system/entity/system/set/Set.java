package com.miku.advs.modular.system.entity.system.set;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * Created by hp on 2019/5/7.
 */
@TableName("rtdb_set")
public class Set implements Serializable {

    private String filed;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }
}
