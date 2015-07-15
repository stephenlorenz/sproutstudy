package edu.harvard.mgh.lcs.sprout.study.web.servlet;

/**
 * Created by slorenz on 7/15/15.
 */

import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidSessionRESTful;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SessionTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.web.security.SproutStudyUserDetails;
import org.springframework.security.core.context.SecurityContext;

import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Log4JavascriptServlet", urlPatterns = {"/log4javascript"})
public class Log4JavascriptServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Log4JavascriptServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.fine("Log4JavascriptServlet.doPost");

        if (request.getParameterMap() != null && request.getParameterMap().size() > 0) {

            String username = "anonymous";
            String message = null;
            String levelString = "INFO";
            String url = null;
            String layout = null;
            String timestampString = null;

            Level level = Level.INFO;

            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String key : parameterMap.keySet()) {
                String value = parameterMap.get(key)[0];
                if (key.equalsIgnoreCase("level")) {
                    levelString = value;
                    try {
                        level = translateLevel(levelString);
                    } catch (Exception e) {
                        LOGGER.severe(String.format("doPost: Unknown log4javascript LEVEL specified, %s.", levelString));
                    }
                } else if (key.equalsIgnoreCase("message")) {
                    message = value;
                } else if (key.equalsIgnoreCase("timestamp")) {
                    timestampString = value;
                } else if (key.equalsIgnoreCase("url")) {
                    url = value;
                } else if (key.equalsIgnoreCase("layout")) {
                    layout = value;
                }
                LOGGER.finest("doPost.key = " + key + ": " + value);
            }

            try {
                SessionTO sessionTO = getSessionTO(request);
                username = sessionTO.getUser();
            } catch (InvalidSessionRESTful invalidSessionRESTful) {}

            LOGGER.log(level, String.format("[%s@%s] %s", username, url, message));
            System.out.println(String.format("[%s@%s] %s", username, url, message));
        }
    }

    private Level translateLevel(String levelString) {
        if (StringUtils.isFull(levelString)) {
            if (levelString.equalsIgnoreCase("trace")) {
                return Level.FINEST;
            } else if (levelString.equalsIgnoreCase("debug")) {
                return Level.FINE;
            } else if (levelString.equalsIgnoreCase("info")) {
                return Level.INFO;
            } else if (levelString.equalsIgnoreCase("warn")) {
                return Level.WARNING;
            } else if (levelString.equalsIgnoreCase("error")) {
                return Level.SEVERE;
            } else if (levelString.equalsIgnoreCase("fatal")) {
                return Level.SEVERE;
            }
        }
        return Level.INFO;
    }

    private SessionTO getSessionTO(HttpServletRequest request) throws InvalidSessionRESTful {
        try {
            HttpSession httpSession = request.getSession(false);

            SessionTO sessionTO = (SessionTO) httpSession.getAttribute("sessionTO");

            if (sessionTO == null) {
                SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");

                SproutStudyUserDetails sproutStudyUserDetails = (SproutStudyUserDetails) securityContext.getAuthentication().getPrincipal();

                if (sproutStudyUserDetails != null) {
                    sessionTO = new SessionTO();
                    sessionTO.setUser(sproutStudyUserDetails.getUsername());
                    sessionTO.setFirstName(sproutStudyUserDetails.getFirstName());
                    sessionTO.setLastName(sproutStudyUserDetails.getLastName());
                    sessionTO.setEmail(sproutStudyUserDetails.getEmail());
                    httpSession.setAttribute("sessionTO", sessionTO);
                    return sessionTO;
                }
            } else {
                return sessionTO;
            }

            throw new InvalidSessionRESTful();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidSessionRESTful();
        }
    }

}
