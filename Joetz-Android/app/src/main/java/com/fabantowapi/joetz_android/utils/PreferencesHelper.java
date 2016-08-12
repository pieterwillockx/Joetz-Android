package com.fabantowapi.joetz_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pieter on 26-7-2016.
 */
public class PreferencesHelper {
    private static final String PREFERENCES_NAME = "JoetzPrefs";

    private static final String PREFERENCE_APPLICATION_COOKIE = "application_cookie";

    public static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static String getApplicationCookie(Context context){
        return PreferencesHelper.getPreferences(context).getString(PREFERENCE_APPLICATION_COOKIE, null);
    }

    public static void saveApplicationCookie(Context context, String cookie){
        SharedPreferences.Editor prefs = PreferencesHelper.getPreferences(context).edit();
        prefs.putString(PREFERENCE_APPLICATION_COOKIE, cookie);
        prefs.apply();
    }
}
