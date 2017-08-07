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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFindSaveTemplateErrors {

	private Set<String> instanceIds = new HashSet<String>();

	@Test
	public void test() throws IOException {

		File folder = new File("/Users/slorenz/tmp/lcs123/log");

		String[] files = folder.list();

		for (String file : files) {
//			System.out.println(file);
			processFile(new File("/Users/slorenz/tmp/lcs123/log/" + file));
		}


		folder = new File("/Users/slorenz/tmp/lcsn150/log");

		files = folder.list();

		for (String file : files) {
//			System.out.println(file);
			processFile(new File("/Users/slorenz/tmp/lcsn150/log/" + file));
		}


		System.out.println("TestFindSaveTemplateErrors.test.instanceIds: " + instanceIds.size());

		for (String instanceId : instanceIds) {
			System.out.println("TestFindSaveTemplateErrors.test.instanceId: " + instanceId);
		}


	}

	private void processFile(File file) throws IOException {
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String s;

		List<String> tmp = new ArrayList<String>();
		do{
			s = br.readLine();
			tmp.add(s);
		}while(s!=null);

		boolean seekingInstanceId = false;

		for(int i=tmp.size()-1;i>=0;i--) {
			String line = tmp.get(i);
			if (StringUtils.isFull(line)) {
				if (line.indexOf("Failed executing POST /command/secure/saveTemplate") >= 0) {
//					System.out.println(line);
					seekingInstanceId = true;
				}
				if (seekingInstanceId) {
					if (line.indexOf("FormLockServiceImpl.pingLock.inboxEntity.getInstanceId()") >= 0) {
						String instanceId = extractInstanceId(line);

						instanceIds.add(instanceId);

//						System.out.println("TestFindSaveTemplateErrors.test.line: " + instanceId);
						seekingInstanceId = false;
					}
				}
			}

		}

	}

	private String extractInstanceId(String line) {
		if (StringUtils.isFull(line)) {
			return line.substring(line.indexOf("getInstanceId():") + 17);
		}
		return null;
	}


	@Before
	public void init() throws IOException {

		InputStream is = new FileInputStream("/Users/slorenz/tmp/lcs123/log/server.log.2017-07-22");
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while(line != null){
			sb.append(line).append("\n"); line = buf.readLine();
		}
	}

}
