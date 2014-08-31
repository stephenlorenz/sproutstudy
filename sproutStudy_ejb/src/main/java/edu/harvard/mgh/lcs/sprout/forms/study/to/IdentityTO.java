package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class IdentityTO implements Serializable {

	public IdentityTO(String schema, String scope, String identifier) {
		super();
		this.schema = schema;
		this.scope = scope;
		this.identifier = identifier;
	}
	public IdentityTO() {
		super();
	}
	private static final long serialVersionUID = 1217243293925090411L;

	private String schema;
	private String scope;
	private String identifier;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
