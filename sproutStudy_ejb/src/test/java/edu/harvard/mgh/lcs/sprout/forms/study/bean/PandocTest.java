package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class PandocTest {

	@Test
	public void test() {

		try {

			String narrative = "<span contenteditable=\"true\" sproutnarrativeeditable=\"true\">This is a test. The order type is </span>MGHSTRESS<span contenteditable=\"true\" sproutnarrativeeditable=\"true\">.</span><h3>Clinical NOTE:</h3>" +
					"<div>" +
					"Jared Moon, MD was the technician for this study. This study was of the type, Test A. The cardiologist was Amy Spooner, MD and the radiologist was Alan J Fischman, MD." +
					"</div>" +
					"  <ul>" +
					"    <li>Cat</li>" +
					"  </ul>" +
					"<div><table><tr><th>ID</th><th>Name</th></tr><tr><td>123</td><td>Bob Peter Edwards</td></tr><tr><td>321</td><td>Fred Rogers</td></tr></table></div>" +
					"<p>This is another note.</p>";

			System.out.println("before narrative = " + narrative);

			narrative = narrative.replaceAll("<span[^>]*>", "").replaceAll("</span[^>]*>", "");

			System.out.println("after narrative = " + narrative);




//		ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", "--from=html", "--to=rtf");
		ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "-s", "--from=html", "--to=markdown_github");
//		ProcessBuilder processBuilder = new ProcessBuilder("pandoc", "--version");
//			ProcessBuilder processBuilder = new ProcessBuilder("pandoc123", "-s", "--from=html", "--to=plain");
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			OutputStream outStream = process.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outStream);
			printWriter.print(narrative);
			printWriter.flush();
			printWriter.close();

			BufferedReader reader =
					new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
//				builder.append("\\\\br.\\\\");
			}
			String result = builder.toString();

			System.out.println(result);
		} catch (IOException e) {

			if (e.getMessage().startsWith("Cannot run program")) {
				System.out.println("\n\n################# Pandoc (http://pandoc.org) does not appear to be installed on this server. Pandoc is required to convert output between formats. #################\n(Source: edu.harvard.mgh.lcs.sprout.forms.study.bean.PandocTest#test)");
			} else {
				e.printStackTrace();
			}


		}

	}

}
