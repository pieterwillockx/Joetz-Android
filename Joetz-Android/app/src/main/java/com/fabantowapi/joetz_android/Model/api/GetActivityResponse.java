package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;

import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.fabantowapi.joetz_android.database.UserActivityTable;
import com.fabantowapi.joetz_android.model.Activity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pieter on 16-8-2016.
 */
public class GetActivityResponse implements Serializable {
    @SerializedName("id")
    private String id;
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
    @SerializedName("aanwezigen")
    private String[] aanwezigen;

    public GetActivityResponse(String id, String naam, String datum, String locatie, boolean heleDag, String beginUur, String eindUur){
        this.id = id;
        this.naam = naam;
        this.datum = datum;
        this.locatie = locatie;
        this.heleDag = heleDag;
        this.beginUur = beginUur;
        this.eindUur = eindUur;
        this.aanwezigen = aanwezigen;
    }

    public String getId() { return id; }
    public String getNaam() { return naam; }
    public String getDatum() { return datum; }
    public String getLocatie() { return locatie; }
    public boolean isHeleDag() { return heleDag; }
    public String getBeginUur() { return beginUur; }
    public String getEindUur() { return eindUur; }
    public String[] getAanwezigen() { return aanwezigen; }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();

        cv.put(ActiviteitTable.COLUMN_ID, this.id);
        cv.put(ActiviteitTable.COLUMN_NAAM, this.naam);
        cv.put(ActiviteitTable.COLUMN_DATUM, this.datum);
        cv.put(ActiviteitTable.COLUMN_LOCATIE, this.locatie);
        cv.put(ActiviteitTable.COLUMN_HELEDAG, this.heleDag);
        cv.put(ActiviteitTable.COLUMN_BEGIN, this.beginUur);
        cv.put(ActiviteitTable.COLUMN_EINDE, this.eindUur);

        return cv;
    }

    public ContentValues[] getUserActivityContentValues(){
        ContentValues[] cvArray = new ContentValues[this.aanwezigen != null ? this.aanwezigen.length : 0];

        for(int i = 0; i < this.aanwezigen.length; i++){
            ContentValues cv = new ContentValues();

            cv.put(UserActivityTable.COLUMN_ACTIVITY_ID, this.id);
            cv.put(UserActivityTable.COLUMN_USER_ID, this.aanwezigen[i]);

            cvArray[i] = cv;
        }

        return cvArray;
    }

    public Activity getActivity(){
        return new Activity(id, naam, datum, locatie, heleDag, beginUur, eindUur, null);
    }
}
