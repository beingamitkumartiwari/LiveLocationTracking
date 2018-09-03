package com.travel.livelocationtracking.db;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPrefs {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    private Context context;
    private final String DATABASE_NAME = "liveLocation";
    private final String LOGIN = "registered";
    private final String LOCATION_LAT = "location_lat";

    public SessionPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void setLogin(boolean registered) {
        spEditor = sharedPreferences.edit();
        spEditor.putBoolean(LOGIN, registered);
        spEditor.commit();
    }

    public String getLOCATION_LAT() {
        return sharedPreferences.getString(LOCATION_LAT, "");
    }

    public void setLOCATION_LAT(String location_lat) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(LOCATION_LAT, location_lat);
        spEditor.commit();
    }
}
