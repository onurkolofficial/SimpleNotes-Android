package com.onurkol.app.notes.tools;

import android.content.Context;
import android.content.SharedPreferences;

import static com.onurkol.app.notes.tools.ContextTool.getContext;

public class SharedPreferenceManager {
    private static final String preferenceName="OKSimpleNotesPreferenceData";

    public static int INTEGER_NULL=-1000;
    public static String STRING_NULL="NULL";

    // Get Current Shared Preferences
    public static SharedPreferences getSPreferences(){
        return getContext().getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    // GET *
    public static String getPreferenceString(SharedPreferences preferences, String data_id){
        return preferences.getString(data_id,null);
    }
    public static int getPreferenceInteger(SharedPreferences preferences, String data_id){
        return preferences.getInt(data_id,INTEGER_NULL);
    }

    // SET *
    public static void setPreferenceString(SharedPreferences preferences, String data_id, String data_value){
        preferences.edit().putString(data_id,data_value).apply();
    }
    public static void setPreferenceInteger(SharedPreferences preferences, String data_id, int data_value){
        preferences.edit().putInt(data_id,data_value).apply();
    }

    // REMOVE ALL PREFERENCES *
    public static void removePreferences(SharedPreferences preferences){
        preferences.edit().clear().apply();
    }
}
