package com.miku.advs.modular.system.entity.channel;

import com.baomidou.mybatisplus.annotation.TableName;


@TableName("rtdb_channel_activeuser_stat")
public class ChannelActiveStat {

    private String date;

    private Integer channelid;

    private Integer parentid;

    private Integer packageid;

    private Integer usercount;

    private Integer newusercount;

    private Integer activeusercount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    public Integer getNewusercount() {
        return newusercount;
    }

    public void setNewusercount(Integer newusercount) {
        this.newusercount = newusercount;
    }

    public Integer getActiveusercount() {
        return activeusercount;
    }

    public void setActiveusercount(Integer activeusercount) {
        this.activeusercount = activeusercount;
    }
}