package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CohortQueryAttrTO implements Serializable {
	
    public enum CohortQueryType {LOCAL, REMOTE}
    public enum CohortQueryRemoteWSType {SOAP, JSON}

    private CohortQueryType cohortQueryType;
    private CohortQueryRemoteWSType remoteWSType;
    private String remoteWSURL;

    public CohortQueryType getCohortQueryType() {
        return cohortQueryType;
    }

    public void setCohortQueryType(CohortQueryType cohortQueryType) {
        this.cohortQueryType = cohortQueryType;
    }

    public CohortQueryRemoteWSType getRemoteWSType() {
        return remoteWSType;
    }

    public void setRemoteWSType(CohortQueryRemoteWSType remoteWSType) {
        this.remoteWSType = remoteWSType;
    }

    public String getRemoteWSURL() {
        return remoteWSURL;
    }

    public void setRemoteWSURL(String remoteWSURL) {
        this.remoteWSURL = remoteWSURL;
    }
}
