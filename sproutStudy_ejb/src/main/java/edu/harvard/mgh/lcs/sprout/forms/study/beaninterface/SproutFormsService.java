package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.NameValue;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.PublicationTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormListMetadataTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;

import java.util.List;
import java.util.Set;

public interface SproutFormsService {
	List<FormInstanceTO> getDeliveredForms(String identitySchema, String identityId, String deliveryKey);
	String getForm(String identitySchema, String identityId, String instanceId);
	List<FormInstanceTO> getForms(String identitySchema, String identityId, String practiceId);
	boolean syncPatientIdentifiersAndAssertions(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions);
    boolean syncFormSubmission(String instanceId);
    PublicationTO getPublicationDetails(String key);
    FormDeliveryStatus deliverToInbox(String schema, String id, String publicationKey, String provider, String expirationDateString);
    List<FormInstanceTO> getSproutInbox(String username, CohortTO cohortTO, String[] identityArray, Set<String> publicationKeys);
    String applyForNonce(String user, String instanceId, String subjectName, String subjectId, String location, String language, String dob);
    List<FormInstanceTO> getMutableForms(String username, CohortTO cohortTO, Set<String> publicationKeys);
    List<FormInstanceTO> getAllForms(String username, CohortTO cohortTO, Set<String> publicationKeys, int page, int rows, String orderBy, String orderDirection, String status, String location, String targetDate, String assignment);
    BooleanTO deleteForm(String instanceId);
    int getAllFormsPageCount(String username, CohortTO cohortTO, Set<String> publicationKeys, int rows, String status, String location, String targetDate, String assignment);
    List<NameValue> getActiveSproutInboxStatuses();
    String getMostRecentInstanceId(String schema, String id, String publicationKey);
    List<NameValue> getAssignments(Set<String> publicationKeys, String status, String expirationDate);
    FormListMetadataTO getAllFormsMetadata(String username, CohortTO cohortTO, Set<String> publicationKeys, int rows, String status, String location, String targetDate, String assignment);
    FormInstanceTO getFormInstance(String instanceId);
    BooleanTO unlock(String instanceId);
    String getPublicationKeyFromInstanceId(String instanceId);
    List<NameValue> getActiveSproutInboxLocations(List<String> publicationKeys);
}
