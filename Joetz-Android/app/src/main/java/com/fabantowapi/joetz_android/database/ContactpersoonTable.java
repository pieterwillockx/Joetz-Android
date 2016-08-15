package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class ContactpersoonTable {
    public static final String TABLE_NAME = "contactpersonen";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAAM = "naam";
    public static final String COLUMN_VOORNAAM = "voornaam";
    public static final String COLUMN_TELEFOONNUMMER = "telefoonNummer";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AANSLUITNUMMER = "aansluitnummer";
    public static final String COLUMN_BETALEND = "betalend";
    public static final String COLUMN_OUDER = "ouder";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_NAAM_FULL = TABLE_NAME + "_" + COLUMN_NAAM;
    public static final String COLUMN_VOORNAAM_FULL = TABLE_NAME + "_" + COLUMN_VOORNAAM;
    public static final String COLUMN_TELEFOONNUMMER_FULL = TABLE_NAME + "_" + COLUMN_TELEFOONNUMMER;
    public static final String COLUMN_EMAIL_FULL = TABLE_NAME + "_" + COLUMN_EMAIL;
    public static final String COLUMN_AANSLUITNUMMER_FULL = TABLE_NAME + "_" + COLUMN_AANSLUITNUMMER;
    public static final String COLUMN_BETALEND_FULL = TABLE_NAME + "_" + COLUMN_BETALEND;
    public static final String COLUMN_OUDER_FULL = TABLE_NAME + "_" + COLUMN_OUDER;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAM, TABLE_NAME + "." + COLUMN_NAAM + " AS " + COLUMN_NAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_VOORNAAM, TABLE_NAME + "." + COLUMN_VOORNAAM + " AS " + COLUMN_VOORNAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_TELEFOONNUMMER, TABLE_NAME + "." + COLUMN_TELEFOONNUMMER + " AS " + COLUMN_TELEFOONNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EMAIL, TABLE_NAME + "." + COLUMN_EMAIL + " AS " + COLUMN_EMAIL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_AANSLUITNUMMER, TABLE_NAME + "." + COLUMN_AANSLUITNUMMER + " AS " + COLUMN_AANSLUITNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BETALEND, TABLE_NAME + "." + COLUMN_BETALEND + " AS " + COLUMN_BETALEND_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_OUDER, TABLE_NAME + "." + COLUMN_OUDER + " AS " + COLUMN_OUDER_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAAM + " text, "
            + COLUMN_VOORNAAM + " text, "
            + COLUMN_TELEFOONNUMMER + " text, "
            + COLUMN_EMAIL + " text, "
            + COLUMN_AANSLUITNUMMER + " text, "
            + COLUMN_BETALEND + " integer, "
            + COLUMN_OUDER + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
