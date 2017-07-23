package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.exception.HtmlToPdfException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ContentTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.TemplateTO;

public interface SproutTransformService {
	String getTemplate(String publicationKey, String instanceId);
	TemplateTO getTemplateTO(String publicationKey, String instanceId);
	BooleanTO saveTemplate(String publicationKey, String instanceId, String template, String templateKey, boolean masterInd, String translations);
	BooleanTO saveNarrative(String instanceId, String narrative, String format);
	String getNarrativeByInstanceId(String instanceId);
	String getNarrativeByInstanceId(String instanceId, String format);
	String getNarrative(String publicationKey, String instanceId, String jsonData);
	String getNarrative(String publicationKey, String instanceId, String jsonData, String format);
	String getNarrative(String publicationKey, String instanceId, String jsonData, String format, String locale, String type);
	void saveNarrativeModel(String instanceId, String model);
	String transformHtml2Markdown(String narrative, String lineSeparator);
	String transformHtml2Markdown(String narrative);
	byte[] transformHtml2PDF(String narrative) throws HtmlToPdfException;
	String transformHtml2PDFAsString(String narrative) throws HtmlToPdfException;
	ContentTO transformHtml2PDFAsContentTO(String narrative) throws HtmlToPdfException;
	BooleanTO saveNarrativeWithLocaleAndType(String instanceId, String narrative, String format, String locale, String type);
	String getNarrativeByInstanceId(String instanceId, String format, String locale, String type);
}
