package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/16/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserPreferenceTO implements Serializable {

    private int id;
    private String code;
    private String description;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
