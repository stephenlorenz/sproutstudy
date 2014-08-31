package edu.harvard.mgh.lcs.sprout.study.ws;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.SubmissionStatus;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidSessionRESTful;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.web.security.SproutStudyUserDetails;
import edu.harvard.mgh.lcs.sprout.study.wsinterface.ApiWS;
import org.springframework.security.core.context.SecurityContext;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.*;
import java.util.Map.Entry;

@ApplicationPath("api")
@Named
public class ApiWSImpl extends Application implements ApiWS {

	@EJB(mappedName="java:global/sproutStudy/sproutStudy_ejb/SecurityServiceImpl!edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService")
	private SecurityService securityService;
	
	@EJB(mappedName="java:global/sproutStudy/sproutStudy_ejb/PatientLookupServiceImpl!edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.PatientService")
	private PatientService patientService;
	
	@EJB(mappedName="java:global/sproutStudy/sproutStudy_ejb/FormSubmissionServiceImpl!edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.FormSubmissionService")
	private FormSubmissionService formSubmissionService;

	@EJB(mappedName="java:global/sproutStudy/sproutStudy_ejb/SproutFormsServiceImpl!edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService")
	private SproutFormsService sproutFormsService;

	@EJB(mappedName="java:global/sproutStudy/sproutStudy_ejb/StudyServiceImpl!edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService")
	private StudyService studyService;


