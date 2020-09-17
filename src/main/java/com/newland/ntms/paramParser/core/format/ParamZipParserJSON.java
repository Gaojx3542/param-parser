package com.newland.ntms.paramParser.core.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newland.ntms.paramParser.core.util.ISOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.*;

/**
 * @author Gaojx
 */
public class ParamZipParserJSON extends AbstractParamZipParser implements ParamZipParser {

    private static Logger log = LoggerFactory.getLogger(ParamZipParserJSON.class);

    private static final String TABLESCHEMA = "tableSchema";
    private static final String INDEX = "index";
    private static final String PARAMVALUES = "paramValues";
    private static final String TYPE = "type";
    private static final String VALUE = "value";


    @Override
    public void doRealParser() {
        InputStream stream = fileMap.get(CORE_PARAM_FILE);
        if(stream != null){
            String paramStr = readCoreParamFile(stream);
            JSONObject jsonObject = JSONObject.parseObject(paramStr);
            headerMap = JSONObject.toJavaObject((JSON) jsonObject.get("header"), Map.class);
            log.info("header of param file:"+ headerMap.toString());
            parserParamBody((JSONObject) jsonObject.get("body"));
        }
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

    // 解析表单
    private void parserParamBody(JSONObject paramBody){
        String tableSchemaName = "";
        String index = "";
        Object paramValue = null;
        Iterator iterator = paramBody.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            log.info("key:{}, value:{}", key, value);
            if(key.equals(TABLESCHEMA)){
                // 得保证index先执行
                if(index.equals("")){
                    log.warn("something is wrong!");
                }
                tableSchemaName = (String) value;
                if(!paramMap.containsKey(tableSchemaName)){
                    Map rowMap = new HashMap();
                    rowMap.put(index, new HashMap<>());
                    paramMap.put(tableSchemaName, rowMap);
                } else {
                    Map map = (Map) paramMap.get(tableSchemaName);
                    if(map.containsKey(index)){
                        // 如果已经解析该index记录则跳过
                        break;
                    } else {
                        map.put(index, new HashMap<>());
                    }
                }
            } else if(key.equals(INDEX)){
                index = (String) value;
            }else if(key.equals(PARAMVALUES)){
                paramValue = value;
            }
        }
        // 最后处理值
        if(paramValue != null){
            traverseJSONObject(tableSchemaName, index, (JSONObject) paramValue);
        }

    }

    // 解析key为paramValues的value域
    private void traverseJSONObject(String tableSchemaName, String index, JSONObject jsonObject){
        Map tableMap = (Map) paramMap.get(tableSchemaName);
        Map rowMap = (Map) tableMap.get(index);
        String key = "";

        Object value = null;
        Iterator iterator = jsonObject.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            key = (String) entry.getKey();
            value = entry.getValue();
            log.info("key:{}, value:{}",key, value);
            Object v = parserValue((JSONObject) value);
            rowMap.put(key, v);
        }
    }


    private Object parserValue(JSONObject valueObject){
        String paramType = (String) valueObject.get(TYPE);
        JSONArray values = (JSONArray) valueObject.get(VALUE);
        switch (ParameterValueType.valueOf(paramType)){
            case BOOLEAN:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                JSONObject value = (JSONObject) values.get(0);
                return (value.get(VALUE).equals("TRUE") ? true:false);
            }
            case HEX:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                JSONObject value = (JSONObject) values.get(0);
                return ISOUtils.hex2byte(value.get(VALUE).toString());
            }
            case NUMBER:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                JSONObject value = (JSONObject) values.get(0);
                return Integer.parseInt(value.get(VALUE).toString());
            }
            case STRING:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                JSONObject value = (JSONObject) values.get(0);
                return value.get(VALUE).toString();
            }
            case FILE:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                JSONObject value = (JSONObject) values.get(0);
                return fileMap.get(value.get(VALUE).toString());
            }
            case REFERENCE:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                Map map = new HashMap();
                Set set = null;
                for(int i = 0; i < values.size(); i++){
                    JSONObject job = (JSONObject) values.get(i);
                    JSONObject rowJSON = (JSONObject) job.get(VALUE);
                    if(!map.containsKey(rowJSON.get(TABLESCHEMA))){
                        set = new HashSet();
                        map.put(rowJSON.get(TABLESCHEMA), set);
                    }  else {
                        set = (Set) map.get(rowJSON.get(TABLESCHEMA));
                    }
                    set.add(rowJSON.get(INDEX));
                    parserParamBody(rowJSON);
                }
                return map;
            }default:
                throw new IllegalArgumentException("not support valueType:["+paramType+"]");

        }
    }


    private static class SingletonHolder {
        private static final ParamZipParserJSON INSTANCE = new ParamZipParserJSON();
    }

    private ParamZipParserJSON (){}

    public static final ParamZipParserJSON getInstance() {
        return ParamZipParserJSON.SingletonHolder.INSTANCE;
    }



}
