package com.fabantowapi.joetz_android.model;

import android.database.Cursor;

import com.fabantowapi.joetz_android.database.UserCampTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class UserCamp {
    private int id;
    private String userId;
    private String campId;

    public UserCamp(int id, String userId, String campId) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
    }

    public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getCampId() { return campId; }

    public void setId(int id) { this.id = id; }

    public static List<UserCamp> constructListFromCursor(Cursor cursor){
        List<UserCamp> userCamps = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                userCamps.add(UserCamp.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return userCamps;
    }

    public static UserCamp constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(UserCampTable.COLUMN_ID_FULL);
        int userIdIndex = cursor.getColumnIndex(UserCampTable.COLUMN_USER_ID_FULL);
        int campIdIndex = cursor.getColumnIndex(UserCampTable.COLUMN_CAMP_ID_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        int id = cursor.getInt(idIndex);
        String userId = cursor.getString(userIdIndex);
        String campId = cursor.getString(campIdIndex);

        return new UserCamp(id, userId, campId);
    }
}
