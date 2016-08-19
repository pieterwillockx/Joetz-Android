package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class CampTable {
    public static final String TABLE_NAME = "camps";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAAM = "naam";
    public static final String COLUMN_OMSCHIRIJVING = "omschrijving";
    public static final String COLUMN_STARTDATUM = "startDatum";
    public static final String COLUMN_EINDDATUM = "eindDatum";
    public static final String COLUMN_AANTALDAGEN = "aantalDagen";
    public static final String COLUMN_AANTALNACHTEN = "aantalNachten";
    public static final String COLUMN_VERVOER = "vervoer";
    public static final String COLUMN_PRIJS = "prijs";
    public static final String COLUMN_MAXLEEFTIJD = "maxLeeftijd";
    public static final String COLUMN_MINLEEFTIJD = "minLeeftijd";
    public static final String COLUMN_MAXDEELNEMERS = "maxDeelnemers";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_SFEERFOTO = "sfeerfoto";
    public static final String COLUMN_NAAMGEBOUW = "naamgebouw";
    public static final String COLUMN_STRAAT = "straat";
    public static final String COLUMN_HUISNUMMER = "huisnummer";
    public static final String COLUMN_BUS = "bus";
    public static final String COLUMN_GEMEENTE = "gemeente";
    public static final String COLUMN_POSTCODE = "postcode";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_NAAM_FULL = TABLE_NAME + "_" + COLUMN_NAAM;
    public static final String COLUMN_OMSCHIRIJVING_FULL = TABLE_NAME + "_" + COLUMN_OMSCHIRIJVING;
    public static final String COLUMN_STARTDATUM_FULL = TABLE_NAME + "_" + COLUMN_STARTDATUM;
    public static final String COLUMN_EINDDATUM_FULL = TABLE_NAME + "_" + COLUMN_EINDDATUM;
    public static final String COLUMN_AANTALDAGEN_FULL = TABLE_NAME + "_" + COLUMN_AANTALDAGEN;
    public static final String COLUMN_AANTALNACHTEN_FULL = TABLE_NAME + "_" + COLUMN_AANTALNACHTEN;
    public static final String COLUMN_VERVOER_FULL = TABLE_NAME + "_" + COLUMN_VERVOER;
    public static final String COLUMN_PRIJS_FULL = TABLE_NAME + "_" + COLUMN_PRIJS;
    public static final String COLUMN_MAXLEEFTIJD_FULL = TABLE_NAME + "_" + COLUMN_MAXLEEFTIJD;
    public static final String COLUMN_MINLEEFTIJD_FULL = TABLE_NAME + "_" + COLUMN_MINLEEFTIJD;
    public static final String COLUMN_MAXDEELNEMERS_FULL = TABLE_NAME + "_" + COLUMN_MAXDEELNEMERS;
    public static final String COLUMN_CONTACT_FULL = TABLE_NAME + "_" + COLUMN_CONTACT;
    public static final String COLUMN_SFEERFOTO_FULL = TABLE_NAME + "_" + COLUMN_SFEERFOTO;
    public static final String COLUMN_NAAMGEBOUW_FULL = TABLE_NAME + "_" + COLUMN_NAAMGEBOUW;
    public static final String COLUMN_STRAAT_FULL = TABLE_NAME + "_" + COLUMN_STRAAT;
    public static final String COLUMN_HUISNUMMER_FULL = TABLE_NAME + "_" + COLUMN_HUISNUMMER;
    public static final String COLUMN_BUS_FULL = TABLE_NAME + "_" + COLUMN_BUS;
    public static final String COLUMN_GEMEENTE_FULL = TABLE_NAME + "_" + COLUMN_GEMEENTE;
    public static final String COLUMN_POSTCODE_FULL = TABLE_NAME + "_" + COLUMN_POSTCODE;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAM, TABLE_NAME + "." + COLUMN_NAAM + " AS " + COLUMN_NAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_OMSCHIRIJVING, TABLE_NAME + "." + COLUMN_OMSCHIRIJVING + " AS " + COLUMN_OMSCHIRIJVING_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_STARTDATUM, TABLE_NAME + "." + COLUMN_STARTDATUM + " AS " + COLUMN_STARTDATUM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EINDDATUM, TABLE_NAME + "." + COLUMN_EINDDATUM + " AS " + COLUMN_EINDDATUM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_AANTALDAGEN, TABLE_NAME + "." + COLUMN_AANTALDAGEN + " AS " + COLUMN_AANTALDAGEN_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_AANTALNACHTEN, TABLE_NAME + "." + COLUMN_AANTALNACHTEN + " AS " + COLUMN_AANTALNACHTEN_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_VERVOER, TABLE_NAME + "." + COLUMN_VERVOER + " AS " + COLUMN_VERVOER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_PRIJS, TABLE_NAME + "." + COLUMN_PRIJS + " AS " + COLUMN_PRIJS_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_MAXLEEFTIJD, TABLE_NAME + "." + COLUMN_MAXLEEFTIJD + " AS " + COLUMN_MAXLEEFTIJD_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_MINLEEFTIJD, TABLE_NAME + "." + COLUMN_MINLEEFTIJD + " AS " + COLUMN_MINLEEFTIJD_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_MAXDEELNEMERS, TABLE_NAME + "." + COLUMN_MAXDEELNEMERS + " AS " +COLUMN_MAXDEELNEMERS_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_CONTACT , TABLE_NAME + "." + COLUMN_CONTACT + " AS " + COLUMN_CONTACT_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_SFEERFOTO, TABLE_NAME + "." + COLUMN_SFEERFOTO + " AS " + COLUMN_SFEERFOTO_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAMGEBOUW, TABLE_NAME + "." + COLUMN_NAAMGEBOUW + " AS " + COLUMN_NAAMGEBOUW_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_STRAAT, TABLE_NAME + "." + COLUMN_STRAAT + " AS " + COLUMN_STRAAT_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_HUISNUMMER, TABLE_NAME + "." + COLUMN_HUISNUMMER + " AS " + COLUMN_HUISNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BUS, TABLE_NAME + "." + COLUMN_BUS + " AS " + COLUMN_BUS_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_GEMEENTE, TABLE_NAME + "." + COLUMN_GEMEENTE + " AS " + COLUMN_GEMEENTE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_POSTCODE, TABLE_NAME + "." + COLUMN_POSTCODE + " AS " + COLUMN_POSTCODE_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_NAAM + " text, "
            + COLUMN_OMSCHIRIJVING + " text, "
            + COLUMN_STARTDATUM + " text, "
            + COLUMN_EINDDATUM + " text, "
            + COLUMN_AANTALDAGEN + " integer, "
            + COLUMN_AANTALNACHTEN + " integer, "
            + COLUMN_VERVOER + " text, "
            + COLUMN_PRIJS + " integer, "
            + COLUMN_MAXLEEFTIJD + " integer, "
            + COLUMN_MINLEEFTIJD + " integer, "
            + COLUMN_MAXDEELNEMERS + " integer, "
            + COLUMN_CONTACT + " text, "
            + COLUMN_SFEERFOTO + " text, "
            + COLUMN_NAAMGEBOUW + " text, "
            + COLUMN_STRAAT + " text, "
            + COLUMN_HUISNUMMER + " integer, "
            + COLUMN_BUS + " text, "
            + COLUMN_GEMEENTE + " text, "
            + COLUMN_POSTCODE + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
