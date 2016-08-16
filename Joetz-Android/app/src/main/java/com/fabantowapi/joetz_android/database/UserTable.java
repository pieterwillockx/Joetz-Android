package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 14-8-2016.
 */
public class UserTable {
    public static final String TABLE_NAME = "users";

    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_NAAMGEBOUW = "naamgebouw";
    public static final String COLUMN_STRAAT = "straat";
    public static final String COLUMN_HUISNUMMER = "huisnummer";
    public static final String COLUMN_BUS = "bus";
    public static final String COLUMN_GEMEENTE = "gemeente";
    public static final String COLUMN_POSTCODE = "postcode";
    public static final String COLUMN_CONTACTPERSOON1_EMAIL = "contactpersoon1_email";
    public static final String COLUMN_CONTACTPERSOON2_EMAIL = "contactpersoon2_email";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_DATE_JOINED = "date_joined";
    public static final String COLUMN_RIJKSREGISTERNUMMER = "rijksregisternummer";

    public static final String COLUMN_EMAIL_FULL = TABLE_NAME + "_" + COLUMN_EMAIL;
    public static final String COLUMN_USERNAME_FULL = TABLE_NAME + "_" + COLUMN_USERNAME;
    public static final String COLUMN_FIRSTNAME_FULL = TABLE_NAME + "_" + COLUMN_FIRSTNAME;
    public static final String COLUMN_LASTNAME_FULL = TABLE_NAME + "_" + COLUMN_LASTNAME;
    public static final String COLUMN_NAAMGEBOUW_FULL = TABLE_NAME + "_" + COLUMN_NAAMGEBOUW;
    public static final String COLUMN_STRAAT_FULL = TABLE_NAME + "_" + COLUMN_STRAAT;
    public static final String COLUMN_HUISNUMMER_FULL = TABLE_NAME + "_" + COLUMN_HUISNUMMER;
    public static final String COLUMN_BUS_FULL = TABLE_NAME + "_" + COLUMN_BUS;
    public static final String COLUMN_GEMEENTE_FULL = TABLE_NAME + "_" + COLUMN_GEMEENTE;
    public static final String COLUMN_POSTCODE_FULL = TABLE_NAME + "_" + COLUMN_POSTCODE;
    public static final String COLUMN_CONTACTPERSOON1_EMAIL_FULL = TABLE_NAME + "_" + COLUMN_CONTACTPERSOON1_EMAIL;
    public static final String COLUMN_CONTACTPERSOON2_EMAIL_FULL = TABLE_NAME + "_" + COLUMN_CONTACTPERSOON2_EMAIL;
    public static final String COLUMN_ROLE_FULL = TABLE_NAME + "_" + COLUMN_ROLE;
    public static final String COLUMN_DATE_JOINED_FULL = TABLE_NAME + "_" + COLUMN_DATE_JOINED;
    public static final String COLUMN_RIJKSREGISTERNUMMER_FULL = TABLE_NAME + "_" + COLUMN_RIJKSREGISTERNUMMER;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_EMAIL, TABLE_NAME + "." + COLUMN_EMAIL + " AS " + COLUMN_EMAIL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_USERNAME, TABLE_NAME + "." + COLUMN_USERNAME + " AS " + COLUMN_USERNAME_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_FIRSTNAME, TABLE_NAME + "." + COLUMN_FIRSTNAME + " AS " + COLUMN_FIRSTNAME_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_LASTNAME, TABLE_NAME + "." + COLUMN_LASTNAME + " AS " + COLUMN_LASTNAME_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_NAAMGEBOUW, TABLE_NAME + "." + COLUMN_NAAMGEBOUW + " AS " + COLUMN_NAAMGEBOUW_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_STRAAT, TABLE_NAME + "." + COLUMN_STRAAT + " AS " + COLUMN_STRAAT_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_HUISNUMMER, TABLE_NAME + "." + COLUMN_HUISNUMMER + " AS " + COLUMN_HUISNUMMER_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_BUS, TABLE_NAME + "." + COLUMN_BUS + " AS " + COLUMN_BUS_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_GEMEENTE, TABLE_NAME + "." + COLUMN_GEMEENTE + " AS " + COLUMN_GEMEENTE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_POSTCODE, TABLE_NAME + "." + COLUMN_POSTCODE + " AS " + COLUMN_POSTCODE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_CONTACTPERSOON1_EMAIL, TABLE_NAME + "." + COLUMN_CONTACTPERSOON1_EMAIL + " AS " + COLUMN_CONTACTPERSOON1_EMAIL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_CONTACTPERSOON2_EMAIL, TABLE_NAME + "." + COLUMN_CONTACTPERSOON2_EMAIL + " AS " + COLUMN_CONTACTPERSOON2_EMAIL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ROLE, TABLE_NAME + "." + COLUMN_ROLE + " AS " + COLUMN_ROLE_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_DATE_JOINED, TABLE_NAME + "." + COLUMN_DATE_JOINED + " AS " + COLUMN_DATE_JOINED_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_RIJKSREGISTERNUMMER, TABLE_NAME + "." + COLUMN_RIJKSREGISTERNUMMER + " AS " + COLUMN_RIJKSREGISTERNUMMER_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_EMAIL + " text primary key, "
            + COLUMN_USERNAME + " text, "
            + COLUMN_FIRSTNAME + " text, "
            + COLUMN_LASTNAME + " text, "
            + COLUMN_NAAMGEBOUW + " text, "
            + COLUMN_STRAAT + " text, "
            + COLUMN_HUISNUMMER + " integer, "
            + COLUMN_BUS + " text, "
            + COLUMN_GEMEENTE + " text, "
            + COLUMN_POSTCODE + " integer, "
            + COLUMN_CONTACTPERSOON1_EMAIL + " string, "
            + COLUMN_CONTACTPERSOON2_EMAIL + " string, "
            + COLUMN_ROLE + " text, "
            + COLUMN_RIJKSREGISTERNUMMER + " text, "
            + COLUMN_DATE_JOINED + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
