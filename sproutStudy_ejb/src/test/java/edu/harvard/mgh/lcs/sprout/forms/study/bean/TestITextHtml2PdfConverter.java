package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.bouncycastle.asn1.DERBitString;
import org.junit.Test;

import java.io.*;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.w3c.tidy.Tidy;

public class TestITextHtml2PdfConverter {

	@Test
	public void test() {

		try
		{
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


			InputStream is = new ByteArrayInputStream(wrapHtml(html).getBytes());
//			InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

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
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"This is the caregiver mode\n" +
			"\n" +
			"\n" +
			"\n" +
			"<h3>Subject: Fred (Date of Birth: 10/10/1999)</h3>";

	private String html = "<img src=\"https://static-s.aa-cdn.net/img/amazon/30600000033303/c936585ae53213b64a946f4fff17dc35?v=1\"></img>\n" +
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
