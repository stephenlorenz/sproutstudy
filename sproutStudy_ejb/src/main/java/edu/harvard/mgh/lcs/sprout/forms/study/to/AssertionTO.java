package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class AssertionTO implements Serializable {

	private static final long serialVersionUID = -3783141424055634763L;

	private String name;
	private String value;
	private String label;
	private String variableName;
	private String searchField;
	private boolean identity;
	
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
	public boolean isIdentity() {
		return identity;
	}
	public void setIdentity(boolean identity) {
		this.identity = identity;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
}
