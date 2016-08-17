package com.fabantowapi.joetz_android.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pieter on 26-7-2016.
 */
public class SharedHelper {
    private static final String DOMAIN = "localhost:8085";

    public static String getDomain(){
        return DOMAIN;
    }

    // format date for api call ex. "26-11-1993" to "1993-11-26"
    public static String formatDateForAPI(String unformattedDate){
        String[] dateSplit = unformattedDate.split("-");
        String formattedDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];

        return formattedDate;
    }

    public static String convertDate(String unformattedDate){
        String formattedDate;

        formattedDate = unformattedDate.split("T")[0];

        String[] splittedDate = formattedDate.split("-");

        String date = splittedDate[2] + "-" + splittedDate[1] + "-" + splittedDate[0];

        return date;
    }

    public static Date parseDateStringToDate(String date){
        String[] split1 = date.split("T");

        String dateString = split1[0];
        String hourString = split1[1].replace("Z", "");

        // split into separate numbers
        String[] dateSplit = dateString.split("-");
        String[] hourSplit = hourString.split(":");

        // remove seconds from hoursplit
        hourSplit = new String[]{hourSplit[0], hourSplit[1]};

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourSplit[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(hourSplit[1]));

        return cal.getTime();
    }
}
