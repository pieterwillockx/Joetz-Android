package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class ContactpersoonTable {
    public static final String TABLE_NAME = "contactpersonen";

    public static final String COLUMN_NAAM = "naam";
    public static final String COLUMN_VOORNAAM = "voornaam";
    public static final String COLUMN_NAAMGEBOUW = "naamgebouw";
    public static final String COLUMN_STRAAT = "straat";
    public static final String COLUMN_HUISNUMMER = "huisnummer";
    public static final String COLUMN_BUS = "bus";
    public static final String COLUMN_GEMEENTE = "gemeente";
    public static final String COLUMN_POSTCODE = "postcode";
    public static final String COLUMN_TELEFOONNUMMER = "telefoonNummer";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AANSLUITNUMMER = "aansluitnummer";
    public static final String COLUMN_BETALEND = "betalend";
    public static final String COLUMN_OUDER = "ouder";
    public static final String COLUMN_RIJKSREGISTERNUMMER = "rijksregisternummer";

    public static final String COLUMN_NAAM_FULL = TABLE_NAME + "_" + COLUMN_NAAM;
    public static final String COLUMN_VOORNAAM_FULL = TABLE_NAME + "_" + COLUMN_VOORNAAM;
    public static final String COLUMN_NAAMGEBOUW_FULL = TABLE_NAME + "_" + COLUMN_NAAMGEBOUW;
    public static final String COLUMN_STRAAT_FULL = TABLE_NAME + "_" + COLUMN_STRAAT;
    public static final String COLUMN_HUISNUMMER_FULL = TABLE_NAME + "_" + COLUMN_HUISNUMMER;
    public static final String COLUMN_BUS_FULL = TABLE_NAME + "_" + COLUMN_BUS;
    public static final String COLUMN_GEMEENTE_FULL = TABLE_NAME + "_" + COLUMN_GEMEENTE;
    public static final String COLUMN_POSTCODE_FULL = TABLE_NAME + "_" + COLUMN_POSTCODE;
    public static final String COLUMN_TELEFOONNUMMER_FULL = TABLE_NAME + "_" + COLUMN_TELEFOONNUMMER;
    public static final String COLUMN_EMAIL_FULL = TABLE_NAME + "_" + COLUMN_EMAIL;
    public static final String COLUMN_AANSLUITNUMMER_FULL = TABLE_NAME + "_" + COLUMN_AANSLUITNUMMER;
    public static final String COLUMN_BETALEND_FULL = TABLE_NAME + "_" + COLUMN_BETALEND;
    public static final String COLUMN_OUDER_FULL = TABLE_NAME + "_" + COLUMN_OUDER;
    public static final String COLUMN_RIJKSREGISTERNUMMER_FULL = TABLE_NAME + "_" + COLUMN_RIJKSREGISTERNUMMER;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAM, TABLE_NAME + "." + COLUMN_NAAM + " AS " + COLUMN_NAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_VOORNAAM, TABLE_NAME + "." + COLUMN_VOORNAAM + " AS " + COLUMN_VOORNAAM_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAMGEBOUW, TABLE_NAME + "." + COLUMN_NAAMGEBOUW + " AS " + COLUMN_NAAMGEBOUW_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_STRAAT, TABLE_NAME + "." + COLUMN_STRAAT + " AS " + COLUMN_STRAAT_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_HUISNUMMER, TABLE_NAME + "." + COLUMN_HUISNUMMER + " AS " + COLUMN_HUISNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BUS, TABLE_NAME + "." + COLUMN_BUS + " AS " + COLUMN_BUS_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_GEMEENTE, TABLE_NAME + "." + COLUMN_GEMEENTE + " AS " + COLUMN_GEMEENTE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_POSTCODE, TABLE_NAME + "." + COLUMN_POSTCODE + " AS " + COLUMN_POSTCODE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_TELEFOONNUMMER, TABLE_NAME + "." + COLUMN_TELEFOONNUMMER + " AS " + COLUMN_TELEFOONNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EMAIL, TABLE_NAME + "." + COLUMN_EMAIL + " AS " + COLUMN_EMAIL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_AANSLUITNUMMER, TABLE_NAME + "." + COLUMN_AANSLUITNUMMER + " AS " + COLUMN_AANSLUITNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BETALEND, TABLE_NAME + "." + COLUMN_BETALEND + " AS " + COLUMN_BETALEND_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_OUDER, TABLE_NAME + "." + COLUMN_OUDER + " AS " + COLUMN_OUDER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_RIJKSREGISTERNUMMER, TABLE_NAME + "." + COLUMN_RIJKSREGISTERNUMMER + " AS " + COLUMN_RIJKSREGISTERNUMMER_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_NAAM + " text, "
            + COLUMN_VOORNAAM + " text, "
            + COLUMN_NAAMGEBOUW + " text, "
            + COLUMN_STRAAT + " text, "
            + COLUMN_HUISNUMMER + " integer, "
            + COLUMN_BUS + " text, "
            + COLUMN_GEMEENTE + " text, "
            + COLUMN_POSTCODE + " integer, "
            + COLUMN_TELEFOONNUMMER + " text, "
            + COLUMN_EMAIL + " text primary key, "
            + COLUMN_AANSLUITNUMMER + " text, "
            + COLUMN_BETALEND + " integer, "
            + COLUMN_RIJKSREGISTERNUMMER + " text, "
            + COLUMN_OUDER + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
