package edu.harvard.mgh.lcs.sprout.study.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class SproutFrontOfficeApiSecurityFilter
 */
public class SproutStudyLoginGetToPostSecurityFilter implements Filter {

//	private static final Logger log = Logger.getLogger(FrontOfficeLoginGetToPostSecurityFilter.class.getName());

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		log.debug(this.getClass().getName() + ".doFilter...");
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
				HttpServletRequestWrapper frontOfficeRequest = new FrontOfficeHttpServletRequestWrapper (httpServletRequest);
				chain.doFilter(frontOfficeRequest, response);
			} else {
//				log.debug(this.getClass().getName() + ".doFilter: login attempt allowed to pass.");
				chain.doFilter(request, response);
			}
		}


	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

}
