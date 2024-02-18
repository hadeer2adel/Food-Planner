package com.example.foodplanner.SQLlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.Models.UserDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_SATURDAY = "saturday";

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(String id, String name, String email) {
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_SATURDAY, getCurrentDate());
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_ID, null);
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getSaturday() {
        return sharedPreferences.getString(KEY_SATURDAY, null);
    }

    public void logoutUser() {
        editor.clear();
        editor.apply();
    }

    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        Date currentSaturday = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(currentSaturday);
    }
}
