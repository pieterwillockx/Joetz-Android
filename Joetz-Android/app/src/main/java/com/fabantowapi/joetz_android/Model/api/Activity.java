package com.fabantowapi.joetz_android.model.api;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 16-8-2016.
 */
public class Activity implements Serializable {
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

    public Activity(String id, String naam, String datum, String locatie, boolean heleDag, String beginUur, String eindUur){
        this.id = id;
        this.naam = naam;
        this.datum = datum;
        this.locatie = locatie;
        this.heleDag = heleDag;
        this.beginUur = beginUur;
        this.eindUur = eindUur;
    }

    public String getId() { return id; }
    public String getNaam() { return naam; }
    public String getDatum() { return datum; }
    public String getLocatie() { return locatie; }
    public boolean isHeleDag() { return heleDag; }
    public String getBeginUur() { return beginUur; }
    public String getEindUur() { return eindUur; }

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

    public static List<Activity> constructListFromCursor(Cursor cursor){
        List<Activity> activities = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                activities.add(Activity.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return activities;
    }

    public static Activity constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_ID_FULL);
        int naamIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_NAAM_FULL);
        int datumIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_DATUM_FULL);
        int locatieIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_LOCATIE_FULL);
        int heleDagIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_HELEDAG_FULL);
        int beginIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_BEGIN_FULL);
        int eindeIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_EINDE_FULL);

        cursor.moveToFirst();

        String id = cursor.getString(idIndex);
        String naam = cursor.getString(naamIndex);
        String datum = cursor.getString(datumIndex);
        String locatie = cursor.getString(locatieIndex);
        boolean heleDag = cursor.getInt(heleDagIndex) != 0;
        String begin = cursor.getString(beginIndex);
        String einde = cursor.getString(eindeIndex);

        return new Activity(id, naam, datum, locatie, heleDag, begin, einde);
    }
}
