package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.*;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import edu.harvard.mgh.lcs.sprout.forms.transform.handlebars.Helpers;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class HandlebarTestWeirdConditional {

	private SecureRandom random = new SecureRandom();

	private String template = null;
	private String model = null;



	@Test
	public void testWeirdConditionalError() {

		try {

			Handlebars handlebars = new Handlebars();

			handlebars.registerHelpers(new Helpers());

			String json = model;
			JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
//			Handlebars handlebars = new Handlebars();
			handlebars.registerHelper("json", Jackson2Helper.INSTANCE);
//			handlebars.registerHelpers("compare", inputStream);

			Context context = Context
					.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE,
							JavaBeanValueResolver.INSTANCE,
							FieldValueResolver.INSTANCE,
							MapValueResolver.INSTANCE,
							MethodValueResolver.INSTANCE
					)
					.build();
			Template template2 = handlebars.compileInline(template.toString());
			String output = template2.apply(context);


			System.out.println(output);


			FileUtils.writeStringToFile(new File("/Users/slorenz/Desktop/output.html"), output, Charset.defaultCharset());


		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void init() throws IOException {

		InputStream is = new FileInputStream("/Users/slorenz/workspace/sproutStudy/sproutStudy/sproutStudy_ejb/src/test/resources/sample_template.html");
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while(line != null){
			sb.append(line).append("\n"); line = buf.readLine();
		}
		template = sb.toString();

		is = new FileInputStream("/Users/slorenz/workspace/sproutStudy/sproutStudy/sproutStudy_ejb/src/test/resources/sample_model.data");
		buf = new BufferedReader(new InputStreamReader(is));
		line = buf.readLine();
		sb = new StringBuilder();
		while(line != null){
			sb.append(line).append("\n"); line = buf.readLine();
		}
		model = sb.toString();
	}

	@After
	public void end() throws IOException {
	}

}
