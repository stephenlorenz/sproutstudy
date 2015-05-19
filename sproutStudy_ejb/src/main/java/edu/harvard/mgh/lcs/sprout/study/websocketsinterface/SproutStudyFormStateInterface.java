package edu.harvard.mgh.lcs.sprout.study.websocketsinterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;

import java.util.Set;

/**
 * Created by slorenz on 5/15/15.
 */
public interface SproutStudyFormStateInterface {
    public void broadcast(Set<CohortTO> cohorts, LockTO lockTO);
    public void broadcast(Set<CohortTO> cohorts, FormInstanceTO formInstanceTO);
}
