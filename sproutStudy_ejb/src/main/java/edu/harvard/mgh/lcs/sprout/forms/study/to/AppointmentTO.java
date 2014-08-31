package edu.harvard.mgh.lcs.sprout.forms.study.to;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentTO implements Serializable {

	private static final long serialVersionUID = -3515656639870352497L;
	
	private static final SimpleDateFormat PRETTY_DATE_TIME = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
	private static final SimpleDateFormat PRETTY_TIME = new SimpleDateFormat("h:mm a");

	private String mrn = "";
	private String patientName = "";

	private String appointmentId = "";
	private String providerId = "";
	private String providerName = "";
	private String duration = "";
	private String appointmentType = "";
	private String appointmentStatus = "";
	private String practiceId;
    private String ihsGroupCode = "";
	private String practiceName;
	private Date appointmentTime;

	private String iHealthSpaceStatus = "";

	private List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();
	
	public void addFormTO(FormInstanceTO formInstanceTO) {
		forms.add(formInstanceTO);
	}
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentDateTime() {
		if (appointmentTime != null) {
			return PRETTY_DATE_TIME.format(appointmentTime);
		}
		return "";
	}
	public String getAppointmentTimePretty() {
		if (appointmentTime != null) {
			return PRETTY_TIME.format(appointmentTime);
		}
		return "";
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public List<FormInstanceTO> getForms() {
		return forms;
	}
	public void setForms(List<FormInstanceTO> forms) {
		this.forms = forms;
	}
	public String getiHealthSpaceStatus() {
		return iHealthSpaceStatus;
	}
	public void setiHealthSpaceStatus(String iHealthSpaceStatus) {
		this.iHealthSpaceStatus = iHealthSpaceStatus;
	}
	public String getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(String practiceId) {
		this.practiceId = practiceId;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

    public String getIhsGroupCode() {
        return ihsGroupCode;
    }

    public void setIhsGroupCode(String ihsGroupCode) {
        this.ihsGroupCode = ihsGroupCode;
    }
}
