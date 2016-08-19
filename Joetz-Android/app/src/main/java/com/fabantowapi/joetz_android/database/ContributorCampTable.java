package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 18-8-2016.
 */
public class ContributorCampTable {
    public static final String TABLE_NAME = "contributor_camp";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTRIBUTOR_ID = "contributor_id";
    public static final String COLUMN_CAMP_ID = "camp_id";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_CONTRIBUTOR_ID_FULL = TABLE_NAME + "_" + COLUMN_CONTRIBUTOR_ID;
    public static final String COLUMN_CAMP_ID_FULL = TABLE_NAME + "_" + COLUMN_CAMP_ID;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();

        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_CONTRIBUTOR_ID, TABLE_NAME + "." + COLUMN_CONTRIBUTOR_ID + " AS " + COLUMN_CONTRIBUTOR_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_CAMP_ID, TABLE_NAME + "." + COLUMN_CAMP_ID + " AS " + COLUMN_CAMP_ID_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CONTRIBUTOR_ID + " text, "
            + COLUMN_CAMP_ID + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
