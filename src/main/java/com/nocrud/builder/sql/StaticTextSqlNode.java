package com.nocrud.builder.sql;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public class StaticTextSqlNode implements SqlNode{

    private final String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(text);
        return true;
    }
}
