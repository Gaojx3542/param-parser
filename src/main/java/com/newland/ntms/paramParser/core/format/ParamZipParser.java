package com.newland.ntms.paramParser.core.format;

/**
 * @author Gaojx
 */
public interface ParamZipParser {

    void doRealParser();

    // 根据key获取实体表单的sn记录行的value
    Object get(String key);

    // 获取表单对应的title记录行
    Object get(String tableSchemaName, String indexTitle);

    // 根据key获取引用表单的title记录行的value
    Object get(String tableSchemaName, String indexTitle, String key);


}
