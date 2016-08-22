package com.fabantowapi.joetz_android.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.fabantowapi.joetz_android.database.ArticleTable;
import com.fabantowapi.joetz_android.database.DatabaseHelper;

/**
 * Created by Anton Rooseleer on 22-8-2016.
 */
public class ArticleContentProvider extends ContentProvider {

    private DatabaseHelper databaseHelper;

    private static final String PROVIDER_NAME = "com.fabantowapi.joetz_android.contentproviders.ArticleContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/");
    private static final int ARTICLES = 1;
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, null, ARTICLES);
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
            case ARTICLES:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME;
        }

        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ArticleTable.TABLE_NAME);
        queryBuilder.setProjectionMap(ArticleTable.PROJECTION_MAP);

        switch(URI_MATCHER.match(uri)){
            case ARTICLES:
                break;

            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(this.getContext().getContentResolver(), CONTENT_URI);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        int rowsUpdated;

        switch(URI_MATCHER.match(uri)){
            case ARTICLES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsUpdated = db.update(ArticleTable.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsUpdated > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }

        return rowsUpdated;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values){
        long rowId;

        switch(URI_MATCHER.match(uri)){
            case ARTICLES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowId = db.replace(ArticleTable.TABLE_NAME, null, values);
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
            case ARTICLES:
                SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
                rowsDeleted = db.delete(ArticleTable.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if(rowsDeleted > 0){
            this.getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }

        return rowsDeleted;
    }

}
