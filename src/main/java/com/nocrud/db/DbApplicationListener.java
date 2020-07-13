package com.nocrud.db;

import com.nocrud.util.ConvertUtils;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 解析 external.db 配置参数，与ExternalDb枚举唯一对应
 * @Author Yan XinYu
 **/
//@Configuration
public class DbApplicationListener extends DbConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static final ConfigurationPropertyName EXTERNAL_DB = ConfigurationPropertyName
            .of("external.db");

    private static final Bindable<Map<String, String>> STRING_STRING_MAP = Bindable
            .mapOf(String.class, String.class);

    private static final Map<String, DbConfig> MYSELF_DB_DB_CONFIG_MAP = new HashMap<>();

    private static final Map<String, DataSource> MYSELF_DB_DATASOURCE_MAP = new HashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Environment environment =  event.getApplicationContext().getEnvironment();
        Binder binder = Binder.get(environment);
        Map<String, String> dbs = binder.bind(EXTERNAL_DB, STRING_STRING_MAP)
                .orElseGet(Collections::emptyMap);
        dbs.forEach((name, value) -> bind(name,value));
    }

    private void bind(String name,String value){
        if(StringUtils.isEmpty(name)|| StringUtils.isEmpty(value)){
            return;
        }
        String[] key = name.split("\\.");
        if(key.length != 2){
            throw new IllegalArgumentException(
                    "springboot properties 中'external.db'参数解析错误，可能参数的格式存在问题，请检查参数:" + name);
        }
        doBind(key[1],value);
    }

    private void doBind(String fieldName,String fieldValue){
        DbConfig dbConfig = MYSELF_DB_DB_CONFIG_MAP.get(fieldName);
        if(dbConfig == null){
            dbConfig = new DbConfig();
            MYSELF_DB_DB_CONFIG_MAP.put(fieldName,dbConfig);
        }
        ConvertUtils.copyProperties(fieldName,fieldValue,dbConfig);
    }

    /**
     * 获取配置信息
     * @param dbName
     * @return
     */
    public DbConfig getConfig(String dbName){
        return MYSELF_DB_DB_CONFIG_MAP.get(dbName);
    }

    /**
     * 获取数据源
     * @param dbName
     * @return
     */
    public DataSource getDataSource(String dbName) {
        DataSource dataSource = MYSELF_DB_DATASOURCE_MAP.get(dbName);
        if (dataSource != null) {
            return dataSource;
        }
        synchronized (this) {
            if (dataSource == null) {
                dataSource = DbUtil.getDataSource(getConfig(dbName));
                MYSELF_DB_DATASOURCE_MAP.put(dbName, dataSource);
            }
            return dataSource;
        }
    }
}
