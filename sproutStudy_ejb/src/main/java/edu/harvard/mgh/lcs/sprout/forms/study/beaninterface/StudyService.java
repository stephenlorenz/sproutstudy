package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.FormAttrEntity;

import javax.jms.Session;
import java.util.List;
import java.util.Set;

public interface StudyService {
    public static final String USER_PREFERENCE_COHORT_ID = "COHORT_ID";
    public static final String USER_PREFERENCE_FORM_FILTER_FORM = "FORM_FILTER_FORM";
    public static final String USER_PREFERENCE_FORM_FILTER_ASSIGNED_TO = "FORM_FILTER_ASSIGNED_TO";
    public static final String USER_PREFERENCE_FORM_FILTER_STATUS = "FORM_FILTER_STATUS";
    public static final String USER_PREFERENCE_FORM_FILTER_STUDY_DATE = "FORM_FILTER_STUDY_DATE";
    public static final String USER_PREFERENCE_DEFAULT_TAB = "USER_PREFERENCE_DEFAULT_TAB";

    public abstract List<CohortTO> getAuthorizedCohorts(String username);
    public List<Result> findCohortMember(String user, String cohortId, String query);
    public CohortTO getCohortTO(Integer cohortId);
    public void persistUserPreference(String username, String key, String value);
    public CohortTO getLastSelectedCohort(String username);
    public CohortEntity getCohort(CohortTO cohortTO);
    public CohortEntity getCohort(int cohortId);
    public List<Result> getRecentCohortMembers(String user, CohortTO cohortTO);
    public List<Result> getRemoteCohortSubjectsByList(CohortTO cohortTO, String idList);
    public List<CohortAuthTO> getCohortAuthorizations(CohortTO cohortTO);
    public boolean sendMessage(String usernameSender, String usernameRecipient, CohortTO cohortTO, String message, String instanceId, String form, String subjectId, String subjectName);
    public List<StudyInboxTO> getStudyInbox(String username, CohortTO cohortTO);
    public StudyInboxTO deleteInboxMessage(int id, CohortTO cohortTO);
    public StudyInboxTO markInboxMessageAsRead(int id, CohortTO cohortTO);
    public StudyInboxTO changeInboxMessageStatus(int id, CohortTO cohortTO, SproutStudyConstantService.InboxStatus inboxStatus);
    public BooleanTO deleteSubmission(CohortTO cohortTO, String instanceId);
    public BooleanTO markInboxMessageAsRead(String instanceId);
    public UserTO getUser(String username);

    BooleanTO saveFormPublicationKey(String id, String publicationKey);

    String getFormFromPublicationKey(String publicationKey);
    String getFormKeyFromPublicationKey(String publicationKey);

    public CohortTO getCohortTO(SessionTO sessionTO, String cohortKey);
    Set<String> getPublicationKeysFromPublicationKey(String publicationKey);

    public List<CohortTO> getAuthorizedCohorts(SessionTO sessionTO);
    public BooleanTO saveCohort(SessionTO sessionTO, String cohortKey, String name, String description, String restfulApiUrl, String restfulApiUsername, String restfulApiPassword, String identitySchemaPrimary) throws UnauthorizedActionException;
    public BooleanTO deleteCohort(SessionTO sessionTO, String cohortKey);
    public BooleanTO saveAuthorization(SessionTO sessionTO, String usernane, String cohortKey);
    public boolean isAdmin(SessionTO sessionTO);
    public boolean isManager(SessionTO sessionTO);
    public List<CohortAuthorizationTO> getCohortAuthorizations(SessionTO sessionTO, String cohortKey);
    public BooleanTO grantCohortAuthorization(SessionTO sessionTO, String cohortKey, String firstName, String lastName, String username, String email, Boolean manager) throws UnauthorizedActionException;
    public BooleanTO revokeCohortAuthorization(SessionTO sessionTO, String cohortKey, String username) throws UnauthorizedActionException;
    public BooleanTO updateCohortAuthorization(SessionTO sessionTO, String cohortKey, String username, Boolean manager) throws UnauthorizedActionException;
    public BooleanTO saveForm(SessionTO sessionTO, String cohort, String name, String formKey, String publicationKey, Boolean demographicInd) throws UnauthorizedActionException;
    public BooleanTO deleteForm(SessionTO sessionTO, String cohortKey, String formKey) throws UnauthorizedActionException;
    public List<NameValue> getUserPreferences(String username);
    public Set<FormAttrEntity> getFormAttributesFromPublicationKey(String publicationKey);
    public BooleanTO persistFormAttribute(SessionTO sessionTO, String cohortKey, String formKey, String attributeKey, String attributeValue) throws UnauthorizedActionException;
}
