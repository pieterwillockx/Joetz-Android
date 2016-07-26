package com.fabantowapi.joetz_android.api;

import android.database.Observable;

import com.fabantowapi.joetz_android.model.api.LoginRequest;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pieter on 26-7-2016.
 */
public interface JoetzApi {
    @POST("/authentication/login")
    Observable<Response> logIn(@Body LoginRequest request);
}
