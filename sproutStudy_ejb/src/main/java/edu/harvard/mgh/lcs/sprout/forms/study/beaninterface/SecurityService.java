package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.study.model.study.UserEntity;

import java.util.List;

public interface SecurityService {
    public abstract UserEntity getUserFromUsername(String username, String domain);
    public LdapUserTO getUser(String cn);
}
