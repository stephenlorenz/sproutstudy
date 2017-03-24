package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ws.security.util.Base64;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestConvertHtmlHex2Unicode {

	@Test
	public void test() {

		try {
			System.out.println("TestConvertHtmlHex2Unicode.test.input: " + input);

			String output = StringEscapeUtils.unescapeHtml(input);

			System.out.println("TestConvertHtmlHex2Unicode.test.output: " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	String input = "\\u2713 Cats &#x2713;\n" +
			"<h1>Sample Narrative</h1>\n" +
			"<h2>\n" +
			"Hello\n" +
			" Peter!\n" +
			"</h2>";
}
