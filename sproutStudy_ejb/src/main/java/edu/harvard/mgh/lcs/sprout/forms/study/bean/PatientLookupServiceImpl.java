package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.PatientVerificationSearchSelector;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.PatientService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.NameValue;
import edu.harvard.mgh.lcs.sprout.forms.study.to.PatientDetailTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

import javax.ejb.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Future;

@Stateless
@Remote(PatientService.class)
@TransactionManagement
public class PatientLookupServiceImpl implements PatientService {

    @Override
    @Asynchronous
    public Future<NameValue> getPatientName(String key, String mrn, String principal) {
        String patientName = getPatientName(mrn, principal);
        NameValue nameValue = new NameValue(key, patientName);
        return new AsyncResult<NameValue>(nameValue);
    }


    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getPatientName(String mrn, String principal) {
//		List<PatientTO> patientTOList = partnersPatientLookupService.searchForPatients(mrn, principal);
//		if (patientTOList != null && patientTOList.size() == 1) {
//			return formatPatientName(patientTOList.get(0));
//		}
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PatientDetailTO getPatientDetail(String mrn, String principal) {

//		PatientDetailTO patientDetailTO = null;
//		try {
//			List<PatientTO> patientTOList = partnersPatientLookupService.searchForPatients(mrn, principal);
//			if (patientTOList != null && patientTOList.size() == 1) {
//				PatientTO patientTO = patientTOList.get(0);
//				patientDetailTO = new PatientDetailTO();
//				patientDetailTO.setMrn(mrn);
//				patientDetailTO.setFirstName(patientTO.getName().getFirstName());
//				patientDetailTO.setMiddleName(patientTO.getName().getMi());
//				patientDetailTO.setLastName(patientTO.getName().getLastName());
//				patientDetailTO.setSex(patientTO.getGender());
//				patientDetailTO.setDob(StringUtils.parseDate(patientTO.getDob()));
//				if (patientTO.getAddress() != null) {
//					patientDetailTO.setAddress1(patientTO.getAddress().getLine1());
//					patientDetailTO.setAddress2(patientTO.getAddress().getLine2());
//					patientDetailTO.setCity(patientTO.getAddress().getCity());
//					patientDetailTO.setState(patientTO.getAddress().getState());
//					patientDetailTO.setZip(patientTO.getAddress().getZip());
//				}
//
//				patientDetailTO.setPin(null);
//				patientDetailTO.setOptOut(false);
//				return patientDetailTO;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public List<PatientDetailTO> patientLookup(Map<String, String> parameterMapRaw, String principal, Integer limit, Integer page) {

		List<PatientDetailTO> patientDetailTOList = new ArrayList<PatientDetailTO>();

//		int offset = 0;
//		if (limit == null) limit = 10;
//		if (page != null && page > 0) offset = page * limit;

		String search = null;

        String mrn = null;
        String firstName = null;
        String lastName = null;

		for (Entry<String, String> entry : parameterMapRaw.entrySet()) {
			try {
				String value = entry.getValue();
				if (!StringUtils.isEmpty(value)) {
					switch (PatientVerificationSearchSelector.valueOf(entry.getKey().toUpperCase())) {
					case MRN:
//						search = value;
                        mrn = value;
						break;
					case FIRSTNAME:
//						search = search != null ? String.format("%s,  %s", search, value) : value;
                        firstName = value;
						break;
					case MIDDLENAME:
						break;
					case LASTNAME:
//						search = search != null ? String.format("%s,  %s", value, search) : value;
                        lastName = value;
						break;
					case DOB:
//						try {
//							parameterMap.put("dateOfBirth", parameterDateFormat.parse(value));
//							queryBuilder.append(" AND dateOfBirth = :dateOfBirth ");
//						} catch (ParseException e) {}
						break;
					case SEX:
//						parameterMap.put("gender", value);
//						queryBuilder.append(" AND gender = UPPER(:gender) ");
						break;
					case PHONE:
//						parameterMap.put("phone", value);
//						queryBuilder.append(" AND (homePhone = :phone OR dayPhone = :phone) ");
						break;
					case ADDRESS:
//						parameterMap.put("address", value);
//						queryBuilder.append(" AND (UPPER(address1) = UPPER(:address) OR UPPER(address2) = UPPER(:address)) ");
						break;
					default:
						break;
					} 
				}
			} catch (IllegalArgumentException e) {
				//System.err.println(String.format("Invalid Patient Lookup Search Selector: %s", entry.getKey().toUpperCase()));
			}
		}

        if (mrn != null) {
            search = mrn;
        } else if (firstName != null && lastName != null) {
            search = String.format("%s, %s", lastName, firstName);
        } else if (firstName != null) {
            search = firstName;
        } else if (lastName != null) {
            search = lastName;
        }

		return patientDetailTOList;
	}
	

}
