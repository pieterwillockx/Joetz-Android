package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class InschrijvingenTable {
    public static final String TABLE_NAME = "inschrijvingen";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXTRAINFORMATIE = "extraInformatie";
    public static final String COLUMN_GOEDGEKEURD = "goedgekeurd";
    public static final String COLUMN_BETAALD = "betaald";
    public static final String COLUMN_LID_ID = "lid_id";
    public static final String COLUMN_KAMP_ID = "kamp_id";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_EXTRAINFORMATIE_FULL = TABLE_NAME + "_" + COLUMN_EXTRAINFORMATIE;
    public static final String COLUMN_GOEDGEKEURD_FULL = TABLE_NAME + "_" + COLUMN_GOEDGEKEURD;
    public static final String COLUMN_BETAALD_FULL = TABLE_NAME + "_" + COLUMN_BETAALD;
    public static final String COLUMN_LID_ID_FULL = TABLE_NAME + "_" + COLUMN_LID_ID;
    public static final String COLUMN_KAMP_ID_FULL = TABLE_NAME + "_" + COLUMN_KAMP_ID;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EXTRAINFORMATIE, TABLE_NAME + "." + COLUMN_EXTRAINFORMATIE + " AS " + COLUMN_EXTRAINFORMATIE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_GOEDGEKEURD, TABLE_NAME + "." + COLUMN_GOEDGEKEURD + " AS " + COLUMN_GOEDGEKEURD_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BETAALD, TABLE_NAME + "." + COLUMN_BETAALD + " AS " + COLUMN_BETAALD_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_LID_ID, TABLE_NAME + "." + COLUMN_LID_ID + " AS " + COLUMN_LID_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_KAMP_ID, TABLE_NAME + "." + COLUMN_KAMP_ID + " AS " + COLUMN_KAMP_ID_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_EXTRAINFORMATIE + " text, "
            + COLUMN_GOEDGEKEURD + " integer, "
            + COLUMN_BETAALD + " integer, "
            + COLUMN_LID_ID + " integer, "
            + COLUMN_KAMP_ID + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
