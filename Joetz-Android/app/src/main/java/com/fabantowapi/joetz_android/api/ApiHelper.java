package com.fabantowapi.joetz_android.api;

import android.content.Context;
import rx.Observable;

import com.fabantowapi.joetz_android.model.api.LoginRequest;
import com.fabantowapi.joetz_android.model.api.LoginResponse;
import com.fabantowapi.joetz_android.utils.CookieHelper;
import com.fabantowapi.joetz_android.utils.PreferencesHelper;
import com.fabantowapi.joetz_android.utils.SharedHelper;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;


/**
 * Created by Pieter on 26-7-2016.
 */
public class ApiHelper {
    private static JoetzApi service;

    private static JoetzApi getService(Context context){
        if(service == null){
            String domain = SharedHelper.getDomain();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://" + domain)
                    .build();

            service = restAdapter.create(JoetzApi.class);
        }

        return service;
    }

    private static void resetService() { service = null; }

    public static Observable<Object> logIn(Context context, String login, String password, boolean permanent){
        ApiHelper.resetService();

        return ApiHelper.getService(context).logIn(new LoginRequest(login, password, permanent))
                .flatMap(response ->
                {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    LoginResponse loginResponse;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        loginResponse = (LoginResponse) converter.fromBody(response.getBody(), LoginResponse.class);
                    }catch (ConversionException e){
                        return Observable.error(e);
                    }

                    if(loginResponse != null && response.getStatus() == 200){
                        return Observable.just(loginResponse);
                    }
                    else{
                        int status = response.getStatus();
                        String error;

                        switch(status){
                            case 400 : error = "Bad Request"; break;
                            case 401 : error = "Unauthorized"; break;
                            default : error = "Unknown Error"; break;
                        }

                        return Observable.error(new IOException(error));
                    }
                })
                .doOnNext(loginResponse -> {
                    PreferencesHelper.saveAccessToken(context, loginResponse.)
                })
    }
}
