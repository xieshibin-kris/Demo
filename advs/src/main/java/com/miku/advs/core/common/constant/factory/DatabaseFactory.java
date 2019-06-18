package com.miku.advs.core.common.constant.factory;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.miku.advs.core.common.constant.DatasourceEnum;
import com.miku.advs.modular.system.mapper.api.TaskMapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  表的生产工厂
 * Created by hp on 2019/4/28.
 */
@Component
@DependsOn("springContextHolder")
public class DatabaseFactory implements IDatabaseFactory {

    private TaskMapper taskMapper = SpringContextHolder.getBean(TaskMapper.class);

    public static IDatabaseFactory getInstance() {
        return SpringContextHolder.getBean("databaseFactory");
    }

    @Override
    public void createDefalutTable(String sql) {
        taskMapper.createDefalutTable(sql);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public void createSysTable(String sql) {
        taskMapper.createSysTable(sql);
    }

    @Override
    public boolean isTableExist(String destTableName) {
        try {
            Map<String,Object> map = taskMapper.getTableName(destTableName);
            if(map!= null && destTableName.equals(map.get("table_name"))){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