    @Override
    @WebMethod(operationName = "getCohortAuthorizations")
    public List<CohortTO> getCohortAuthorizations(@Context HttpServletRequest request) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return studyService.getCohorts(sessionTO.getUser());
        }
        return null;
    }

    @Override
    @WebMethod(operationName = "findCohortMember")
    public List<Result> findCohortMember(@Context HttpServletRequest request, @QueryParam("cohortQueryURL") String cohortQueryURL, @QueryParam("query") String query) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return studyService.findCohortMember(sessionTO.getUser(), cohortQueryURL, query);
        }
        return null;
    }

    @Override
    @WebMethod(operationName = "getLastSelectedCohort")
    public CohortTO getLastSelectedCohort(@Context HttpServletRequest request) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            if (sessionTO.getCohortTO() != null) {
                return sessionTO.getCohortTO();
            } else {
                CohortTO cohortTO = studyService.getLastSelectedCohort(sessionTO.getUser());
                if (cohortTO != null) {
                    sessionTO.setCohortTO(cohortTO);
                    updateSessionTO(request, sessionTO);
                    return cohortTO;
                }
            }
        }
        return null;
    }

	@Override
	@WebMethod(operationName="auth")
	public Boolean auth(@QueryParam("username") String username) {
		List<String> grantedAuthorities = securityService.getGrantedAuthorties(username);
		return grantedAuthorities != null && grantedAuthorities.size() > 0; 
	}
	
	@Override
	@WebMethod(operationName="ping")
	public AckTO ping(@QueryParam("clientKey") String clientKey) {
		return new AckTO();
	}
	
	@Override
	@WebMethod(operationName="getPatientDetail")
    public PatientDetailTO getPatientDetail(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
			return patientService.getPatientDetail(mrn, sessionTO.getUser());
		}
		return null;
	}

	@Override
	@WebMethod(operationName="isPatientVerified")
    public BooleanTO isPatientVerified(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
			return formSubmissionService.isPatientVerified(mrn, sessionTO.getUser());
		}
		return null;
	}

    @Override
    @WebMethod(operationName="isPatientAssertive")
    public BooleanTO isPatientAssertive(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return formSubmissionService.isPatientAssertive(mrn, sessionTO.getUser());
        }
        return null;
    }

    @Override
	@WebMethod(operationName="patientLookup")
    public List<PatientDetailTO> patientLookup(@Context HttpServletRequest request, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
			Map<String, String[]> p = request.getParameterMap();
			Map<String, String> parameterMap = new HashMap<String, String>();
			for (Entry<String, String[]> entry : p.entrySet()) {
				parameterMap.put(entry.getKey(), entry.getValue()[0]);
			}
			return patientService.patientLookup(parameterMap, sessionTO.getUser(), limit, page);
		}
		return null;
	}

    @Override
    @WebMethod(operationName="getForm")
    public StringTO getForm(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return new StringTO(sproutFormsService.getForm(schema, mrn, instanceId));
        }
        return null;
    }

    @Override
    @WebMethod(operationName="getForms")
    public List<FormInstanceTO> getForms(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("mrn") String mrn, @QueryParam("practiceId") String practiceId) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return sproutFormsService.getForms(schema, mrn, practiceId);
        }
        return null;
    }

    @Override
	@WebMethod(operationName="pushForm")
    public StringTO pushForm(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
			SubmissionStatus submissionStatus = formSubmissionService.pushToEMR(instanceId);
			return submissionStatus != null ? new StringTO(submissionStatus.toString()) : null;

		}
		return null;
	}

	@Override
	@WebMethod(operationName="setSessionCohort")
    public BooleanTO setSessionCohort(@Context HttpServletRequest request, @QueryParam("cohortId") Integer cohortId) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
            CohortTO cohortTO = studyService.getCohort(cohortId);
            if (cohortTO != null) {
                studyService.persistUserPreference(sessionTO.getUser(), StudyService.USER_PREFERENCE_COHORT_ID, cohortId.toString());
                sessionTO.setCohortTO(cohortTO);
                updateSessionTO(request, sessionTO);
                return new BooleanTO(true);
            }
		}
		return new BooleanTO(false);
	}

    private void updateSessionTO(HttpServletRequest request, SessionTO sessionTO) {
        try {
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("sessionTO", sessionTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
	@WebMethod(operationName="getSproutInbox")
    public List<FormInstanceTO> getSproutInbox(@Context HttpServletRequest request, @QueryParam("id") String id) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null && sessionTO.getCohortTO() != null) {
            List<CohortFormTO> cohortFormTOList = sessionTO.getCohortTO().getForms();
            Set<String> publicationKeys = new HashSet<String>();
            if (cohortFormTOList != null && cohortFormTOList.size() > 0) {
                for (CohortFormTO cohortFormTO : cohortFormTOList) {
                    publicationKeys.add(cohortFormTO.getPublicationKey());
                }
                return sproutFormsService.getSproutInbox(id, publicationKeys);
            }
		}
		return null;
	}

    @Override
    @WebMethod(operationName="returnToSender")
    public StringTO returnToSender(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            SubmissionStatus submissionStatus = formSubmissionService.returnToSender(instanceId);
            return submissionStatus != null ? new StringTO(submissionStatus.toString()) : null;
        }
        return null;
    }

    @Override
    @WebMethod(operationName="deliverForm")
    public FormDeliveryStatus deliverForm(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("publicationKey") String publicationKey, @QueryParam("provider") String provider, @QueryParam("expirationDate") String expirationDateString) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);

        if (sessionTO != null && !StringUtils.isEmpty(mrn) && !StringUtils.isEmpty(publicationKey) && !StringUtils.isEmpty(provider)) {
            return sproutFormsService.deliverToInbox(mrn, publicationKey, provider, expirationDateString);
        }

        return null;
    }

    @Override
    @WebMethod(operationName="applyForNonce")
    public NonceTO applyForNonce(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);

        if (sessionTO != null && !StringUtils.isEmpty(instanceId)) {
            String nonce = sproutFormsService.applyForNonce(sessionTO.getUser(), instanceId);
            if (nonce != null) return new NonceTO(nonce, instanceId);
        }
        return null;
    }

    @Override
    @WebMethod(operationName="getPublications")
    public List<PublicationTO> getPublications(@Context HttpServletRequest request) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);

        List<PublicationTO> publicationTOList = new ArrayList<PublicationTO>();

