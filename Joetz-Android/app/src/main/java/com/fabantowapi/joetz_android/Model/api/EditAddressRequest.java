package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditAddressRequest {
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

    public EditAddressRequest(int postcode, String straat, int huisnummer, String bus, String gemeente, String naamgebouw) {
        this.postcode = postcode;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.bus = bus;
        this.gemeente = gemeente;
        this.naamgebouw = naamgebouw;
    }
}
