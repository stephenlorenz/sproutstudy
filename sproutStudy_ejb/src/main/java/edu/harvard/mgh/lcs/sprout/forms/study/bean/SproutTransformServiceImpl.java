package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.*;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.transform.handlebars.Helpers;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.TemplateTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.transform.*;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
@Remote(SproutTransformService.class)
@TransactionManagement
public class SproutTransformServiceImpl implements SproutTransformService {

	@PersistenceContext(unitName = "sproutTransform_PU")
	private EntityManager entityManager;

	@EJB
	private SproutTransformService sproutTransformService;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TemplateTO getTemplateTO(String publicationKey, String instanceId) {
		TemplateTO templateTO = new TemplateTO();
		if (StringUtils.isFull(instanceId) && !instanceId.equals("null")) {
			TemplateCloneEntity templateCloneEntity = getTemplateCloneEntities(instanceId);
			if (templateCloneEntity == null && StringUtils.isFull(publicationKey)) {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) {
					templateTO.setKey(templateMasterEntity.getKey());
					templateTO.setTemplate(templateMasterEntity.getTemplate());
					return templateTO;
				}
			} else {
				templateTO.setKey(templateCloneEntity.getKey());
				templateTO.setTemplate(templateCloneEntity.getTemplate());
				return templateTO;
			}
		} else if (StringUtils.isFull(publicationKey)) {
			TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
			if (templateMasterEntity != null) {
				templateTO.setKey(templateMasterEntity.getKey());
				templateTO.setTemplate(templateMasterEntity.getTemplate());
				return templateTO;
			}
		}
		return templateTO;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveNarrativeModel(String instanceId, String model) {
		if (StringUtils.isFull(instanceId, model)) {
			NarrativeEntity narrativeEntity = getNarrative(instanceId);
			if (narrativeEntity == null) {
				narrativeEntity = new NarrativeEntity();
				narrativeEntity.setInstanceId(instanceId);
				narrativeEntity.setKey(StringUtils.getGuid());
				narrativeEntity.setActivityDate(new Date());
				entityManager.persist(narrativeEntity);
			}
			if (narrativeEntity.getModel() != null && narrativeEntity.getModel().size() > 0) {
				NarrativeModelEntity narrativeModelEntity = narrativeEntity.getModel().get(0);
				if (narrativeModelEntity != null) {
					narrativeModelEntity.setModel(model);
					narrativeModelEntity.setActivityDate(new Date());
					entityManager.merge(narrativeModelEntity);
				}
			} else {
				NarrativeModelEntity narrativeModelEntity = new NarrativeModelEntity();
				narrativeModelEntity.setNarrative(narrativeEntity);
				narrativeModelEntity.setModel(model);
				narrativeModelEntity.setActivityDate(new Date());
				entityManager.persist(narrativeModelEntity);
			}
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public BooleanTO saveNarrative(String instanceId, String narrative, String format) {
		if (StringUtils.isFull(instanceId, narrative, format)) {
			VNarrativeFormatEntity vNarrativeFormatEntity = getVNarrativeFormatEntity(format);
			if (vNarrativeFormatEntity != null) {
				NarrativeEntity narrativeEntity = getNarrative(instanceId);
				if (narrativeEntity == null) {
					narrativeEntity = new NarrativeEntity();
					narrativeEntity.setInstanceId(instanceId);
					narrativeEntity.setKey(StringUtils.getGuid());
					narrativeEntity.setActivityDate(new Date());
					entityManager.persist(narrativeEntity);

					return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity);
				} else {
					if (narrativeEntity.getFormats() != null && narrativeEntity.getFormats().size() > 0) {
						NarrativeFormatEntity narrativeFormatEntity = null;
						for (NarrativeFormatEntity narrativeFormatEntityTmp : narrativeEntity.getFormats()) {
							if (narrativeFormatEntityTmp.getFormat().getCode().equalsIgnoreCase(format)) {
								narrativeFormatEntity = narrativeFormatEntityTmp;
								break;
							}
						}
						if (narrativeFormatEntity != null) {
							narrativeFormatEntity.setData(narrative.trim());
							narrativeFormatEntity.setActivityDate(new Date());
							entityManager.merge(narrativeFormatEntity);
							return new BooleanTO(true);
						} else {
							return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity);
						}
					} else {
						return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity);
					}
				}
			} else {
				return new BooleanTO(false, "Invalid format code");
			}

		}
		return new BooleanTO(false);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrativeByInstanceId(String instanceId) {
		return getNarrativeByInstanceId(instanceId, "HTML");
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrativeByInstanceId(String instanceId, String format) {
		if (StringUtils.isEmpty(format)) format = "HTML";
		NarrativeEntity narrativeEntity = getNarrative(instanceId);
		if (narrativeEntity != null && narrativeEntity.getFormats() != null) {
			Set<NarrativeFormatEntity> narrativeFormatEntitySet = narrativeEntity.getFormats();
			if (narrativeFormatEntitySet != null && narrativeFormatEntitySet.size() > 0) {
				for (NarrativeFormatEntity narrativeFormatEntity : narrativeFormatEntitySet) {
					if (narrativeFormatEntity.getFormat().getCode().equalsIgnoreCase(format)) {
						return narrativeFormatEntity.getData();
					}
				}
			}
		}
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrative(String publicationKey, String instanceId, String jsonData) {
		return getNarrative(publicationKey, instanceId, jsonData, "HTML");
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrative(String publicationKey, String instanceId, String jsonData, String format) {

		System.out.println("SproutTransformServiceImpl.getNarrative");
		System.out.println("publicationKey = [" + publicationKey + "], instanceId = [" + instanceId + "], jsonData = [" + jsonData + "]");

		if (StringUtils.isFull(publicationKey, instanceId, jsonData)) {
			NarrativeEntity narrativeEntity = getNarrative(instanceId);
			if (narrativeEntity != null && narrativeEntity.getFormats() != null) {
				for (NarrativeFormatEntity narrativeFormatEntity : narrativeEntity.getFormats()) {
					if (narrativeFormatEntity.getFormat().getCode().equalsIgnoreCase(format)) {
						return narrativeFormatEntity.getData();
					}
				}
			}

			String templateText = null;

			TemplateCloneEntity templateCloneEntity = getTemplateCloneEntities(instanceId);
			if (templateCloneEntity != null && StringUtils.isFull(templateCloneEntity.getTemplate())) {
				templateText = templateCloneEntity.getTemplate();
			} else {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) templateText = templateMasterEntity.getTemplate();
			}

			if (StringUtils.isFull(templateText)) {

//					System.out.println("templateText = " + templateText);

				try {
					Handlebars handlebars = new Handlebars();

					handlebars.registerHelpers(new Helpers());

					Template template = handlebars.compileInline(templateText);

					JsonNode jsonNode = new ObjectMapper().readValue(jsonData, JsonNode.class);
					handlebars.registerHelper("json", Jackson2Helper.INSTANCE);

					Context context = Context
							.newBuilder(jsonNode)
							.resolver(JsonNodeValueResolver.INSTANCE,
									JavaBeanValueResolver.INSTANCE,
									FieldValueResolver.INSTANCE,
									MapValueResolver.INSTANCE,
									MethodValueResolver.INSTANCE
							)
							.build();
					String narrative = template.apply(context);

//					System.out.println("narrative = " + narrative);

					sproutTransformService.saveNarrative(instanceId, narrative, "html");

					saveNarrativeModel(instanceId, jsonData);

					if (StringUtils.isEmpty(format) || format.equalsIgnoreCase("html")) {
						return narrative;
					} else {
						if (format.equalsIgnoreCase("rtf")) {
							String narrativeRtf = transformHtml2RTF(narrative);
							sproutTransformService.saveNarrative(instanceId, narrativeRtf, "rtf");
							return narrativeRtf;
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private String transformHtml2RTF(String narrative) {
		if (StringUtils.isFull(narrative)) {
			try {
				ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", "--from=html", "--to=rtf");
//				ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", "--from=html", "--to=markdown_github");
//				ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", "--from=html", "--to=plain");
				processBuilder.redirectErrorStream(true);
				Process process = processBuilder.start();

				OutputStream outStream = process.getOutputStream();
				PrintWriter printWriter = new PrintWriter(outStream);
				printWriter.print(narrative);
				printWriter.flush();
				printWriter.close();

				BufferedReader reader =
						new BufferedReader(new InputStreamReader(process.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ( (line = reader.readLine()) != null) {
					builder.append(line);
					builder.append(System.getProperty("line.separator"));
				}
				return builder.toString();
			} catch (IOException e) {
				if (e.getMessage().startsWith("Cannot run program")) {
					System.out.println("\n\n################# Pandoc (http://pandoc.org) does not appear to be installed on this server. Pandoc is required to convert output between formats. #################\n(Source: edu.harvard.mgh.lcs.sprout.forms.study.bean.SproutTransformServiceImpl.transformHtml2RTF)");
				} else {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private BooleanTO saveNarrativeFormat(String narrative, VNarrativeFormatEntity vNarrativeFormatEntity, NarrativeEntity narrativeEntity) {
		NarrativeFormatEntity narrativeFormatEntity = new NarrativeFormatEntity();
		narrativeFormatEntity.setNarrative(narrativeEntity);
		narrativeFormatEntity.setFormat(vNarrativeFormatEntity);
		narrativeFormatEntity.setData(narrative.trim());
		narrativeFormatEntity.setActivityDate(new Date());
		entityManager.persist(narrativeFormatEntity);
		return new BooleanTO(true);
	}

	private VNarrativeFormatEntity getVNarrativeFormatEntity(String format) {
		if (StringUtils.isFull(format)) {
			try {
				Query query = entityManager.createNamedQuery(VNarrativeFormatEntity.FIND_BY_CODE);
				query.setParameter("code", format);
				return (VNarrativeFormatEntity) query.getSingleResult();
			} catch (NoResultException e) {}
		}
		return null;
	}

	private NarrativeEntity getNarrative(String instanceId) {
		if (StringUtils.isFull(instanceId)) {
			try {
				Query query = entityManager.createNamedQuery(NarrativeEntity.BY_INSTANCE_ID);
				query.setParameter("instanceId", instanceId);
				return (NarrativeEntity) query.getSingleResult();
			} catch (NoResultException e) {}
		}
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public BooleanTO saveTemplate(String publicationKey, String instanceId, String template, String templateKey, boolean masterInd) {
		if (StringUtils.isFull(instanceId) && !instanceId.equalsIgnoreCase("null")) {
			TemplateCloneEntity templateCloneEntity = getTemplateCloneEntities(instanceId);
			if (templateCloneEntity == null && StringUtils.isFull(publicationKey)) {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) {
					TemplateCloneEntity templateCloneEntityNew = new TemplateCloneEntity();
					templateCloneEntityNew.setInstanceId(instanceId);
					templateCloneEntityNew.setKey(StringUtils.getGuid());
					templateCloneEntityNew.setTemplate(template);
					templateCloneEntityNew.setActive(true);
					templateCloneEntityNew.setMasterTemplate(templateMasterEntity);
					templateCloneEntityNew.setActivityDate(new Date());
					entityManager.persist(templateCloneEntityNew);
					return new BooleanTO(true);
				}
			} else {
				templateCloneEntity.setActive(false);
				templateCloneEntity.setActivityDate(new Date());
				templateCloneEntity = entityManager.merge(templateCloneEntity);

				TemplateCloneEntity templateCloneEntityNew = new TemplateCloneEntity();
				templateCloneEntityNew.setInstanceId(instanceId);
				templateCloneEntityNew.setKey(StringUtils.getGuid());
				templateCloneEntityNew.setTemplate(template);
				templateCloneEntityNew.setActive(true);
				templateCloneEntityNew.setMasterTemplate(templateCloneEntity.getMasterTemplate());
				templateCloneEntityNew.setActivityDate(new Date());
				entityManager.persist(templateCloneEntityNew);
				return new BooleanTO(true);
			}
		} else if (StringUtils.isFull(publicationKey, templateKey) && masterInd) {
			TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
			if (templateMasterEntity != null && templateMasterEntity.getKey().equals(templateKey)) {
				templateMasterEntity.setTemplate(template);
				templateMasterEntity.setActivityDate(new Date());
				entityManager.merge(templateMasterEntity);
				return new BooleanTO(true);
			} else {
				templateMasterEntity = new TemplateMasterEntity();
				templateMasterEntity.setPublicationKey(publicationKey);
				templateMasterEntity.setTemplate(template);
				templateMasterEntity.setKey(StringUtils.getGuid());
				templateMasterEntity.setActive(true);
				templateMasterEntity.setActivityDate(new Date());
				entityManager.persist(templateMasterEntity);
				return new BooleanTO(true, templateMasterEntity.getKey());
			}

		}
		return new BooleanTO(false);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getTemplate(String publicationKey, String instanceId) {
		if (StringUtils.isFull(instanceId) && !instanceId.equalsIgnoreCase("null")) {
			TemplateCloneEntity templateCloneEntity = getTemplateCloneEntities(instanceId);
			if (templateCloneEntity == null && StringUtils.isFull(publicationKey)) {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) return templateMasterEntity.getTemplate();
			} else {
				return templateCloneEntity.getTemplate();
			}
		} else if (StringUtils.isFull(publicationKey)) {
			TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
			if (templateMasterEntity != null) return templateMasterEntity.getTemplate();
		}
		return null;
	}

	private TemplateCloneEntity getTemplateCloneEntities(String instanceId) {
		TemplateCloneEntity templateCloneEntity = null;
		Query query = entityManager.createNamedQuery(TemplateCloneEntity.BY_INSTANCE_ID);
		query.setParameter("instanceId", instanceId);
		List<TemplateCloneEntity> templateCloneEntities = query.getResultList();
		if (templateCloneEntities != null && templateCloneEntities.size() > 0) {
			for (TemplateCloneEntity templateCloneEntityTmp : templateCloneEntities) {
				if (templateCloneEntity == null) {
					templateCloneEntity = templateCloneEntityTmp;
				} else {
					if (templateCloneEntityTmp.getId() > templateCloneEntity.getId()) {
						templateCloneEntity = templateCloneEntityTmp;
					}
				}
			}
		}
		return templateCloneEntity;
	}

	private TemplateMasterEntity getTemplateMasterEntity(String publicationKey) {
		try {
			Query query = entityManager.createNamedQuery(TemplateMasterEntity.BY_PUBLICATION_KEY);
			query.setParameter("publicationKey", publicationKey);
			return (TemplateMasterEntity) query.getSingleResult();
		} catch (NoResultException e) {}
		return null;
	}

}
