package com.newland.ntms.paramParser.core.entity.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Created by gaojx on 2020/6/8.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tbl")
public class XmlForm implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "isTerminal")
    private Boolean isTerminal;

    @XmlElement(name = "entry")
    private LinkedHashSet<XmlEntity> entries;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsTerminal() {
        return isTerminal;
    }

    public void setIsTerminal(Boolean terminal) {
        isTerminal = terminal;
    }

    public LinkedHashSet<XmlEntity> getEntries() {
        return entries;
    }

    public void setEntries(LinkedHashSet<XmlEntity> entries) {
        this.entries = entries;
    }
}
