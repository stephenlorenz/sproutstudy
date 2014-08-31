package edu.harvard.mgh.lcs.sprout.study.wsinterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidSessionRESTful;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("command")
public interface ApiWS {

    @GET
    @Path("/secure/getCohortAuthorizations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortTO> getCohortAuthorizations(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/findCohortMember")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Result> findCohortMember(@Context HttpServletRequest request, @QueryParam("cohortQueryURL") String cohortQueryURL, @QueryParam("query") String query) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getLastSelectedCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public CohortTO getLastSelectedCohort(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/auth")
    @Produces(MediaType.APPLICATION_JSON)
	public Boolean auth(@QueryParam("username") String username);
	
    @GET
    @Path("/secure/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public AckTO ping(@QueryParam("clientToken") String clientToken);

    @GET
    @Path("/secure/getPatientDetail")
    @Produces(MediaType.APPLICATION_JSON)
    public PatientDetailTO getPatientDetail(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getForm")
    @Produces(MediaType.APPLICATION_JSON)
    public StringTO getForm(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/applyForNonce")
    @Produces(MediaType.APPLICATION_JSON)
    public NonceTO applyForNonce(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getForms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getForms(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("mrn") String mrn, @QueryParam("practiceId") String practiceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/setSessionCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO setSessionCohort(@Context HttpServletRequest request, @QueryParam("cohortId") Integer cohortId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/pushForm")
    @Produces(MediaType.APPLICATION_JSON)
    public StringTO pushForm(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/returnToSender")
    @Produces(MediaType.APPLICATION_JSON)
    public StringTO returnToSender(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getPublications")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PublicationTO> getPublications(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/patientLookup")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PatientDetailTO> patientLookup(@Context HttpServletRequest request, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) throws InvalidSessionRESTful;
    
    @GET
    @Path("/secure/syncSproutPatientIdentifiers")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO syncSproutPatientIdentifiers(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("verifiedIdentifier") String[] verifiedIdentifiers, @QueryParam("matchedIdentifier") String[] matchedIdentifiers, @QueryParam("assertion") String[] matchedAssertions) throws InvalidSessionRESTful;
    
    @GET
    @Path("/secure/syncFormSubmission")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO syncFormSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("verifiedIdentifier") String[] verifiedIdentifiers, @QueryParam("matchedIdentifier") String[] matchedIdentifiers, @QueryParam("assertion") String[] matchedAssertions) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/appendProviderAsParameter")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO appendProviderAsParameter(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("pun") String partnersUsername) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getFormSubmission")
    @Produces(MediaType.APPLICATION_JSON)
    public FormSubmissionTO getFormSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/isAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO isSproutAdmin(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getUser(@Context HttpServletRequest request, @QueryParam("cn") String cn) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getApplicationAuthorities")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplicationAuthorityTO> getApplicationAuthorities(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/updateManager")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO updateManager(@Context HttpServletRequest request, @QueryParam("grantedAuthId") int grantedAuthId, @QueryParam("manager") Boolean managerInd) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deliverForm")
    @Produces(MediaType.APPLICATION_JSON)
    public FormDeliveryStatus deliverForm(@Context HttpServletRequest request, @QueryParam("mrn") String mrn, @QueryParam("publicationKey") String publicationKey, @QueryParam("provider") String provider, @QueryParam("expirationDate") String expirationDateString) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getSproutInbox")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getSproutInbox(@Context HttpServletRequest request, @QueryParam("id") String id) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/isPatientVerified")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO isPatientVerified(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/isPatientAssertive")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO isPatientAssertive(@Context HttpServletRequest request, @QueryParam("mrn") String mrn) throws InvalidSessionRESTful;

    }
