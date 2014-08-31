package edu.harvard.mgh.lcs.sprout.study.web.filter;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.codec.binary.Base64;

public class SproutHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public static final String SPROUT_API_REALM = "Sprout FrontDesk API";
	
	private String method;
	private String principal;
	private String credentials;
	
	public SproutHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.method = request.getMethod();
	}

	@Override
	public String getMethod() {
		return method;
	}
	
	public String getAuthorization(String principal, String credentials) {
		setPrincipal(principal);
		setCredentials(credentials);
		return getAuthorization();
	}
	
	public String getAuthorization() {
		String authorization = String.format("%s:%s", principal, credentials);
		return "Basic " + new String(Base64.encodeBase64(authorization.getBytes()));
	}
	
	public void setMethod(String method) {
		this.method = method;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Override
	public String getAuthType() {
		return HttpServletRequest.BASIC_AUTH;
	}

	@Override
	public String getHeader(String name) {
		if (name.equalsIgnoreCase("Authorization")) {
			return getAuthorization();
		} else if (name.equalsIgnoreCase("WWW-Authenticate")) {
			return getWWWAuthenticate();
		}
		return super.getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		if (name.equalsIgnoreCase("Authorization")) {
			Vector<String> header = new Vector<String>();
			header.add(getAuthorization());
			return header.elements();
		} else if (name.equalsIgnoreCase("WWW-Authenticate")) {
			Vector<String> header = new Vector<String>();
			header.add(getWWWAuthenticate());
			return header.elements();
		}
		return super.getHeaders(name);
	}
	
	private String getWWWAuthenticate() {
		return String.format("Basic realm=\"%s\"", SPROUT_API_REALM);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		Enumeration<String> headerNamesEnumeration = super.getHeaderNames();
		Vector<String> headerNames = new Vector<String>();
		
		boolean hasAuthorization = false;
		boolean hasWWWAuthenticate = false;
		
		while (headerNamesEnumeration.hasMoreElements()) {
			String headerName = headerNamesEnumeration.nextElement();
			if (headerName.equalsIgnoreCase("Authorization")) {
				hasAuthorization = true;
			} else if (headerName.equalsIgnoreCase("WWW-Authenticate")) {
				hasWWWAuthenticate = true;
			}
			headerNames.add(headerName);
		}
		
		if (!hasAuthorization) headerNames.add("Authorization");
		if (!hasWWWAuthenticate) headerNames.add("WWW-Authenticate");
		
		return headerNames.elements();
	}
	
}
