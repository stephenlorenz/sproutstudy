package edu.harvard.mgh.lcs.sprout.forms.study.handlebars;

import com.github.jknack.handlebars.Options;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

import java.io.IOException;

public class Helpers {

    public CharSequence compare(String lvalue, String operator, String rvalue, Options options) throws IOException {

        System.out.println("Helpers.compare");
        System.out.println("lvalue = [" + lvalue + "], operator = [" + operator + "], rvalue = [" + rvalue + "], options = [" + options + "]");

        boolean result = false;

        if (StringUtils.isFull(operator) && lvalue != null && rvalue != null) {
            if (operator.equals("=") || operator.equals("==") || operator.equals("===")) {
                if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue)) {
                    result = StringUtils.getFloat(lvalue).floatValue() == StringUtils.getFloat(rvalue).floatValue();
                } else {
                    result = lvalue.equals(rvalue);
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


        System.out.println("result = " + result);


        return result ? options.fn(true) : null;
    }




}