package com.newland.ntms.paramParser.core.entity.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Created by gaojx on 2020/6/8.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tpl")
public class XmlTpl implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "pkgName")
    private String pkgName;

    @XmlElement(name = "form")
    private LinkedHashSet<XmlForm> forms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public LinkedHashSet<XmlForm> getForms() {
        return forms;
    }

    public void setForms(LinkedHashSet<XmlForm> forms) {
        this.forms = forms;
    }
}
