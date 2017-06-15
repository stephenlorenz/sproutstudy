package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.lowagie.text.DocumentException;
//import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.XMLResource;
import org.w3c.dom.Document;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class TestITextHtml2PdfConverter {

	@Test
	public void test() {

		try {
//			createPdf("/Users/slorenz/Desktop/HTMLtoPDF.pdf");
//			String pdfAsString = transformHtml2PDFAsString(html);
//			createPdf2("/Users/slorenz/Desktop/HTMLtoPDF2.pdf");
//			createBlankPdf("/Users/slorenz/Desktop/blank.pdf");

//			System.out.println("TestITextHtml2PdfConverter.test.pdfAsString: " + pdfAsString);

			createPDF();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void createPDF()
			throws IOException, DocumentException {
		OutputStream os = null;
		try {
			os = new FileOutputStream("/Users/slorenz/Desktop/blank.pdf");

//			String url = "/Users/slorenz/Desktop/source.html";

			ITextRenderer renderer = new ITextRenderer();

//			String htmlNew = getStyleDecl("Courier") + html;

			html = wrapHtml(html);
			System.out.println("TestITextHtml2PdfConverter.createPDF.htmlNew: " + html);




			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(html.getBytes("UTF-8")));
//			renderer.setDocument(doc,null);
//			renderer.layout();
//			renderer.createPDF(os);
//			os.close();
			



//			Document doc = XMLResource.load(new ByteArrayInputStream(html.getBytes("UTF-8"))).getDocument();
			renderer.setDocument(doc, null);

			ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
			callback.setSharedContext(renderer.getSharedContext());
			renderer.getSharedContext ().setUserAgentCallback(callback);
			renderer.getFontResolver().addFont("ufonts.com_arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			SharedContext scontext=renderer.getSharedContext();
			scontext.setDotsPerPixel(12);

			renderer.layout();
			renderer.createPDF(os);

			os.close();
			os = null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	private String wrapHtml(String html) {
		if (html != null && html.trim().length() > 0) {

			return String.format("<!DOCTYPE html><html><head><title>Title of the document</title>" +
					"<style type=\"text/css\" media=\"print\">" +
					"    .well {" +
					"        display: none;\n" +
					"    }\n" +
					"    p {\n" +
					"        color: red;\n" +
					"    }\n" +
					"    .red {\n" +
					"        color: red;\n" +
					"    }\n" +
					"    .img-checkbox {\n" +
					"        height: 20px;\n" +
					"        v-align: top; \n" +
					"    }\n" +
					"-->\n" +
					"</style>\" +\n" +
					"</head><body><div style=\"font-size:12.0pt; font-family: 'Arial Unicode MS'\">%s</div></body></html>", html);
		}
		return null;
	}

	private static class ResourceLoaderUserAgent extends ITextUserAgent
	{
		public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
			super(outputDevice);
		}

		protected InputStream resolveAndOpenStream(String uri) {
			System.out.println("ResourceLoaderUserAgent.resolveAndOpenStream.uri: " + uri);
			InputStream is = super.resolveAndOpenStream(uri);
			System.out.println("IN resolveAndOpenStream() " + uri);
			return is;
//			return new ByteArrayInputStream(html.getBytes("UTF-8"));
		}
	}

	private String getStyleDecl(String fontFamily) {
		String css = "* {font-size: 8pt; font-family: \"" + fontFamily + "\" } " +
				"table {table-layout: fixed; width: 100%; border-collapse: collapse; border: 1px solid black;} " +
				"col {} " +
				".glyph {width: 1.35em; border-right-width: 2px;} " +
				"td {border: 1px solid black; }" +
				"td .glyph {}" +
				"p {color: red; }";

		return "<style type=\"text/css\" media=\"all\">\n" + css + "\n</style>\n";
	}

	private static String html = "<img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\" style='height: 30px;'/>\n" +
			"<h1>Sample Narrative</h1>\n" +
			"<p>This should be red</p>\n" +
			"<span class=\"red\">This should be red</span>\n" +
			"\n" +
			"<h2>\n" +
			"Hello\n" +
			" Fred!\n" +
			"</h2>\n" +
			"check: &#10003; \u2713<br/>\n" +
//			"Vous \u00eates d'o\\u00f9?<br/>\n" +
			"\n" +
			"\n" +
			"\n" +
			"This is the caregiver mode\n" +
			"\n" +
			"\n" +
			"\n" +
			"<h3>Subject: Fred (Date of Birth: 10/10/1999)</h3>";


}
