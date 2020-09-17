package com.newland.ntms.paramParser.core.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gaojx on 2020/5/12.
 */
public class XmlUtils {

    public static final String DEFAULT_ENCODING = "UTF-8";


    /**
     * pojo转换成xml 默认编码UTF-8
     *
     * @param obj 待转化的对象
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj) throws Exception {
        return convertToXml(obj, DEFAULT_ENCODING);
    }

    public static String convertToXmlIgnoreXmlHead(Object obj) throws Exception {
        return convertToXmlIgnoreXmlHead(obj, DEFAULT_ENCODING);
    }

    /**
     * pojo转换成xml
     *
     * @param obj 待转化的对象
     * @param encoding 编码
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj, String encoding) throws Exception {
        String result = null;

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        result = writer.toString();

        return result;
    }

    /**
     * pojo转换成xml,去除xml声明部分
     *
     * @param obj 待转化的对象
     * @param encoding 编码
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXmlIgnoreXmlHead(Object obj, String encoding) throws Exception {
        String result = null;

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        result = writer.toString();

        return result;
    }

    /**
     * xml转换成pojo
     * @param xmlStr  xml格式字符串
     * @param load 待转化的对象
     * @return
     * @throws Exception
     */
    public static Object convertToBean(String xmlStr, Class<?> load) throws Exception{
        Object object = null;
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        object = unmarshaller.unmarshal(new StringReader(xmlStr));
        return object;
    }


    /**
 　　* @description: 使bean中为null的属性转换成空字符串
 　　* @param [bean]
 　　* @return void
 　　* @throws
 　　*/
    public static <T> void  nullToEmpty(T bean) {
        Field[] field = bean.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字
            //将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field[j].getGenericType().toString();    //获取属性的类型
            if (type.equals("class java.lang.String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
                try {
                    Method mGet = bean.getClass().getMethod("get" + name);
                    String value = (String) mGet.invoke(bean);    //调用getter方法获取属性值
                    if (value == null || "".equals(value)) {
                        Method mSet = bean.getClass().getMethod("set" + name, new Class[]{String.class});
                        mSet.invoke(bean, new Object[]{new String("")});
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else if(type.equals("class java.lang.Integer")){
                try {
                    Method mGet = bean.getClass().getMethod("get" + name);
                    Integer value = (Integer) mGet.invoke(bean);    //调用getter方法获取属性值
                    if (value == null) {
                        Method mSet = bean.getClass().getMethod("set" + name, new Class[]{Integer.class});
                        mSet.invoke(bean, new Object[]{new Integer(0)});
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
