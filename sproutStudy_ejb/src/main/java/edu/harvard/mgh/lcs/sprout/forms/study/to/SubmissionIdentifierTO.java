package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class SubmissionIdentifierTO implements Serializable {

	private static final long serialVersionUID = 287740168402610336L;

	private String schema;
	private String identifier;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
