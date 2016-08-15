package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 15-8-2016.
 */
public class Adres{
    @SerializedName("naamgebouw")
    private String naamgebouw;
    @SerializedName("straat")
    private String straat;
    @SerializedName("huisnummer")
    private int huisnummer;
    @SerializedName("bus")
    private String bus;
    @SerializedName("gemeente")
    private String gemeente;
    @SerializedName("postcode")
    private int postcode;

    public String getNaamgebouw() { return naamgebouw; }
    public String getStraat() { return straat; }
    public int getHuisnummer() { return huisnummer; }
    public String getBus() { return bus; }
    public String getGemeente() { return gemeente; }
    public int getPostcode() { return postcode; }
}
