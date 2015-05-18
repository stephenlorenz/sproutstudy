package edu.harvard.mgh.lcs.sprout.study.websockets;

import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import edu.harvard.mgh.lcs.sprout.study.websocketsinterface.SproutStudyFormStateInterface;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.IOException;

/**
 * Simple annotated class that demonstrate the power of Atmosphere. This class supports all transports, support
 * message length guarantee, heart beat, message cache thanks to the @ManagedService.
 */
@ManagedService(path = "/sproutStudyFormState")
@Singleton
@Stateless
@Remote(SproutStudyFormStateInterface.class)
public class SproutStudyFormState implements SproutStudyFormStateInterface {
    private static final Logger logger = LoggerFactory.getLogger(SproutStudyFormState.class);

    public static Broadcaster broadcaster;

    /**
     * Invoked when the connection as been fully established and suspended, e.g ready for receiving messages.
     *
     * @param r the atmosphere resource
     */
    @Ready
    public final void onReady(final AtmosphereResource r){

        broadcaster = r.getBroadcaster();

        logger.info("Browser {} connected.", r.uuid());
        System.out.println("broadcaster = " + broadcaster);

        broadcaster.broadcast(new LockTO());
    }

    @Override
    public void broadcast(LockTO lockTO) {
        if (broadcaster != null) {
            broadcaster.broadcast(lockTO);
        }
    }

    /**
     * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
     *
     * @param event the event
     */
    @Disconnect
    public final void onDisconnect(final AtmosphereResourceEvent event){
        if(event.isCancelled())
            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        else if(event.isClosedByClient())
            logger.info("Browser {} closed the connection", event.getResource().uuid());
    }

    /**
     * Simple annotated class that demonstrate how {@link org.atmosphere.config.managed.Encoder} and {@link org.atmosphere.config.managed.Decoder
     * can be used.
     *
     * @param message an instance of {@link LockTO }
     * @return the chat message
     * @throws IOException
     */
    @Message(encoders = {JacksonEncoder.class}, decoders = {JacksonDecoder.class})
    public final LockTO onMessage(final LockTO lockTO) throws IOException{
        System.out.println("SproutStudyFormState.onMessage");
        logger.info("SproutStudyFormState.onMessage.instanceId: {}", lockTO.getInstanceId());

        return lockTO;
    }
}
