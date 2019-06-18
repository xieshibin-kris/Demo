package com.miku.advs.core.task;

import com.miku.advs.core.common.constant.DBModule;
import com.miku.advs.core.util.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by hp on 2019/4/28.
 */
public class CreateTableTask extends TimerTask {

    private static Logger log = LoggerFactory.getLogger(CreateTableTask.class.getName());

    @Override
    public void run() {

        log.info("创建表任务开始------");

        for (int i=0;i<2;i++){
            DatabaseUtils.createTableDay("rtdb_active_user",null, DBModule.RTDBINFO_ADVS.ordinal(),i);
        }

   }
}
