package com.miku.advs.modular.system.entity.api;

import java.io.Serializable;
import java.util.Date;

public class ShowStatistic implements Serializable {
    private Date date;

    private Integer channelid;

    private String advertiseid;

    private String uuid;

    private Integer requestcount;

    private Integer showcount;

    private Integer clickcount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public String getAdvertiseid() {
        return advertiseid;
    }

    public void setAdvertiseid(String advertiseid) {
        this.advertiseid = advertiseid == null ? null : advertiseid.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getRequestcount() {
        return requestcount;
    }

    public void setRequestcount(Integer requestcount) {
        this.requestcount = requestcount;
    }

    public Integer getShowcount() {
        return showcount;
    }

    public void setShowcount(Integer showcount) {
        this.showcount = showcount;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }
}