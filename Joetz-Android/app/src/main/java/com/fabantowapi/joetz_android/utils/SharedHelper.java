package com.fabantowapi.joetz_android.utils;

/**
 * Created by Pieter on 26-7-2016.
 */
public class SharedHelper {
    private static final String DOMAIN = "localhost:8085";

    public static String getDomain(){
        return DOMAIN;
    }

    public static String convertDate(String unformattedDate){
        String formattedDate;

        formattedDate = unformattedDate.split("T")[0];

        String[] splittedDate = formattedDate.split("-");

        String date = splittedDate[2] + "-" + splittedDate[1] + "-" + splittedDate[0];

        return date;
    }
}
