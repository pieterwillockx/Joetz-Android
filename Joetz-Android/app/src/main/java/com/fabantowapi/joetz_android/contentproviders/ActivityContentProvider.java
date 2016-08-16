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

import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.DatabaseHelper;

/**
 * Created by Pieter on 16-8-2016.
 */
public class ActivityContentProvider extends ContentProvider {
    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.fabantowapi.joetz_android.contentproviders.ActivityContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int ACTIVITIES = 1;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, ACTIVITIES);
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
            case ACTIVITIES:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ActiviteitTable.TABLE_NAME);
        queryBuilder.setProjectionMap(ActiviteitTable.PROJECTION_MAP);

        switch(URI_MATCHER.match(uri)){
            case ACTIVITIES:
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
            case ACTIVITIES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowId = db.replace(ActiviteitTable.TABLE_NAME, null, values);
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
            case ACTIVITIES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsDeleted = db.delete(ActiviteitTable.TABLE_NAME, selection, selectionArgs);
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
            case ACTIVITIES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsUpdated = db.update(ActiviteitTable.TABLE_NAME, values, selection, selectionArgs);
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
            case ACTIVITIES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                DatabaseUtils.InsertHelper inserter = new DatabaseUtils.InsertHelper(db, ActiviteitTable.TABLE_NAME);

                db.beginTransaction();
                try{
                    if(values != null){
                        for(ContentValues cv : values){
                            if(cv != null){
                                inserter.prepareForInsert();
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_ID), cv.getAsString(ActiviteitTable.COLUMN_ID));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_NAAM), cv.getAsString(ActiviteitTable.COLUMN_NAAM));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_DATUM), cv.getAsInteger(ActiviteitTable.COLUMN_DATUM));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_LOCATIE), cv.getAsString(ActiviteitTable.COLUMN_LOCATIE));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_HELEDAG), cv.getAsInteger(ActiviteitTable.COLUMN_HELEDAG));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_BEGIN), cv.getAsInteger(ActiviteitTable.COLUMN_BEGIN));
                                inserter.bind(inserter.getColumnIndex(ActiviteitTable.COLUMN_EINDE), cv.getAsString(ActiviteitTable.COLUMN_EINDE));
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
