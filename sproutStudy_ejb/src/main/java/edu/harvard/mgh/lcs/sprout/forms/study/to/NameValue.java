package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class NameValue implements Serializable {

    private static final long serialVersionUID = 3395381998156339327L;

    private String name;
    private String value;

    public NameValue() {
        super();
    }

    public NameValue(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
