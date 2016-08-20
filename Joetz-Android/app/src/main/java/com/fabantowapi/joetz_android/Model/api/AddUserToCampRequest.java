package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 20-8-2016.
 */
public class AddUserToCampRequest {
    @SerializedName("extraInformatie")
    private String extraInformatie;
    @SerializedName("betaald")
    private boolean betaald;
    @SerializedName("goedgekeurd")
    private boolean goedgekeurd;
    @SerializedName("userId")
    private String userId;
    @SerializedName("kampId")
    private String kampId;

    public AddUserToCampRequest(String extraInformatie, boolean betaald, boolean goedgekeurd, String userId, String kampId) {
        this.extraInformatie = extraInformatie;
        this.betaald = betaald;
        this.goedgekeurd = goedgekeurd;
        this.userId = userId;
        this.kampId = kampId;
    }
}
