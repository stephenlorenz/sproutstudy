package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.to.ApplicationAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.GrantedAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.UserTO;
import edu.harvard.mgh.lcs.sprout.study.model.study.UserEntity;

import java.util.List;

public interface SecurityService {
    public abstract UserEntity getUserFromUsername(String username, String domain);
}
