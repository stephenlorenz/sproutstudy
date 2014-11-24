package edu.harvard.mgh.lcs.sprout.forms.study.wsinterface;

import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;

@WebService
public interface SproutStudyWebService {
    public abstract BooleanTO markAsRead(@WebParam(name = "instanceId") String instanceId);
    public abstract String getFormFromPublicationKey(@WebParam(name = "publicationKey") String publicationKey);
    public abstract String getFormKeyFromPublicationKey(@WebParam(name = "publicationKey") String publicationKey);
}
