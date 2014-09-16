package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ApplicationAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.GrantedAuthorityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.UserTO;
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

}
