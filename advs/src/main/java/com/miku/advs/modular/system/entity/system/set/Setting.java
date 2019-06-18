package com.miku.advs.modular.system.entity.system.set;

import java.io.Serializable;

/**
 * Created by hp on 2019/5/7.
 */
public class Setting implements Serializable {

    private String apiRequestInterval;

    public String getApiRequestInterval() {
        return apiRequestInterval;
    }

    public void setApiRequestInterval(String apiRequestInterval) {
        this.apiRequestInterval = apiRequestInterval;
    }
}
