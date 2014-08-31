package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class SubmissionAssertionTO implements Serializable {

	private static final long serialVersionUID = 7022234488083887673L;

	private String name;
	private String value;
	private String label;
	private String variableName;
	private String fieldCode;
	private String searchField;
	private boolean identifierInd;
	
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
	public boolean isIdentifierInd() {
		return identifierInd;
	}
	public void setIdentifierInd(boolean identifierInd) {
		this.identifierInd = identifierInd;
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
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
}
