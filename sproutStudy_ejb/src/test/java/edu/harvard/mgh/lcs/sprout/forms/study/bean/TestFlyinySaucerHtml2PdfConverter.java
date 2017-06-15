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
import org.apache.ws.security.util.Base64;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class TestFlyinySaucerHtml2PdfConverter {

	@Test
	public void test() {

		try {
//			createPdf("/Users/slorenz/Desktop/HTMLtoPDF.pdf");
//			String pdfAsString = transformHtml2PDFAsString(html);
//			createPdf2("/Users/slorenz/Desktop/HTMLtoPDF2.pdf");
			createBlankPdf("/Users/slorenz/Desktop/blank.pdf");

//			System.out.println("TestITextHtml2PdfConverter.test.pdfAsString: " + pdfAsString);



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
	@Test
	public void test() {

		try
		{






//			String s = "Vous êtes d'où?";
//			System.out.print("\"");
//			for (int i = 0; i < s.length(); i++) {
//				char c = s.charAt(i);
//				if (c > 31 && c < 127)
//					System.out.print(String.valueOf(c));
//				else
//					System.out.print(String.format("\\u%04x", (int)c));
//			}
//			System.out.println("\"");

			createPdf("/Users/slorenz/Desktop/HTMLtoPDF2.pdf");

			OutputStream file = new FileOutputStream(new File("/Users/slorenz/Desktop/HTMLtoPDF.pdf"));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			writer.setStrictImageSequence(true);
//			StringBuilder htmlString = new StringBuilder();
//			htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> "));
//			htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));
//			htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='https://www.google.com'>Google</a> </td> </tr></table></body></html>"));

			document.open();


//			Image img = Image.getInstance(new URL("http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg"));
//			img.scaleToFit(300, 200);
//			document.add(new Paragraph("Sample 1: This is simple image demo."));
//			document.add(img);


			InputStream is = new ByteArrayInputStream(wrapHtml(html).getBytes(Charset.forName("UTF-8")));
//			InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
			document.close();
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
*/


	public String transformHtml2PDFAsString(String narrative) {
//		System.out.println("SproutTransformServiceImpl.transform");
//		System.out.println("transform: " + "narrative = [" + narrative + "]");

		if (StringUtils.isFull(narrative)) {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, outputStream);
				writer.setStrictImageSequence(true);
				document.open();

//				InputStream is = new ByteArrayInputStream(wrapHtml(narrative).getBytes(Charset.forName("UTF-8")));
//				XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));
//				document.close();


//				String css = "body {" +
//						"    font-size: 12.0pt;" +
//						"    font-family: Arial;" +
//						"    encoding: Identity-H;" +
//						"    face: Arial;" +
//						"    size: 10pt;" +
//						"}";


				XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
				fontProvider.register("ufonts.com_arial-unicode-ms.ttf", "Arial");
				fontProvider.setUseUnicode(true);
				fontProvider.defaultEncoding = "Identity-H";
				CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

				HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
				htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);


				Pipeline<?> pipeline =

						new CssResolverPipeline(cssResolver,

								new HtmlPipeline(htmlContext,

										new PdfWriterPipeline(document, writer)));


				XMLWorker worker = new XMLWorker(pipeline, true);

				XMLParser p = new XMLParser(worker);

				InputStream is = new ByteArrayInputStream(wrapHtml(narrative).getBytes(Charset.forName("UTF-8")));

//		p.parse(new FileInputStream("/html/loremipsum.html"));
				p.parse(is, Charset.forName("UTF-8"));



//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, cis, Charset.forName("UTF-8"), fontProvider);
				document.close();

				String retVal = Base64.encode(outputStream.toByteArray()).toString();
//				System.out.println("TestITextHtml2PdfConverter.transformHtml2PDFAsString.retVal: " + retVal);


				return retVal;

			}
			catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {}
				}
			}
		}
		return null;
	}



	public void createBlankPdf(String fileName) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		OutputStream file = new FileOutputStream(new File(fileName));
		PdfWriter writer = PdfWriter.getInstance(document, file);

		document.open();

		String str = "<html><head>" +
				"</head><body></body></html>";



//		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
		fontProvider.register("ufonts.com_arial-unicode-ms.ttf", "Arial");
		fontProvider.setUseUnicode(true);
		fontProvider.defaultEncoding = "Identity-H";
		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);


//		StyleSheet styles = new StyleSheet();
//		styles.loadTagStyle(HtmlTags.BODY, HtmlTags.FONTFAMILY, "tahoma");
//		styles.loadTagStyle(HtmlTags.BODY, HtmlTags.ENCODING, "Identity-H");
//

		HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);



		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());


		CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);


		Pipeline<?> pipeline =

				new CssResolverPipeline(cssResolver,

						new HtmlPipeline(htmlContext,

								new PdfWriterPipeline(document, writer)));


		XMLWorker worker = new XMLWorker(pipeline, true);

		XMLParser p = new XMLParser(worker);

		InputStream is = new ByteArrayInputStream(wrapHtml(str).getBytes(Charset.forName("UTF-8")));

