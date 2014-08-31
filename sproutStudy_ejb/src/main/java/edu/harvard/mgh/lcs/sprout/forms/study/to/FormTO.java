package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class FormTO implements Serializable {

	private static final long serialVersionUID = 5893416729846641724L;

	private String title;
	private String publicationId;
	private String instanceId;
	private String formHtml;
	private String model;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getFormHtml() {
		return formHtml;
	}
	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
}
