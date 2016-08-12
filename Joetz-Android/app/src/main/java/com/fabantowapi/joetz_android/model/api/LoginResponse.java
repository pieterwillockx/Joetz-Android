package com.fabantowapi.joetz_android.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 26-7-2016.
 */
public class LoginResponse {

    @SerializedName("token")
    private Token token;

    public String getAccessToken(){ return token.getAccessToken(); }
    public String getRefreshToken(){ return token.getRefreshToken(); }
    public boolean isSuccess(){
        if(!token.getAccessToken().isEmpty() && !getRefreshToken().isEmpty()){
            return true;
        }

        return false;
    }

    class Token{
        @SerializedName("access")
        private String access;
        @SerializedName("refresh")
        private String refresh;

        public String getAccessToken(){ return access; }
        public String getRefreshToken(){ return refresh; }
    }
}


