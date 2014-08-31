package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ApplicationAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.GrantedAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.UserTO;
import edu.harvard.mgh.lcs.sprout.study.model.cas.ApplicationAuthorityEntity;
import edu.harvard.mgh.lcs.sprout.study.model.cas.GrantedAuthorityEntity;
import edu.harvard.mgh.lcs.sprout.study.model.cas.SproutAdminUserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.naming.InvalidNameException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(SecurityService.class)
@TransactionManagement
public class SecurityServiceImpl implements SecurityService {

    LdapTemplate ldapTemplate;
    ApplicationContext applicationContext;

    public static final String APPLICATION_NAME = "FRONTOFFICE";

    @PersistenceContext(unitName = "cas_PU")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getGrantedAuthorties(String principal) {
        List<String> grantedAuthorities = new ArrayList<String>();

        Query query = entityManager.createNamedQuery(ApplicationAuthorityEntity.GET_AUTHORITIES);
        query.setParameter("applicationName", APPLICATION_NAME);
        query.setParameter("principal", principal);
        List<ApplicationAuthorityEntity> applicationAuthorityEntities = query.getResultList();
        if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
            for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities)
                grantedAuthorities.add(formatGrantedAuthority(applicationAuthorityEntity.getCode()));
            return grantedAuthorities;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BooleanTO updateManager(int grantedAuthId, Boolean manager, String principal) {
        List<String> grantedAuthorities = new ArrayList<String>();

        GrantedAuthorityEntity grantedAuthorityEntity = entityManager.find(GrantedAuthorityEntity.class, grantedAuthId);
        if (grantedAuthorityEntity != null) {
            if (isAdministrator(principal)) {
                grantedAuthorityEntity.setManager(manager);
                entityManager.merge(grantedAuthorityEntity);
            } else {
                List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
                if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                    boolean hasManagerRole = false;
                    for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities) {
                        if (applicationAuthorityEntity.getId() == grantedAuthorityEntity.getApplicationAuthority().getId()) {
                            hasManagerRole = true;
                            break;
                        }
                    }
                    if (hasManagerRole) {
                        grantedAuthorityEntity.setManager(manager);
                        entityManager.merge(grantedAuthorityEntity);
                    }
                }
            }

        }
        return null;
    }


    @Override
    public BooleanTO saveFrontOfficeUserAuth(int applicationAuthId, String username, boolean managerInd, String principal) {
        ApplicationAuthorityEntity applicationAuthorityEntity = entityManager.find(ApplicationAuthorityEntity.class, applicationAuthId);
        if (applicationAuthorityEntity != null) {
            if (isAdministrator(principal)) {
                GrantedAuthorityEntity grantedAuthorityEntity = getGrantedAuthority(applicationAuthorityEntity, username);
                if (grantedAuthorityEntity == null) {
                    grantedAuthorityEntity = new GrantedAuthorityEntity();
                    grantedAuthorityEntity.setApplicationAuthority(applicationAuthorityEntity);
                    grantedAuthorityEntity.setPrincipal(username);
                    grantedAuthorityEntity.setManager(managerInd);
                    entityManager.persist(grantedAuthorityEntity);
                } else {
                    grantedAuthorityEntity.setManager(managerInd);
                    entityManager.persist(grantedAuthorityEntity);
                }
                return new BooleanTO(true);
            } else {
                List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
                if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                    boolean hasManagerRole = false;
                    for (ApplicationAuthorityEntity applicationAuthorityEntityTmp : applicationAuthorityEntities) {
                        if (applicationAuthorityEntityTmp.getId() == applicationAuthorityEntity.getId()) {
                            hasManagerRole = true;
                            break;
                        }
                    }
                    if (hasManagerRole) {
                        GrantedAuthorityEntity grantedAuthorityEntity = getGrantedAuthority(applicationAuthorityEntity, username);
                        if (grantedAuthorityEntity == null) {
                            grantedAuthorityEntity = new GrantedAuthorityEntity();
                            grantedAuthorityEntity.setApplicationAuthority(applicationAuthorityEntity);
                            grantedAuthorityEntity.setPrincipal(username);
                            grantedAuthorityEntity.setManager(managerInd);
                            entityManager.persist(grantedAuthorityEntity);
                        } else {
                            grantedAuthorityEntity.setManager(managerInd);
                            entityManager.persist(grantedAuthorityEntity);
                        }
                        return new BooleanTO(true);
                    }
                }
            }
        }

        return new BooleanTO(false);
    }

    private GrantedAuthorityEntity getGrantedAuthority(ApplicationAuthorityEntity applicationAuthorityEntity, String username) {
        try {
            Query query = entityManager.createNamedQuery(GrantedAuthorityEntity.GET_GRANTED_AUTHORITY);
            query.setParameter("applicationAuthority", applicationAuthorityEntity);
            query.setParameter("principal", username);
            return (GrantedAuthorityEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GrantedAuthorityTO> deleteFrontOfficeUserAuth(int grantedAuthId, String principal) {
        GrantedAuthorityEntity grantedAuthorityEntity = entityManager.find(GrantedAuthorityEntity.class, grantedAuthId);
        if (grantedAuthorityEntity != null) {
            if (isAdministrator(principal)) {
                entityManager.remove(grantedAuthorityEntity);
            } else {
                List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
                if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                    boolean hasManagerRole = false;
                    for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities) {
                        if (applicationAuthorityEntity.getId() == grantedAuthorityEntity.getApplicationAuthority().getId()) {
                            hasManagerRole = true;
                            break;
                        }
                    }
                    if (hasManagerRole) entityManager.remove(grantedAuthorityEntity);
                }
            }
        }

        return getFrontOfficeUserAuth(principal);
    }

    @SuppressWarnings("unchecked")
    @Override
    public GrantedAuthorityTO getFrontOfficeUserAuth(int grantedAuthId, String principal) {
        GrantedAuthorityEntity grantedAuthorityEntity = entityManager.find(GrantedAuthorityEntity.class, grantedAuthId);
        if (grantedAuthorityEntity != null) {
            if (isAdministrator(principal)) {
                return assembleGrantedAuthorityTO(grantedAuthorityEntity);
            } else {
                List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
                if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                    for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities) {
                        if (applicationAuthorityEntity.getId() == grantedAuthorityEntity.getApplicationAuthority().getId()) {
                            return assembleGrantedAuthorityTO(grantedAuthorityEntity);
                        }
                    }
                }
            }

        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BooleanTO isManager(String principal) {
        List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
        return new BooleanTO(applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0);
    }

    @Override
    public BooleanTO isAdmin(String principal) {
        return new BooleanTO(isAdministrator(principal));
    }

    private boolean isAdministrator(String principal) {
        try {
            Query query = entityManager.createNamedQuery(SproutAdminUserEntity.BY_PRINCIPAL);
            query.setParameter("principal", principal);
            SproutAdminUserEntity sproutAdminUserEntity = (SproutAdminUserEntity) query.getSingleResult();
            if (sproutAdminUserEntity != null) return true;
        } catch (NoResultException e) {}
        return false;
    }
//    SproutAdminUserEntity

    @SuppressWarnings("unchecked")
    @Override
    public List<GrantedAuthorityTO> getFrontOfficeUserAuth(String principal) {
        List<GrantedAuthorityTO> grantedAuthorities = new ArrayList<GrantedAuthorityTO>();

        if (isAdministrator(principal)) {
            Query query = entityManager.createNamedQuery(GrantedAuthorityEntity.GET_ALL_GRANTED_AUTHORITIES);
            List<GrantedAuthorityEntity> grantedAuthorityEntities = query.getResultList();
            if (grantedAuthorityEntities != null && grantedAuthorityEntities.size() > 0) {
                for (GrantedAuthorityEntity grantedAuthorityEntity : grantedAuthorityEntities)
                    grantedAuthorities.add(assembleGrantedAuthorityTO(grantedAuthorityEntity));
                return grantedAuthorities;
            }
        } else {
            List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);

            Query query = entityManager.createNamedQuery(GrantedAuthorityEntity.GET_GRANTED_AUTHORITIES);
            query.setParameter("applicationAuthorities", applicationAuthorityEntities);
            List<GrantedAuthorityEntity> grantedAuthorityEntities = query.getResultList();
            if (grantedAuthorityEntities != null && grantedAuthorityEntities.size() > 0) {
                for (GrantedAuthorityEntity grantedAuthorityEntity : grantedAuthorityEntities)
                    grantedAuthorities.add(assembleGrantedAuthorityTO(grantedAuthorityEntity));
                return grantedAuthorities;
            }
        }
        return null;
    }

    @Override
    public UserTO getUser(String cn) {
        DirContextAdapter dirContextAdapter = findPartnersUser(cn);
        if (dirContextAdapter != null) {
            Attributes attributes = dirContextAdapter.getAttributes();
            if (attributes != null) {
                UserTO userTO = new UserTO();

                userTO.setSn(getAttribute("sn", attributes));
                userTO.setGivenName(getAttribute("givenName", attributes));
                userTO.setDisplayName(getAttribute("displayName", attributes));
                userTO.setCn(getAttribute("cn", attributes));
                userTO.setEmail(getAttribute("mail", attributes));
                return userTO;
            }
        }

        return null;
    }

    private GrantedAuthorityTO assembleGrantedAuthorityTO(GrantedAuthorityEntity grantedAuthorityEntity) {
        GrantedAuthorityTO grantedAuthorityTO = new GrantedAuthorityTO();
        grantedAuthorityTO.setPrincipal(grantedAuthorityEntity.getPrincipal());
        grantedAuthorityTO.setId(grantedAuthorityEntity.getId());
        grantedAuthorityTO.setApplicationAuthId(grantedAuthorityEntity.getApplicationAuthority().getId());
        grantedAuthorityTO.setApplicationAuthName(grantedAuthorityEntity.getApplicationAuthority().getDescription());
        grantedAuthorityTO.setManager(grantedAuthorityEntity.isManager());

        DirContextAdapter dirContextAdapter = findPartnersUser(grantedAuthorityEntity.getPrincipal());
        if (dirContextAdapter != null) {
            Attributes attributes = dirContextAdapter.getAttributes();
            if (attributes != null) {
                grantedAuthorityTO.setSn(getAttribute("sn", attributes));
                grantedAuthorityTO.setGivenName(getAttribute("givenName", attributes));
                grantedAuthorityTO.setDisplayName(getAttribute("displayName", attributes));
                grantedAuthorityTO.setCn(getAttribute("cn", attributes));
                grantedAuthorityTO.setEmail(getAttribute("mail", attributes));
            }
        }

        return grantedAuthorityTO;
    }

    private String getAttribute(String name, Attributes attributes) {
        try {
            return attributes.get(name).get(0).toString();
        } catch (javax.naming.NamingException e) {
        } catch (NullPointerException e) {
//            System.out.println("getAttribute.NullPointerException: " + name);
        }
        return null;
    }

    @Override
    public List<ApplicationAuthorityTO> getApplicationAuthorities(String principal) {
        List<ApplicationAuthorityTO> applicationAuthorityTOList = new ArrayList<ApplicationAuthorityTO>();

        if (isAdministrator(principal)) {
            List<ApplicationAuthorityEntity> applicationAuthorityEntities = getAdminAuthorities();
            if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities) {
                    ApplicationAuthorityTO applicationAuthorityTO = new ApplicationAuthorityTO();
                    applicationAuthorityTO.setId(applicationAuthorityEntity.getId());
                    applicationAuthorityTO.setCode(applicationAuthorityEntity.getCode());
                    applicationAuthorityTO.setDescription(applicationAuthorityEntity.getDescription());
                    applicationAuthorityTO.setApplicationId(applicationAuthorityEntity.getApplication().getId());
                    applicationAuthorityTO.setApplicationName(applicationAuthorityEntity.getApplication().getName());
                    applicationAuthorityTO.setApplicationDescription(applicationAuthorityEntity.getApplication().getDescription());
                    applicationAuthorityTO.setApplicationTitle(applicationAuthorityEntity.getApplication().getTitle());

                    applicationAuthorityTOList.add(applicationAuthorityTO);
                }
            }

        } else {
            List<ApplicationAuthorityEntity> applicationAuthorityEntities = getManagerAuthorities(principal);
            if (applicationAuthorityEntities != null && applicationAuthorityEntities.size() > 0) {
                for (ApplicationAuthorityEntity applicationAuthorityEntity : applicationAuthorityEntities) {
                    ApplicationAuthorityTO applicationAuthorityTO = new ApplicationAuthorityTO();
                    applicationAuthorityTO.setId(applicationAuthorityEntity.getId());
                    applicationAuthorityTO.setCode(applicationAuthorityEntity.getCode());
                    applicationAuthorityTO.setDescription(applicationAuthorityEntity.getDescription());
                    applicationAuthorityTO.setApplicationId(applicationAuthorityEntity.getApplication().getId());
                    applicationAuthorityTO.setApplicationName(applicationAuthorityEntity.getApplication().getName());
                    applicationAuthorityTO.setApplicationDescription(applicationAuthorityEntity.getApplication().getDescription());
                    applicationAuthorityTO.setApplicationTitle(applicationAuthorityEntity.getApplication().getTitle());

                    applicationAuthorityTOList.add(applicationAuthorityTO);
                }
            }
        }

        return applicationAuthorityTOList;
    }

    private List<ApplicationAuthorityEntity> getManagerAuthorities(String principal) {
        Query query = entityManager.createNamedQuery(ApplicationAuthorityEntity.GET_MANAGER_AUTHORITIES);
        query.setParameter("applicationName", APPLICATION_NAME);
        query.setParameter("principal", principal);
        return query.getResultList();
    }

    private List<ApplicationAuthorityEntity> getAdminAuthorities() {
        Query query = entityManager.createNamedQuery(ApplicationAuthorityEntity.GET_ADMIN_AUTHORITIES);
        query.setParameter("applicationName", APPLICATION_NAME);
        return query.getResultList();
    }

    private DirContextAdapter findPartnersUser(String principal) {
        if (ldapTemplate != null) {
            try {
                LdapName ldapName = new LdapName(String.format("cn=%s", principal));
                return (DirContextAdapter) ldapTemplate.lookup(ldapName);
            } catch (InvalidNameException e) {
            } catch (NameNotFoundException e) {
            } catch (   Exception e) {
            }
        }
        return null;
    }

    private String formatGrantedAuthority(String role) {
//		return String.format("ROLE_%s", role);
        return role;
    }

    @PostConstruct
    private void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-ldap.xml");
        if (applicationContext != null) ldapTemplate = (LdapTemplate) applicationContext.getBean("ldapTemplate");
    }
}
