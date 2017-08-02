package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestExtractPunctuationPrefix {

	@Test
	public void prefix() {

		String prefix = null;
		String content = null;

		Pattern regexPrefix = Pattern.compile("^[^A-z0-9]*");
		Matcher regexMatcher = regexPrefix.matcher(template);
		if (regexMatcher.find()) {
			prefix = regexMatcher.group();
			content = template.substring(prefix.length());
		}

		System.out.println("TestExtractPunctuationPrefix.prefix.prefix: " + prefix);
		System.out.println("TestExtractPunctuationPrefix.prefix.content: " + content);


//		String t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");


//		System.out.println("TestEscapeHandlebarHelpers.test.t: " + t);
	}

	@Test
	public void suffix() {

		String content = "This is a test  ";;
		String suffix = null;

		Pattern regexSuffix = Pattern.compile("[ \\t]+$");
		Matcher regexMatcherSuffix = regexSuffix.matcher(content);
		if (regexMatcherSuffix.find()) {
			suffix = regexMatcherSuffix.group();
			if ((content.length() - suffix.length()) > 0) {
				content = content.substring(0, content.length() - suffix.length());
			}
		}

		System.out.println("TestExtractPunctuationPrefix.suffix.content: " + content);
		System.out.println("TestExtractPunctuationPrefix.suffix.suffix: ==|" + suffix + "|==");


//		String t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");


//		System.out.println("TestEscapeHandlebarHelpers.test.t: " + t);
	}

	@Test
	public void hasText() {

		String content = ", 123a ";;

		Pattern regexText = Pattern.compile("[a-zA-Z]");
		Matcher regexMatcherText = regexText.matcher(content);
		if (regexMatcherText.find()) {
			System.out.println("has text...");

		}


//		System.out.println("TestExtractPunctuationPrefix.suffix.content: " + content);
//		System.out.println("TestExtractPunctuationPrefix.suffix.suffix: ==|" + suffix + "|==");


//		String t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");


//		System.out.println("TestEscapeHandlebarHelpers.test.t: " + t);
	}

//	private String template = ". This is a test.";
//	private String template = "This is a test.";
	private String template = ", ";

}
