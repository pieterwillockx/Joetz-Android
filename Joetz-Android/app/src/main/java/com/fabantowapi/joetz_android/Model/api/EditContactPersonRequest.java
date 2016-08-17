package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditContactPersonRequest {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("telefoonnummer")
    private String telefoonnummer;
    @SerializedName("rijksregisternummer")
    private String rijksregisternummer;
    @SerializedName("aansluitnummer")
    private String aansluitnummer;
    @SerializedName("betalend")
    private boolean betalend;
    @SerializedName("ouder")
    private boolean ouder;
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

    public EditContactPersonRequest(String firstname, String lastname, String email, String telefoonnummer, String rijksregisternummer, String aansluitnummer, boolean betalend, boolean ouder, String naamgebouw, String straat, int huisnummer, String bus, String gemeente, int postcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.rijksregisternummer = rijksregisternummer;
        this.aansluitnummer = aansluitnummer;
        this.betalend = betalend;
        this.ouder = ouder;
        this.naamgebouw = naamgebouw;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }
}
