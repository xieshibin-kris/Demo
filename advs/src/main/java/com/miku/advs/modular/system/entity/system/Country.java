package com.miku.advs.modular.system.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("rtdb_dict_country")
public class Country implements Serializable {
    private Short id;

    private String name;

    private String zhName;

    private String code;

    private String code2;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2 == null ? null : code2.trim();
    }
}