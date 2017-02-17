package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class ContentTO implements Serializable {

    private String message;
    private Boolean success;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
