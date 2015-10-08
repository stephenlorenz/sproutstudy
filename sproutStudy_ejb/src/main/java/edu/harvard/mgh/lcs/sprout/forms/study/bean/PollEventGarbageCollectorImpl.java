package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormLockServiceLocal;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.DuplicateCohortListKeyException;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.DuplicateCohortNameException;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.ejb.*;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class PollEventGarbageCollectorImpl {

    @PersistenceContext(unitName="sproutStudy_PU")
    private EntityManager entityManager;

    public static final int EVENT_TTL_MINUTES = 1;

    private static final Logger LOGGER = Logger.getLogger(PollEventGarbageCollectorImpl.class.getName());

    @Schedule(second="*/30", minute="*",hour="*", persistent=false)
    public void collectGarbage() {
        Query query = entityManager.createNativeQuery(String.format("DELETE FROM dbo.poll_event WHERE activity_date < DATEADD(MINUTE,-%s,getdate())", EVENT_TTL_MINUTES));
        int recordsDeleted = query.executeUpdate();

        LOGGER.fine(String.format("PollEventGarbageCollectorImpl.collectGarbage cleared %s stale events.", recordsDeleted));
    }

}
