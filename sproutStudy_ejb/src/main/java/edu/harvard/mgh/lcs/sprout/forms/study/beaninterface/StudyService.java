package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;

import java.util.List;

public interface StudyService {
    public static final String USER_PREFERENCE_COHORT_ID = "COHORT_ID";

    public abstract List<CohortTO> getCohorts(String username);
    public List<Result> findCohortMember(String user, String cohortId, String query);
    public CohortTO getCohort(Integer cohortId);
    public void persistUserPreference(String username, String key, String value);
    public CohortTO getLastSelectedCohort(String username);
}
