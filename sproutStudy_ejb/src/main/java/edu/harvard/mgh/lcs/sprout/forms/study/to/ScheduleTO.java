package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTO implements Serializable {

	private static final long serialVersionUID = -8724991077434556983L;

	private List<AppointmentTO> appointments = new ArrayList<AppointmentTO>();

	public List<AppointmentTO> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentTO> appointments) {
		this.appointments = appointments;
	}
	
	public void addAppointment(AppointmentTO appointmentTO) {
		appointments.add(appointmentTO);
	}
}
