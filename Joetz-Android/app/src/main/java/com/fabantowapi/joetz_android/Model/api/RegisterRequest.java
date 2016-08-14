package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 14-8-2016.
 */
public class RegisterRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;

    public RegisterRequest(String email, String firstname, String lastname, String password, String username){
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }
}