//		p.parse(new FileInputStream("/html/loremipsum.html"));
		p.parse(is, Charset.forName("UTF-8"));



//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, cis, Charset.forName("UTF-8"), fontProvider);
		document.close();
		file.close();

	}
	public void createPdf(String fileName) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		OutputStream file = new FileOutputStream(new File(fileName));
		PdfWriter writer = PdfWriter.getInstance(document, file);

		document.open();

		String str = "<html><head>" +
//				"</head><body style=\"font-size:12.0pt; font-family:Arial\">"+
				"</head><body>"+
				"<div style=\"font-size:12.0pt; font-family:Arial\">"+
				"<h1>Pets: \u2713 &#10003; \u2610 \u2611 \u00B6 \u0104.</h1>" +
				"<ul>" +
				"<li>\u2713 Cats</li>" +
				"<li>\u2610 Dogs</li>" +
				"<li>\u2713 Fish</li>" +
				"</ul>" +
				"\u2713 Cats..." +
				"</div>" +
				"</body></html>";

		writer.setStrictImageSequence(true);
//			StringBuilder htmlString = new StringBuilder();
//			htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> "));
//			htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));
//			htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='https://www.google.com'>Google</a> </td> </tr></table></body></html>"));

//			Image img = Image.getInstance(new URL("http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg"));
//			img.scaleToFit(300, 200);
//			document.add(new Paragraph("Sample 1: This is simple image demo."));
//			document.add(img);

//		FontFactory.register("ufonts.com_arial-unicode-ms.ttf",  "Arial");   // just give a path of arial.ttf
//		StyleSheet css = new StyleSheet();
//		css.loadTagStyle("body", "face", "Arial");
//		css.loadTagStyle("body", "encoding", "Identity-H");
//		css.loadTagStyle("body", "size", "10pt");

//		hw.setStyleSheet(css);

//		System.out.println("TestITextHtml2PdfConverter.createPdf.css: " + css.);

		String css = "body {" +
				"    font-size: 12.0pt;" +
				"    font-family: Arial;" +
				"    encoding: Identity-H;" +
				"    face: Arial;" +
				"    size: 10pt;" +
				"}";


		ByteArrayInputStream cis =
				new ByteArrayInputStream(css.getBytes());
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, bis, cis);

//		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
		fontProvider.register("ufonts.com_arial-unicode-ms.ttf", "Arial");
		fontProvider.setUseUnicode(true);
		fontProvider.defaultEncoding = "Identity-H";
		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);


//		StyleSheet styles = new StyleSheet();
//		styles.loadTagStyle(HtmlTags.BODY, HtmlTags.FONTFAMILY, "tahoma");
//		styles.loadTagStyle(HtmlTags.BODY, HtmlTags.ENCODING, "Identity-H");
//

		HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);



		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());


		CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);


		Pipeline<?> pipeline =

				new CssResolverPipeline(cssResolver,

						new HtmlPipeline(htmlContext,

								new PdfWriterPipeline(document, writer)));


		XMLWorker worker = new XMLWorker(pipeline, true);

		XMLParser p = new XMLParser(worker);

		InputStream is = new ByteArrayInputStream(wrapHtml(str).getBytes(Charset.forName("UTF-8")));

//		p.parse(new FileInputStream("/html/loremipsum.html"));
		p.parse(is, Charset.forName("UTF-8"));



//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, cis, Charset.forName("UTF-8"), fontProvider);
		document.close();
		file.close();

	}

	public void createPdf2(String file) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
//		writer.getAcroForm().setNeedAppearances(true);
		// step 3
		document.open();

		String str = "<html><head>" +
				"<style>" +
				"ul {\n" +
				"   list-style: none;\n" +
				"   margin-left: 0;\n" +
				"   padding-left: 1em;\n" +
				"   text-indent: -1em;\n" +
				"}" +
				"</style>" +
				"" +
				"" +
				"" +
				"</head><body style=\"font-size:12.0pt; font-family:Arial\">"+
				"<h1>Pets: \u2713 &#10003; \u2610 \u2611 \u00B6 \u0104.</h1>" +
				"<ul>" +
				"<li>\u2713 Cats</li>" +
				"<li>\u2610 Dogs</li>" +
				"<li>\u2713 Fish</li>" +
				"</ul>" +
				"</body></html>";

		System.out.println("TestITextHtml2PdfConverter.createPdf.str: " + str);

//		XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//
//		CSSResolver cssResolver = new StyleAttrCSSResolver();
//		CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
//		cssResolver.addCss(cssFile);
//

		HTMLWorker hw = new HTMLWorker(document);
