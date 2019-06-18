package com.miku.advs.core.common.constant.factory;

/**
 * Created by hp on 2019/4/28.
 *
 */
public interface IDatabaseFactory {

    /**
     * 创建默认数据库中的表
     * @param toString
     */
    void createDefalutTable(String toString);

    void createSysTable(String toString);

    boolean isTableExist(String destTableName);
}
