package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SproutListTO implements Serializable, Comparable<SproutListTO> {

    private String name;
    private String value;
    private Map<String, String> details;

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

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public void addDetail(String name, String value) {
        if (details == null) details = new HashMap<String, String>();
        details.put(name, value);
    }

    @Override
    public int compareTo(SproutListTO sproutListTO) {
        return this.getName().compareTo(sproutListTO.getName());
    }

}
