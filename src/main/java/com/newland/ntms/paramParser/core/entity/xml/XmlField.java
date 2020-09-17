package com.newland.ntms.paramParser.core.entity.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gaojx on 2020/6/8.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "field")
public class XmlField implements Serializable {

    @XmlAttribute(name = "key")
    private String key;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "ref")
    private String reference;

    @XmlElement(name = "value")
    private List<XmlValue> values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<XmlValue> getValues() {
        return values;
    }

    public void setValues(List<XmlValue> values) {
        this.values = values;
    }
}
