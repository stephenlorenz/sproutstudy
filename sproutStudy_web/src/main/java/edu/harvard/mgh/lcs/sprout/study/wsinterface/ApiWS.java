package edu.harvard.mgh.lcs.sprout.study.wsinterface;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormDeliveryStatus;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormListMetadataTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidSessionRESTful;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("command")
public interface ApiWS {

    @GET
    @Path("/secure/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean auth(@QueryParam("username") String username);

    @GET
    @Path("/secure/nucardauth")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean authNucard(@QueryParam("username") String username);

    @GET
    @Path("/secure/getAuthorizedCohorts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortTO> getAuthorizedCohorts(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getManagedCohorts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortTO> getManagedCohorts(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/findCohortMember")
    @Produces("application/json;charset=UTF-8")
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
    @Path("/secure/saveForm")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveForm(@Context HttpServletRequest request, @QueryParam("cohort") String cohort, @QueryParam("name") String name, @QueryParam("formKey") String formKey, @QueryParam("publicationKey") String publicationKey, @QueryParam("demographicInd") Boolean demographicInd) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deleteForm")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO deleteForm(@Context HttpServletRequest request, @QueryParam("cohort") String cohortKey, @QueryParam("formKey") String formKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/persistFormAttribute")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO persistFormAttribute(@Context HttpServletRequest request, @QueryParam("cohort") String cohortKey, @QueryParam("publicationKey") String publicationKey, @QueryParam("formKey") String formKey, @QueryParam("attributeKey") String attributeKey, @QueryParam("attributeValue") String attributeValue) throws InvalidSessionRESTful, UnauthorizedActionException;

