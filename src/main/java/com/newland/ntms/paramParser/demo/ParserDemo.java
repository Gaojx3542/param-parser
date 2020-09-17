package com.newland.ntms.paramParser.demo;

import com.newland.ntms.paramParser.core.format.*;
import com.newland.ntms.paramParser.core.util.ISOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Gaojx
 */
public class ParserDemo {

    private static Logger log = LoggerFactory.getLogger(ParserDemo.class);

    public static void main(String[] args) {
        /*  DEMO Part1: JSON formatter  */
        parserJSONFormat();


        /*  DEMO Part2: XML formatter  */
//        parserXMLFormat();


        /*  DEMO Part3: ASN.1 formatter  */
//        parserASN1Format();

    }


    private static void parserJSONFormat(){
        ParamZipParserJSON paramZipParserJSON = (ParamZipParserJSON)ParamZipParserManager.factory(FormatType.JSON);
        paramZipParserJSON.clearAll();
        String path = Thread.currentThread().getContextClassLoader().getResource("json/213-com.newland.launcher04-8-48E9D1C99BEA8EDD1681983BA96C21C5F07D11A6.zip").getPath();
        paramZipParserJSON.parserParamZipFile(path);

        /*  recommend: method 1:  */
        // String type:
        String str = (String) paramZipParserJSON.get("key1");
        // Boolean type:
        Boolean bool = (Boolean) paramZipParserJSON.get("bool");
        // Number type:
        Integer num = (Integer) paramZipParserJSON.get("key3");
        // Hex type:
        byte[] hex = (byte[]) paramZipParserJSON.get("hex");
        if(hex != null){
            String hexStr = ISOUtils.hexString(hex);
            System.out.println(hexStr);
        }
        // File type:
        InputStream in = (InputStream) paramZipParserJSON.get("file");
        // Reference type:
        Map refMap = (Map) paramZipParserJSON.get("languagte");
        if(refMap != null && refMap instanceof Map){
            Map<String, Object> valueMap = getReferenceParamValues(paramZipParserJSON, refMap);
            for(Map.Entry<String, Object> entry : valueMap.entrySet()){
                String index = entry.getKey();
                Map rowMap = (Map) entry.getValue();
                String str1 = (String) rowMap.get("value");
            }

        }


        /*  method 2:   */
        // String type:
        String str2 = (String) paramZipParserJSON.get("Profile","213","key1");
        // Boolean type:
        Boolean bool2 = (Boolean) paramZipParserJSON.get("Profile","213","bool");
        // Number type:
        Integer num2 = (Integer) paramZipParserJSON.get("Profile","213","key3");
        // Hex type:
        byte[] hex2 = (byte[]) paramZipParserJSON.get("Profile","213","hex");
        if(hex2 != null){
            String hexStr = ISOUtils.hexString(hex2);
            System.out.println(hexStr);
        }
        // File type:
        InputStream in2 = (InputStream) paramZipParserJSON.get("Profile","213","file");
        // Reference type:
        Map<String, Object> refMap2 = (Map) paramZipParserJSON.get("languagte");
        if(refMap2 != null && refMap2 instanceof Map){
            String tableSchemaName= null;
            Set<String> refSet = null;
            for (Map.Entry<String, Object> entry : refMap2.entrySet()) {
                tableSchemaName = entry.getKey();
                refSet = (Set) entry.getValue();
                if (tableSchemaName != null) {
                    break;
                }
            }
            for(String refIndex: refSet){
                String value = (String) paramZipParserJSON.get(tableSchemaName, refIndex, "value");
                System.out.println(value);
            }
        }
    }

    private static void parserXMLFormat(){
        ParamZipParserXML paramZipParserXML = (ParamZipParserXML)ParamZipParserManager.factory(FormatType.XML);
        paramZipParserXML.clearAll();
        String path = Thread.currentThread().getContextClassLoader().getResource("xml/213-com.newland.launcher04-4-3BD9457B513D66271FF4BB5DF4B309C3E04C8830.zip").getPath();
        paramZipParserXML.parserParamZipFile(path);

        /*  recommend: method 1:  */
        // String type:
        String str = (String) paramZipParserXML.get("key1");
        // Boolean type:
        Boolean bool = (Boolean) paramZipParserXML.get("bool");
        // Number type:
        Integer num = (Integer) paramZipParserXML.get("key3");
        // Hex type:
        byte[] hex = (byte[]) paramZipParserXML.get("hex");
        if(hex != null){
            String hexStr = ISOUtils.hexString(hex);
            System.out.println(hexStr);
        }

        // File type:
        InputStream in = (InputStream) paramZipParserXML.get("file");
        // Reference type:
        Map refMap = (Map) paramZipParserXML.get("languagte");
        if(refMap != null && refMap instanceof Map){
            Map<String, Object> valueMap = getReferenceParamValues(paramZipParserXML, refMap);
            for(Map.Entry<String, Object> entry : valueMap.entrySet()){
                String index = entry.getKey();
                Map rowMap = (Map) entry.getValue();
                String str1 = (String) rowMap.get("value");
                System.out.println(str1);
            }

        }
    }

    private static void parserASN1Format(){
        ParamZipParserASN1 paramZipParserASN1 = (ParamZipParserASN1)ParamZipParserManager.factory(FormatType.ASN1);
        paramZipParserASN1.clearAll();
        String path = Thread.currentThread().getContextClassLoader().getResource("asn1/213-com.newland.launcher04-14-70AF6A8E5CBBB701A4151B186B2ED3011662F10A.zip").getPath();
        paramZipParserASN1.parserParamZipFile(path);

        /*  recommend: method 1:  */
        // String type:
        String str = (String) paramZipParserASN1.get("key1");
        // Boolean type:
        Boolean bool = (Boolean) paramZipParserASN1.get("bool");
        // Number type:
        BigInteger num = (BigInteger) paramZipParserASN1.get("key3");
        System.out.println(num);
        // Hex type:
        byte[] hex = (byte[]) paramZipParserASN1.get("hex");
        if(hex != null){
            String hexStr = ISOUtils.hexString(hex);
            System.out.println(hexStr);
        }
        // File type: ( from the infos of param file, we could not indentify this type, user need to deal with it by themselves)
        String relativePath = (String) paramZipParserASN1.get("file");
        if(StringUtils.isNotEmpty(relativePath)){
            InputStream in = paramZipParserASN1.getFileContainers().get("filestorages/"+ relativePath);
        }

        // Reference type:
        Map refMap = (Map) paramZipParserASN1.get("languagte");
        if(refMap != null && refMap instanceof Map){
            Map<String, Object> valueMap = getReferenceParamValues(paramZipParserASN1, refMap);
            for(Map.Entry<String, Object> entry : valueMap.entrySet()){
                String index = entry.getKey();
                Map rowMap = (Map) entry.getValue();
                String str1 = (String) rowMap.get("value");
                System.out.println(str1);
            }

        }
    }

    private static Map getReferenceParamValues(ParamZipParser paramZipParser, Map<String, Object> refMap){
        if(refMap == null) return null;
        Map resultMap = new HashMap();
        String tableSchemaName= null;
        Set<String> refSet = null;
        for (Map.Entry<String, Object> entry : refMap.entrySet()) {
            tableSchemaName = entry.getKey();
            refSet = (Set) entry.getValue();
            if (tableSchemaName != null) {
                break;
            }
        }
        for(String refIndex: refSet){
            Map rowMap = (Map) paramZipParser.get(tableSchemaName, refIndex);
            resultMap.put(refIndex, rowMap);
        }

        return resultMap;
    }






}
