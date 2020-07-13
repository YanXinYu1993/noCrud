package com.nocrud.service;

import com.nocrud.builder.SqlSource;
import com.nocrud.builder.XMLScriptBuilder;
import com.nocrud.config.MappingConfiguration;
import com.nocrud.domain.UrlStore;
import com.nocrud.parsing.SNode;
import com.nocrud.parsing.ScriptParser;

/**
 * @Description:
 * @Author Yan XinYu
 **/
public class XmlParerService extends BaseService {

    public XmlParerService(MappingConfiguration mappingConfiguration) {
        super(mappingConfiguration);
    }

    @Override
    public void register(UrlStore urlStore) {
        // 读取文档结构
        ScriptParser scriptParserDemo = new ScriptParser(urlStore.getSchemaSQL());
        SNode sNode = scriptParserDemo.evalNode("/script");
        // 动态解析文档SQL
        XMLScriptBuilder builder = new XMLScriptBuilder(sNode);
        SqlSource sqlSource = builder.parseScriptNode();
        super.register(urlStore.getId(),sqlSource);
    }

}
