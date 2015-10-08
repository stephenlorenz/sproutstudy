package edu.harvard.mgh.lcs.sprout.forms.study.to;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PollEventTO implements Serializable {

    private int pollKey = 0;
    private List<String> data;

    public int getPollKey() {
        return pollKey;
    }

    public void setPollKey(int pollKey) {
        this.pollKey = pollKey;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void addData(String eventData) {
        if (data == null) data = new ArrayList<String>();
        data.add(eventData);
    }
}