    @GET
    @Path("/secure/saveList")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveList(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("nameColumnTitle") String nameColumnTitle, @QueryParam("valueColumnTitle") String valueColumnTitle, @QueryParam("publicInd") Boolean publicInd, @QueryParam("active") Boolean active, @QueryParam("listKey") String listKey, @QueryParam("listKeyFormer") String listKeyFormer, @QueryParam("details") String details) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/saveListData")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveListData(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("listKey") String listKey, String data) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deleteList")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO deleteList(@Context HttpServletRequest request, @QueryParam("cohort") String cohortKey, @QueryParam("listKey") String listKey) throws InvalidSessionRESTful, UnauthorizedActionException;

    @GET
    @Path("/secure/persistListAttribute")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO persistListAttribute(@Context HttpServletRequest request, @QueryParam("cohort") String cohortKey, @QueryParam("listKey") String listKey, @QueryParam("attributeKey") String attributeKey, @QueryParam("attributeValue") String attributeValue) throws InvalidSessionRESTful, UnauthorizedActionException;

    @GET
    @Path("/secure/applyForNonce")
    @Produces(MediaType.APPLICATION_JSON)
    public NonceTO applyForNonce(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("subjectName") String subjectName, @QueryParam("subjectId") String subjectId, @QueryParam("location") String location, @QueryParam("language") String language, @QueryParam("dob") String dob) throws InvalidSessionRESTful;

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
    @Path("/secure/deliverOrOpenForm")
    @Produces(MediaType.APPLICATION_JSON)
    public FormDeliveryStatus deliverOrOpenForm(@Context HttpServletRequest request, @QueryParam("schema") String schema, @QueryParam("id") String id, @QueryParam("publicationKey") String publicationKey, @QueryParam("provider") String provider, @QueryParam("expirationDate") String expirationDateString) throws InvalidSessionRESTful;

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
    public List<FormInstanceTO> getAllForms(@Context HttpServletRequest request, @QueryParam("page") int page, @QueryParam("rows") int rows, @QueryParam("orderBy") String orderBy, @QueryParam("orderDirection") String orderDirection, @QueryParam("form") String publicationKey, @QueryParam("status") String status, @QueryParam("location") String location, @QueryParam("targetDate") String targetDate, @QueryParam("assignment") String assignment) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getAllFormsPageCount")
    @Produces(MediaType.APPLICATION_JSON)
    public int getAllFormsPageCount(@Context HttpServletRequest request, @QueryParam("rows") int rows, @QueryParam("form") String publicationKey, @QueryParam("status") String status, @QueryParam("location") String location, @QueryParam("targetDate") String targetDate, @QueryParam("assignment") String assignment) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getAllFormsMetadata")
    @Produces(MediaType.APPLICATION_JSON)
    public FormListMetadataTO getAllFormsMetadata(@Context HttpServletRequest request, @QueryParam("rows") int rows, @QueryParam("form") String publicationKey, @QueryParam("status") String status, @QueryParam("location") String location, @QueryParam("targetDate") String targetDate, @QueryParam("assignment") String assignment) throws InvalidSessionRESTful;

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
    public BooleanTO deleteSubmission(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("demographicInd") Boolean demographicInd, @QueryParam("identity") String[] identities) throws InvalidSessionRESTful;

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
    public CohortFormTO saveFormPublicationKey(@Context HttpServletRequest request, @QueryParam("id") String id, @QueryParam("publicationKey") String publicationKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getActiveSproutInboxStatuses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.NameValue> getActiveSproutInboxStatuses(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getActiveSproutInboxLocations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.NameValue> getActiveSproutInboxLocations(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/saveCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveCohort(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("restfulApiUrl") String restfulApiUrl, @QueryParam("restfulApiUsername") String restfulApiUsername, @QueryParam("restfulApiPassword") String restfulApiPassword, @QueryParam("identitySchemaPrimary") String identitySchemaPrimary) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/revokeCohortAuthorization")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO revokeCohortAuthorization(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("username") String username) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/updateCohortAuthorization")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO updateCohortAuthorization(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("username") String username, @QueryParam("manager") Boolean manager) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/grantCohortAuthorization")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO grantCohortAuthorization(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("manager") Boolean manager) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/deleteCohort")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO deleteCohort(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getDomainUser")
    @Produces(MediaType.APPLICATION_JSON)
    public LdapUserTO getUser(@Context HttpServletRequest request, @QueryParam("cn") String cn) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/isAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO isAdmin(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/isManager")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO isManager(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getCohortAuthorizationsByKey")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortAuthorizationTO> getCohortAuthorizationsByKey(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getCohortByKey")
    @Produces(MediaType.APPLICATION_JSON)
    public CohortTO getCohortTO(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getTemplate")
    @Produces(MediaType.APPLICATION_JSON)
    public TemplateTO getTemplate(@Context HttpServletRequest request, @QueryParam("publicationKey") String publicationKey, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/getNarrativeText")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNarrativeText(@Context HttpServletRequest request, String narrative) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/getNarrativePDF")
    @Produces(MediaType.TEXT_HTML)
    public String getNarrativePDF(@Context HttpServletRequest request, @Context HttpServletResponse response, String narrative) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/getNarrativeServer")
    @Produces(MediaType.TEXT_HTML)
    public String getNarrativeServer(@Context HttpServletRequest request, @QueryParam("publicationKey") String publicationKey, @QueryParam("instanceId") String instanceId, String model) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/saveTemplate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BooleanTO saveTemplate(@Context HttpServletRequest request, @QueryParam("publicationKey") String publicationKey, @QueryParam("instanceId") String instanceId, @QueryParam("templateKey") String templateKey, @QueryParam("masterInd") boolean masterInd, TemplateTO templateTO) throws InvalidSessionRESTful;
//    public BooleanTO saveTemplate(@Context HttpServletRequest request, @QueryParam("publicationKey") String publicationKey, @QueryParam("instanceId") String instanceId, @QueryParam("templateKey") String templateKey, @QueryParam("masterInd") boolean masterInd, String template) throws InvalidSessionRESTful;
//    public BooleanTO saveTemplate(@Context HttpServletRequest request, @QueryParam("publicationKey") String publicationKey, @QueryParam("instanceId") String instanceId, @QueryParam("template") String template, @QueryParam("templateKey") String templateKey, @QueryParam("masterInd") boolean masterInd) throws InvalidSessionRESTful;

    @POST
    @Path("/secure/saveNarrative")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO saveNarrative(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId, @QueryParam("format") String format, String narrative) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getAssignments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.NameValue> getAssignments(@Context HttpServletRequest request, @QueryParam("status") String status, @QueryParam("targetDate") String targetDate) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/setSessionFormFilter")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO setSessionFormFilter(@Context HttpServletRequest request, @QueryParam("formFilter") String formFilter, @QueryParam("assignmentFilter") String assignmentFilter, @QueryParam("statusFilter") String statusFilter, @QueryParam("locationFilter") String locationFilter, @QueryParam("targetDateFilter") String targetDateFilter) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getUserPreferences")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NameValue> getUserPreferences(@Context HttpServletRequest request) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/refreshList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CohortListDataTO> refreshList(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("listKey") String listKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/getPollEvents")
    @Produces(MediaType.APPLICATION_JSON)
    public PollEventTO getPollEvents(@Context HttpServletRequest request, @QueryParam("pollKey") Integer pollKey) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/setDefaultTab")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO setDefaultTab(@Context HttpServletRequest request, @QueryParam("defaultTab") String defaultTab) throws InvalidSessionRESTful;

    @GET
    @Path("/secure/toggleFormArchive")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO toggleFormArchive(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, @QueryParam("formKey") String formKey, @QueryParam("publicationKey") String publicationKey, @QueryParam("archiveInd") Boolean archiveInd) throws InvalidSessionRESTful, UnauthorizedActionException;

    @GET
    @Path("/secure/unlock")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO unlock(@Context HttpServletRequest request, @QueryParam("instanceId") String instanceId) throws InvalidSessionRESTful, UnauthorizedActionException;

    @POST
    @Path("/secure/sendFeedback")
    @Produces(MediaType.APPLICATION_JSON)
    public BooleanTO sendFeedback(@Context HttpServletRequest request, @QueryParam("cohortKey") String cohortKey, String feedback) throws InvalidSessionRESTful;

}
