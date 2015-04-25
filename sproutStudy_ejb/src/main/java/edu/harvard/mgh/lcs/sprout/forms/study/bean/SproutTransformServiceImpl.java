package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.PatientService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.PatientVerificationSearchSelector;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.NameValue;
import edu.harvard.mgh.lcs.sprout.forms.study.to.PatientDetailTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.TemplateTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.transform.*;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

@Stateless
@Remote(SproutTransformService.class)
@TransactionManagement
public class SproutTransformServiceImpl implements SproutTransformService {

	@PersistenceContext(unitName = "sproutTransform_PU")
	private EntityManager entityManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TemplateTO getTemplateTO(String publicationKey, String instanceId) {
		TemplateTO templateTO = new TemplateTO();
		if (StringUtils.isFull(instanceId)) {
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
		if (StringUtils.isFull(instanceId)) {
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
		if (StringUtils.isFull(instanceId)) {
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
