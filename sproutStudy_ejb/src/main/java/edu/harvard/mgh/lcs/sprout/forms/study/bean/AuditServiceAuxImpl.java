package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditServiceAux;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.study.model.study.VAuditCategoryEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.VAuditTypeEntity;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
@Local(AuditServiceAux.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuditServiceAuxImpl implements AuditServiceAux, SproutStudyConstantService {

    private static final Logger log = Logger.getLogger(AuditService.class.getName());

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public VAuditTypeEntity createVAuditTypeEntity(String code, VAuditCategoryEntity vAuditCategoryEntity) {
        System.out.println("AuditServiceImpl.createVAuditTypeEntity.code123: " + code);
        VAuditTypeEntity vAuditTypeEntity = new VAuditTypeEntity();
        vAuditTypeEntity.setActivityDate(new Date());
        vAuditTypeEntity.setCode(code);
        vAuditTypeEntity.setDescription(String.format("Auto-generated Audit Type: %s", code));
        vAuditTypeEntity.setCategory(vAuditCategoryEntity);
        entityManager.persist(vAuditTypeEntity);
        return vAuditTypeEntity;
    }


}
