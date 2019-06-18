package com.miku.advs.modular.system.service.channel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.util.TableNameUtils;
import com.miku.advs.modular.system.entity.channel.ActiveUserRecord;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.mapper.channel.ActiveUserRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by hp on 2019/4/28.
 */
@Service
public class ActiveUserRecordService extends ServiceImpl<ActiveUserRecordMapper, ActiveUserRecord> {

    @Resource
    private ChannelActiveUserStatService channelActiveUserStatService;

    public int addActiveUser(String uuid, Integer cid) {

        int row = 0;

        String tableName = TableNameUtils.getDateSuffix(new Date());

        ActiveUserRecord userRecord = baseMapper.selectByPrimaryKey(uuid,tableName);
        if(userRecord == null){
            userRecord = new ActiveUserRecord();
            userRecord.setUuid(uuid);
            userRecord.setChannelsubid(cid);
            ChannelSub channelSub = ConstantFactory.me().getChannelSubById(cid);
            if(channelSub != null){
                userRecord.setChannelid(channelSub.getParentid());
            }
            userRecord.setCreatetime(System.currentTimeMillis());
            userRecord.setTableName(tableName);

            row = baseMapper.insert(userRecord);

            if(row > 0){
                //统计活跃用户
                channelActiveUserStatService.updateActiveUser(new Date(),cid);
            }
        }
        return row;
    }
}
