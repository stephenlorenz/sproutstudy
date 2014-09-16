package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class InboxStatusTO implements Serializable {

    private int id;
    private String code;
    private String description;
    private boolean mutable;

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

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }
}
