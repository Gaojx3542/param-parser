package com.newland.ntms.paramParser.core.entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by gaojx on 2020/6/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "value")
public class XmlValue implements Serializable {

    @javax.xml.bind.annotation.XmlValue
    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
