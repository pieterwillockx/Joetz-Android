package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditUserRequest {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("rijksregisternummer")
    private String rijksregisternummer;
    @SerializedName("geboortedatum")
    private String geboortedatum;

    public EditUserRequest(String firstname, String lastname, String rijksregisternummer, String geboortedatum){
        this.firstname = firstname;
        this.lastname = lastname;
        this.rijksregisternummer = rijksregisternummer;
        this.geboortedatum = geboortedatum;
    }
}
