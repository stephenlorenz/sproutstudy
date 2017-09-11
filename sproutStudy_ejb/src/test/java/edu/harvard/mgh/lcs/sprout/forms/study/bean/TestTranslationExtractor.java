package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTranslationExtractor {

	PrintWriter printWriter = null;

	Pattern regexPrefix = Pattern.compile("^[^A-z0-9!{{!(!'!\"]*");
	Pattern regexSuffix = Pattern.compile("[ \\t]+$");

	Map<String, String> codeMap = new HashMap<String, String>();

	@Test
	public void test() {
//		System.out.println("TestTranslationExtractor.test.template: " + template);

//		String html = "<html><head><title>First parse</title></head>"
//				+ "<body><p>Parsed HTML into a doc.</p></body></html>";


		template = wrapHandlebarHelpers(template);

//		String html = Parser.unescapeEntities(template, false);
//		Parser parser = new Parser()

		Document document = Jsoup.parse(template);



//		Document.OutputSettings settings = document.outputSettings();

//		settings.prettyPrint(false);
//		settings.escapeMode(Entities.EscapeMode.extended);
//		settings.charset("ASCII");
//
//		String modifiedFileHtmlStr = document.html();

//		document.outputSettings().prettyPrint(false).escapeMode(Entities.EscapeMode.extended).charset("ISO-8859-1");
		document.outputSettings().prettyPrint(false);

		Elements head = document.getElementsByTag("head");
		Elements body = document.getElementsByTag("body");

//		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,div.well,strong,span.hidden-print,span.visible-print-inline,span.skip");
//		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,a,div.well,strong,span.hidden-print,span.visible-print-inline");
		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,a,div.well,span.hidden-print,span.visible-print-inline");

		for (Element element : elements) {
//			if (StringUtils.isFull(element.ownText()) && element.ownText().length() > 1) {
//			System.out.println("***********************************************");


			processElement(element);
		}


		String newTemplate = head.toString() + extractSkipTags(body.toString());


//		System.out.println("TestTranslationExtractor.test.newTemplate: " + newTemplate);
		try {



			printTemplate(newTemplate);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void processElement(Element element) {
		for (Node node : element.childNodes()) {
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                String text = textNode.text();

                if (StringUtils.isFull(text) && text.indexOf("i18n") < 0) {


//					System.out.println("TestTranslationExtractor.processElement.text : " + (!text.startsWith("{{#i18n")) + " ==> " + text);

					boolean extractInd = true;

					String prefix = "";
					String suffix = "";
                    String content = text;

                    Matcher regexMatcherPrefix = regexPrefix.matcher(text);
                    if (regexMatcherPrefix.find()) {
                        prefix = regexMatcherPrefix.group();
						content = text.substring(prefix.length());
                    }

                    Matcher regexMatcherSuffix = regexSuffix.matcher(content);
                    if (regexMatcherSuffix.find()) {
                        suffix = regexMatcherSuffix.group();
						if ((content.length() - suffix.length()) > 0) {
							content = content.substring(0, content.length() - suffix.length());
						} else {
							extractInd = false;
						}
                    }

                    boolean hasText = false;

					Pattern regexText = Pattern.compile("[a-zA-Z]");
					Matcher regexMatcherText = regexText.matcher(content);
					if (regexMatcherText.find()) {
						hasText = true;
					}


//						if (StringUtils.isFull(prefix)) {
//							System.out.println("TestTranslationExtractor.test.prefix: " + prefix + "   suffix: " + suffix);
//						}


                    if (extractInd && hasText && StringUtils.isFull(content)) {
                        String guid = StringUtils.getGuid();

//                        if (codeMap.containsKey(content)) {
//                            guid = codeMap.get(content);
//                        } else {
//                            codeMap.put(content, guid);
                            printMessage(guid, content);
//                        }

						if (suffix == null) suffix = "";

                        textNode.text(String.format("%s{{#i18n \"%s\"}}%s{{/i18n}}%s", prefix, guid, content, suffix));
                    } else {
//						System.out.println("skipping: " + content);
					}
                }
            } else if (node instanceof Element) {
            	Element elementTmp = (Element) node;

				String href = element.attr("href");

				if (StringUtils.isFull(href) && !href.startsWith("/sproutlink/redirect") && href.indexOf("mailto") < 0) {
					System.out.println(href);
					String newURL = String.format("/sproutlink/redirect/pcoridsp?url=%s&token={{sprout.instanceId}}", encodeUrl(href));
					element.attr("href", newURL);
				}


				if (!elementTmp.tagName().equalsIgnoreCase("skip")) {
//					System.out.println("TestTranslationExtractor.processElement.elementTmp.tagName(): " + elementTmp.tagName());
					processElement((Element) node);
				}
            } else {

//                System.out.println("TestTranslationExtractor.test.node.getClass().getName(): " + node.getClass().getName());
            }
        }
	}

	private String encodeUrl(String href) {
		if (StringUtils.isFull(href)) {
			try {
				return URLEncoder.encode(href, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return href;
	}

	private String extractSkipTags(String input) {
		if (StringUtils.isFull(input)) {

//			System.out.println(input);


			String output = input.replaceAll("<skip>", "");
			output = output.replaceAll("</skip>", "");
			output = output.replaceAll("(?s)(\"&lt;?\")", "\"<\"");
			output = output.replaceAll("(?s)(\"&lt;=?\")", "\"<=\"");
			output = output.replaceAll("(?s)(\"&gt;?\")", "\">\"");
			output = output.replaceAll("(?s)(\"&gt;=?\")", "\">=\"");
			output = output.replaceAll("●", "&#x25cf;");
			output = output.replaceAll("®", "&#174;");
			output = output.replaceAll("™", "&#153;");
			output = output.replaceAll("ñ", "&ntilde;");
			output = output.replaceAll("’", "'");

//			output = output.replaceAll("\\*\\*([^;]+?);", "&$1;");
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
			message = message.replaceAll("\"", "\"\"");
			message = message.replaceAll("●", "&#x25cf;");
			message = message.replaceAll("®", "&#174;");
			message = message.replaceAll("™", "&#153;");
			message = message.replaceAll("ñ", "&ntilde;");
			message = message.replaceAll(" & ", " &amp; ");
			message = message.replaceAll("’", "'");

			String csvLine = String.format("%s, \"%s\", \"\"", key, message);
			printWriter.println(csvLine);
		}
	}

	private String wrapHandlebarHelpers(String input) {
		String output = input.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");
		output = output.replaceAll      ("(?s)(\\{\\{\\/.*?}})", "<skip>$1</skip>");
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
