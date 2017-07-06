package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Iterator;

public class TestTranslationsTransform {


	@Test
	public void test() {



		if (StringUtils.isFull(translationsText)) {
			try {

				ObjectNode newTranslationNodes = new ObjectMapper().createObjectNode();

				JsonNode translationNodes = new ObjectMapper().readValue(translationsText, JsonNode.class);
				if (translationNodes != null) {
					Iterator<JsonNode> translations = translationNodes.elements();

					if (translations != null) {
						while (translations.hasNext()) {
							JsonNode translation = translations.next();

							System.out.println("TestTranslationsTransform.test.translation: " + translation);


							String key = translation.get("key").textValue();

							System.out.println("TestTranslationsTransform.test.key: " + key);

							JsonNode localesNode = translation.get("locales");

							Iterator<JsonNode> locales = localesNode.elements();

//							ObjectNode translationNode = new ObjectMapper().createObjectNode();
							ObjectNode localeNodes = new ObjectMapper().createObjectNode();

							if (locales != null) {
								while (locales.hasNext()) {
									JsonNode locale = locales.next();

									String localeKey = locale.get("locale").get("key").textValue();
									String localeMessage = locale.get("message").textValue();

									System.out.println(localeKey + ": " + localeMessage);

//								((ObjectNode) jsonNode).put("translations", translationNodes);

									localeNodes.put(localeKey, localeMessage);
								}
								newTranslationNodes.put(key, localeNodes);
							}

						}
					}

					System.out.println("TestTranslationsTransform.test.newTranslationNodes: " + newTranslationNodes);
//					((ObjectNode) jsonNode).put("translations", translationNodes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	String translationsText = "[{\"key\":\"test\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"\\\"This is a test.\\\"\",\"$$hashKey\":\"011\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"Esta es una prueba\",\"$$hashKey\":\"013\"}],\"$$hashKey\":\"00N\"},{\"key\":\"cow\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"c\\\"ow\",\"$$hashKey\":\"015\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"vaca\",\"$$hashKey\":\"017\"}],\"$$hashKey\":\"00P\"},{\"key\":\"dog\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"dog\",\"$$hashKey\":\"019\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"perro\",\"$$hashKey\":\"01B\"}],\"$$hashKey\":\"00R\"},{\"key\":\"cat\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"cat\",\"$$hashKey\":\"01D\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"gato\",\"$$hashKey\":\"01F\"}],\"$$hashKey\":\"00T\"},{\"key\":\"tilde\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"tilde\",\"$$hashKey\":\"01H\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"ñî\",\"$$hashKey\":\"01J\"}],\"$$hashKey\":\"00V\"},{\"key\":\"wall\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"wall\",\"$$hashKey\":\"01L\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"pared\",\"$$hashKey\":\"01N\"}],\"$$hashKey\":\"00X\"},{\"key\":\"lamp\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"lamp\",\"$$hashKey\":\"01P\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"lampara\",\"$$hashKey\":\"01R\"}],\"$$hashKey\":\"00Z\"},{\"key\":\"train\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"train\",\"$$hashKey\":\"01A\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"tren\",\"$$hashKey\":\"01C\"}],\"$$hashKey\":\"018\"},{\"key\":\"car\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"cat\",\"$$hashKey\":\"01G\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"coche\",\"$$hashKey\":\"01I\"}],\"$$hashKey\":\"01E\"},{\"key\":\"new\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"new\",\"$$hashKey\":\"02P\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"noticias\",\"$$hashKey\":\"02R\"}],\"$$hashKey\":\"02N\"},{\"key\":\"hello\",\"locales\":[{\"locale\":{\"key\":\"en\",\"name\":\"English\"},\"message\":\"Hello {{pcori_dsp_interventi.first_name}},\",\"$$hashKey\":\"0DZ\"},{\"locale\":{\"key\":\"es\",\"name\":\"Spanish\"},\"message\":\"Hola {{pcori_dsp_interventi.first_name}},\",\"$$hashKey\":\"0E1\"}],\"$$hashKey\":\"0DX\"}]";

}