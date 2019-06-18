package com.miku.advs.modular.system.entity.channel;

import java.io.Serializable;

public class ActiveUserRecord implements Serializable {

    private String uuid;

    private Integer channelid;

    private Integer channelsubid;

    private Long createtime;

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Integer getChannelsubid() {
        return channelsubid;
    }

    public void setChannelsubid(Integer channelsubid) {
        this.channelsubid = channelsubid;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}