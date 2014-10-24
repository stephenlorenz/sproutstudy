package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class BooleanTO implements Serializable {
	
	private static final long serialVersionUID = -5808452918661227118L;

	private String value = "false";
    private String message;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BooleanTO(String value) {
		super();
		this.value = value;
	}

	public BooleanTO(boolean value) {
		super();
		this.value = new Boolean(value).toString();
	}

	public BooleanTO() {
		super();
	}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTrue() {
        return value != null && value.equalsIgnoreCase("true");
    }

    public boolean isFalse() {
        return value == null || (value != null && value.equalsIgnoreCase("false"));
    }
}
