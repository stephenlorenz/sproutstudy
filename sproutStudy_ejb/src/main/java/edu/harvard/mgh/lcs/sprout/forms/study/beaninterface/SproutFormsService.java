package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.PublicationTO;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;

import java.util.List;
import java.util.Set;

public interface SproutFormsService {
	public abstract List<FormInstanceTO> getDeliveredForms(String identitySchema, String identityId, String deliveryKey);
	public abstract String getForm(String identitySchema, String identityId, String instanceId);
	public abstract List<FormInstanceTO> getForms(String identitySchema, String identityId, String practiceId);
	public abstract boolean syncPatientIdentifiersAndAssertions(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions);
    public abstract boolean syncFormSubmission(String instanceId);
    public abstract PublicationTO getPublicationDetails(String key);
    public abstract FormDeliveryStatus deliverToInbox(String mrn, String publicationKey, String provider, String expirationDateString);
    public abstract List<FormInstanceTO> getSproutInbox(String identityId, Set<String> publicationKeys);
    public abstract String applyForNonce(String user, String instanceId);
}
