package com.nocrud.builder.sql;

import com.nocrud.builder.BoundSql;
import com.nocrud.builder.SqlSource;
import com.nocrud.parsing.ParameterMapping;

import java.util.List;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public class StaticSqlSource implements SqlSource {
    private final String sql;
    private final List<ParameterMapping> parameterMappings;

    public StaticSqlSource(String sql) {
        this(sql, null);
    }

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(sql, parameterMappings, parameterObject);
    }

    public List<ParameterMapping> getParameterMappings(){
        return this.parameterMappings;
    }
}
