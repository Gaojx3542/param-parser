package com.newland.ntms.paramParser.core.format;

import com.newland.ntms.paramParser.core.entity.xml.XmlField;
import com.newland.ntms.paramParser.core.entity.xml.XmlTpl;
import com.newland.ntms.paramParser.core.entity.xml.XmlValue;
import com.newland.ntms.paramParser.core.util.ISOUtils;
import com.newland.ntms.paramParser.core.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.*;

/**
 * @author Gaojx
 */
public class ParamZipParserXML extends AbstractParamZipParser implements ParamZipParser {

    private static Logger log = LoggerFactory.getLogger(ParamZipParserXML.class);

    @Override
    public void doRealParser() {
        InputStream stream = fileMap.get(CORE_PARAM_FILE);
        if(stream != null){
            String paramStr = readCoreParamFile(stream);
            try {
                XmlTpl xmlTpl = (XmlTpl) XmlUtils.convertToBean(paramStr, XmlTpl.class);
                parserParamObj(xmlTpl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parserParamObj(XmlTpl xmlTpl){
        Assert.notNull(xmlTpl, "obj could not be null");
        headerMap.put("pkgName", xmlTpl.getPkgName());
        headerMap.put("tplName", xmlTpl.getName());
        xmlTpl.getForms().stream().forEach(xmlForm -> {
            String tableSchemaName = xmlForm.getName();
            xmlForm.getEntries().stream().forEach(xmlEntity -> {
                String index = xmlEntity.getTitle();
                if(xmlForm.getIsTerminal()){
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
                Map tableMap = (Map) paramMap.get(tableSchemaName);
                Map rowMap = (Map) tableMap.get(index);
                xmlEntity.getFields().stream().forEach(xmlField -> {
                    String key = xmlField.getKey();
                    Object value = parserXmlField(xmlField);
                    rowMap.put(key, value);
                });
            });
        });
    }

    private Object parserXmlField(XmlField xmlField){
        String paramType = xmlField.getType();
        List<XmlValue> values = xmlField.getValues();

        switch (ParameterValueType.valueOf(paramType)){
            case BOOLEAN:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                XmlValue value = values.get(0);
                return (value.getValue().equalsIgnoreCase("TRUE") ? true:false);
            }
            case HEX:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                XmlValue value = values.get(0);
                return ISOUtils.hex2byte(value.getValue().toString());
            }
            case NUMBER:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                XmlValue value = values.get(0);
                return Integer.parseInt(value.getValue());
            }
            case STRING:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                XmlValue value = values.get(0);
                return value.getValue().toString();
            }
            case FILE:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                XmlValue value = values.get(0);
                return fileMap.get(value.getValue().toString());
            }
            case REFERENCE:{
                if(CollectionUtils.isEmpty(values)) {
                    return null;
                }
                Map map = new HashMap();
                Set set = new HashSet();
                for(int i = 0; i < values.size(); i++){
                    XmlValue value = values.get(i);
                    set.add(value.getValue());
                }
                map.put(xmlField.getReference(), set);
                return map;
            }default:
                throw new IllegalArgumentException("not support valueType:["+paramType+"]");

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

    private static class SingletonHolder {
        private static final ParamZipParserXML INSTANCE = new ParamZipParserXML();
    }

    private ParamZipParserXML (){}

    public static final ParamZipParserXML getInstance() {
        return ParamZipParserXML.SingletonHolder.INSTANCE;
    }
}
