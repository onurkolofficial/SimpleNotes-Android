package com.onurkol.app.notes.tools;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onurkol.app.notes.adapters.NoteListAdapter;
import com.onurkol.app.notes.data.NoteData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.onurkol.app.notes.activity.MainActivity.noNoteLayoutWeak;
import static com.onurkol.app.notes.data.PreferenceData.APP_NOTES;
import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;

public class NoteManager {
    private static WeakReference<NoteListAdapter> noteListAdapterWeak;

    // Gson
    static Gson gson=new Gson();

    public static void buildNoteList(ListView noteListView){
        noteListAdapterWeak=new WeakReference<>(new NoteListAdapter(getContext(), APP_NOTES));
        noteListView.setAdapter(noteListAdapterWeak.get());
        // Get Data
        getNoteDataList();
    }

    public static void getNoteDataList(){
        // Get Preference Data
        String noteDataString=getPreferenceString(getSPreferences(), "APP_NOTES");

        if(noteDataString.equals("NULL")) {
            // Show No Note Layout
            noNoteLayoutWeak.get().setVisibility(View.VISIBLE);
            // Clear Array
            APP_NOTES.clear();
        }
        else {
            // Hide No Note Layout
            noNoteLayoutWeak.get().setVisibility(View.GONE);
            // Data Convert String to List
            List<NoteData> getNotesData=gson.fromJson(noteDataString, new TypeToken<ArrayList<NoteData>>(){}.getType());

            // Check Data
            if(APP_NOTES.size()<=0) {
                // Adding Data
                int i=0;
                while(i<getNotesData.size()){
                    // Get Data
                    String gId=getNotesData.get(i).getNoteID();
                    String gName=getNotesData.get(i).getNoteName();
                    String gNote=getNotesData.get(i).getNoteText();
                    String gPassword=getNotesData.get(i).getNotePassword();
                    int gColor=getNotesData.get(i).getNoteColor();
                    // Add View
                    APP_NOTES.add(new NoteData(gId,gName,gNote,gPassword,gColor));
                    // Count
                    i++;
                }
            }
        }
    }
}
