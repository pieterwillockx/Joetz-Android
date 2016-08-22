package com.fabantowapi.joetz_android.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton Rooseleer on 22-8-2016.
 */
public class ArticleTable {

    public static final String TABLE_NAME = "artikels";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITEL = "titel";
    public static final String COLUMN_TEKST= "tekst";
    public static final String  COLUMN_FOTO_URL ="fotoUrl";

    public static final String COLUMN_ID_FULL = TABLE_NAME + "_" + COLUMN_ID;
    public static final String COLUMN_TITEL_FULL = TABLE_NAME + "_" + COLUMN_TITEL;
    public static final String COLUMN_TEKST_FULL = TABLE_NAME + "_" + COLUMN_TEKST;
    public static final String  COLUMN_FOTO_URL_FULL = TABLE_NAME + "_" + COLUMN_FOTO_URL;

    public static final Map<String, String> PROJECTION_MAP;

    static{
        PROJECTION_MAP = new HashMap<>();
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_ID, TABLE_NAME + "." + COLUMN_ID + " AS " + COLUMN_ID_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_TITEL, TABLE_NAME + "." + COLUMN_TITEL + " AS " + COLUMN_TITEL_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_TEKST, TABLE_NAME + "." + COLUMN_TEKST + " AS " + COLUMN_TEKST_FULL);
        PROJECTION_MAP.put(TABLE_NAME + "." + COLUMN_FOTO_URL, TABLE_NAME + "." + COLUMN_FOTO_URL + " AS " + COLUMN_FOTO_URL_FULL);

}
    private static final String CREATE_TABLE = "create table IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " text primary key, "
            + COLUMN_TITEL + " text, "
            + COLUMN_TEKST + " text, "
            + COLUMN_FOTO_URL + " text "
            + ");";

    public static void onCreate(SQLiteDatabase database) { database.execSQL(CREATE_TABLE); }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}

