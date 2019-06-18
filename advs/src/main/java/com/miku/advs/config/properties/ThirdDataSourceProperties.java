package com.miku.advs.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hp on 2019/4/28.
 *
 * 第三个数据源的配置
 */
public class ThirdDataSourceProperties {

    private static Logger log = LoggerFactory.getLogger(ThirdDataSourceProperties.class.getName());
    private String url = "jdbc:mysql://156.236.112.51:3306/?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    private String username = "hello";

    private String password = "hello";

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String validationQuery = "SELECT 'x'";

    private String  thirdDataSourceName =  "dataSourceAll" ;

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setValidationQuery(validationQuery);
        log.info("url:"+url);
        log.info("username:"+username);
        log.info("password:"+password);

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getThirdDataSourceName() {
        return thirdDataSourceName;
    }

    public void setThirdDataSourceName(String thirdDataSourceName) {
        this.thirdDataSourceName = thirdDataSourceName;
    }
}
