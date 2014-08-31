package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;

public class SubmissionTO implements Serializable {

	private static final long serialVersionUID = 7497387724506668485L;

	private String publicationKey;
	private String instanceId;
	private String deliveryKey;
	private String formTitle;
	private String formDescription;
	private String model;
	private String modelRaw;
	private Date timestamp;
	
	public String getPublicationKey() {
		return publicationKey;
	}
	public void setPublicationKey(String publicationKey) {
		this.publicationKey = publicationKey;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public String getFormDescription() {
		return formDescription;
	}
	public void setFormDescription(String formDescription) {
		this.formDescription = formDescription;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelRaw() {
		return modelRaw;
	}
	public void setModelRaw(String modelRaw) {
		this.modelRaw = modelRaw;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDeliveryKey() {
		return deliveryKey;
	}
	public void setDeliveryKey(String deliveryKey) {
		this.deliveryKey = deliveryKey;
	}

	
}
