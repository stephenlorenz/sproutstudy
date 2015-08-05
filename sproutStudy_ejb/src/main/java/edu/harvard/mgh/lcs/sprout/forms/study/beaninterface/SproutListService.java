package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SproutListTO;

import java.util.List;

public interface SproutListService {
    public List<SproutListTO> getList(String listKey, String publicationKey) throws UnauthorizedActionException;
    public SproutListTO getListTO(String listKey, String publicationKey, String value) throws UnauthorizedActionException;
}
