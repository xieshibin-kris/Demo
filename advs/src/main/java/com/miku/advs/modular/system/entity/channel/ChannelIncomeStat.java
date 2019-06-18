package com.miku.advs.modular.system.entity.channel;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("rtdb_channel_income_date_stat")
public class ChannelIncomeStat {
    private String date;

    private Integer channelid;

    private BigDecimal predictincome;

    private BigDecimal realincome;

    private String remark;

    private Long createtime;

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