package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;

import java.util.List;

public interface StudyService {
    public static final String USER_PREFERENCE_COHORT_ID = "COHORT_ID";

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
}
