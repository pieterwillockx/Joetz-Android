package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 19-8-2016.
 */
public class EditUserRoleRequest {
    @SerializedName("role")
    private String role;

    public EditUserRoleRequest(String role) {
        this.role = role;
    }
}
