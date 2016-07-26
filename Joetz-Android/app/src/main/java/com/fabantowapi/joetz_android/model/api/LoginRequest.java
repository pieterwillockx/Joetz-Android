package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 26-7-2016.
 */
public class LoginRequest {
    @SerializedName("Login")
    private String login;
    @SerializedName("Password")
    private String password;
    @SerializedName("Permanent")
    private boolean permanent;

    public LoginRequest(String login, String password, boolean permanent){
        this.login = login;
        this.password = password;
        this.permanent = permanent;
    }
}

