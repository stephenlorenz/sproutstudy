package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CohortIdentityTO implements Serializable {
	
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
