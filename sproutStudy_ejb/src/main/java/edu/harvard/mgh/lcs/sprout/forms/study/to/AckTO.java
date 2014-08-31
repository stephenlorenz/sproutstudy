package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class AckTO implements Serializable {
	
	private static final long serialVersionUID = -5808452918661227118L;

	private String message = "ACK";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
