package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;

public class StudyInboxTO implements Serializable {

    private int id;
    private String subject;
    private String body;
    private UserTO sender;
    private UserTO recipient;
    private String subjectName;
    private String subjectId;
    private String instanceId;
    private String form;
    private String formTitle ="bo";
    private InboxStatusTO status;
    private Date activityDate;
    private CohortTO cohortTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserTO getSender() {
        return sender;
    }

    public void setSender(UserTO sender) {
        this.sender = sender;
    }

    public UserTO getRecipient() {
        return recipient;
    }

    public void setRecipient(UserTO recipient) {
        this.recipient = recipient;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        System.out.println("StudyInboxTO.setForm");
        System.out.println("formTitle = [" + formTitle + "]");

        this.formTitle = formTitle;
    }

    public InboxStatusTO getStatus() {
        return status;
    }

    public void setStatus(InboxStatusTO status) {
        this.status = status;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public CohortTO getCohortTO() {
        return cohortTO;
    }

    public void setCohortTO(CohortTO cohortTO) {
        this.cohortTO = cohortTO;
    }
}
