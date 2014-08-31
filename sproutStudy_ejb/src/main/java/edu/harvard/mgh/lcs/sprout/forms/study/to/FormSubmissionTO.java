package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class FormSubmissionTO implements Serializable {

	private static final long serialVersionUID = -1284560071101937055L;

	private String publicationKey;
	private String instanceId;
	private String deliveryKey;
	private Date submissionDate;
	private AppointmentTO appointment;
	private String formTitle;
	private String formDescription;
	private String status;
	private Set<SubmissionParameterTO> parameters;
	private Set<SubmissionIdentifierTO> identifiers;
	private List<SubmissionAssertionTO> assertions;

	private boolean verified;
	
	public String getPublicationKey() {
		return publicationKey;
	}
	public void setPublicationKey(String publicationKey) {
		this.publicationKey = publicationKey;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getDeliveryKey() {
		return deliveryKey;
	}
	public void setDeliveryKey(String deliveryKey) {
		this.deliveryKey = deliveryKey;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<SubmissionParameterTO> getParameters() {
		return parameters;
	}
	public void setParameters(Set<SubmissionParameterTO> parameters) {
		this.parameters = parameters;
	}
	public Set<SubmissionIdentifierTO> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(Set<SubmissionIdentifierTO> identifiers) {
		this.identifiers = identifiers;
	}
	public AppointmentTO getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentTO appointment) {
		this.appointment = appointment;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public List<SubmissionAssertionTO> getAssertions() {
		return assertions;
	}
	public void setAssertions(List<SubmissionAssertionTO> assertions) {
		this.assertions = assertions;
	}	
}
