package edu.harvard.mgh.lcs.sprout.forms.study.util;

import org.apache.commons.lang.WordUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class DateUtils {

    public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
        if (date != null) {
            try {
                GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
                gregorianCalendar.setTime(date);
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException e) {
//            e.printStackTrace();
            }
        }
        return null;
    }
}
