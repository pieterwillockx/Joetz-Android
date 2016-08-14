package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 26-7-2016.
 */
public class LoginRequest {
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("permanent")
    private boolean permanent;

    public LoginRequest(String login, String password, boolean permanent){
        this.login = login;
        this.password = password;
        this.permanent = permanent;
    }
}

