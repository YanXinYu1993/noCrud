package com.nocrud.executor;

import com.nocrud.domain.UrlStore;

import java.sql.ResultSet;
import java.util.Map;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public interface SQLExecutor {

    ResultSet execute(UrlStore urlStore,Map parse);

}
