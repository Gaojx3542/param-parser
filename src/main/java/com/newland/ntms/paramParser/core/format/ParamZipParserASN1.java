package com.newland.ntms.paramParser.core.format;


import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.core.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Gaojx
 */
public class ParamZipParserASN1 extends AbstractParamZipParser implements ParamZipParser {

    private static Logger log = LoggerFactory.getLogger(ParamZipParserASN1.class);

    @Override
    public void doRealParser() {
        InputStream stream = fileMap.get(CORE_PARAM_FILE);
        if(stream != null){
            try {
                byte[] bytes = FileUtils.inputStreamToByte(stream);
                List obj = (List) ASN1InputStream.decode(bytes);
                parserParamObj(obj);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void parserParamObj(List asn1Obj){
        Assert.notNull(asn1Obj, "obj could not be null");
        List tableList = (List) asn1Obj.get(0);
        tableList.stream().forEach(table -> {
            List tableItems = (List) table;
            String tableSchemaName = (String) tableItems.get(0);
            Set entities = (Set) tableItems.get(2);
            entities.stream().forEach(entity -> {
                List entityItems = (List) entity;
                String index = (String) entityItems.get(0);
                if(tableSchemaName.equals(TERMINAL)){
                    headerMap.put("sn", index);
                    log.info("header of param file:"+ headerMap.toString());
                }
                if(!paramMap.containsKey(tableSchemaName)){
                    Map rowMap = new HashMap();
                    rowMap.put(index, new HashMap<>());
                    paramMap.put(tableSchemaName, rowMap);
                } else {
                    Map map = (Map) paramMap.get(tableSchemaName);
                    if(map.containsKey(index)){
                        // 如果已经解析该index记录则跳过
                        return;
                    } else {
                        map.put(index, new HashMap<>());
                    }
                }
                List params = (List) entityItems.get(1);
                Map tableMap = (Map) paramMap.get(tableSchemaName);
                Map rowMap = (Map) tableMap.get(index);
                params.stream().forEach(param ->{
                    List paramItems = (List) param;
                    String key = (String) paramItems.get(0);
                    Object value = paramItems.get(1);
                    if(value instanceof List){
                        // 引用类型值时
                        List ValueList = (List) value;
                        String refTableSchemaName = (String) ValueList.get(0);
                        Set refValues = (Set) ValueList.get(1);
                        Map map = new HashMap();
                        map.put(refTableSchemaName, refValues);
                        rowMap.put(key, map);
                    } else {
                        rowMap.put(key, value);
                    }
                });
            });
        });
    }




    @Override
    public Object get(String key) {
        return get(TERMINAL, (String) headerMap.get("sn"),  key);
    }

    @Override
    public Object get(String tableSchemaName, String indexTitle) {
        Map map = null;
        if(paramMap.get(tableSchemaName) != null){
            map = (Map) paramMap.get(tableSchemaName);
        }
        return map == null ? null : map.get(indexTitle);
    }


    @Override
    public Object get(String tableSchemaName, String indexTitle, String key) {
        Map map = null;
        if(get(tableSchemaName, indexTitle) != null){
            map = (Map) get(tableSchemaName, indexTitle);
        }
        log.info("[getValue] - tableSchmaName:{}, index:{}, key:{}, value:{}",tableSchemaName, indexTitle, key, map == null ? null : map.get(key));
        return map == null ? null : map.get(key);
    }

    private static class SingletonHolder {
        private static final ParamZipParserASN1 INSTANCE = new ParamZipParserASN1();
    }

    private ParamZipParserASN1 (){}

    public static final ParamZipParserASN1 getInstance() {
        return SingletonHolder.INSTANCE;
    }

}


