package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class SproutStudyPayloadTO implements Serializable {

    private String id;
    private String name;

    public SproutStudyPayloadTO() {}

    public SproutStudyPayloadTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
