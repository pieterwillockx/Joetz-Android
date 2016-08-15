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

import com.fabantowapi.joetz_android.database.ContactpersoonTable;
import com.fabantowapi.joetz_android.database.DatabaseHelper;

/**
 * Created by Pieter on 15-8-2016.
 */
public class ContactpersoonContentProvider extends ContentProvider {
    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.fabantowapi.joetz_android.contentproviders.ContactpersoonContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int CONTACTPERSONEN = 1;
    private static final UriMatcher URI_MATCHER;

    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, CONTACTPERSONEN);
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
            case CONTACTPERSONEN:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ContactpersoonTable.TABLE_NAME);
        queryBuilder.setProjectionMap(ContactpersoonTable.PROJECTION_MAP);

        switch(URI_MATCHER.match(uri)){
            case CONTACTPERSONEN:
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
            case CONTACTPERSONEN:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowId = db.replace(ContactpersoonTable.TABLE_NAME, null, values);
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
            case CONTACTPERSONEN:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsDeleted = db.delete(ContactpersoonTable.TABLE_NAME, selection, selectionArgs);
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
            case CONTACTPERSONEN:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsUpdated = db.update(ContactpersoonTable.TABLE_NAME, values, selection, selectionArgs);
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
            case CONTACTPERSONEN:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                DatabaseUtils.InsertHelper inserter = new DatabaseUtils.InsertHelper(db, ContactpersoonTable.TABLE_NAME);

                db.beginTransaction();
                try{
                    if(values != null){
                        for(ContentValues cv : values){
                            if(cv != null){
                                inserter.prepareForInsert();
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_ID), cv.getAsInteger(ContactpersoonTable.COLUMN_ID));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_NAAM), cv.getAsString(ContactpersoonTable.COLUMN_EMAIL));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_VOORNAAM), cv.getAsString(ContactpersoonTable.COLUMN_VOORNAAM));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_NAAMGEBOUW), cv.getAsString(ContactpersoonTable.COLUMN_NAAMGEBOUW));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_STRAAT), cv.getAsString(ContactpersoonTable.COLUMN_STRAAT));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_HUISNUMMER), cv.getAsInteger(ContactpersoonTable.COLUMN_HUISNUMMER));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_BUS), cv.getAsString(ContactpersoonTable.COLUMN_BUS));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_GEMEENTE), cv.getAsString(ContactpersoonTable.COLUMN_GEMEENTE));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_POSTCODE), cv.getAsInteger(ContactpersoonTable.COLUMN_POSTCODE));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_TELEFOONNUMMER), cv.getAsString(ContactpersoonTable.COLUMN_TELEFOONNUMMER));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_EMAIL), cv.getAsString(ContactpersoonTable.COLUMN_EMAIL));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_AANSLUITNUMMER), cv.getAsString(ContactpersoonTable.COLUMN_AANSLUITNUMMER));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_BETALEND), cv.getAsBoolean(ContactpersoonTable.COLUMN_BETALEND));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_OUDER), cv.getAsBoolean(ContactpersoonTable.COLUMN_OUDER));
                                inserter.bind(inserter.getColumnIndex(ContactpersoonTable.COLUMN_RIJKSREGISTERNUMMER), cv.getAsString(ContactpersoonTable.COLUMN_RIJKSREGISTERNUMMER));
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
