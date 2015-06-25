package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.*;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import edu.harvard.mgh.lcs.sprout.forms.transform.handlebars.Helpers;
import org.junit.Test;

import java.io.IOException;
import java.security.SecureRandom;

public class HandlebarTest {

	 private SecureRandom random = new SecureRandom();

	@Test
	public void test() {
		try {

			Handlebars handlebars = new Handlebars();

//			handlebars.registerHelper("compare", , function (context) {
//				return 'Hello ' + context;
//			})


//			InputStream inputStream = this.getClass().getResourceAsStream("edu/harvard/mgh/lcs/sprout/forms/study/handlebars/helpers/compare.js");

			handlebars.registerHelpers(new Helpers());

//			handlebars.registerHelper("compare", new Helper<Blog>() {
//				public CharSequence apply(List<Blog> list, Options options) {
//
//
//
//					return null;
//				}
//			});


//			String templateText = "Hello {{name}}!<br/>{{#compare name \"=\" \"world\"}}The name is equal to world!{{/compare}}<br/>{{#compare \"1.2\" \"===\" \"1.2\"}}1 and 2!{{/compare}}";
			String templateText = "{{#each signature.purpose_grid}}\n" +
//					"Name: {{this}}\n" +
					"{{#each this}}\n" +
					"<li>{{this.name}}</li>\n" +
					"{{/each}}\n" +
					"{{/each}}\n" +
					"\n" +
					"{{#getNode signature.purpose_grid 'p_arrhythmia'}}\n" +
					"Name: {{this.name}}\n" +
					"{{/getNode}}" +
//					"{{#each signature.purpose_grid}}\n" +
//					"Name: {{this}}\n" +
//					"{{/each}}"
					""
					;


			String json = "{\n" +
					"  \"form\": {\n" +
					"    \"id\": \"123\",\n" +
					"    \"version\": \"12\"\n" +
					"  },\n" +
					"  \"signature\": {\n" +
					"    \"firstName\": \"Stephen\",\n" +
					"    \"lastName\": \"Lorenz\",\n" +
					"    \"middleName\": \"Charles\",\n" +
					"    \"ecg_twnl\": {\n" +
					"      \"name\": \"TWNL\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ecg_imi\": {\n" +
					"      \"name\": \"IMI\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"purpose_grid\": [\n" +
					"      {\"p_arrhythmia\": \n" +
					"      {\n" +
					"        \"key\": \"p_arrhythmia\",\n" +
					"        \"name\": \"Assess arryhthmia\",\n" +
					"        \"value\": \"2\"\n" +
					"      }},\n" +
					"      {\"p_angio\": \n" +
					"      {\n" +
					"        \"key\": \"p_angio\",\n" +
					"        \"name\": \"Angiographic correlation\",\n" +
					"        \"value\": \"1\"\n" +
					"      }}\n" +
					"    ]\n" +
					"  }\n" +
					"}";
			JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
//			Handlebars handlebars = new Handlebars();
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
			Template template = handlebars.compileInline(templateText);
			System.out.println(template.apply(context));




		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
