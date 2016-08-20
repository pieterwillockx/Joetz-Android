package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 19-8-2016.
 */
public class CreateActivityRequest {
    @SerializedName("naam")
    private String naam;
    @SerializedName("datum")
    private String datum;
    @SerializedName("locatie")
    private String locatie;
    @SerializedName("heleDag")
    private boolean heleDag;
    @SerializedName("beginUur")
    private String beginUur;
    @SerializedName("eindUur")
    private String eindUur;

    public CreateActivityRequest(String naam, String datum, String locatie, boolean heleDag, String beginUur, String eindUur) {
        this.naam = naam;
        this.datum = datum;
        this.locatie = locatie;
        this.heleDag = heleDag;
        this.beginUur = beginUur;
        this.eindUur = eindUur;
    }
}
