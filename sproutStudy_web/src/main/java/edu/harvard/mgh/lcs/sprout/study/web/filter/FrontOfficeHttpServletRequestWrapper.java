package edu.harvard.mgh.lcs.sprout.study.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FrontOfficeHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public FrontOfficeHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getMethod() {
		return "POST";
	}
	
	@Override
	public String getAuthType() {
		return HttpServletRequest.FORM_AUTH;
	}

}
