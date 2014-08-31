package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class AuthTO implements Serializable {

	private static final long serialVersionUID = -2877765600773157220L;

	private String serverToken;
	private String clientToken;
	
	public AuthTO() {
		super();
	}

	public AuthTO(String serverToken, String clientToken) {
		super();
		this.serverToken = serverToken;
		this.clientToken = clientToken;
	}

	public String getServerToken() {
		return serverToken;
	}
	public void setServerToken(String serverToken) {
		this.serverToken = serverToken;
	}
	public String getClientToken() {
		return clientToken;
	}
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
	
}
