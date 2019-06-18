package com.miku.advs.core.api;

import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.api.ShowStatistic;
import com.miku.advs.modular.system.service.advertise.ShowStatisticService;
import com.miku.advs.modular.system.service.channel.ActiveUserRecordService;
import com.miku.advs.modular.system.service.channel.UuidInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by hp on 2019/4/29.
 */
public class ApiTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(ApiTaskFactory.class);

    private static UuidInfoService uuidInfoService = SpringContextHolder.getBean(UuidInfoService.class);
    private static ActiveUserRecordService activeUserRecordService = SpringContextHolder.getBean(ActiveUserRecordService.class);
    private static ShowStatisticService showStatisticService = SpringContextHolder.getBean(ShowStatisticService.class);

    public static TimerTask saveUuidInfoTask(String uuid, JSONObject jsonParam, String ip){
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    uuidInfoService.insert(uuid,jsonParam,ip);
                } catch (Exception e){
                    logger.info("持久化设备uuid信息失败！"+e);
                    e.printStackTrace();
                }
            }
        };

    }

    public static TimerTask addActiverRecordTask(String uuid,Integer cid){
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    activeUserRecordService.addActiveUser(uuid,cid);
                } catch (Exception e){
                    logger.info("统计活跃记录失败！"+e);
                    e.printStackTrace();
                }
            }
        };

    }

    public static TimerTask saveShowDataTask(JSONObject jsonParam) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    ShowStatistic show = new ShowStatistic();
                    show.setChannelid(jsonParam.getInteger("cid"));
                    show.setAdvertiseid(jsonParam.getString("aid"));
                    show.setRequestcount(1);
                    if (jsonParam.getInteger("action") == 1) {
                        show.setShowcount(1);
                    } else if (jsonParam.getInteger("action") == 1) {
                        show.setClickcount(1);
                    }
                    boolean bean = showStatisticService.selectByPrimaryKey(show.getDate(), show.getChannelid(), show.getAdvertiseid());
                    if (bean) {
                        showStatisticService.updateBySum(show);
                    } else {
                        showStatisticService.insert(show);
                    }

                }catch (Exception e){
                    logger.info("持久化展示数据失败!"+e);
                    e.printStackTrace();
                }
            }
        };

    }
}
