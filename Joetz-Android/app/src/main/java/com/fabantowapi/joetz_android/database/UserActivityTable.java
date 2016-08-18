package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pieter on 17-8-2016.
 */
public class UserActivityTable {
    public static final String TABLE_NAME = "user_activity";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ACTIVITY_ID = "activity_id";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_USER_ID_FULL = TABLE_NAME + "_" + COLUMN_USER_ID;
    public static final String COLUMN_ACTIVITY_ID_FULL = TABLE_NAME + "_" + COLUMN_ACTIVITY_ID;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();

        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_USER_ID, TABLE_NAME + "." + COLUMN_USER_ID + " AS " + COLUMN_USER_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ACTIVITY_ID, TABLE_NAME + "." + COLUMN_ACTIVITY_ID + " AS " + COLUMN_ACTIVITY_ID_FULL);
    }

    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER_ID + " text, "
            + COLUMN_ACTIVITY_ID + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
