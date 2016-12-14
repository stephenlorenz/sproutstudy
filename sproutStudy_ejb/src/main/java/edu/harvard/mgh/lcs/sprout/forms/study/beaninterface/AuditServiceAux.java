package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.study.model.study.VAuditCategoryEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.VAuditTypeEntity;

public interface AuditServiceAux {
    VAuditTypeEntity createVAuditTypeEntity(String code, VAuditCategoryEntity vAuditCategoryEntity);
}
