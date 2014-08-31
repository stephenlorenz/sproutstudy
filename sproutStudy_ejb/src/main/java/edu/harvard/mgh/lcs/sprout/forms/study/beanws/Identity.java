package edu.harvard.mgh.lcs.sprout.forms.study.beanws;

import java.io.Serializable;

/**
 * Created by slorenz on 8/24/14.
 */
public class Identity implements Serializable {

    private String schema;
    private String id;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
