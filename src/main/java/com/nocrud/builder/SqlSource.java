package com.nocrud.builder;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
