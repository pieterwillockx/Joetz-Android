package com.fabantowapi.joetz_android.model.api;

import android.database.Cursor;

import com.fabantowapi.joetz_android.database.ActiviteitTable;
import com.fabantowapi.joetz_android.database.UserActivityTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 18-8-2016.
 */
public class UserActivity {
    private int id;
    private String userId;
    private String activityId;

    public UserActivity(int id, String userId, String activityId) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
    }

    public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getActivityId() { return activityId; }

    public void setId(int id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public static List<UserActivity> constructListFromCursor(Cursor cursor){
        List<UserActivity> userActivities = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do{
                userActivities.add(UserActivity.constructFromCursor(cursor));
            }
            while(cursor.moveToNext());
        }

        return userActivities;
    }

    public static UserActivity constructFromCursor(Cursor cursor){
        int idIndex = cursor.getColumnIndex(UserActivityTable.COLUMN_ID_FULL);
        int userIdIndex = cursor.getColumnIndex(UserActivityTable.COLUMN_USER_ID_FULL);
        int activityIdIndex = cursor.getColumnIndex(UserActivityTable.COLUMN_ACTIVITY_ID_FULL);

        if(cursor.getPosition() == -1){
            cursor.moveToFirst();
        }

        int id = cursor.getInt(idIndex);
        String userId = cursor.getString(userIdIndex);
        String activityId = cursor.getString(activityIdIndex);

        return new UserActivity(id, userId, activityId);
    }
}
