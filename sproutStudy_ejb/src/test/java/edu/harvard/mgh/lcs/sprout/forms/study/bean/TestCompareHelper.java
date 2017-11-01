package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.SecureRandom;



public class TestCompareHelper {

	 private SecureRandom random = new SecureRandom();

	@Test
	public void testCompare() throws IOException {

//		System.out.println(compare("1", "==", "1"));

		Assert.assertTrue(compare("1", "==", "1"));
		Assert.assertFalse(compare("0", "==", "1"));
		Assert.assertFalse(compare("==", "1", null));
//		Assert.assertFalse(compare("!=", "1", null));
		Assert.assertTrue(compare("!=", "1", null));

	}


	private boolean compare(String lvalue, String operator, String rvalue) throws IOException {

//        System.out.println("\nHelpers.compare");
//        System.out.println("lvalue = [" + lvalue + "], operator = [" + operator + "], rvalue = [" + rvalue + "], options = [" + options + "]");

		boolean result = false;

        if (rvalue == null) { // This means the left value was empty and causes the parameters to shift; fix this by reassigning variables
            rvalue = operator;
            operator = lvalue;
            lvalue = "";
        }



		if (StringUtils.isFull(operator) && lvalue != null && rvalue != null) {
			if (operator.equals("=") || operator.equals("==") || operator.equals("===")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue)) {
					result = StringUtils.getFloat(lvalue).floatValue() == StringUtils.getFloat(rvalue).floatValue();
				} else {
					result = lvalue.equals(rvalue);
				}
			} else if (operator.equals("!==") || operator.equals("!=") || operator.equals("<>")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue)) {
					result = StringUtils.getFloat(lvalue).floatValue() != StringUtils.getFloat(rvalue).floatValue();
				} else {
					result = !lvalue.equals(rvalue);
				}
			} else if (operator.equals(">")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
					result = StringUtils.getFloat(lvalue).floatValue() > StringUtils.getFloat(rvalue).floatValue();
			} else if (operator.equals("<")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
					result = StringUtils.getFloat(lvalue).floatValue() < StringUtils.getFloat(rvalue).floatValue();
			} else if (operator.equals(">=")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
					result = StringUtils.getFloat(lvalue).floatValue() >= StringUtils.getFloat(rvalue).floatValue();
			} else if (operator.equals("<=")) {
				if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
					result = StringUtils.getFloat(lvalue).floatValue() <= StringUtils.getFloat(rvalue).floatValue();
			}
		}

		System.out.println("Helpers.compare.result: " + result);

		return result;

//		return options.isFalsy(result) ? options.inverse() : options.fn();
	}


}
