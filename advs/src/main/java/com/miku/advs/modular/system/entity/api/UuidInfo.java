package com.miku.advs.modular.system.entity.api;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("rtdb_uuidinfo")
public class UuidInfo {

    private Long id;

    private String uuid;

    private String imei;

    private String imsi;

    private String factory;

    private String sdkversion;

    private Integer channelid;

    private Long totalspacesize;

    private Long cardspacesize;

    private String rate;

    private String model;

    private String packagename;

    private String appversion;

    private String mac;

    private Long createtime;

    private Integer osversion;

    private String net;

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getOsversion() {
        return osversion;
    }

    public void setOsversion(Integer osversion) {
        this.osversion = osversion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getSdkversion() {
        return sdkversion;
    }

    public void setSdkversion(String sdkversion) {
        this.sdkversion = sdkversion == null ? null : sdkversion.trim();
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Long getTotalspacesize() {
        return totalspacesize;
    }

    public void setTotalspacesize(Long totalspacesize) {
        this.totalspacesize = totalspacesize;
    }

    public Long getCardspacesize() {
        return cardspacesize;
    }

    public void setCardspacesize(Long cardspacesize) {
        this.cardspacesize = cardspacesize;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename == null ? null : packagename.trim();
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion == null ? null : appversion.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }


    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
}