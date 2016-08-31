package com.fabantowapi.joetz_android.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.fabantowapi.joetz_android.database.DatabaseHelper;
import com.fabantowapi.joetz_android.database.CampTable;

/**
 * Created by Pieter on 18-8-2016.
 */
public class CampContentProvider extends ContentProvider {
    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.fabantowapi.joetz_android.contentproviders.CampContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int CAMPS = 1;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, CAMPS);
    }

    @Override
    public boolean onCreate(){
        this.databaseHelper = new DatabaseHelper(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri){
        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CampTable.TABLE_NAME);
        queryBuilder.setProjectionMap(CampTable.PROJECTION_MAP);

        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                break;

            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(this.getContext().getContentResolver(), CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values){
        long rowId;

        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowId = db.replace(CampTable.TABLE_NAME, null, values);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowId > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
            return Uri.withAppendedPath(CONTENT_URI, String.valueOf(rowId));
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        int rowsDeleted;

        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsDeleted = db.delete(CampTable.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsDeleted > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        int rowsUpdated;

        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsUpdated = db.update(CampTable.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsUpdated > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values){
        int rowsInserted = 0;

        switch(URI_MATCHER.match(uri)){
            case CAMPS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                DatabaseUtils.InsertHelper inserter = new DatabaseUtils.InsertHelper(db, CampTable.TABLE_NAME);

                db.beginTransaction();
                try{
                    if(values != null){
                        for(ContentValues cv : values){
                            if(cv != null){
                                inserter.prepareForInsert();
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_ID), cv.getAsString(CampTable.COLUMN_ID));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_NAAM), cv.getAsString(CampTable.COLUMN_NAAM));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_OMSCHIRIJVING), cv.getAsString(CampTable.COLUMN_OMSCHIRIJVING));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_STARTDATUM), cv.getAsString(CampTable.COLUMN_STARTDATUM));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_EINDDATUM), cv.getAsInteger(CampTable.COLUMN_EINDDATUM));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_AANTALDAGEN), cv.getAsString(CampTable.COLUMN_AANTALDAGEN));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_AANTALNACHTEN), cv.getAsString(CampTable.COLUMN_AANTALNACHTEN));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_VERVOER), cv.getAsInteger(CampTable.COLUMN_VERVOER));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_PRIJS), cv.getAsString(CampTable.COLUMN_PRIJS));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_MAXDEELNEMERS), cv.getAsString(CampTable.COLUMN_MAXDEELNEMERS));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_MINLEEFTIJD), cv.getAsString(CampTable.COLUMN_MINLEEFTIJD));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_MAXDEELNEMERS), cv.getAsBoolean(CampTable.COLUMN_MAXDEELNEMERS));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_CONTACT), cv.getAsBoolean(CampTable.COLUMN_CONTACT));
                                inserter.bind(inserter.getColumnIndex(CampTable.COLUMN_SFEERFOTO), cv.getAsString(CampTable.COLUMN_SFEERFOTO));
                                long rowId = inserter.execute();

                                if(rowId != -1){
                                    rowsInserted++;
                                }
                            }
                        }
                    }

                    db.setTransactionSuccessful();
                }
                catch(Exception e){
                    rowsInserted = 0;
                }
                finally{
                    db.endTransaction();
                    inserter.close();
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsInserted > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }

        return rowsInserted;
    }
}
