package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jknack.handlebars.*;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.HtmlToPdfException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ContentTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.TemplateTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.forms.transform.handlebars.Helpers;
import edu.harvard.mgh.lcs.sprout.study.model.transform.*;
import okhttp3.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ws.security.util.Base64;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Stateless
@Remote(SproutTransformService.class)
@TransactionManagement
public class SproutTransformServiceImpl implements SproutTransformService {

	@PersistenceContext(unitName = "sproutTransform_PU")
	private EntityManager entityManager;

	@EJB
	private SproutTransformService sproutTransformService;

	@EJB
	private SproutFormsService sproutFormsService;

    private static final Logger LOGGER = Logger.getLogger(SproutTransformServiceImpl.class.getName());

	JsonFactory factory = new JsonFactory();

	ObjectMapper objectMapper = new ObjectMapper(factory);

	OkHttpClient okHttpClient = new OkHttpClient.Builder()
			.connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();


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
					templateTO.setTranslations(templateMasterEntity.getTranslations());


//					System.out.println("SproutTransformServiceImpl.getTemplateTO.templateMasterEntity.getTranslations() 1: " + templateMasterEntity.getTranslations());

					return templateTO;
				}
			} else {
				templateTO.setKey(templateCloneEntity.getKey());
				templateTO.setTemplate(templateCloneEntity.getTemplate());
				templateTO.setTranslations(templateCloneEntity.getTranslations());
				return templateTO;
			}
		} else if (StringUtils.isFull(publicationKey)) {
			TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
			if (templateMasterEntity != null) {
				templateTO.setKey(templateMasterEntity.getKey());
				templateTO.setTemplate(templateMasterEntity.getTemplate());
				templateTO.setTranslations(templateMasterEntity.getTranslations());

//				System.out.println("SproutTransformServiceImpl.getTemplateTO.templateMasterEntity.getTranslations() 2: " + templateMasterEntity.getTranslations());

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
	public BooleanTO saveNarrativeWithLocaleAndType(String instanceId, String narrative, String format, String locale, String type) {
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

					return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity, locale, type);
				} else {
					if (narrativeEntity.getFormats() != null && narrativeEntity.getFormats().size() > 0) {
						NarrativeFormatEntity narrativeFormatEntity = null;
						for (NarrativeFormatEntity narrativeFormatEntityTmp : narrativeEntity.getFormats()) {
							if (narrativeFormatEntityTmp.getFormat().getCode().equalsIgnoreCase(format)
                                    && (narrativeFormatEntityTmp.getLocale() != null && narrativeFormatEntityTmp.getLocale().equalsIgnoreCase(locale) || (narrativeFormatEntityTmp.getLocale() == null && locale == null))
                                    && (narrativeFormatEntityTmp.getType() != null && narrativeFormatEntityTmp.getType().equalsIgnoreCase(type) || (narrativeFormatEntityTmp.getType() == null && type == null))
                                    ) {
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
							return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity, locale, type);
						}
					} else {
						return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity, locale, type);
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
		return getNarrativeByInstanceId(instanceId, format, null, null);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrativeByInstanceId(String instanceId, String format, String locale, String type) {
		if (StringUtils.isEmpty(format)) format = "HTML";
		NarrativeEntity narrativeEntity = getNarrative(instanceId);
		if (narrativeEntity != null && narrativeEntity.getFormats() != null) {
			Set<NarrativeFormatEntity> narrativeFormatEntitySet = narrativeEntity.getFormats();
			if (narrativeFormatEntitySet != null && narrativeFormatEntitySet.size() > 0) {
				for (NarrativeFormatEntity narrativeFormatEntity : narrativeFormatEntitySet) {
					if (narrativeFormatEntity.getFormat().getCode().equalsIgnoreCase(format)) {
						boolean validMatch = true;
						if (validMatch && StringUtils.isFull(locale)) {
							if (StringUtils.isEmpty(narrativeFormatEntity.getLocale()) || !narrativeFormatEntity.getLocale().equalsIgnoreCase(locale)) {
								validMatch = false;
							}
						}
						if (validMatch && StringUtils.isFull(type)) {
							if (StringUtils.isEmpty(narrativeFormatEntity.getType()) || !narrativeFormatEntity.getType().equalsIgnoreCase(type)) {
								validMatch = false;
							}
						}

						if (validMatch) return narrativeFormatEntity.getData();
					}
				}
			} else {
				if (narrativeEntity.getModel() != null && narrativeEntity.getModel().size() > 0) {
					NarrativeModelEntity narrativeModelEntity = narrativeEntity.getModel().get(0);
					if (narrativeModelEntity != null) {
						String publicationKey = sproutFormsService.getPublicationKeyFromInstanceId(instanceId);
						if (StringUtils.isFull(publicationKey)) {
							try {
								String narrative = getNarrative(publicationKey, instanceId, narrativeModelEntity.getModel(), "PDF", locale, type);
								if (StringUtils.isFull(narrative)) {
									saveNarrative(instanceId, narrative, "HTML");
									return narrative;
								}
							} catch (Exception e) {
								return e.getMessage();
							}
						}
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
        return getNarrative(publicationKey, instanceId, jsonData, format, null, null);
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getNarrative(String publicationKey, String instanceId, String jsonData, String format, String locale, String type) {

		System.out.println("***************************************");
		System.out.println("SproutTransformServiceImpl.getNarrative");
        System.out.println("publicationKey = [" + publicationKey + "], instanceId = [" + instanceId + "], jsonData = [too large to display], format = [" + format + "], locale = [" + locale + "], type = [" + type + "]");

		if (StringUtils.isFull(publicationKey, instanceId, jsonData)) {
//			NarrativeEntity narrativeEntity = getNarrative(instanceId);
//			if (narrativeEntity != null && narrativeEntity.getFormats() != null) {
//				for (NarrativeFormatEntity narrativeFormatEntity : narrativeEntity.getFormats()) {
//					if (narrativeFormatEntity.getFormat().getCode().equalsIgnoreCase(format)) {
//
//						System.out.println("SproutTransformServiceImpl.getNarrative.1");
//
//						return narrativeFormatEntity.getData();
//					}
//				}
//			}

//			System.out.println("SproutTransformServiceImpl.getNarrative.2");

            String templateText = null;
            String translationsText = null;

			TemplateCloneEntity templateCloneEntity = null;

			if (StringUtils.isFull(instanceId) && !instanceId.equalsIgnoreCase("null")) {
				templateCloneEntity = getTemplateCloneEntities(instanceId);
			}

			if (templateCloneEntity != null && StringUtils.isFull(templateCloneEntity.getTemplate())) {
				templateText = templateCloneEntity.getTemplate();
				translationsText = templateCloneEntity.getTranslations();
			} else {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) {
					templateText = templateMasterEntity.getTemplate();

//					System.out.println("SproutTransformServiceImpl.getNarrative.templateMasterEntity.getTranslations() 0: " + templateMasterEntity.getTranslations());

					translationsText = templateMasterEntity.getTranslations();
				}
			}

//			System.out.println("SproutTransformServiceImpl.getNarrative.3");

            if (StringUtils.isFull(templateText)) {

//				System.out.println("978: SproutTransformServiceImpl.getNarrative.4");
//
//				System.out.println("978: templateText = " + templateText);
//				System.out.println("978: jsonData = " + jsonData);

				try {
					Handlebars handlebars = new Handlebars();

					handlebars.registerHelpers(new Helpers());

					Template template = handlebars.compileInline(templateText);

					JsonNode jsonNode = new ObjectMapper().readValue(jsonData, JsonNode.class);

//					System.out.println("978: SproutTransformServiceImpl.getNarrative.translationsText: " + translationsText);

					if (StringUtils.isFull(translationsText)) {
						try {

							ObjectNode newTranslationNodes = new ObjectMapper().createObjectNode();

							JsonNode translationNodes = new ObjectMapper().readValue(translationsText, JsonNode.class);
							if (translationNodes != null) {
								Iterator<JsonNode> translations = translationNodes.elements();

//								System.out.println("978: before");

								if (translations != null) {
									while (translations.hasNext()) {
//										System.out.println("978: 1");

										JsonNode translation = translations.next();

//										System.out.println("978: 2");
//										System.out.println("TestTranslationsTransform.test.translation: " + translation);

										if (translation != null && translation.get("key") != null) {
//											System.out.println("978: 3");

											String key = translation.get("key").textValue();

//											System.out.println("978: 4");

//											System.out.println("TestTranslationsTransform.test.key: " + key);

//											System.out.println("978: 5");

											JsonNode localesNode = translation.get("locales");

//											System.out.println("978: 6");

											Iterator<JsonNode> locales = localesNode.elements();

//											System.out.println("978: 7");

											ObjectNode localeNodes = new ObjectMapper().createObjectNode();

//											System.out.println("978: 8");

											if (locales != null) {

//												System.out.println("978: 9");

												while (locales.hasNext()) {

//													System.out.println("978: 10");

													JsonNode localeTmp = locales.next();

//													System.out.println("978: 11");

													if (localeTmp != null && localeTmp.get("locale") != null && localeTmp.get("locale").get("key") != null && localeTmp.get("message") != null) {

//														System.out.println("978: 12");

														String localeKey = localeTmp.get("locale").get("key").textValue();
														String localeMessage = localeTmp.get("message").textValue();

												System.out.println("363: " + localeKey + ": " + localeMessage);

//														System.out.println("978: 13");

														localeNodes.put(localeKey, localeMessage);
													}
												}

//												System.out.println("978: 14");

//												System.out.println("978: SproutTransformServiceImpl.getNarrative.localeNodes: " + key + " :: " + localeNodes);

												newTranslationNodes.put(key, localeNodes);

//												System.out.println("978: 15");

											}

//											System.out.println("978: 16");

										}

//										System.out.println("978: 17");

									}

//									System.out.println("978: 18");

								}

//								System.out.println("978: after");

//								System.out.println("978: TestTranslationsTransform.test.newTranslationNodes: " + newTranslationNodes);
								((ObjectNode) jsonNode).put("translations", newTranslationNodes);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					System.out.println("SproutTransformServiceImpl.getNarrative.jsonNode: " + jsonNode);


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

//					System.out.println("SproutTransformServiceImpl.getNarrative.5.narrative = " + narrative);
//					System.out.println("SproutTransformServiceImpl.getNarrative.5: " + format);

                    sproutTransformService.saveNarrativeWithLocaleAndType(instanceId, narrative, "html", locale, type);

					saveNarrativeModel(instanceId, jsonData);

					if (StringUtils.isEmpty(format) || format.equalsIgnoreCase("html")) {
//						System.out.println("SproutTransformServiceImpl.getNarrative.6");
                        return narrative;
					} else {
						if (format.equalsIgnoreCase("rtf")) {
//							System.out.println("SproutTransformServiceImpl.getNarrative.7");
                            String narrativeRtf = transformHtml2RTF(narrative);
							sproutTransformService.saveNarrativeWithLocaleAndType(instanceId, narrativeRtf, "rtf", locale, type);
							return narrativeRtf;
						} else if (format.equalsIgnoreCase("md")) {
//							System.out.println("SproutTransformServiceImpl.getNarrative.8");
                            String narrativeMarkdown = transformHtml2Markdown(narrative);
							sproutTransformService.saveNarrativeWithLocaleAndType(instanceId, narrativeMarkdown, "md", locale, type);
                            LOGGER.fine("==> narrativeMarkdown = " + narrativeMarkdown);
							return narrativeMarkdown;
						} else if (format.equalsIgnoreCase("pdf")) {
//							System.out.println("SproutTransformServiceImpl.getNarrative.9");
                            byte[] narrativePDFArray = transformHtml2PDF(narrative);
							String narrativePDF = Base64.encode(narrativePDFArray);
							sproutTransformService.saveNarrativeWithLocaleAndType(instanceId, narrativePDF, "pdf", locale, type);
                            LOGGER.fine("==> narrativePDF = " + narrativePDF);
							return narrativePDF;
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
		return transform(narrative, "html", "rtf");
	}

	public String transformHtml2Markdown(String narrative) {
        return transformHtml2Markdown(narrative, "\\.br\\");
    }

	public String transformHtml2Markdown(String narrative, String lineSeparator) {
        if (StringUtils.isFull(narrative)) {
			narrative = narrative.replaceAll("<span[^>]*>", "").replaceAll("</span[^>]*>", "").replaceAll("&nbsp;", " ");
        }
//        return transform(narrative, "html", "markdown_github", lineSeparator);
        return transform(narrative, "html", "plain", lineSeparator);
    }

	private String transform(String narrative, String from, String to) {
        return transform(narrative, from, to, null);
    }

	private String transform(String narrative, String from, String to, String lineSeparator) {
        System.out.println("SproutTransformServiceImpl.transform");
		System.out.println("transform: " + "narrative = [" + narrative + "], from = [" + from + "], to = [" + to + "], lineSeparator = [" + lineSeparator + "]");

		if (StringUtils.isFull(narrative)) {
			try {
				System.out.println("Pandoc command: pandoc -s " + String.format("--from=%s", StringUtils.isFull(from) ? from : "html") + " " + String.format("--to=%s", StringUtils.isFull(to) ? to : "plain"));

				ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", String.format("--from=%s", StringUtils.isFull(from) ? from : "html"), String.format("--to=%s", StringUtils.isFull(to) ? to : "plain"));
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
                    if (StringUtils.isFull(lineSeparator)) {
                        builder.append(lineSeparator);
                    } else {
                        builder.append(System.getProperty("line.separator"));
                    }
				}
				System.out.println("new narrative: " + builder.toString());
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

	@Override
	public byte[] transformHtml2PDF(String narrative) {
        System.out.println("SproutTransformServiceImpl.transformHtml2PDF");
//		System.out.println("transform: " + "narrative = [" + narrative + "]");

		if (StringUtils.isFull(narrative)) {
			byte[] pdf = null;
			try {
				pdf = callSproutHtmlToPdf(narrative);
			} catch (HtmlToPdfException e) {
				pdf = e.getMessage().getBytes();
			}
			if (pdf != null) return pdf;
		}
		return null;
	}

	@Override
	public String transformHtml2PDFAsString(String narrative) throws HtmlToPdfException {

//		narrative = StringEscapeUtils.unescapeHtml(narrative);

		if (StringUtils.isFull(narrative)) {
			byte[] pdf = callSproutHtmlToPdf(narrative);
			if (pdf != null) {
				return Base64.encode(pdf).toString();
			}
		}
		return null;
	}

	@Override
	public ContentTO transformHtml2PDFAsContentTO(String narrative) throws HtmlToPdfException {

		if (StringUtils.isFull(narrative)) {
			try {
				String data = transformHtml2PDFAsString(narrative);

				ContentTO contentTO = new ContentTO();
				contentTO.setSuccess(true);
				contentTO.setData(data);
				return contentTO;
			}
			catch (Exception e) {
				e.printStackTrace();
				ContentTO contentTO = new ContentTO();
				contentTO.setSuccess(false);
				contentTO.setMessage(e.getMessage());
				return contentTO;
			} finally {
			}
		}
		return null;
	}

	private byte[] callSproutHtmlToPdf(String html) throws HtmlToPdfException {
		try {

			System.out.println("SproutTransformServiceImpl.callSproutHtmlToPdf.html: " + html);


			MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
			RequestBody body = RequestBody.create(TEXT, html);
			Request request = new Request.Builder()
					.url(System.getProperty("edu.harvard.mgh.lcs.sprout.util.sprouthtmltopdf.url", "http://localhost:9494/convert"))
					.post(body)
					.build();
			Response response = okHttpClient.newCall(request).execute();


			String exception = response.header("Exception");
			String exceptionLine = response.header("ExceptionLine");

			if (StringUtils.isFull(exception)) {
				if (StringUtils.isFull(exceptionLine)) {
					throw new HtmlToPdfException(String.format("ERROR: %s<div class='well well-sm'><textarea readonly='readonly' style='width: 100%%; height: 100px;'>%s</textarea></div>", exception, exceptionLine));
				} else {
					throw new HtmlToPdfException(exception);
				}

			} else {
				return response.body().bytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String wrapHtml(String html) {
		if (StringUtils.isFull(html)) {

//			return String.format("<div style=\"font-size:12.0pt;\">%s</div>", html);
			return String.format("<div style=\"font-size:12.0pt; font-family: Arial Unicode MS\">%s</div>", html);
		}
		return null;
	}

	private BooleanTO saveNarrativeFormat(String narrative, VNarrativeFormatEntity vNarrativeFormatEntity, NarrativeEntity narrativeEntity) {
		return saveNarrativeFormat(narrative, vNarrativeFormatEntity, narrativeEntity, null, null);
	}

	private BooleanTO saveNarrativeFormat(String narrative, VNarrativeFormatEntity vNarrativeFormatEntity, NarrativeEntity narrativeEntity, String locale, String type) {
		NarrativeFormatEntity narrativeFormatEntity = new NarrativeFormatEntity();
		narrativeFormatEntity.setNarrative(narrativeEntity);
		narrativeFormatEntity.setFormat(vNarrativeFormatEntity);
		narrativeFormatEntity.setData(narrative.trim());
		narrativeFormatEntity.setLocale(locale);
		narrativeFormatEntity.setType(type);
		narrativeFormatEntity.setActivityDate(new Date());
		entityManager.persist(narrativeFormatEntity);
		return new BooleanTO(true);
	}

	private VNarrativeFormatEntity getVNarrativeFormatEntity(String format) {
		if (StringUtils.isFull(format)) {
			try {
				System.out.println("SproutTransformServiceImpl.getVNarrativeFormatEntity");
				System.out.println("format = [" + format + "]");


				Query query = entityManager.createNamedQuery(VNarrativeFormatEntity.FIND_BY_CODE);
				query.setParameter("code", format);
				VNarrativeFormatEntity vNarrativeFormatEntity = (VNarrativeFormatEntity) query.getSingleResult();

				System.out.println("vNarrativeFormatEntity.getDescription() = " + vNarrativeFormatEntity.getDescription());

				return vNarrativeFormatEntity;

			} catch (NoResultException e) {

			} catch (Exception e) {
				e.printStackTrace();
			}

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
	public BooleanTO saveTemplate(String publicationKey, String instanceId, String template, String templateKey, boolean masterInd, String translations) {
		if (StringUtils.isFull(instanceId) && !instanceId.equalsIgnoreCase("null")) {
			TemplateCloneEntity templateCloneEntity = getTemplateCloneEntities(instanceId);
			if (templateCloneEntity == null && StringUtils.isFull(publicationKey)) {
				TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);
				if (templateMasterEntity != null) {
					TemplateCloneEntity templateCloneEntityNew = new TemplateCloneEntity();
					templateCloneEntityNew.setInstanceId(instanceId);
					templateCloneEntityNew.setKey(StringUtils.getGuid());
					templateCloneEntityNew.setTemplate(template);
					templateCloneEntityNew.setTranslations(translations);
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
				templateCloneEntityNew.setTranslations(translations);
				templateCloneEntityNew.setActive(true);
				templateCloneEntityNew.setMasterTemplate(templateCloneEntity.getMasterTemplate());
				templateCloneEntityNew.setActivityDate(new Date());
				entityManager.persist(templateCloneEntityNew);
				return new BooleanTO(true);
			}
		} else if (StringUtils.isFull(publicationKey, templateKey) && masterInd) {
			TemplateMasterEntity templateMasterEntity = getTemplateMasterEntity(publicationKey);

			if (templateMasterEntity != null) System.out.println("templateMasterEntity.getKey() = " + templateMasterEntity.getKey() + " vs " + templateKey);

//			if (templateMasterEntity != null && templateMasterEntity.getKey().equals(templateKey)) {
			if (templateMasterEntity != null) {
				templateMasterEntity.setTemplate(template);
				templateMasterEntity.setTranslations(translations);
				templateMasterEntity.setActivityDate(new Date());
				entityManager.merge(templateMasterEntity);
				return new BooleanTO(true);
			} else {
				templateMasterEntity = new TemplateMasterEntity();
				templateMasterEntity.setPublicationKey(publicationKey);
				templateMasterEntity.setTemplate(template);
				templateMasterEntity.setTranslations(translations);
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
