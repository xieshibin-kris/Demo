package com.miku.advs.modular.system.service.api;

import cn.stylefeng.roses.core.util.HttpContext;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miku.advs.core.api.ApiTaskFactory;
import com.miku.advs.core.api.ApiThreadManager;
import com.miku.advs.core.common.constant.Const;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.core.util.HttpEncryptUtil;
import com.miku.advs.core.util.UUIDUtils;
import com.miku.advs.core.util.ip.CityUtils;
import com.miku.advs.core.util.ip.IpAddrUtils;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.entity.api.ApiLog;
import com.miku.advs.modular.system.entity.system.set.SettingKey;
import com.miku.advs.modular.system.mapper.api.ApiLogMapper;
import com.miku.advs.modular.system.service.advertise.AdvertiseRuleService;
import com.miku.advs.modular.system.service.advertise.AdvertiseService;
import com.miku.advs.modular.system.service.system.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * Created by hp on 2019/4/16.
 */
@Service
public class SystemApiService {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AdvertiseService advertiseService;

    @Resource
    private AdvertiseRuleService advertiseRuleService;

    @Autowired
    private SetService setService;

    @Resource
    private ApiLogMapper apiLogMapper;

    public JSONObject getConfig(JSONObject jsonParam) {
        JSONObject jsonObject = new JSONObject();
        try {
            String uuid = jsonParam.getString("uuid");

            if (StringUtils.isEmpty(uuid)) {
                uuid = "";
            }
            String ip = IpAddrUtils.getIpAddr(HttpContext.getRequest());
            //持久化设备信息
            ApiThreadManager.getInstance().execute(ApiTaskFactory.saveUuidInfoTask(uuid, jsonParam, ip));
            //统计活跃用户
            ApiThreadManager.getInstance().execute(ApiTaskFactory.addActiverRecordTask(uuid, jsonParam.getInteger("cid")));

            jsonObject.put("cycle", setService.getSetValueByKey(SettingKey.apiRequestInterval.toString()));//客户端轮循请求周期
            jsonObject.put("domain", ConstantFactory.me().getDomainName());//接口域名

            int cid = jsonParam.getInteger("cid");
            String packageName = jsonParam.getString("packageName");
            JSONArray aids = jsonParam.getJSONArray("aids");
            Advertise advertise;
            JSONObject aidJson;


            List<Advertise> ads = advertiseService.getListByCid(1);
            List<Advertise> advs = new ArrayList<Advertise>();

            for(Advertise adv : ads){

                AdvRule adRule = advertiseRuleService.selectByPrimaryKey(adv.getRuleid());

                int showCount = jsonParam.getInteger("showCount");
                if (showCount>=adRule.getDayshowcount()) {
                    continue;
                }

                String Country = CityUtils.getCityAddress("53.45.252.0");
                int hour = DateUtils.getCurrentHour();
                if (!adRule.getCountry().contains(Country)) {
                    continue;
                }

                String hours = "";
                if (hour<10){
                    hours = 0+ Integer.toString(hour);
                }else{
                    hours = Integer.toString(hour);
                }
                if (!adRule.getTimerange().contains(hours)) {
                    continue;
                }

                String osversion = jsonParam.getString("osversion");
                String[] version = adRule.getVersion().split(",");
                if (!Arrays.asList(version).contains(osversion)) {
                    continue;
                }
                advs.add(adv);
            }

            Random random = new Random();
            Integer weightSum = 0;
            for (Advertise adv : advs){
                weightSum += Integer.valueOf(adv.getWeight());
            }
            Integer n = random.nextInt(weightSum); // n in [0, weightSum)
            Integer m = 0;
            for (Advertise wc : advs) {
                if (m <= n && n < m + wc.getWeight()) {
                    System.out.println("This Random Category is=-============》 "+wc.getName());

                    jsonObject.put("aid",wc.getId());
                    jsonObject.put("type",wc.getAdtype());
                    break;
                }
                m += wc.getWeight();

            }

            JSONObject param = HttpEncryptUtil.paramEncrypt(jsonObject);
            param.put("error", Const.SUCCESS_STATUS);
            return param;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 错误日志上报
     *
     * @param jsonParam
     * @return
     */
    public int upLog(JSONObject jsonParam) {
        try {
            ApiLog apiLog = new ApiLog();
            apiLog.setUuid(jsonParam.getString("uuid"));
            apiLog.setChannelid(jsonParam.getInteger("cid"));
            apiLog.setAppversioncode(jsonParam.getString("av"));
            apiLog.setSdkversioncode(jsonParam.getString("sv"));
            apiLog.setErrmessage(jsonParam.getString("err"));
            apiLog.setErrtype(jsonParam.getInteger("ty"));
            apiLog.setCreatetime(System.currentTimeMillis());
            return apiLogMapper.insert(apiLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 上报点击，展示数据
     *
     * @param jsonParam
     * @return
     */
    public int reportData(JSONObject jsonParam) {
        int rows = 1;
        try {
            ApiThreadManager.getInstance().execute(ApiTaskFactory.saveShowDataTask(jsonParam));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}
