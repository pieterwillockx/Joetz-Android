package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 17-8-2016.
 */
public class EditUserDetailsRequest {
    @SerializedName("codegerechtigde")
    private String codegerechtigde;

    public EditUserDetailsRequest(String codegerechtigde){
        this.codegerechtigde = codegerechtigde;
    }
}
