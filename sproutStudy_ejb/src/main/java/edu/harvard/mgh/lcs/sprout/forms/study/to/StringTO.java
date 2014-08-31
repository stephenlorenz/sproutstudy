package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class StringTO implements Serializable {
	
	private static final long serialVersionUID = -5808452918661227118L;

	private String value = "";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StringTO(String value) {
		super();
		this.value = value;
	}

	public StringTO() {
		super();
	}


}
