package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/16/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class DomainTO implements Serializable {

    private int id;
    private String name;
    private String description;
    private Set<DomainAttrTO> attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DomainAttrTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<DomainAttrTO> attributes) {
        this.attributes = attributes;
    }
}
