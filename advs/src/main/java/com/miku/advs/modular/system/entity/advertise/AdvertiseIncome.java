package com.miku.advs.modular.system.entity.advertise;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.miku.advs.core.util.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("rtdb_adv_income")
public class AdvertiseIncome implements Serializable {

    @TableId()
    private Long id;

    private String advertiseid;

    private Date date;

    private BigDecimal predictincome;

    private BigDecimal realincome;

    private String remark;

    private Long createtime;

    private Integer channelid;

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertiseid() {
        return advertiseid;
    }

    public void setAdvertiseid(String advertiseid) {
        this.advertiseid = advertiseid == null ? null : advertiseid.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = DateUtils.getDayFromStr(date);
    }

    public BigDecimal getPredictincome() {
        return predictincome;
    }

    public void setPredictincome(BigDecimal predictincome) {
        this.predictincome = predictincome;
    }

    public BigDecimal getRealincome() {
        return realincome;
    }

    public void setRealincome(BigDecimal realincome) {
        this.realincome = realincome;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}