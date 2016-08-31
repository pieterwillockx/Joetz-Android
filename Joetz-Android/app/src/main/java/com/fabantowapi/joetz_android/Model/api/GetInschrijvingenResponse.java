package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;

import com.fabantowapi.joetz_android.database.UserCampTable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pieter on 31-8-2016.
 */
public class GetInschrijvingenResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("user")
    private String user;
    @SerializedName("kamp")
    private String kamp;

    public GetInschrijvingenResponse(String id, String user, String kamp) {
        this.id = id;
        this.user = user;
        this.kamp = kamp;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(UserCampTable.COLUMN_ID, this.id);
        cv.put(UserCampTable.COLUMN_CAMP_ID, this.kamp);
        cv.put(UserCampTable.COLUMN_USER_ID, this.user);

        return cv;
    }
}
