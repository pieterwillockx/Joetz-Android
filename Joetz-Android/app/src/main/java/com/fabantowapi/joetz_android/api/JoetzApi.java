package com.fabantowapi.joetz_android.api;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

import com.fabantowapi.joetz_android.model.api.LoginRequest;
import com.fabantowapi.joetz_android.model.api.LogoutRequest;
import com.fabantowapi.joetz_android.model.api.RegisterRequest;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Pieter on 26-7-2016.
 */
public interface JoetzApi {
    @POST("/authentication/login")
    Observable<Response> logIn(@Body LoginRequest request);

    @POST("/authentication/logout")
    Observable<Response> logOut(@Body LogoutRequest request);

    @POST("/user")
    Observable<Response> register(@Body RegisterRequest request);

    @GET("/user/{email}")
    Observable<Response> getUser(@Path("email") String email);
}
