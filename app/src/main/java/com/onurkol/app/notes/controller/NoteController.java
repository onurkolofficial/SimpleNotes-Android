package com.onurkol.app.notes.controller;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onurkol.app.notes.data.NoteData;

import java.util.List;

import static com.onurkol.app.notes.activity.MainActivity.noNoteLayoutWeak;
import static com.onurkol.app.notes.data.PreferenceData.APP_NOTES;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.STRING_NULL;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceString;

public class NoteController {
    // Gson
    static Gson gson=new Gson();

    static String mNote;

    public static void addNewNote(String Id, String Name, String Note, int Color){
        mNote=((Note==null) ? "" : Note);

        // Convert Get Values to NoteData
        NoteData getNoteData=new NoteData(Id,Name,mNote,Color);
        // Convert NoteData to JSON String
        String getData="["+gson.toJson(getNoteData)+"]";

        // Get Preference Data
        String oldData=getPreferenceString(getSPreferences(), "APP_NOTES");

        String mergeData="";
        String newData=getData;

        // Check Data
        if(!oldData.equals("NULL")){
            // Remove array sembol '[' & ']'
            getData=getData.substring(1,(getData.length()-1));
            oldData=oldData.substring(1,(oldData.length()-1));
            // Add new Data
            mergeData=getData+","+oldData;
            // Add Array symbol '[{data},{old}]'
            newData="["+mergeData+"]";
        }
        // Add View
        APP_NOTES.add(0,getNoteData);

        // Save Preference
        setPreferenceString(getSPreferences(), "APP_NOTES", newData);
    }

    public static void removeNote(String Id, String Name, String Note, int Color){
        mNote=((Note==null) ? "" : Note);

        // Get Preference Data
        String allNoteData=getPreferenceString(getSPreferences(), "APP_NOTES");
        // Convert Get Values to NoteData
        NoteData convertData=new NoteData(Id,Name,mNote,Color);
        // Convert NoteData to JSON String
        String deleteData=gson.toJson(convertData);
        String newData="",deleteConvert="";
        // Search Data Position
        if (allNoteData.contains(deleteData + ","))
            deleteConvert = deleteData + ",";
        else if (allNoteData.contains("," + deleteData))
            deleteConvert = "," + deleteData;
        else
            deleteConvert = deleteData;
        newData=allNoteData.replace(deleteConvert,"");

        // <Fixed> If data to empty is set NULL value
        if(newData.equals("[]")) {
            newData = STRING_NULL;
            // Show No Note Layout.
            noNoteLayoutWeak.get().setVisibility(View.VISIBLE);
        }

        // Save Preference
        setPreferenceString(getSPreferences(), "APP_NOTES", newData);

        // Update Note List
        if(!newData.equals(STRING_NULL))
            APP_NOTES=gson.fromJson(newData, new TypeToken<List<NoteData>>(){}.getType());

    }
}
