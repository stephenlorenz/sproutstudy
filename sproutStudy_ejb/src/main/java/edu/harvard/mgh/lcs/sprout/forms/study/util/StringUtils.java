package edu.harvard.mgh.lcs.sprout.forms.study.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.WordUtils;

public class StringUtils {

	private static final SimpleDateFormat COMMON_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public static boolean isEmpty(String value) {
        return value != null && value.trim().length() > 0 ? false : true;
    }

	public static boolean isEmpty(String... values) {
        if (values != null) {
            for (String value : values) if (value.trim().length() > 0) return false;
            return true;
        }
        return true;
    }

	public static boolean isFull(String... values) {
        if (values != null) {
            for (String value : values) if (value == null || value.trim().length() == 0) return false;
            return true;
        }
        return false;
    }

	public static String sanatize(String input) {

		if (input != null) {

			if (input.startsWith("In general, would you say your health")) {
				System.out.println("input: " + input);
			}

			return input.replaceAll("…", "...");
		}
		return input;
	}

	public static boolean isInteger(String input) {
		try {
			new Integer(input);
			return true;
		} catch (Exception e) {}
		return false;
	}

	public static Integer getInteger(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {}

		return null;
	}

	public static String encode(String input) {
		try {
			return URLEncoder.encode(input != null ? input : "", "UTF-8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {}
		return "";
	}

	public static String codifyString(String code) {
		return codifyString(code, -1);
	}

	public static String codifyString(String code, int maxLength) {
		if (code != null) {
			String codeNew = code.trim().replaceAll("[^a-z||^A-Z||^0-9]+", "_").replaceAll("__", "_").toLowerCase().trim();

			if (maxLength > 0 && !isEmpty(codeNew) && codeNew.length() > maxLength) {
				return codeNew.substring(0, maxLength);
			}

			return codeNew;
		}
		return null;
	}

    public static Date parseDate(String date) {
        return parseDate(date, false);
    }

    public static Date parseDate(String date, boolean endOfDay) {
        try {
            if (endOfDay) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                return simpleDateFormat.parse(String.format("%s 23:59:59", date));
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                return simpleDateFormat.parse(date);
            }
        } catch (ParseException e) {
        } catch (NumberFormatException e) {
            System.out.println("date = " + date);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static InputStream stringToInputStream(String value) {
		try {
			return new ByteArrayInputStream(value.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decodifyString(String string) {
		if (!StringUtils.isEmpty(string)) {
			string = string.replaceAll("_", " ").trim();
			
			return string.length() > 3 ? WordUtils.capitalizeFully(string.replaceAll("_", " ")) : string.toUpperCase();
		}
		return null;
	}

	public static String formatSimpleDate(Date dob) {
		return dob != null ? COMMON_DATE_FORMAT.format(dob) : null;
	}

    public static String getGuid() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String getGuidClean() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
    }

}
