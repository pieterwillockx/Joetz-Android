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
import android.util.Log;

import com.fabantowapi.joetz_android.database.DatabaseHelper;
import com.fabantowapi.joetz_android.database.UserTable;

/**
 * Created by Pieter on 15-8-2016.
 */
public class UserContentProvider extends ContentProvider{
    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.fabantowapi.joetz_android.contentproviders.UserContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int USERS = 1;
    private static final UriMatcher URI_MATCHER;

    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, USERS);
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
            case USERS:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(UserTable.TABLE_NAME);
        queryBuilder.setProjectionMap(UserTable.PROJECTION_MAP);

        switch(URI_MATCHER.match(uri)){
            case USERS:
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
            case USERS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowId = db.replace(UserTable.TABLE_NAME, null, values);
                break;

            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }

        if(rowId > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);

            // log database
            Log.d("UserContentProvider", "Printing DB after insert...");
            printDatabase();

            return Uri.withAppendedPath(CONTENT_URI, String.valueOf(rowId));
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        int rowsDeleted;

        switch(URI_MATCHER.match(uri)){
            case USERS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsDeleted = db.delete(UserTable.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsDeleted > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);

            // log database
            Log.d("UserContentProvider", "Printing DB after delete...");
            printDatabase();
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        int rowsUpdated;

        switch(URI_MATCHER.match(uri)){
            case USERS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsUpdated = db.update(UserTable.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsUpdated > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);

            // log database
            Log.d("UserContentProvider", "Printing DB after update...");
            printDatabase();
        }

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values){
        int rowsInserted = 0;

        switch(URI_MATCHER.match(uri)){
            case USERS:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                DatabaseUtils.InsertHelper inserter = new DatabaseUtils.InsertHelper(db, UserTable.TABLE_NAME);

                db.beginTransaction();
                try{
                    if(values != null){
                        for(ContentValues cv : values){
                            if(cv != null){
                                inserter.prepareForInsert();
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_EMAIL), cv.getAsString(UserTable.COLUMN_EMAIL));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_USERNAME), cv.getAsString(UserTable.COLUMN_USERNAME));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_FIRSTNAME), cv.getAsString(UserTable.COLUMN_FIRSTNAME));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_LASTNAME), cv.getAsString(UserTable.COLUMN_LASTNAME));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_NAAMGEBOUW), cv.getAsString(UserTable.COLUMN_NAAMGEBOUW));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_STRAAT), cv.getAsString(UserTable.COLUMN_STRAAT));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_HUISNUMMER), cv.getAsInteger(UserTable.COLUMN_HUISNUMMER));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_BUS), cv.getAsString(UserTable.COLUMN_BUS));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_GEMEENTE), cv.getAsString(UserTable.COLUMN_GEMEENTE));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_POSTCODE), cv.getAsInteger(UserTable.COLUMN_POSTCODE));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_EMAIL), cv.getAsString(UserTable.COLUMN_EMAIL));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_CONTACTPERSOON1_EMAIL), cv.getAsInteger(UserTable.COLUMN_CONTACTPERSOON1_EMAIL));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_CONTACTPERSOON2_EMAIL), cv.getAsInteger(UserTable.COLUMN_CONTACTPERSOON2_EMAIL));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_ROLE), cv.getAsString(UserTable.COLUMN_ROLE));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_DATE_JOINED), cv.getAsString(UserTable.COLUMN_DATE_JOINED));
                                inserter.bind(inserter.getColumnIndex(UserTable.COLUMN_RIJKSREGISTERNUMMER), cv.getAsString(UserTable.COLUMN_RIJKSREGISTERNUMMER));

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

    public void printDatabase(){
        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        Log.d("UserContentProvider", this.databaseHelper.getTableAsString(db, UserTable.TABLE_NAME));
    }
}
