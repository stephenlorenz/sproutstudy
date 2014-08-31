package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class PracticeTO implements Serializable {

	private static final long serialVersionUID = -8001307988580326781L;

	private String practiceId;
	private String name;
    private String code;

    public PracticeTO(String practiceId, String name) {
        this.practiceId = practiceId;
        this.name = name;
    }
    public PracticeTO(String practiceId, String name, String code) {
        this.practiceId = practiceId;
        this.name = name;
        this.code = code;
    }
	public PracticeTO() {
		super();
	}
	public String getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(String practiceId) {
		this.practiceId = practiceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
