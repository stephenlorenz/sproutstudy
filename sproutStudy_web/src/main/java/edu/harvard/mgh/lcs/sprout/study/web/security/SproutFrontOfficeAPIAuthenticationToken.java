package edu.harvard.mgh.lcs.sprout.study.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import edu.harvard.mgh.lcs.sprout.forms.study.to.SessionTO;

public class SproutFrontOfficeAPIAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -1196855866130468756L;

	private SessionTO session;
	
	public SproutFrontOfficeAPIAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public SessionTO getSession() {
		return session;
	}

	public void setSession(SessionTO session) {
		this.session = session;
	}


}
