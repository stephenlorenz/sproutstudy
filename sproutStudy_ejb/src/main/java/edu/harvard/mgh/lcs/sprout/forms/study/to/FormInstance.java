package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.InboxStatus;

public class FormInstance implements Serializable {

	private static final long serialVersionUID = 9020802358351386124L;

	private String title;
	private InboxStatus inboxStatus;
	private String instanceId;
	private String model;
	private String formMarkup;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public InboxStatus getInboxStatus() {
		return inboxStatus;
	}
	public void setInboxStatus(InboxStatus inboxStatus) {
		this.inboxStatus = inboxStatus;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFormMarkup() {
		return formMarkup;
	}
	public void setFormMarkup(String formMarkup) {
		this.formMarkup = formMarkup;
	}
	
}
