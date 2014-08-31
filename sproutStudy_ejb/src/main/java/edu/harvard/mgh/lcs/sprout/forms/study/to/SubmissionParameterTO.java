package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class SubmissionParameterTO implements Serializable{

	private static final long serialVersionUID = 6811734115259582602L;

	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
