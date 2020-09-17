package com.newland.ntms.paramParser.core.entity.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gaojx on 2020/6/8.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "entry")
public class XmlEntity implements Serializable {

    @XmlAttribute(name = "title")
    private String title;

    @XmlElement(name = "field")
    private List<XmlField> fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<XmlField> getFields() {
        return fields;
    }

    public void setFields(List<XmlField> fields) {
        this.fields = fields;
    }
}
