package com.newland.ntms.paramParser.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 简单文件工具
 *
 */
public class FileUtils {


    /**
     * 输入流转字节数组
     * @param is  输入字节流
     * @return  字节数组
     * @throws IOException
     */
	public static byte[] inputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            bytestream.write(buffer,0,ch);
        }
        byte data[] = bytestream.toByteArray();
        bytestream.close();
        return data;
    }

}

