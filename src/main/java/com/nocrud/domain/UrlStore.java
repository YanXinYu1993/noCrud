package com.nocrud.domain;

import lombok.Data;

/**
 * @Description:
 * @Author Yan XinYu
 **/
@Data
public class UrlStore {

    private String id;

    private String name;

    // uri address
    private String uri;

    // method name
    private String methodName;

    // sample param
    private String sampleParam;

    // 动态表达式SQL
    private String schemaSQL;

    // 指定解析SQL源地址
    private String urlSqlSourceId;

}
