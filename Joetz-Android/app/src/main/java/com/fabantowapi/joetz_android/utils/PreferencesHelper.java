package com.fabantowapi.joetz_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pieter on 26-7-2016.
 */
public class PreferencesHelper {
    private static final String PREFERENCES_NAME = "JoetzPrefs";

    private static final String PREFERENCE_APPLICATION_COOKIE = "application_cookie";
    private static final String PREFERENCE_ACCESS_TOKEN = "access_token";
    private static final String PREFERENCE_REFRESH_TOKEN = "refresh_token";

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

    public static void saveAccessToken(Context context, String accessToken){
        SharedPreferences.Editor prefs = PreferencesHelper.getPreferences(context).edit();
        prefs.putString(PREFERENCE_ACCESS_TOKEN, accessToken);
        prefs.apply();
    }

    public static void saveRefreshToken(Context context, String refreshToken){
        SharedPreferences.Editor prefs = PreferencesHelper.getPreferences(context).edit();
        prefs.putString(PREFERENCE_REFRESH_TOKEN, refreshToken);
        prefs.apply();
    }
}
