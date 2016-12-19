package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import org.junit.Test;

import java.io.*;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

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


			Image img = Image.getInstance(new URL("http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg"));
			img.scaleToFit(300, 200);
			document.add(new Paragraph("Sample 1: This is simple image demo."));
			document.add(img);


			InputStream is = new ByteArrayInputStream(html.getBytes());
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


	private String html = "<img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\">\n" +
			"<h1>Sample Narrative</h1>\n" +
			"<h3>Subject: David (Date of Birth: 10/10/1999)</h3>";


}
