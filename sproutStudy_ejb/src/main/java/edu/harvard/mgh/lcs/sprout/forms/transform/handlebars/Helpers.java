package edu.harvard.mgh.lcs.sprout.forms.transform.handlebars;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Options;
import com.sun.crypto.provider.BlowfishKeyGenerator;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Helpers {

    private static final SimpleDateFormat DATE_FORMAT_SHORT = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat DATE_FORMAT_WEEKDAY = new SimpleDateFormat("EEEE, MMM d");
    private static final SimpleDateFormat DATE_FORMAT_MEDIUM = new SimpleDateFormat("MMM d");
    private static final SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("EEEE, MMM d, yyyy");

//    short: "MM/DD/YYYY",
//    weekday: "dddd, MMM D",
//    medium: "MMM D",
//    long: "dddd, MMM D, YYYY"
//


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

//        System.out.println("Helpers.compare.result: " + result);

        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence locale(String locale, Options options) throws IOException {

        boolean result = false;

        try {
            ObjectNode model = (ObjectNode) options.context.model();
            JsonNode localeNode = model.get("sprout").get("locale");
            String sproutLocale = localeNode.textValue();
            if (StringUtils.isFull(sproutLocale)) {
                result = sproutLocale.equalsIgnoreCase(locale);
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence en(Options options) throws IOException {

        boolean result = false;

        try {
            ObjectNode model = (ObjectNode) options.context.model();
            JsonNode localeNode = model.get("sprout").get("locale");
            String sproutLocale = localeNode.textValue();
            if (StringUtils.isFull(sproutLocale)) {
                result = sproutLocale.equalsIgnoreCase("en");
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence es(Options options) throws IOException {

        boolean result = false;

        try {
            ObjectNode model = (ObjectNode) options.context.model();
            JsonNode localeNode = model.get("sprout").get("locale");
            String sproutLocale = localeNode.textValue();
            if (StringUtils.isFull(sproutLocale)) {
                result = sproutLocale.equalsIgnoreCase("es");
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence formatDate(String date, String format, Options options) throws IOException {

//        System.out.println("date = [" + date + "], format = [" + format + "], options = [" + options + "]");
//        System.out.println("Helpers.formatDate.options: " + options);

        StringBuilder buffer = new StringBuilder();

        try {
            if (StringUtils.isFull(date, format)) {
                SimpleDateFormat simpleDateFormat = DATE_FORMAT_LONG;

                if (StringUtils.isLong(date)) {
                    Date realDate = new Date(new Long(date));

                    if (format.equalsIgnoreCase("short")) {
                        simpleDateFormat = DATE_FORMAT_SHORT;
                    } else if (format.equalsIgnoreCase("weekday")) {
                        simpleDateFormat = DATE_FORMAT_WEEKDAY;
                    } else if (format.equalsIgnoreCase("medium")) {
                        simpleDateFormat = DATE_FORMAT_MEDIUM;
                    } else if (format.equalsIgnoreCase("long")) {
                        simpleDateFormat = DATE_FORMAT_LONG;
                    }
                    return simpleDateFormat.format(realDate);
                }
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return date;
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