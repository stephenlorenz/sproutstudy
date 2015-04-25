package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.NameValue;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.PublicationTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;

import java.util.List;
import java.util.Set;

public interface SproutFormsService {
	public abstract List<FormInstanceTO> getDeliveredForms(String identitySchema, String identityId, String deliveryKey);
	public abstract String getForm(String identitySchema, String identityId, String instanceId);
	public abstract List<FormInstanceTO> getForms(String identitySchema, String identityId, String practiceId);
	public abstract boolean syncPatientIdentifiersAndAssertions(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions);
    public abstract boolean syncFormSubmission(String instanceId);
    public abstract PublicationTO getPublicationDetails(String key);
    public abstract FormDeliveryStatus deliverToInbox(String schema, String id, String publicationKey, String provider, String expirationDateString);
    public abstract List<FormInstanceTO> getSproutInbox(String username, CohortTO cohortTO, String[] identityArray, Set<String> publicationKeys);
    public abstract String applyForNonce(String user, String instanceId, String subjectName, String subjectId);
    public abstract List<FormInstanceTO> getMutableForms(String username, CohortTO cohortTO, Set<String> publicationKeys);
    public List<FormInstanceTO> getAllForms(String username, CohortTO cohortTO, Set<String> publicationKeys, int page, int rows, String orderBy, String orderDirection, String status);
    public BooleanTO deleteForm(String instanceId);
    public int getAllFormsPageCount(String username, CohortTO cohortTO, Set<String> publicationKeys, int rows, String status);
    public List<NameValue> getActiveSproutInboxStatuses();
    public String getMostRecentInstanceId(String schema, String id, String publicationKey);
}
