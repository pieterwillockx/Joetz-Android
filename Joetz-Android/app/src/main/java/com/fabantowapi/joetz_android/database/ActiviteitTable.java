package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class ActiviteitTable {
    public static final String TABLE_NAME = "activiteiten";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAAM = "naam";
    public static final String COLUMN_DATUM = "datum";
    //public static final String COLUMN_MEDEWERKER_ID = "medewerker_id ";
    public static final String COLUMN_LOCATIE = "locatie";
    public static final String COLUMN_HELEDAG = "heleDag";
    public static final String COLUMN_BEGIN = "begin";
    public static final String COLUMN_EINDE = "einde";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    //public static final String COLUMN_MEDEWERKER_ID_FULL = TABLE_NAME + "_" + COLUMN_MEDEWERKER_ID;
    public static final String COLUMN_NAAM_FULL = TABLE_NAME + "_" + COLUMN_NAAM;
    public static final String COLUMN_DATUM_FULL = TABLE_NAME + "_" + COLUMN_DATUM;
    public static final String COLUMN_LOCATIE_FULL = TABLE_NAME + "_" + COLUMN_LOCATIE;
    public static final String COLUMN_HELEDAG_FULL = TABLE_NAME + "_" + COLUMN_HELEDAG;
    public static final String COLUMN_BEGIN_FULL = TABLE_NAME + "_" + COLUMN_BEGIN;
    public static final String COLUMN_EINDE_FULL = TABLE_NAME + "_" + COLUMN_EINDE;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAM, TABLE_NAME + "." + COLUMN_NAAM + " AS " + COLUMN_NAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_DATUM, TABLE_NAME + "." + COLUMN_DATUM + " AS " + COLUMN_DATUM_FULL);
        //PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_MEDEWERKER_ID, TABLE_NAME + "." + COLUMN_MEDEWERKER_ID + " AS " + COLUMN_MEDEWERKER_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_LOCATIE, TABLE_NAME + "." + COLUMN_LOCATIE + " AS " + COLUMN_LOCATIE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_HELEDAG, TABLE_NAME + "." + COLUMN_HELEDAG + " AS " + COLUMN_HELEDAG_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BEGIN, TABLE_NAME + "." + COLUMN_BEGIN + " AS " + COLUMN_BEGIN_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EINDE, TABLE_NAME + "." + COLUMN_EINDE + " AS " + COLUMN_EINDE_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_NAAM + " text, "
            + COLUMN_DATUM + " text, "
            //+ COLUMN_MEDEWERKER_ID + "integer, "
            + COLUMN_LOCATIE + " text, "
            + COLUMN_HELEDAG + " integer default 0, "
            + COLUMN_BEGIN + " text, "
            + COLUMN_EINDE + " text "
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
