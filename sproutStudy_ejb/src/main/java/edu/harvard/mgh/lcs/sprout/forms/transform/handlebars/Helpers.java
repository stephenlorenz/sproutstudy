package edu.harvard.mgh.lcs.sprout.forms.transform.handlebars;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Options;
import com.sun.crypto.provider.BlowfishKeyGenerator;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;

public class Helpers {

    public CharSequence compare(String lvalue, String operator, String rvalue, Options options) throws IOException {

//        System.out.println("\nHelpers.compare");
//        System.out.println("lvalue = [" + lvalue + "], operator = [" + operator + "], rvalue = [" + rvalue + "], options = [" + options + "]");

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
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }



    public CharSequence getNode(Object context, String queryKey, Options options) throws IOException {

//        System.out.println("Helpers.getNode");
//        System.out.println("sourceNode = [" + context + "], queryKey = [" + queryKey + "], options = [" + options + "]");

        StringBuilder buffer = new StringBuilder();

        if (queryKey != null) {
            if (context instanceof Iterable) {
                Iterable iterable = (Iterable) context;
                Iterator<Object> iterator = iterable.iterator();
                Context parent = options.context;
                while (iterator.hasNext()) {
                    Object element = iterator.next();
                    if (element instanceof ObjectNode) {
                        ObjectNode elementNode = (ObjectNode) element;
                        JsonNode jsonNode = elementNode.get(queryKey);
                        if (jsonNode != null) {
                            Context current = Context.newBuilder(parent, jsonNode).build();
                            buffer.append(options.fn(current));
                            break;
                        }
                    }
                }
            }
        }

        return buffer.toString();
    }




}