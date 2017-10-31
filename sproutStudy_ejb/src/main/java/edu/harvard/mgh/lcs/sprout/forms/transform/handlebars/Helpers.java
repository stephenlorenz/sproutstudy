package edu.harvard.mgh.lcs.sprout.forms.transform.handlebars;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
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
    private Handlebars handlebars = new Handlebars();

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
            ObjectNode model = getModel(options);

            if (model != null) {
                JsonNode localeNode = model.get("sprout").get("locale");
                String sproutLocale = localeNode.textValue();
                if (StringUtils.isFull(sproutLocale)) {
                    result = sproutLocale.equalsIgnoreCase(locale);
                }
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence en(Options options) throws IOException {

        boolean result = false;

        try {
            ObjectNode model = getModel(options);

            if (model != null) {
                JsonNode localeNode = model.get("sprout").get("locale");
                String sproutLocale = localeNode.textValue();
                if (StringUtils.isFull(sproutLocale)) {
                    result = sproutLocale.equalsIgnoreCase("en");
                }
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

    public CharSequence es(Options options) throws IOException {

        boolean result = false;

        try {
            ObjectNode model = getModel(options);

            if (model != null) {
                JsonNode localeNode = model.get("sprout").get("locale");
                String sproutLocale = localeNode.textValue();
                if (StringUtils.isFull(sproutLocale)) {
                    result = sproutLocale.equalsIgnoreCase("es");
                }
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }
        return options.isFalsy(result) ? options.inverse() : options.fn();
    }

        public CharSequence or(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15, String value16, String value17, String value18, String value19, String value20, Options options) throws IOException {
        boolean result = false;

        try {
            result = ( new Boolean(value1)
                    || new Boolean(value2)
                    || new Boolean(value3)
                    || new Boolean(value4)
                    || new Boolean(value5)
                    || new Boolean(value6)
                    || new Boolean(value7)
                    || new Boolean(value8)
                    || new Boolean(value9)
                    || new Boolean(value10)
                    || new Boolean(value11)
                    || new Boolean(value12)
                    || new Boolean(value13)
                    || new Boolean(value14)
                    || new Boolean(value15)
                    || new Boolean(value16)
                    || new Boolean(value17)
                    || new Boolean(value18)
                    || new Boolean(value19)
                    || new Boolean(value20)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result ? new String("" + result) : null;
    }
    
    public CharSequence and(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15, String value16, String value17, String value18, String value19, String value20, Options options) throws IOException {
        boolean result = false;

        try {
            result = ( new Boolean(value1)
                    && new Boolean(value2)
                    && new Boolean((StringUtils.isEmpty(value3) ? "true" : value3))
                    && new Boolean((StringUtils.isEmpty(value4) ? "true" : value4))
                    && new Boolean((StringUtils.isEmpty(value5) ? "true" : value5))
                    && new Boolean((StringUtils.isEmpty(value6) ? "true" : value6))
                    && new Boolean((StringUtils.isEmpty(value7) ? "true" : value7))
                    && new Boolean((StringUtils.isEmpty(value8) ? "true" : value8))
                    && new Boolean((StringUtils.isEmpty(value9) ? "true" : value9))
                    && new Boolean((StringUtils.isEmpty(value10) ? "true" : value10))
                    && new Boolean((StringUtils.isEmpty(value11) ? "true" : value11))
                    && new Boolean((StringUtils.isEmpty(value12) ? "true" : value12))
                    && new Boolean((StringUtils.isEmpty(value13) ? "true" : value13))
                    && new Boolean((StringUtils.isEmpty(value14) ? "true" : value14))
                    && new Boolean((StringUtils.isEmpty(value15) ? "true" : value15))
                    && new Boolean((StringUtils.isEmpty(value16) ? "true" : value16))
                    && new Boolean((StringUtils.isEmpty(value17) ? "true" : value17))
                    && new Boolean((StringUtils.isEmpty(value18) ? "true" : value18))
                    && new Boolean((StringUtils.isEmpty(value19) ? "true" : value19))
                    && new Boolean((StringUtils.isEmpty(value20) ? "true" : value20))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result ? new String("" + result) : null;
    }

    public CharSequence eq(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (StringUtils.isFull(lvalue, rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue)) {
                result = StringUtils.getFloat(lvalue).floatValue() == StringUtils.getFloat(rvalue).floatValue();
            } else {
                result = lvalue.equals(rvalue);
            }
        }
        return new String("" + result);
    }

    public CharSequence ne(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (lvalue == null) return "true";

//        System.out.println("Helpers.ne.options: " + options);
//        System.out.println("********************************** ");
//        System.out.println("Helpers.ne.lvalue: " + lvalue);
//        System.out.println("Helpers.ne.rvalue: " + rvalue);

//        if (StringUtils.isFull(lvalue, rvalue)) {
        if (StringUtils.isFull(rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue)) {
                result = StringUtils.getFloat(lvalue).floatValue() != StringUtils.getFloat(rvalue).floatValue();
            } else {
                result = !lvalue.equals(rvalue);
            }
        }

//        System.out.println("Helpers.ne.result: " + result);

        return new String("" + result);
    }

    public CharSequence gt(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (StringUtils.isFull(lvalue, rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
                result = StringUtils.getFloat(lvalue).floatValue() > StringUtils.getFloat(rvalue).floatValue();
        }
        return new String("" + result);
    }

    public CharSequence gte(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (StringUtils.isFull(lvalue, rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
                result = StringUtils.getFloat(lvalue).floatValue() >= StringUtils.getFloat(rvalue).floatValue();
        }
        return new String("" + result);
    }

    public CharSequence lt(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (StringUtils.isFull(lvalue, rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
                result = StringUtils.getFloat(lvalue).floatValue() < StringUtils.getFloat(rvalue).floatValue();
        }
        return new String("" + result);
    }

    public CharSequence lte(String lvalue, String rvalue, Options options) throws IOException {
        boolean result = false;

        if (StringUtils.isFull(lvalue, rvalue)) {
            if (StringUtils.isInteger(lvalue) && StringUtils.isInteger(rvalue))
                result = StringUtils.getFloat(lvalue).floatValue() <= StringUtils.getFloat(rvalue).floatValue();
        }
        return new String("" + result);
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

    public CharSequence i18n(String key, Options options) throws IOException {
//        System.out.println("Helpers.i18n");
//        System.out.println("key = [" + key + "], options = [" + options + "]");

//        if (key.equalsIgnoreCase("ED728071-02CD-466B-8BDD-C2A0E57BA361")) {
//            System.out.println("stop here");
//        }

        try {
            if (options.context != null) {
                ObjectNode model = null;

                model = getModel(options.context);

                if (model != null) {
                    JsonNode localeNode = model.get("sprout").get("locale");
                    String sproutLocale = "en";

                    if (localeNode != null) sproutLocale = localeNode.textValue();

                    JsonNode translationsNode = model.get("translations");

                    if (translationsNode != null) {
                        JsonNode translationNode = translationsNode.get(key);
                        if (translationNode != null) {
                            JsonNode locale = translationNode.get(sproutLocale);
                            if (locale != null) {
                                String message = locale.textValue();
                                Template template = handlebars.compileInline(message);
                                return template.apply(options.context);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Helpers.locale.e.getMessage();: " + e.getMessage());
        }

        return "";

    }

    private ObjectNode getModel(Context context) {
        if (context != null) {
            if (context.model() instanceof ObjectNode) {
                return (ObjectNode) context.model();
            } else {
                return getModel(context.parent());
            }
        } else {
            return null;
        }
    }

    private ObjectNode getModel(Options options) {
        ObjectNode model;
        if (options.context.parent() != null) {
            model = (ObjectNode) options.context.parent().model();
        } else {
            model = (ObjectNode) options.context.model();
        }
        return model;
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