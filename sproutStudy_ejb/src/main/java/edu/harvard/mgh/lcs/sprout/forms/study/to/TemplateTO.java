package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TemplateTO implements Serializable {

    private String key;
    private String template;
    private String translations;

    @Override
    public String toString() {
        return "TemplateTO{" +
                "key='" + key + '\'' +
                ", template='" + template + '\'' +
                ", translations='" + translations + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }
}
