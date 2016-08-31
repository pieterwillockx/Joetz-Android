package com.fabantowapi.joetz_android.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ActiviteitTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class Activity implements Serializable {
    private String id;
    private String naam;
    private String datum;
    private String locatie;
    private boolean heleDag;
    private String beginUur;
    private String eindUur;
    private List<User> aanwezigen;

    public Activity(String id, String naam, String datum, String locatie, boolean heleDag, String beginUur, String eindUur, List<User> aanwezigen) {
        this.id = id;
        this.naam = naam;
        this.datum = datum;
        this.locatie = locatie;
        this.heleDag = heleDag;
        this.beginUur = beginUur;
        this.eindUur = eindUur;

        if(aanwezigen == null){
            this.aanwezigen = new ArrayList<>();
        }
        else {
            this.aanwezigen = aanwezigen;
        }
    }

    public String getId() { return id; }
    public String getNaam() { return naam; }
    public String getLocatie() { return locatie; }
    public String getBeginUur() { return beginUur; }
    public String getEindUur() { return eindUur; }
    public List<User> getAanwezigen() { return aanwezigen; }

    public void setId(String id) { this.id = id; }

    public void addAanwezige(User user){ this.aanwezigen.add(user); }

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

    public static Activity constructFromCursor(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_ID_FULL);
        int naamIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_NAAM_FULL);
        int datumIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_DATUM_FULL);
        int locatieIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_LOCATIE_FULL);
        int heleDagIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_HELEDAG_FULL);
        int beginIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_BEGIN_FULL);
        int eindeIndex = cursor.getColumnIndex(ActiviteitTable.COLUMN_EINDE_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        String id = cursor.getString(idIndex);
        String naam = cursor.getString(naamIndex);
        String datum = cursor.getString(datumIndex);
        String locatie = cursor.getString(locatieIndex);
        boolean heleDag = cursor.getInt(heleDagIndex) != 0;
        String begin = cursor.getString(beginIndex);
        String einde = cursor.getString(eindeIndex);

        return new Activity(id, naam, datum, locatie, heleDag, begin, einde, null);
    }

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
}
