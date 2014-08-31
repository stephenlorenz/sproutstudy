package edu.harvard.mgh.lcs.sprout.study.web.security;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;

public class SproutStudyCasAssertionUserDetailsService extends AbstractCasAssertionUserDetailsService {
	
    @Autowired
    private SecurityService securityService;
    
    @Override
    protected UserDetails loadUserDetails(final Assertion assertion) {
        return new SproutStudyUserDetails(assertion, securityService);
    }
}
