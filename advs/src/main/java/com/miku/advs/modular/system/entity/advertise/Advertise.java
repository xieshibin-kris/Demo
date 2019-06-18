package com.miku.advs.modular.system.entity.advertise;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("rtdb_adv")
public class Advertise implements Serializable {

    private String id;

    private Integer channelid;

    @NotNull(message = "广告主id不能为空")
    private Integer advertiserid;

    @NotBlank(message = "名称不能为空")
    private String name;

    private Integer ruleid;

    private Integer status;

    private Integer adposition;

    private Integer adtype;

    private String remark;

    private Long updatetime;

    private Long createtime;

    private Integer weight;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Integer getAdvertiserid() {
        return advertiserid;
    }

    public void setAdvertiserid(Integer advertiserid) {
        this.advertiserid = advertiserid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdposition() {
        return adposition;
    }

    public void setAdposition(Integer adposition) {
        this.adposition = adposition;
    }

    public Integer getAdtype() {
        return adtype;
    }

    public void setAdtype(Integer adtype) {
        this.adtype = adtype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}