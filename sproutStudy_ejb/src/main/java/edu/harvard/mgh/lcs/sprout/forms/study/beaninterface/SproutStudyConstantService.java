package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import java.util.Calendar;

public interface SproutStudyConstantService {

    public String SYSTEM_ACCOUNT_USERNAME = "system";
    public String TEMP_DOMAIN_NAME = "partners";

    public static enum SubmissionStatus {NEW, INPROGRESS, PUSHED, HOLD, REVOKED, NONE, ERROR}
    public static enum InboxStatus {NEW, READ, STAR, ARCHIVE, DELETE}
    public static enum FormAttr {UNIQUE}
    public static enum UserPreference {COHORT_ID, EMAIL_PRIMARY, EMAIL_SECONDARY, SMS_TEXT_PHONE}
    public static enum CohortAttribute {QUERY, QUERY_AUTH_USERNAME, QUERY_AUTH_PASSWORD, IDENTITY_SCHEMA_PRIMARY}

	public static enum PatientVerificationSearchSelector {MRN, LASTNAME, FIRSTNAME, MIDDLENAME, DOB, SEX, PHONE, ADDRESS}
	public static final String SENDER_FRONT_OFFICE = "FRONT_OFFICE";
	public static final String IDENTITY_SCHEMA_PRIMARY = "IDENTITY_SCHEMA_PRIMARY";

    public static final String DEFAULT_FORM_EMAIL_SUBJECT = "A SproutStudy form has been delivered to you.";

    public static final String DEFAULT_WEB_SOCKET_URL_MASK = "wss://scl30.partners.org:8443/sproutstudy/sproutStudyFormState/%s";

    public static enum AuditType {INFO, ERROR, DEBUG, WARN, FORM_IMPORT, FORM_COMPILATION, USER_LOGIN, ADMIN_LOGIN, USER_LOGOUT, ADMIN_LOGOUT, USER_CHANGE_PASSWORD, ADMIN_CHANGE_PASSWORD, USER_LOGIN_FAILURE, IDENTITY_TRANSACTION, AUTHORIZATION_EXCEPTION, FORM_SUBMISSION, LOAD_FORM_DATASOURCE, FORM_DELETION, LOAD_FORM, EXIT_FORM, SUBMIT_FORM, PING_FORM, FORM_DELIVERY, NONCE_AUTH, GET_INBOX, NONCE_APPLICATION, SEND_FORM_EMAIL, PUBLISH_FORM_EMAIL_QUEUE, ADMIN_NEW_COHORT, ADMIN_EDIT_COHORT, ADMIN_AUTHORIZE_COHORT, DELETE_INBOX, REVOKE_FORM}
    public static enum AuditCategory {SYSTEM, SECURITY, APPLICATION}
    public static enum AuditVerbosity {INFO, ERROR, DEBUG, WARN}


}
