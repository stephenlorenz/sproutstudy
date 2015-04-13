package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.AuditType;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.AuditVerbosity;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.UserEntity;

public interface AuditService {
    public abstract int info(String message);
    public abstract int error(String message);
    public abstract int warn(String message);
    public abstract int debug(String message);
    public abstract int error(String message, Exception exception);

    public abstract int info(String title, String message);
    public abstract int error(String title, String message);
    public abstract int warn(String title, String message);
    public abstract int debug(String title, String message);
    public abstract int error(String title, String message, Exception exception);

    public abstract int info(int auditId, String message);
    public abstract int error(int auditId, String message);
    public abstract int warn(int auditId, String message);
    public abstract int debug(int auditId, String message);
    public abstract int error(int auditId, String message, Exception exception);

    public abstract int log(UserEntity userEntity, AuditType auditType, AuditVerbosity verbosity, String title, String message);
    public abstract int log(String username, AuditType auditType, AuditVerbosity verbosity, String title, String message);
    public abstract int log(String username, AuditType auditType, AuditVerbosity verbosity, String title, CohortTO cohortTO, String subjectSchema, String subjectId, String message);
    public abstract int log(AuditType auditType, AuditVerbosity verbosity, String title, String message);
    public abstract int log(int auditId, AuditVerbosity verbosity, String message);
    public int log(UserEntity userEntity, AuditType auditType, AuditVerbosity verbosity, CohortEntity cohortEntity, String title, String message);
}
