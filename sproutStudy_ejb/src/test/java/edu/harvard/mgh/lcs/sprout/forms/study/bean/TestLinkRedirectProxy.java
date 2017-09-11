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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLinkRedirectProxy {

	PrintWriter printWriter = null;

	Pattern regexPrefix = Pattern.compile("^[^A-z0-9!{{!(!'!\"]*");
	Pattern regexSuffix = Pattern.compile("[ \\t]+$");

	Map<String, String> codeMap = new HashMap<String, String>();

	@Test
	public void test() {
//		System.out.println("TestTranslationExtractor.test.template: " + template);

//		String html = "<html><head><title>First parse</title></head>"
//				+ "<body><p>Parsed HTML into a doc.</p></body></html>";


		Document document = Jsoup.parse(template);

		Elements head = document.getElementsByTag("head");
		Elements body = document.getElementsByTag("body");

//		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,div.well,strong,span.hidden-print,span.visible-print-inline,span.skip");
//		Elements elements = document.body().select("h1,h2,h3,h4,h5,p,a,div.well,strong,span.hidden-print,span.visible-print-inline");
		Elements elements = document.body().select("a");

		for (Element element : elements) {
//			if (StringUtils.isFull(element.ownText()) && element.ownText().length() > 1) {
//				System.out.println("***********************************************");
//			}

			processElement(element);
		}


		String newTemplate = head.toString() + body.toString();


//		System.out.println("TestTranslationExtractor.test.newTemplate: " + newTemplate);
		try {



			printTemplate(newTemplate);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void processElement(Element element) {


//		System.out.println("TestLinkRedirectProxy.processElement.element.getClass().getName(): " + element.getClass().getName());

		if (element instanceof Element) {
			String href = element.attr("href");
			System.out.println(href);

			if (StringUtils.isFull(href) && !href.startsWith("/sproutlink/redirect") && href.indexOf("mailto") < 0) {
				String newURL = String.format("/sproutlink/redirect/pcoridsp?url=%s&token={{sprout.instanceId}}", encodeUrl(href));
				element.attr("href", newURL);
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


	private void printTemplate(String template) throws IOException {
		PrintWriter printWriterBody = new PrintWriter(new FileWriter("/Users/slorenz/Desktop/templateLinks.html"));

		if (printWriterBody != null) {
			printWriterBody.write(template);
		}
		printWriterBody.close();
	}

	@Before
	public void init() throws IOException {

		InputStream is = new FileInputStream("/Users/slorenz/Desktop/template.html");
//		InputStream is = new FileInputStream("/Users/slorenz/workspace/sproutStudy/sproutStudy/sproutStudy_ejb/src/test/resources/sample_template.html");
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while(line != null){
			sb.append(line).append("\n"); line = buf.readLine();
		}
		template = sb.toString();
	}


	private String template = "";

}
