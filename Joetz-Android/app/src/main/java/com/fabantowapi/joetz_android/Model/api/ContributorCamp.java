package com.fabantowapi.joetz_android.model.api;

import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ContributorCampTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class ContributorCamp {
    private int id;
    private String userId;
    private String campId;

    public ContributorCamp(int id, String userId, String campId) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
    }

    public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getCampId() { return campId; }

    public void setId(int id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setCampId(String campId) { this.campId = campId; }

    public static List<ContributorCamp> constructListFromCursor(Cursor cursor){
        List<ContributorCamp> userActivities = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                userActivities.add(ContributorCamp.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return userActivities;
    }

    public static ContributorCamp constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(ContributorCampTable.COLUMN_ID_FULL);
        int userIdIndex = cursor.getColumnIndex(ContributorCampTable.COLUMN_CONTRIBUTOR_ID_FULL);
        int activityIdIndex = cursor.getColumnIndex(ContributorCampTable.COLUMN_CAMP_ID_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        int id = cursor.getInt(idIndex);
        String userId = cursor.getString(userIdIndex);
        String activityId = cursor.getString(activityIdIndex);

        return new ContributorCamp(id, userId, activityId);
    }
}
