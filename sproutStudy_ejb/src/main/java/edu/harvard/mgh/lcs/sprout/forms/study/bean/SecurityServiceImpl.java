package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.cas.ApplicationAuthorityEntity;
import edu.harvard.mgh.lcs.sprout.study.model.cas.GrantedAuthorityEntity;
import edu.harvard.mgh.lcs.sprout.study.model.cas.SproutAdminUserEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.UserEntity;
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

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @Override
    public UserEntity getUserFromUsername(String username, String domain) {
        if (!StringUtils.isEmpty(username)) {
            try {
                Query query = entityManager.createNamedQuery(UserEntity.BY_USERNAME);
                query.setParameter("username", username);
                return (UserEntity) query.getSingleResult();
            } catch (NoResultException e) {}
        }
        return null;
    }

    @Override
    public LdapUserTO getUser(String cn) {
        DirContextAdapter dirContextAdapter = findPartnersUser(cn);

        if (dirContextAdapter != null) {
            Attributes attributes = dirContextAdapter.getAttributes();
            if (attributes != null) {
                LdapUserTO ldapUserTO = new LdapUserTO();

                ldapUserTO.setSn(getAttribute("sn", attributes));
                ldapUserTO.setGivenName(getAttribute("givenName", attributes));
                ldapUserTO.setDisplayName(getAttribute("displayName", attributes));
                ldapUserTO.setCn(getAttribute("cn", attributes));
                ldapUserTO.setEmail(getAttribute("mail", attributes));
                return ldapUserTO;
            }

        }

        return null;
    }

    public LdapUserTO getUserDummy(String cn) {
        LdapUserTO ldapUserTO = new LdapUserTO();

        ldapUserTO.setSn("Rogers");
        ldapUserTO.setGivenName("Fred");
        ldapUserTO.setDisplayName("Fred Rogers");
        ldapUserTO.setCn("fr10");
        ldapUserTO.setEmail("frogers@gmail.com");
        return ldapUserTO;
    }

    private DirContextAdapter findPartnersUser(String principal) {

        System.out.println("SecurityServiceImpl.findPartnersUser");
        System.out.println("principal = [" + principal + "]");

        System.out.println("ldapTemplate = " + ldapTemplate);

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

    private String getAttribute(String name, Attributes attributes) {
        try {
            return attributes.get(name).get(0).toString();
        } catch (javax.naming.NamingException e) {
        } catch (NullPointerException e) {
//            System.out.println("getAttribute.NullPointerException: " + name);
        }
        return null;
    }

    @PostConstruct
    private void init() {

        System.out.println("SecurityServiceImpl.init");

        applicationContext = new ClassPathXmlApplicationContext("applicationContext-ldap.xml");

        System.out.println("applicationContext = " + applicationContext);

        if (applicationContext != null) ldapTemplate = (LdapTemplate) applicationContext.getBean("ldapTemplate");

        System.out.println("ldapTemplate = " + ldapTemplate);

    }


}
