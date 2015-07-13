package edu.harvard.mgh.lcs.sprout.forms.study;

import org.junit.Test;

public class JSONTest {


	@Test
	public void test() {
        String response = "1234!{'test': 'yo'}";

        if (!response.trim().startsWith("{") && response.indexOf("{") >= 0) {
            System.out.println("response = " + response.substring(response.indexOf("{")));
        }



	}


}
