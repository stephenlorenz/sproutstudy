package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.to.ApplicationAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.GrantedAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.UserTO;

import java.util.List;

public interface SecurityService {
	public List<String> getGrantedAuthorties(String principal);
    public BooleanTO isManager(String principal);
    public List<GrantedAuthorityTO> getFrontOfficeUserAuth(String principal);
    public List<GrantedAuthorityTO> deleteFrontOfficeUserAuth(int grantedAuthId, String principal);
    public UserTO getUser(String cn);
    public List<ApplicationAuthorityTO> getApplicationAuthorities(String principal);
    public BooleanTO saveFrontOfficeUserAuth(int applicationAuthId, String username, boolean managerInd, String principal);
    public GrantedAuthorityTO getFrontOfficeUserAuth(int grantedAuthId, String principal);
    public BooleanTO updateManager(int grantedAuthId, Boolean manager, String principal);
    public BooleanTO isAdmin(String principal);
}
