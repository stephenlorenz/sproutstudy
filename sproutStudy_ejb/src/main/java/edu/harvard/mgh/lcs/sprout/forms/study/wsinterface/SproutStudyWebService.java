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
    public String getPublicationKeyFromFormKey(@WebParam(name = "formKey") String formKey);
    public String getNarrative(@WebParam(name = "publicationKey") String publicationKey, @WebParam(name = "instanceId") String instanceId, @WebParam(name = "jsonData") String jsonData, @WebParam(name = "format") String format);
    public String getNarrativeByInstanceId(@WebParam(name = "instanceId") String instanceId);
    public String getNarrativeByInstanceIdWithFormat(@WebParam(name = "instanceId") String instanceId, @WebParam(name = "format") String format);
}
