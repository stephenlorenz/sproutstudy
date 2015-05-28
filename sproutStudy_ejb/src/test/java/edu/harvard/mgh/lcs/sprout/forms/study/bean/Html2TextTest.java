package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.forms.transform.xalan.extension.XHTML2RTF;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class Html2TextTest {

	@Test
	public void test() {

		String narrative = "<h3>Clinical NOTE:</h3>\n" +
				"\n" +
				"<div>\n" +
				"Jared Moon, MD was the technician for this study. This study was of the type, Test A. The cardiologist was Amy Spooner, MD and the radiologist was Alan J Fischman, MD.\n" +
				"</div>\n" +
				"\n" +
				"  <ul>\n" +
				"    <li>Cat</li>\n" +
				"  </ul>\n" +
				"\n<div><table><tr><td>ID</td><td>Name</td></tr><tr><td>Bob</td><td>Dole</td></tr><tr><td>Fred</td><td>Rogers</td></tr></table></div>" +
				"<p>This is another note.</p>";

		if (StringUtils.isFull(narrative)) {

			Document document = Jsoup.parse(narrative);

			HtmlToPlainText formatter = new HtmlToPlainText();

			String plainText = formatter.getPlainText(document);
			System.out.println(plainText);


		}
	}

}
