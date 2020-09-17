package com.newland.ntms.paramParser.core.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Gaojx
 */
public abstract class AbstractParamZipParser implements ParamZipParser {

    private static Logger log = LoggerFactory.getLogger(AbstractParamZipParser.class);

    protected static final String TERMINAL = "Profile";
    protected static final String HEADER = "header";
    protected static final String BODY = "body";
    protected static final String CORE_PARAM_FILE = "param_file";

    // 文件容器, key需与文件中的参数值保持一致
    protected static final Map<String, InputStream> fileMap = new HashMap();

    protected static Map headerMap = new HashMap();

    protected static final Map paramMap = new HashMap();


    // 将zip包中的文件都加载进文件容器中
    public void resolveParamZip(String zipPath){
        clearFileContainer();
        try {
            ZipFile zipFile = new ZipFile(zipPath);
            ZipInputStream zipIn = new ZipInputStream(new BufferedInputStream(
                    new FileInputStream(zipPath)));

            ZipEntry ze = null;
            while ((ze = zipIn.getNextEntry()) != null) {
                if (!ze.isDirectory()){
                    String zeName = ze.getName();
                    if(zeName.contains("filestorages")){
                        int index = zeName.indexOf("filestorages/") + 13;
                        String key = zeName.substring(index);
                        fileMap.put(key, zipFile.getInputStream(ze));
                        log.info("resolve file, key:{}, fileName:{}", key, zeName);
                    }else if(zeName.endsWith("CFG")){
                        fileMap.put(CORE_PARAM_FILE, zipFile.getInputStream(ze));
                        log.info("resolve file, key:{}, fileName:{}", CORE_PARAM_FILE, zeName);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, InputStream> getFileContainers(){
        Map<String, InputStream> fileContainers = new HashMap();
        fileContainers.putAll(fileMap);
        return fileContainers;
    }

    // 清空文件容器入口
    public void clearFileContainer(){
        fileMap.clear();
    }

    // 增加文件入口1
    public void addFiles(Map<String, File> map) throws FileNotFoundException {
        for(Map.Entry<String, File> entry : map.entrySet()){
            addFile(entry.getKey(), entry.getValue());
        }
    }
    // 增加文件入口2
    public void addFileStreams(Map<String, InputStream> map) {
        for(Map.Entry<String, InputStream> entry : map.entrySet()){
            addFile(entry.getKey(), entry.getValue());
        }
    }

    private void addFile(String key, File file) throws FileNotFoundException {
        Assert.notNull(key, "key could not be null");
        Assert.notNull(file, "file could not be null");
        if(fileMap.containsKey(key)){
            throw new IllegalArgumentException("file container has contained key[" + key + "]");
        }
        InputStream inputStream = new FileInputStream(file);
        fileMap.put(key, inputStream);
        log.info("add file, key:{}, fileName:{}", key, file.getName());
    }

    private void addFile(String key, InputStream inputStream) {
        Assert.notNull(key, "key could not be null");
        Assert.notNull(inputStream, "stream of file could not be null");
        if(fileMap.containsKey(key)){
            throw new IllegalArgumentException("file container has contained key[" + key + "]");
        }
        fileMap.put(key, inputStream);
        log.info("add file inputstream, key:{}", key);
    }

    protected String readCoreParamFile(InputStream in){
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // 加载整个zip包并解析核心参数文件
    public void parserParamZipFile(String zipPath){
        resolveParamZip(zipPath);
        doRealParser();
    }

    public void clearAll(){
        headerMap.clear();
        paramMap.clear();
        fileMap.clear();
    }
}
