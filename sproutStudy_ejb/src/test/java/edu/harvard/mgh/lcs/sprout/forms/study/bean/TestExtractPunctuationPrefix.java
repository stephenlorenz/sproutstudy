package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestExtractPunctuationPrefix {

	@Test
	public void test() {

		String prefix = null;
		String suffix = null;

		Pattern regex = Pattern.compile("^[^A-z0-9]*");
		Matcher regexMatcher = regex.matcher(template);
		if (regexMatcher.find()) {
			prefix = regexMatcher.group();
			suffix = template.substring(prefix.length());
		}

		System.out.println("TestExtractPunctuationPrefix.test.prefix: " + prefix);
		System.out.println("TestExtractPunctuationPrefix.test.suffix: " + suffix);


//		String t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");


//		System.out.println("TestEscapeHandlebarHelpers.test.t: " + t);
	}

//	private String template = ". This is a test.";
//	private String template = "This is a test.";
	private String template = ", ";

}
