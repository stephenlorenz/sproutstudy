package edu.harvard.mgh.lcs.sprout.forms.study.to;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class SproutStudyPayloadTO implements Serializable {

    private String id;
    private String name;
    private String location;
    private String language;
    private String dob;

    public SproutStudyPayloadTO() {}

    public SproutStudyPayloadTO(String id, String name, String location, String language, String dob) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.language = language;
        this.dob = dob;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDob() {
        if (StringUtils.isFull(dob)) {
            try {
                Long dobLong = new Long(dob);
                if (dobLong != null) {
                    Date dobDate = new Date(dobLong);
                    if (dobDate != null) {
                        return StringUtils.formatSimpleDate(dobDate);
                    }
                }
            } catch (Exception e) {}
        }




        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
