package com.miku.advs.modular.system.entity.channel;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("rtdb_channel_withdraw_record")
public class ChannelWithdrawRecord {
    private Long id;

    private String account;

    private BigDecimal predictincome;

    private BigDecimal realincome;

    private String remark;

    private Long createtime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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