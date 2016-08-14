package com.fabantowapi.joetz_android.api;

import android.content.Context;

import retrofit.client.Response;
import rx.Observable;

import com.fabantowapi.joetz_android.model.api.GetUserResponse;
import com.fabantowapi.joetz_android.model.api.LoginRequest;
import com.fabantowapi.joetz_android.model.api.LoginResponse;
import com.fabantowapi.joetz_android.model.api.RegisterRequest;
import com.fabantowapi.joetz_android.model.api.RegisterResponse;
import com.fabantowapi.joetz_android.utils.CookieHelper;
import com.fabantowapi.joetz_android.utils.PreferencesHelper;
import com.fabantowapi.joetz_android.utils.SharedHelper;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Pieter on 26-7-2016.
 */
public class ApiHelper {
    private static JoetzApi service;

    private static JoetzApi getService(Context context){
        if(service == null){
            //String domain = SharedHelper.getDomain();
            String domain = "10.0.3.2:8085";

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
                    PreferencesHelper.saveAccessToken(context, loginResponse.getAccessToken());
                    PreferencesHelper.saveRefreshToken(context, loginResponse.getRefreshToken());
                })
                .flatMap(loginResponse -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> register(Context context, String email, String firstname, String lastname, String password, String username){
        ApiHelper.resetService();

        return ApiHelper.getService(context).register(new RegisterRequest(email, firstname, lastname, password, username))
                .flatMap(response ->
                {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    RegisterResponse registerResponse;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        registerResponse = (RegisterResponse) converter.fromBody(response.getBody(), RegisterResponse.class);
                    }catch (ConversionException e){
                        return Observable.error(e);
                    }

                    if(registerResponse != null && response.getStatus() == 200){
                            return Observable.just(registerResponse);
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
                .doOnNext(registerResponse -> {})
                .flatMap(registerResponse -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> getUser(Context context, String username){
        ApiHelper.resetService();

        return ApiHelper.getService(context).getUser(username)
                .flatMap(response -> {
                    String applicationCookie = CookieHelper.getApplicationCookie(response.getHeaders());
                    PreferencesHelper.saveApplicationCookie(context, applicationCookie);

                    GetUserResponse getUserResponse;

                    try{
                        GsonConverter converter = new GsonConverter(new Gson());
                        getUserResponse = (GetUserResponse) converter.fromBody(response.getBody(), GetUserResponse.class);
                    }catch(ConversionException e){
                        return Observable.error(e);
                    }

                    if(getUserResponse != null && response.getStatus() == 200){
                        return Observable.just(getUserResponse);
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
                .doOnNext(getUserResponse -> {
                    // TODO: contentResolver stuff
                })
                .flatMap(getUserResponse -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
