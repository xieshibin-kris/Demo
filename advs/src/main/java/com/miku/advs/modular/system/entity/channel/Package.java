package com.miku.advs.modular.system.entity.channel;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("rtdb_installappinfo")
public class Package {
    private Integer id;

    private String pakage;

    private String appname;

    private Integer isdecrypt;

    private String version;

    private String url;

    private String info;

    private Integer starttype;

    private String startentrance;

    private Integer installfile;

    private Integer weigth;

    private Integer downcount;

    private Integer downstatus;

    private Integer openbeginhour;

    private Integer openendhour;

    private String shieldtime;

    private Integer iswifi;

    private Integer isimsi;

    private Integer secondstart;

    private Integer secondstartcnt;

    private String sdkversion;

    private Integer provider;

    private Integer userid;

    private Date createtime;

    private Integer downhourcount;

    private Boolean runagain;

    private String runshieldtime;

    private Integer runtimes;

    private Integer runtimelimit;

    private Integer runtaskday;

    private String runway;

    private Integer newusereffective;

    private String elfverlimit;

    private Boolean isforcesys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage == null ? null : pakage.trim();
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname == null ? null : appname.trim();
    }

    public Integer getIsdecrypt() {
        return isdecrypt;
    }

    public void setIsdecrypt(Integer isdecrypt) {
        this.isdecrypt = isdecrypt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getStarttype() {
        return starttype;
    }

    public void setStarttype(Integer starttype) {
        this.starttype = starttype;
    }

    public String getStartentrance() {
        return startentrance;
    }

    public void setStartentrance(String startentrance) {
        this.startentrance = startentrance == null ? null : startentrance.trim();
    }

    public Integer getInstallfile() {
        return installfile;
    }

    public void setInstallfile(Integer installfile) {
        this.installfile = installfile;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Integer getDowncount() {
        return downcount;
    }

    public void setDowncount(Integer downcount) {
        this.downcount = downcount;
    }

    public Integer getDownstatus() {
        return downstatus;
    }

    public void setDownstatus(Integer downstatus) {
        this.downstatus = downstatus;
    }

    public Integer getOpenbeginhour() {
        return openbeginhour;
    }

    public void setOpenbeginhour(Integer openbeginhour) {
        this.openbeginhour = openbeginhour;
    }

    public Integer getOpenendhour() {
        return openendhour;
    }

    public void setOpenendhour(Integer openendhour) {
        this.openendhour = openendhour;
    }

    public String getShieldtime() {
        return shieldtime;
    }

    public void setShieldtime(String shieldtime) {
        this.shieldtime = shieldtime == null ? null : shieldtime.trim();
    }

    public Integer getIswifi() {
        return iswifi;
    }

    public void setIswifi(Integer iswifi) {
        this.iswifi = iswifi;
    }

    public Integer getIsimsi() {
        return isimsi;
    }

    public void setIsimsi(Integer isimsi) {
        this.isimsi = isimsi;
    }

    public Integer getSecondstart() {
        return secondstart;
    }

    public void setSecondstart(Integer secondstart) {
        this.secondstart = secondstart;
    }

    public Integer getSecondstartcnt() {
        return secondstartcnt;
    }

    public void setSecondstartcnt(Integer secondstartcnt) {
        this.secondstartcnt = secondstartcnt;
    }

    public String getSdkversion() {
        return sdkversion;
    }

    public void setSdkversion(String sdkversion) {
        this.sdkversion = sdkversion == null ? null : sdkversion.trim();
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getDownhourcount() {
        return downhourcount;
    }

    public void setDownhourcount(Integer downhourcount) {
        this.downhourcount = downhourcount;
    }

    public Boolean getRunagain() {
        return runagain;
    }

    public void setRunagain(Boolean runagain) {
        this.runagain = runagain;
    }

    public String getRunshieldtime() {
        return runshieldtime;
    }

    public void setRunshieldtime(String runshieldtime) {
        this.runshieldtime = runshieldtime == null ? null : runshieldtime.trim();
    }

    public Integer getRuntimes() {
        return runtimes;
    }

    public void setRuntimes(Integer runtimes) {
        this.runtimes = runtimes;
    }

    public Integer getRuntimelimit() {
        return runtimelimit;
    }

    public void setRuntimelimit(Integer runtimelimit) {
        this.runtimelimit = runtimelimit;
    }

    public Integer getRuntaskday() {
        return runtaskday;
    }

    public void setRuntaskday(Integer runtaskday) {
        this.runtaskday = runtaskday;
    }

    public String getRunway() {
        return runway;
    }

    public void setRunway(String runway) {
        this.runway = runway == null ? null : runway.trim();
    }

    public Integer getNewusereffective() {
        return newusereffective;
    }

    public void setNewusereffective(Integer newusereffective) {
        this.newusereffective = newusereffective;
    }

    public String getElfverlimit() {
        return elfverlimit;
    }

    public void setElfverlimit(String elfverlimit) {
        this.elfverlimit = elfverlimit == null ? null : elfverlimit.trim();
    }

    public Boolean getIsforcesys() {
        return isforcesys;
    }

    public void setIsforcesys(Boolean isforcesys) {
        this.isforcesys = isforcesys;
    }
}