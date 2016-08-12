package com.fabantowapi.joetz_android.api;

import rx.Observable;

import com.fabantowapi.joetz_android.model.api.LoginRequest;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Pieter on 26-7-2016.
 */
public interface JoetzApi {
    @POST("/authentication/login")
    Observable<Response> logIn(@Body LoginRequest request);
}
