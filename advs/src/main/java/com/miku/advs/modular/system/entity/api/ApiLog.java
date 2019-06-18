package com.miku.advs.modular.system.entity.api;

import java.io.Serializable;

public class ApiLog implements Serializable {
    private String uuid;

    private String errmessage;

    private Integer errtype;

    private String appversioncode;

    private String sdkversioncode;

    private Integer channelid;

    private Long createtime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getErrmessage() {
        return errmessage;
    }

    public void setErrmessage(String errmessage) {
        this.errmessage = errmessage == null ? null : errmessage.trim();
    }

    public Integer getErrtype() {
        return errtype;
    }

    public void setErrtype(Integer errtype) {
        this.errtype = errtype;
    }

    public String getAppversioncode() {
        return appversioncode;
    }

    public void setAppversioncode(String appversioncode) {
        this.appversioncode = appversioncode == null ? null : appversioncode.trim();
    }

    public String getSdkversioncode() {
        return sdkversioncode;
    }

    public void setSdkversioncode(String sdkversioncode) {
        this.sdkversioncode = sdkversioncode == null ? null : sdkversioncode.trim();
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}