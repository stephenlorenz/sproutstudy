package edu.harvard.mgh.lcs.sprout.study.websocketsinterface;

import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;

/**
 * Created by slorenz on 5/15/15.
 */
public interface SproutStudyFormStateInterface {
    public void broadcast(LockTO lockTO);
}
