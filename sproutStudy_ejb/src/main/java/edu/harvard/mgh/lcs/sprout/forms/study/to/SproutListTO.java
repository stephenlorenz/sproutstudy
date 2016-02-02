package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SproutListTO implements Serializable, Comparable<SproutListTO> {

    private String name;
    private String value;
    private List<SproutListDetailTO> details;

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

    public List<SproutListDetailTO> getDetails() {
        return details;
    }

    public void setDetails(List<SproutListDetailTO> details) {
        this.details = details;
    }

    public void addDetail(SproutListDetailTO sproutListDetailTO) {
        if (details == null) details = new ArrayList<SproutListDetailTO>();
        details.add(sproutListDetailTO);
    }

    @Override
    public int compareTo(SproutListTO sproutListTO) {
        return this.getName().compareTo(sproutListTO.getName());
    }

}
