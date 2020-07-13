package com.nocrud.builder.sql;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public interface SqlNode {

    boolean apply(DynamicContext context);
}
