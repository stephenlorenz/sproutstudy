package edu.harvard.mgh.lcs.sprout.forms.study.mdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.exceptions.InvalidPublicationKeyException;
import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.websocketsinterface.SproutStudyFormStateInterface;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.HashSet;
import java.util.Set;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="topic/sproutFormLockTopic"),
                @ActivationConfigProperty(propertyName="clientID", propertyValue = "SproutStudyFormsLockMDB")
        })
public class SproutFormsLockMDB implements MessageListener {

    @SuppressWarnings("EjbEnvironmentInspection")
    @EJB
    private SproutStudyFormStateInterface sproutStudyFormState;

    @EJB
    private StudyService studyService;

    @Override
    public void onMessage(Message message) {

        System.out.println("SproutFormsLockMDB.onMessage");

        TextMessage textMessage = (TextMessage) message;
        try {
            String content = textMessage.getText();
            System.out.println("content = " + content);

            ObjectMapper mapper = new ObjectMapper();
            LockTO lockTO = mapper.readValue(content, LockTO.class);

            System.out.println("lockTO.getInstanceId() = " + lockTO.getInstanceId());

            String publicationKey = lockTO.getPublicationKey();

            if (StringUtils.isFull(publicationKey)) {
                Set<CohortTO> cohorts = studyService.getCohortsFromPublicationKey(publicationKey);
//                sproutStudyFormState.broadcast(cohorts, lockTO);
            } else {
                throw new InvalidPublicationKeyException(null);
            }

//            SproutStudyFormState.broadcaster.broadcast(lockTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
