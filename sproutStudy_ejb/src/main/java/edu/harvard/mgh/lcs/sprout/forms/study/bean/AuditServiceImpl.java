package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidAuditTypeCodeException;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.InvalidAuditVerbosityCodeException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Local(AuditService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class AuditServiceImpl implements AuditService, SproutStudyConstantService {

    private static final Logger log = Logger.getLogger(AuditService.class.getName());

    private Map<String, VAuditTypeEntity> auditTypeMap = null;
    private Map<String, VAuditVerbosityEntity> auditVerbosityMap = null;

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private SecurityService securityService;

    @EJB
    private StudyService studyService;

    private UserEntity systemUserEntity = null;

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @Override
    public int info(String message) {
        return addGenericAuditMessage(AuditType.INFO, AuditVerbosity.INFO, null, message);
    }

    @Override
    public int error(String message) {
        return addGenericAuditMessage(AuditType.ERROR, AuditVerbosity.ERROR, null, message);
    }

    @Override
    public int error(String message, Exception exception) {
        return addGenericAuditMessage(AuditType.ERROR, AuditVerbosity.ERROR, null, formatException(message, exception));
    }

    @Override
    public int warn(String message) {
        return addGenericAuditMessage(AuditType.WARN, AuditVerbosity.WARN, null, message);
    }

    @Override
    public int debug(String message) {
        return addGenericAuditMessage(AuditType.DEBUG, AuditVerbosity.DEBUG, null, message);
    }

    @Override
    public int info(String title, String message) {
        return addGenericAuditMessage(AuditType.INFO, AuditVerbosity.INFO, title, message);
    }

    @Override
    public int error(String title, String message) {
        return addGenericAuditMessage(AuditType.ERROR, AuditVerbosity.ERROR, null, message);
    }

    @Override
    public int error(String title, String message, Exception exception) {
        return addGenericAuditMessage(AuditType.ERROR, AuditVerbosity.ERROR, null, formatException(message, exception));
    }

    @Override
    public int warn(String title, String message) {
        return addGenericAuditMessage(AuditType.WARN, AuditVerbosity.WARN, null, message);
    }

    @Override
    public int debug(String title, String message) {
        return addGenericAuditMessage(AuditType.DEBUG, AuditVerbosity.DEBUG, null, message);
    }

    @Override
    public int info(int auditId, String message) {
        return addAuditMessage(auditId, AuditVerbosity.INFO, message);
    }

    @Override
    public int error(int auditId, String message) {
        return addAuditMessage(auditId, AuditVerbosity.ERROR, message);
    }

    @Override
    public int error(int auditId, String message, Exception exception) {
        return addAuditMessage(auditId, AuditVerbosity.ERROR, formatException(message, exception));
    }

    @Override
    public int warn(int auditId, String message) {
        return addAuditMessage(auditId, AuditVerbosity.WARN, message);
    }

    @Override
    public int debug(int auditId, String message) {
        return addAuditMessage(auditId, AuditVerbosity.DEBUG, message);
    }

    @Override
    public int log(String username, AuditType auditType, AuditVerbosity verbosity, String title, String message) {
        return addAuditMessage(securityService.getUserFromUsername(username, null), auditType, verbosity, title, message);
    }

    @Override
    public int log(String username, AuditType auditType, AuditVerbosity verbosity, String title, CohortTO cohortTO, String subjectSchema, String subjectId, String message) {
        return addAuditMessage(securityService.getUserFromUsername(username, null), auditType, verbosity, title, studyService.getCohort(cohortTO), subjectSchema, subjectId, message);
    }

    public int log(UserEntity userEntity, AuditType auditType, AuditVerbosity verbosity, String title, String message) {
        return addAuditMessage(userEntity, auditType, verbosity, title, message);
    }

    @Override
    public int log(AuditType auditType, AuditVerbosity verbosity, String title, String message) {
        return addAuditMessage(null, auditType, verbosity, title, message);
    }

    @Override
    public int log(int auditId, AuditVerbosity verbosity, String message) {
        return addAuditMessage(auditId, verbosity, message);
    }

    private int addGenericAuditMessage(AuditType auditType, AuditVerbosity verbosity, String title, String message) {
        return addAuditMessage(null, auditType, verbosity, title, message);
    }

    private int addAuditMessage(UserEntity userEntity, AuditType auditType, AuditVerbosity verbosity, String title, String message) {
        return addAuditMessage(userEntity, auditType, verbosity, title, null, null, null, message);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private int addAuditMessage(UserEntity userEntity, AuditType auditType, AuditVerbosity verbosity, String title, CohortEntity cohortEntity, String subjectSchema, String subjectId, String message) {
        try {
            if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) userTransaction.begin();
            VAuditTypeEntity vAuditTypeEntity = getVAuditTypeEntity(auditType.toString());
            VAuditVerbosityEntity vAuditVerbosityEntity = getVAuditVerbosityEntity(verbosity.toString());
            if (vAuditTypeEntity != null && vAuditVerbosityEntity != null) {
                AuditEntity auditEntity = new AuditEntity();
                auditEntity.setAuditType(vAuditTypeEntity);
                auditEntity.setTitle(title != null ? title : auditType.toString());
                auditEntity.setCohort(cohortEntity);
                auditEntity.setSubjectSchema(subjectSchema);
                auditEntity.setSubjectId(subjectId);
                auditEntity.setUser(userEntity != null ? userEntity : getSystemUser(SproutStudyConstantService.TEMP_DOMAIN_NAME));
                entityManager.persist(auditEntity);

                AuditMessageEntity auditMessageEntity = new AuditMessageEntity(auditEntity);
                auditMessageEntity.setVerbosity(vAuditVerbosityEntity);
                auditMessageEntity.setMessage(message);
                entityManager.persist(auditMessageEntity);
                logMessage(verbosity, message);
                userTransaction.commit();

                return auditEntity.getId();
            } else {
                throw new InvalidAuditTypeCodeException(auditType.toString());
            }
        } catch (InvalidAuditTypeCodeException e) {
            log.error(message, e);
        } catch (InvalidAuditVerbosityCodeException e) {
            log.error(message, e);
        } catch (SystemException e) {
            log.error(message, e);
        } catch (NotSupportedException e) {
            log.error(message, e);
        } catch (SecurityException e) {
            log.error(message, e);
        } catch (IllegalStateException e) {
            log.error(message, e);
        } catch (RollbackException e) {
            log.error(message, e);
        } catch (HeuristicMixedException e) {
            log.error(message, e);
        } catch (HeuristicRollbackException e) {
            log.error(message, e);
        }
        return 0;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private int addAuditMessage(int auditId, AuditVerbosity auditVerbosity, String message) {
        try {

            AuditEntity auditEntity = entityManager.find(AuditEntity.class, auditId);
            VAuditVerbosityEntity vAuditVerbosityEntity = getVAuditVerbosityEntity(auditVerbosity.toString());
            if (auditEntity == null) {
                try {
                    VAuditTypeEntity vAuditTypeEntity = getVAuditTypeEntity(AuditType.INFO.toString());
                    if (vAuditTypeEntity != null) {
                        if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) userTransaction.begin();
                        auditEntity = new AuditEntity();
                        auditEntity.setAuditType(vAuditTypeEntity);
                        auditEntity.setTitle(AuditType.INFO.toString());
                        auditEntity.setUser(getSystemUser(SproutStudyConstantService.TEMP_DOMAIN_NAME));
                        entityManager.persist(auditEntity);
                        userTransaction.commit();
                    } else {
                        throw new InvalidAuditTypeCodeException(AuditType.INFO.toString());
                    }
                } catch (SystemException e) {
                    log.error(message, e);
                } catch (NotSupportedException e) {
                    log.error(message, e);
                } catch (SecurityException e) {
                    log.error(message, e);
                } catch (IllegalStateException e) {
                    log.error(message, e);
                } catch (RollbackException e) {
                    log.error(message, e);
                } catch (HeuristicMixedException e) {
                    log.error(message, e);
                } catch (HeuristicRollbackException e) {
                    log.error(message, e);
                }
            }
            if (auditEntity != null && vAuditVerbosityEntity != null) {
                if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) userTransaction.begin();
                AuditMessageEntity auditMessageEntity = new AuditMessageEntity(auditEntity);
                auditMessageEntity.setVerbosity(vAuditVerbosityEntity);
                auditMessageEntity.setMessage(message);
                entityManager.persist(auditMessageEntity);
                logMessage(auditVerbosity, message);
                userTransaction.commit();
                return auditEntity.getId();
            } else {
                throw new InvalidAuditVerbosityCodeException(auditVerbosity.toString());
            }
        } catch (InvalidAuditTypeCodeException e) {
            log.error(message, e);
        } catch (InvalidAuditVerbosityCodeException e) {
            log.error(message, e);
        } catch (SystemException e) {
            log.error(message, e);
        } catch (NotSupportedException e) {
            log.error(message, e);
        } catch (SecurityException e) {
            log.error(message, e);
        } catch (IllegalStateException e) {
            log.error(message, e);
        } catch (RollbackException e) {
            log.error(message, e);
        } catch (HeuristicMixedException e) {
            log.error(message, e);
        } catch (HeuristicRollbackException e) {
            log.error(message, e);
        }
        return 0;
    }

    private void logMessage(AuditVerbosity auditVerbosity, String message) throws InvalidAuditVerbosityCodeException {
        if (auditVerbosity != null) {
            switch (auditVerbosity) {
                case INFO:
                    log.info(message);
                    return;
                case WARN:
                    log.warn(message);
                    return;
                case ERROR:
                    log.error(message);
                    return;
                case DEBUG:
                    log.debug(message);
                    return;
                default:
                    throw new InvalidAuditVerbosityCodeException(auditVerbosity.toString());
            }
        }
        throw new InvalidAuditVerbosityCodeException("null");
    }


    private VAuditTypeEntity getVAuditTypeEntity(String code) {
        if (auditTypeMap == null) {
            auditTypeMap = new HashMap<String, VAuditTypeEntity>();
            Query query = entityManager.createNamedQuery(VAuditTypeEntity.ALL_AUDIT_TYPES);
            List<?> auditTypes = query.getResultList();
            for (Object vAuditTypeObject : auditTypes) {
                VAuditTypeEntity vAuditTypeEntity = (VAuditTypeEntity) vAuditTypeObject;
                auditTypeMap.put(vAuditTypeEntity.getCode().trim().toUpperCase(), vAuditTypeEntity);
            }
        }
        return auditTypeMap.get(code.trim().toUpperCase());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private VAuditVerbosityEntity getVAuditVerbosityEntity(String code) {
        if (auditVerbosityMap == null) {
            auditVerbosityMap = new HashMap<String, VAuditVerbosityEntity>();
            Query query = entityManager.createNamedQuery(VAuditVerbosityEntity.ALL_VERBOSITY_LEVELS);
            List<?> verbosityLevels = query.getResultList();
            for (Object vAuditVerbosityObject : verbosityLevels) {
                VAuditVerbosityEntity vAuditVerbosityEntity = (VAuditVerbosityEntity) vAuditVerbosityObject;
                auditVerbosityMap.put(vAuditVerbosityEntity.getCode().trim().toUpperCase(), vAuditVerbosityEntity);
            }
        }
        return auditVerbosityMap.get(code.trim().toUpperCase());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private UserEntity getSystemUser(String domainName) {
        if (systemUserEntity == null) {
            Query query = entityManager.createNamedQuery(DomainEntity.BY_NAME);
            query.setParameter("name", domainName);
            DomainEntity domainEntity = (DomainEntity) query.getSingleResult();
            if (domainEntity != null) {
                return getSystemUser(domainEntity);
            }
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private UserEntity getSystemUser(DomainEntity domainEntity) {
        if (systemUserEntity == null) {
            Query query = entityManager.createNamedQuery(UserEntity.BY_USERNAME_AND_DOMAIN);
            query.setParameter("username", SproutStudyConstantService.SYSTEM_ACCOUNT_USERNAME);
            query.setParameter("domain", domainEntity);
            systemUserEntity = (UserEntity) query.getSingleResult();
            return systemUserEntity;
        }
        return null;
    }

    private String formatException(String message, Exception exception) {
        StringBuilder stringBuilder = new StringBuilder(message);
        if (exception != null) {
            if (exception.getMessage() != null) stringBuilder.append("\n" + exception.getMessage());
            stringBuilder.append("\n" + ExceptionUtils.getStackTrace(exception));
        }
        return stringBuilder.toString();
    }

}
