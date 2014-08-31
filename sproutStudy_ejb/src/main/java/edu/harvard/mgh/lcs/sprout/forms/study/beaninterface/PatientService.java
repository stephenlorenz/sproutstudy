package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import edu.harvard.mgh.lcs.sprout.forms.study.to.PatientDetailTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.NameValue;

public interface PatientService {
    public abstract Future<NameValue> getPatientName(String key, String mrn, String principal);
	public abstract String getPatientName(String mrn, String principal);
	public abstract PatientDetailTO getPatientDetail(String mrn, String principal);
	public abstract List<PatientDetailTO> patientLookup(Map<String, String> parameterMap, String principal, Integer limit, Integer page);
}