//		FontFactory.register("/Users/slorenz/Desktop/ufonts.com_arial-unicode-ms.ttf",  "Arial");   // just give a path of arial.ttf
		FontFactory.register("ufonts.com_arial-unicode-ms.ttf",  "Arial");   // just give a path of arial.ttf
		StyleSheet css = new StyleSheet();
		css.loadTagStyle("body", "face", "Arial");
		css.loadTagStyle("body", "encoding", "Identity-H");
		css.loadTagStyle("body", "size", "10pt");

		hw.setStyleSheet(css);

		hw.parse(new StringReader(str.toString()));
//		hw.parse(new StringReader(stringBuilder.toString()));

//		InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
//		worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"), new XMLWorkerFontProvider("resources/fonts/"));
//		worker.parseXHtml(writer, document, is, Charset.forName("UTF-16"));
		// step 5
		document.close();
	}

	private String wrapHtml(String html) {
		if (StringUtils.isFull(html)) {

//			try {
//				InputStream in = new ByteArrayInputStream(html.getBytes());
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//				Tidy tidy = new Tidy();
//				tidy.setShowWarnings(true);
//				tidy.setXmlTags(true);
//				tidy.setInputEncoding("UTF-8");
//				tidy.setOutputEncoding("UTF-8");
//				tidy.setXHTML(true);
////				tidy.setMakeClean(true);
//				org.w3c.dom.Document xmlDoc = tidy.parseDOM(in, out);
//				tidy.pprint(xmlDoc, out);
//
//				html = new String(out.toByteArray());
//
//				System.out.println("TestITextHtml2PdfConverter.wrapHtml.html: " + out.toString());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}


			return String.format("<div>%s</div>", html);
		}
		return null;
	}


	//	private String html = "<style>li {font-family: Georgia, Serif;}</style><img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\"></img>\n" +
//			"<h1>Sample Narrative: <img src='http://img15.deviantart.net/2d65/i/2012/340/8/7/png_rabbit_by_moonglowlilly-d5n7yiz.png'></img></h1>\n" +
//			"<h3>Subject: David (Date of Birth: 10/10/1999)</h3>" +
//            "<div style='background-color: #efefef; margin: 20px; padding: 30px;'>Here are some resources:" +
//			"<ul>" +
//			"<li><a href=\"https://www.google.com\">Google</a></li>" +
//			"<li><a href=\"https://clinicaltrials.partners.org\">Clinical Trials @ Partners</a></li>" +
//			"</ul>" +
//			"</div>";

//	private String html = "<img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\">\n" +
//			"<h1>Sample Narrative</h1>\n" +
//			"\n" +
//			"<h2>\n" +
//			"Hello\n" +
//			" Fred!\n" +
//			"</h2>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"This is the caregiver mode\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"<h3>Subject: Fred (Date of Birth: 10/10/1999)</h3>";

	private String html = "<img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\" />\n" +
			"<h1>Sample Narrative</h1>\n" +
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

	private String html2 = "<img src=\"https://static-s.aa-cdn.net/img/amazon/30600000033303/c936585ae53213b64a946f4fff17dc35?v=1\"></img>\n" +
			"<h1>Sample Narrative</h1>\n" +
			"\n" +
			"<h2>\n" +
			"{{#en}}Hello{{/en}}\n" +
			"{{#es}}¡Hola{{/es}} {{pcori_dsp_interventi.first_name}}!\n" +
			"</h2>\n" +
			"<span>\n" +
			"{{#es}}¡Esto es Español1!{{/es}}\n" +
			"</span>\n" +
			"\n" +
			"<div>\n" +
			"{{#es}}¡Esto <strong>es</strong> Español2!{{/es}}\n" +
			"</div>\n" +
			"\n" +
			"<p>\n" +
			"{{#es}}¡Esto <strong>es</strong> Español2.0!{{/es}}\n" +
			"</p>\n" +
			"\n" +
			"{{#es}}¡Esto <strong>es</strong> Español2.1!{{/es}}\n" +
			"\n" +
			"\n" +
			"{{#es}}¡Esto es Español3!{{/es}}\n" +
			"\n" +
			"<div>random text</div>\n" +
			"\n" +
			"\n" +
			"\n" +
			"{{#compare sprout.custom.mode \"==\" \"caregiver\"}}\n" +
			"This is the caregiver mode\n" +
			"{{/compare}}\n" +
			"\n" +
			"\n" +
			"<h3>{{#locale \"en\"}}Subject{{/locale}}{{#locale \"es\"}}Subjecto{{/locale}}: {{pcori_dsp_interventi.first_name}} (Date of Birth: {{pcori_dsp_interventi.pt_dob}})</h3>\n" +
			"\n" +
			"{{#compare sprout.locale \"==\" \"es\"}}\n" +
			"\n" +
			"{{/compare}}";


}
