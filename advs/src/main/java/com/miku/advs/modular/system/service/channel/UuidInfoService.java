package com.miku.advs.modular.system.service.channel;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.api.UuidInfo;
import com.miku.advs.modular.system.mapper.api.UuidInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by hp on 2019/4/16.
 */
@Service
public class UuidInfoService extends ServiceImpl<UuidInfoMapper, UuidInfo> {

    @Resource
    private ChannelActiveUserStatService channelActiveUserStatService;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public int insert(String uuid, JSONObject jsonParam, String ip) {

        int row = 0;
        UuidInfo uuidInfo = baseMapper.selectByUuid(uuid);
        if(uuidInfo == null){
            try {
                uuidInfo = new UuidInfo();
                uuidInfo.setUuid(jsonParam.getString("uuid"));
                uuidInfo.setMac(jsonParam.getString("wmac")); //mac地址
                uuidInfo.setImei(jsonParam.getString("imei")); //imei
                uuidInfo.setImsi(jsonParam.getString("imsi"));
                uuidInfo.setTotalspacesize(jsonParam.getLong("tps"));
                uuidInfo.setCardspacesize(jsonParam.getLong("tss"));
                uuidInfo.setFactory(jsonParam.getString("mf"));
                uuidInfo.setModel(jsonParam.getString("model")); //机型
                uuidInfo.setChannelid(jsonParam.getInteger("cid"));
                uuidInfo.setPackagename(jsonParam.getString("packgeName"));
                uuidInfo.setOsversion(jsonParam.getInteger("osversion"));
                uuidInfo.setNet(jsonParam.getString("net"));
                uuidInfo.setCreatetime(System.currentTimeMillis());
                uuidInfo.setIp(ip);

                row = baseMapper.insert(uuidInfo);

                if(row > 0){
                    channelActiveUserStatService.updateNewUserCount(new Date(),uuidInfo.getChannelid());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return row;

    }

    public Page<Map<String, Object>> selectnewUserCount(Page page, String dateParam, String channelId) {
        Long startTime = 0L;
        Long endTime = 0L;
        if(!ToolUtil.isEmpty(dateParam)){
            String[] arrs = dateParam.split(" - ");
            startTime = DateUtils.getDateFromStr(arrs[0]+" 00:00:00").getTime();
            endTime = DateUtils.getDateFromStr(arrs[1]+" 23:59:59").getTime();
        }
        return baseMapper.selectnewUserCount(page,startTime,endTime,channelId);
    }
}
