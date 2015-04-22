package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.TemplateTO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface SproutTransformService {
	public String getTemplate(String publicationKey, String instanceId);
	public TemplateTO getTemplateTO(String publicationKey, String instanceId);
	public BooleanTO saveTemplate(String publicationKey, String instanceId, String template);
	public BooleanTO saveNarrative(String instanceId, String narrative, String format);
}
