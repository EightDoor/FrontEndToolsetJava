package com.start6.personalkit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }
    public static String formatDate(String dateFormat, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }
}