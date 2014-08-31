package edu.harvard.mgh.lcs.sprout.study.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

/**
 * Servlet Filter implementation class SproutFrontOfficeApiSecurityFilter
 */
public class SproutFrontOfficeApiSecurityFilter implements Filter {

//	private static final Logger log = Logger.getLogger(SproutFrontOfficeApiSecurityFilter.class.getName());

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		log.debug(this.getClass().getName() + ".doFilter...");
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			String relativePath = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
			if (relativePath.toLowerCase().startsWith("/assets/")) {
				// assets do not require authorization
				chain.doFilter(request, response);
			} else if (relativePath.toLowerCase().startsWith("/public/")) {
				// public do not require authorization
				chain.doFilter(request, response);
			} else if (relativePath.toLowerCase().startsWith("/api/command/getauthtoken")) {
				// public do not require authorization
				chain.doFilter(request, response);
			} else {
				String authorizationHeader = null;

				Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
				while (headerNames.hasMoreElements()) {
					String headerName = headerNames.nextElement();

					if (headerName.equalsIgnoreCase("Authorization")) {
						authorizationHeader = httpServletRequest.getHeader(headerName);
					}
				}

				if (authorizationHeader == null) {
					AuthToken authToken = getAuthToken(httpServletRequest);

					if (authToken != null) {
						SproutHttpServletRequestWrapper sproutRequest = new SproutHttpServletRequestWrapper(httpServletRequest);
						sproutRequest.setPrincipal(authToken.getServerToken());
						sproutRequest.setCredentials(authToken.getClientToken());

						chain.doFilter(sproutRequest, response);
					}
				} else {
//					log.debug(this.getClass().getName() + ".doFilter: login attempt allowed to pass.");
					chain.doFilter(request, response);
				}
			}
			
		}

	}

	private AuthToken getAuthToken(HttpServletRequest request) {
		String serverToken = getAuthParameter(request, "serverToken");
		String clientToken = getAuthParameter(request, "clientToken");
		
		if (serverToken != null && clientToken != null) {
			AuthToken authToken = new AuthToken();
			authToken.setServerToken(serverToken);
			authToken.setClientToken(clientToken);
			
			if (serverToken.equalsIgnoreCase("admin")) {
				if (!request.getServerName().equals("localhost")) {
					authToken.setClientToken("");
				}
			}
			
			return authToken;
		}
		return null;
	}

	private String getAuthParameter(HttpServletRequest request, String parameterName) {
		if (request.getParameter(parameterName) != null) {
			return !StringUtils.isEmpty(request.getParameter(parameterName).trim()) ? request.getParameter(parameterName).trim() : null;
		}
		return null;
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

class AuthToken {
	private String serverToken;
	private String clientToken;

	public String getClientToken() {
		return clientToken;
	}
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
	public String getServerToken() {
		return serverToken;
	}
	public void setServerToken(String serverToken) {
		this.serverToken = serverToken;
	}

}
