package edu.harvard.mgh.lcs.sprout.study.wsinterface;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidSessionRESTful;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;

import javax.jws.WebMethod;
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
    @Path("/secure/getAuthorizedCohorts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortTO> getAuthorizedCohorts(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/findCohortMember")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Result> findCohortMember(@Context HttpServletRequest request, @QueryParam("cohortQueryURL") String cohortQueryURL, @QueryParam("query") String query) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getLastSelectedCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public CohortTO getLastSelectedCohort(@Context HttpServletRequest request) throws InvalidSessionRESTful;

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
    public NonceTO applyForNonce(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("subjectName") String subjectName, @QueryParam("subjectId") String subjectId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getForms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getForms(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("mrn") String mrn, @QueryParam("practiceId") String practiceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/setSessionCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO setSessionCohort(@Context HttpServletRequest request, @QueryParam("cohortId") Integer cohortId) throws InvalidSessionRESTful;

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
    @Path("/secure/deliverForm")
    @Produces(MediaType.APPLICATION_JSON)
    public FormDeliveryStatus deliverForm(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("id") String id, @QueryParam("publicationKey") String publicationKey, @QueryParam("provider") String provider, @QueryParam("expirationDate") String expirationDateString) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getSproutInbox")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getSproutInbox(@Context HttpServletRequest request, @QueryParam("identity") String[] identities) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getMutableForms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getMutableForms(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getAllForms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FormInstanceTO> getAllForms(@Context HttpServletRequest request, @QueryParam("page") int page, @QueryParam("rows") int rows, @QueryParam("orderBy") String orderBy, @QueryParam("orderDirection") String orderDirection, @QueryParam("form") String publicationKey, @QueryParam("status") String status) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getAllFormsPageCount")
    @Produces(MediaType.APPLICATION_JSON)
    public int getAllFormsPageCount(@Context HttpServletRequest request, @QueryParam("rows") int rows, @QueryParam("form") String publicationKey, @QueryParam("status") String status) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getRecentCohortMembers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Result> getRecentCohortMembers(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getCohortAuthorizations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortAuthTO> getCohortAuthorizations(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO sendMessage(@Context HttpServletRequest request, @QueryParam("to") String usernameRecipient, @QueryParam("form") String form, @QueryParam("message") String message, @QueryParam("instanceId") String instanceId, @QueryParam("formTitle") String formTitle, @QueryParam("subjectId") String subjectId, @QueryParam("subjectName") String subjectName) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getStudyInbox")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudyInboxTO> getStudyInbox(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deleteInboxMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public StudyInboxTO deleteInboxMessage(@Context HttpServletRequest request, @QueryParam("id") int id) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/markInboxMessageAsRead")
    @Produces(MediaType.APPLICATION_JSON)
    public StudyInboxTO markInboxMessageAsRead(@Context HttpServletRequest request, @QueryParam("id") int id) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/changeInboxMessageStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public StudyInboxTO changeInboxMessageStatus(@Context HttpServletRequest request, @QueryParam("id") int id, @QueryParam("status") String status) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deleteSubmission")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO deleteSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getUserTO(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getSession")
    @Produces(MediaType.APPLICATION_JSON)
    public SessionTO getSessionTO(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/saveFormPublicationKey")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveFormPublicationKey(@Context HttpServletRequest request, @QueryParam("id") String id, @QueryParam("publicationKey") String publicationKey) throws InvalidSessionRESTful;

    }
