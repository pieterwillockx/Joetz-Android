package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 16-8-2016.
 */
public class LogoutRequest {
    @SerializedName("refreshToken")
    private String refreshToken;

    public LogoutRequest(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