//        if (sessionTO != null) {
//            Set<String> publicationKeys = clockworkService.getPublicationKeysByIhsGroupId(sessionTO.getPractices());
//
//            if (publicationKeys != null) {
//                for (String publicationKey : publicationKeys) {
//                    PublicationTO publicationTO = new PublicationTO();
//                    publicationTO.setKey(publicationKey);
//
//                    edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.PublicationTO publicationTO1 = sproutFormsService.getPublicationDetails(publicationKey);
//
//                    if (publicationTO1 != null) {
//                        publicationTO.setId(publicationTO1.getId());
//                        publicationTO.setTitle(publicationTO1.getName());
//                        publicationTO.setDescription(publicationTO1.getDescription());
//                        publicationTO.setPublicationDate(publicationTO1.getPublicationDatePretty());
//                    }
//
//                    Map<String, String> idxProviderMap = iHealthSpaceService.getIdxProviderMap(clockworkService.getIhsGroupCodeFromPublicationKey(publicationKey));
//                    if (idxProviderMap != null) {
//                        for (Entry<String, String> entry : idxProviderMap.entrySet()) {
//                            publicationTO.addProvider(new ProviderTO(entry.getKey(), entry.getValue()));
//                        }
//                    }
//
//                    publicationTOList.add(publicationTO);
//                }
//            }
//        }

        Collections.sort(publicationTOList);
        return publicationTOList;
    }


    @Override
	@WebMethod(operationName="syncSproutPatientIdentifiers")
    public BooleanTO syncSproutPatientIdentifiers(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("verifiedIdentifier") String[] verifiedIdentifiers, @QueryParam("matchedIdentifier") String[] matchedIdentifiers, @QueryParam("assertion") String[] matchedAssertions) throws InvalidSessionRESTful {
		return sproutFormsService.syncPatientIdentifiersAndAssertions(instanceId, verifiedIdentifiers, matchedIdentifiers, matchedAssertions) ? new BooleanTO(true) : new BooleanTO(false);
	}	

    @Override
	@WebMethod(operationName="appendProviderAsParameter")
    public BooleanTO appendProviderAsParameter(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("pun") String partnersUsername) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return formSubmissionService.appendProviderAsParameter(instanceId, partnersUsername, sessionTO.getUser());
        }
        return new BooleanTO(false);
	}

	@Override
	@WebMethod(operationName="syncFormSubmission")
    public BooleanTO syncFormSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("verifiedIdentifier") String[] verifiedIdentifiers, @QueryParam("matchedIdentifier") String[] matchedIdentifiers, @QueryParam("assertion") String[] matchedAssertions) throws InvalidSessionRESTful {
		SessionTO sessionTO = getSessionTO(request);
		if (sessionTO != null) {
			return formSubmissionService.syncFormSubmission(instanceId, verifiedIdentifiers, matchedIdentifiers, matchedAssertions, sessionTO.getUser());
		}
		return new BooleanTO(false);
	}

    @Override
    @WebMethod(operationName="getFormSubmission")
    public FormSubmissionTO getFormSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return formSubmissionService.getFormSubmission(instanceId, sessionTO.getUser());
        }
        return null;
    }

    @Override
    @WebMethod(operationName="isAdmin")
    public BooleanTO isSproutAdmin(@Context HttpServletRequest request) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return securityService.isAdmin(sessionTO.getUser());
        }
        return null;
    }

    @Override
    @WebMethod(operationName="getApplicationAuthorities")
    public List<ApplicationAuthorityTO> getApplicationAuthorities(@Context HttpServletRequest request) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return securityService.getApplicationAuthorities(sessionTO.getUser());
        }
        return null;
    }

    @Override
    @WebMethod(operationName="getUser")
    public UserTO getUser(@Context HttpServletRequest request, @QueryParam("cn") String cn) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return securityService.getUser(cn);
        }
        return null;
    }

    @Override
    @WebMethod(operationName="updateManager")
    public BooleanTO updateManager(@Context HttpServletRequest request, @QueryParam("grantedAuthId") int grantedAuthId, @QueryParam("manager") Boolean managerInd) throws InvalidSessionRESTful {
        SessionTO sessionTO = getSessionTO(request);
        if (sessionTO != null) {
            return securityService.updateManager(grantedAuthId, managerInd, sessionTO.getUser());
        }
        return null;
    }

    private SessionTO getSessionTO(HttpServletRequest request) throws InvalidSessionRESTful {
		try {
			HttpSession httpSession = request.getSession(false);

            SessionTO sessionTO = (SessionTO) httpSession.getAttribute("sessionTO");


            System.out.println("sessionTO = " + sessionTO);

            if (sessionTO == null) {
                SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");

                SproutStudyUserDetails sproutStudyUserDetails = (SproutStudyUserDetails) securityContext.getAuthentication().getPrincipal();

                if (sproutStudyUserDetails != null) {
                    sessionTO = new SessionTO();
                    sessionTO.setUser(sproutStudyUserDetails.getUsername());
                    httpSession.setAttribute("sessionTO", sessionTO);
                    return sessionTO;
                }
            } else {
                return sessionTO;
            }

			throw new InvalidSessionRESTful();
		} catch (Exception e) {
			throw new InvalidSessionRESTful();
		}
	}
	
	
}
