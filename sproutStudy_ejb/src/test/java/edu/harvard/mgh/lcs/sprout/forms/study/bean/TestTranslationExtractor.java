package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTranslationExtractor {

	PrintWriter printWriter = null;

	Pattern regex = Pattern.compile("^[^A-z0-9!{{!(!'!\"]*");

	Map<String, String> codeMap = new HashMap<String, String>();

	@Test
	public void test() {
//		System.out.println("TestTranslationExtractor.test.template: " + template);

//		String html = "<html><head><title>First parse</title></head>"
//				+ "<body><p>Parsed HTML into a doc.</p></body></html>";


		template = wrapHandlebarHelpers(template);

		Document document = Jsoup.parse(template);

		Elements head = document.getElementsByTag("head");
		Elements body = document.getElementsByTag("body");

//		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,div.well,strong,span.hidden-print,span.visible-print-inline,span.skip");
		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,a,div.well,strong,span.hidden-print,span.visible-print-inline");

		for (Element element : elements) {
//			if (StringUtils.isFull(element.ownText()) && element.ownText().length() > 1) {
//			System.out.println("***********************************************");

			for (Node node : element.childNodes()) {
				if (node instanceof TextNode) {
					TextNode textNode = (TextNode) node;
					String text = textNode.text();

					if (StringUtils.isFull(text)) {

						String prefix = null;
						String suffix = null;

						Matcher regexMatcher = regex.matcher(text);
						if (regexMatcher.find()) {
							prefix = regexMatcher.group();
							suffix = text.substring(prefix.length());
						}

						if (StringUtils.isFull(prefix)) {
							System.out.println("TestTranslationExtractor.test.prefix: " + prefix + "   suffix: " + suffix);
						}


						if (StringUtils.isFull(suffix)) {
							String guid = StringUtils.getGuid();

							if (codeMap.containsKey(suffix)) {
								guid = codeMap.get(suffix);
							} else {
								codeMap.put(suffix, guid);
								printMessage(guid, suffix);
							}

							textNode.text(String.format("%s{{#i18n \"%s\"}}%s{{/i18n}}", prefix, guid, suffix));
						}
					}
				} else {
//					System.out.println("TestTranslationExtractor.test.node.getClass().getName(): " + node.getClass().getName());
				}
			}
		}


		String newTemplate = head.toString() + extractSkipTags(body.toString());


//		System.out.println("TestTranslationExtractor.test.newTemplate: " + newTemplate);
		try {



			printTemplate(newTemplate);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String extractSkipTags(String input) {
		if (StringUtils.isFull(input)) {
			String output = input.replaceAll("<skip>", "");
			output = output.replaceAll("</skip>", "");
			return output;
		}
		return null;
	}

	private void printTemplate(String template) throws IOException {
		PrintWriter printWriterBody = new PrintWriter(new FileWriter("/Users/slorenz/Desktop/template.html"));

		if (printWriterBody != null) {
			printWriterBody.write(template);
		}
		printWriterBody.close();
	}

	private void printMessage(String key, String message) {

		if (printWriter != null) {
			String csvLine = String.format("%s, \"%s\", \"\"", key, message.replaceAll("\"", "\"\""));
			printWriter.println(csvLine);
		}
	}

	private String wrapHandlebarHelpers(String input) {
		String output = input.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");
		output = output.replaceAll("(?s)(\\{\\{\\/.*?}})", "<skip>$1</skip>");
		output = output.replaceAll("\\{\\{else}}", "<skip>{{else}}</skip>");
		return output;
	}


	@Before
	public void init() throws IOException {

		printWriter = new PrintWriter(new FileWriter("/Users/slorenz/Desktop/translations.csv"));

		printWriter.println("Key, English, Español");
		InputStream is = new FileInputStream("/Users/slorenz/workspace/sproutStudy/sproutStudy/sproutStudy_ejb/src/test/resources/sample_template.html");
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while(line != null){
			sb.append(line).append("\n"); line = buf.readLine();
		}
		template = sb.toString();
	}

	@After
	public void end() throws IOException {
		printWriter.close();
	}

	private String template = "";

}
