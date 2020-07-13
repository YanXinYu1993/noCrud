package com.nocrud.builder;

import com.nocrud.builder.sql.DynamicContext;
import com.nocrud.builder.sql.SqlNode;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public class RawSqlSource implements SqlSource {

    private final SqlNode rootSqlNode;

    public RawSqlSource(SqlNode node){
        this.rootSqlNode=node;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        DynamicContext context = new DynamicContext(parameterObject);
        rootSqlNode.apply(context);
        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder();
        SqlSource sqlSource = sqlSourceBuilder.parse(context.getSql());
        return sqlSource.getBoundSql(parameterObject);
    }
}
