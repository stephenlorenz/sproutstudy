package edu.harvard.mgh.lcs.sprout.study.websockets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CohortProtocol implements JacksonEncoder.Encodable {

    private String message = "";
    private long time = System.currentTimeMillis();
    private List<String> cohorts = new ArrayList<String>();
    private String uuid = UUID.randomUUID().toString();

    public CohortProtocol() {
        this("");
    }

    public CohortProtocol(String message) {
        this.message = message;
        this.time = new Date().getTime();
    }

    public CohortProtocol(String message, Collection<String> rooms) {
        this.cohorts.addAll(cohorts);
    }

    public CohortProtocol(Collection<String> cohorts) {
        this.cohorts.addAll(cohorts);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getCohorts() {
        return cohorts;
    }

    public void setCohorts(List<String> cohorts) {
        this.cohorts = cohorts;
    }

}
