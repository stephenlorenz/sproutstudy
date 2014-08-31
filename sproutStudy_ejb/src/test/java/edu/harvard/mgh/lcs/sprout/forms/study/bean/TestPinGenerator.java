package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;

public class TestPinGenerator {

	 private SecureRandom random = new SecureRandom();

	@Test
	public void test() {
		int counter = 0;
		while (counter++ < 20)
		System.out.println(new BigInteger(25, random).toString(32));
	}

}
