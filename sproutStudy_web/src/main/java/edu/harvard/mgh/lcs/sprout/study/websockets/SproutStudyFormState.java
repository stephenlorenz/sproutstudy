package edu.harvard.mgh.lcs.sprout.study.websockets;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.study.websocketsinterface.SproutStudyFormStateInterface;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Simple annotated class that demonstrate the power of Atmosphere. This class supports all transports, support
 * message length guarantee, heart beat, message cache thanks to the @ManagedService.
 */
@ManagedService(path = "/sproutStudyFormState/{cohort: [a-zA-Z][a-zA-Z_0-9]*}")
@Singleton
@Stateful
@Remote(SproutStudyFormStateInterface.class)
public class SproutStudyFormState implements SproutStudyFormStateInterface {
    private static final Logger logger = LoggerFactory.getLogger(SproutStudyFormState.class);

    public static Broadcaster broadcaster;

//    private final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<String, String>();

    private final static String SPROUT_STUDY_FORM_STATE = "/sproutStudyFormState/";

    @PathParam("cohort")
    private String cohortName;

//    @Inject
    private static BroadcasterFactory broadcasterFactory;

//    @Inject
//    private AtmosphereResourceFactory atmosphereResourceFactory;
//
//    @Inject
//    private MetaBroadcaster metaBroadcaster;

    /**
     * Invoked when the connection as been fully established and suspended, e.g ready for receiving messages.
     *
     * @param atmosphereResource the atmosphere resource
     */
    @Ready(encoders = {JacksonEncoder.class})
//    @DeliverTo(DeliverTo.DELIVER_TO.ALL)
    public final CohortProtocol onReady(final AtmosphereResource atmosphereResource){

        broadcaster = atmosphereResource.getBroadcaster();

        if (broadcasterFactory == null) broadcasterFactory = atmosphereResource.getAtmosphereConfig().getBroadcasterFactory();

        logger.info("Browser {} connected.", atmosphereResource.uuid());
        System.out.println("broadcaster = " + broadcaster);
        System.out.println("broadcasterFactory = " + broadcasterFactory);

//        broadcaster.broadcast(new LockTO());

        logger.info("Browser {} connected.", atmosphereResource.uuid());
//        return new CohortProtocol(users.keySet(), getCohorts(broadcasterFactory.lookupAll()));
        return new CohortProtocol(getCohorts(broadcasterFactory.lookupAll()));
    }

    @Override
    public void broadcast(Set<CohortTO> cohorts, LockTO lockTO) {

        System.out.println("SproutStudyFormState.broadcast");
        System.out.println("broadcasterFactory = " + broadcasterFactory);
        System.out.println("broadcaster = " + broadcaster);

        if (broadcasterFactory != null) {
            if (cohorts != null && cohorts.size() > 0) {
                for (CohortTO cohortTO : cohorts) {
                    Broadcaster broadcaster = broadcasterFactory.lookup(SPROUT_STUDY_FORM_STATE + cohortTO.getCohortKey());
                    if (broadcaster != null) {
                        broadcaster.broadcast(lockTO);
                        System.out.println(String.format("SproutStudyFormState.broadcast: broadcasting to cohort: %s", cohortTO.getCohortKey()));
                    } else {
                        System.out.println(String.format("SproutStudyFormState.broadcast: skipping broadcast because unable to locate broadcaster for cohort: %s", cohortTO.getCohortKey()));
                    }
                }
            } else {
                System.out.println("SproutStudyFormState.broadcast: skipping broadcast because form is not associated with any SproutStudy cohorts.");
            }

        }
    }

    @Override
    public void broadcast(Set<CohortTO> cohorts, FormInstanceTO formInstanceTO) {

        System.out.println("SproutStudyFormState.broadcast");
        System.out.println("broadcasterFactory = " + broadcasterFactory);
        System.out.println("broadcaster = " + broadcaster);

        if (broadcasterFactory != null) {
            if (cohorts != null && cohorts.size() > 0) {
                for (CohortTO cohortTO : cohorts) {
                    Broadcaster broadcaster = broadcasterFactory.lookup(SPROUT_STUDY_FORM_STATE + cohortTO.getCohortKey());
                    if (broadcaster != null) {
                        broadcaster.broadcast(formInstanceTO);
                        System.out.println(String.format("SproutStudyFormState.broadcast: broadcasting to cohort: %s", cohortTO.getCohortKey()));
                    } else {
                        System.out.println(String.format("SproutStudyFormState.broadcast: skipping broadcast because unable to locate broadcaster for cohort: %s", cohortTO.getCohortKey()));
                    }
                }
            } else {
                System.out.println("SproutStudyFormState.broadcast: skipping broadcast because form is not associated with any SproutStudy cohorts.");
            }

        }
    }

    private static Collection<String> getCohorts(Collection<Broadcaster> broadcasters) {
        Collection<String> cohorts = new ArrayList<String>();
        for (Broadcaster broadcaster : broadcasters) {
            if (!("/*".equals(broadcaster.getID()))) {
                cohorts.add(broadcaster.getID().split("/")[2]);
            }
        }
        return cohorts;
    }

        /**
         * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
         *
         * @param event the event
         */
    @Disconnect
    public final void onDisconnect(final AtmosphereResourceEvent event){
//        if(event.isCancelled())
//            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
//        else if(event.isClosedByClient())
//            logger.info("Browser {} closed the connection", event.getResource().uuid());


        if (event.isCancelled()) {
            // We didn't get notified, so we remove the user.
//            users.values().remove(event.getResource().uuid());
            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.info("Browser {} closed the connection", event.getResource().uuid());
        }
    }

    @Message(encoders = {LockEncoder.class}, decoders = {LockDecoder.class})
    public final LockTO onMessage(final LockTO lockTO) throws IOException{
        System.out.println("SproutStudyFormState.onMessage");
        logger.info("SproutStudyFormState.onMessage.instanceId: {}", lockTO.getInstanceId());
        return lockTO;
    }

    @Message(encoders = {FormInstanceTOEncoder.class}, decoders = {FormInstanceTODecoder.class})
    public final FormInstanceTO onMessage(final FormInstanceTO formInstanceTO) throws IOException{
        System.out.println("SproutStudyFormState.onMessage");
        logger.info("SproutStudyFormState.onMessage.instanceId: {}", formInstanceTO.getInstanceId());
        return formInstanceTO;
    }
}
