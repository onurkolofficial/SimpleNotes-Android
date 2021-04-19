package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onurkol.app.notes.R;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.tools.CustomEditText;

import static com.onurkol.app.notes.data.PreferenceData.APP_NOTES;
import static com.onurkol.app.notes.fragments.SettingsFragment.setApplicationTheme;
import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.ContextTool.setContext;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.INTEGER_NULL;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceString;

public class EditNoteActivity extends AppCompatActivity {

    ImageButton backActionBtn,saveActionBtn;
    TextView actionTitle;
    CustomEditText noteEditText;

    String noteId,noteName,noteText;
    int noteColor,dataPosition;

    // Gson
    static Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Current Activity Context
        setContext(this);
        // Load Theme
        setApplicationTheme(this);
        // Create View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Get Intent Data
        // Note Array Index
        dataPosition=getIntent().getIntExtra("NOTE_DATA_POSITION",INTEGER_NULL);

        // Get Data
        NoteData data=APP_NOTES.get(dataPosition);
        noteId=data.getNoteID();
        noteName=data.getNoteName();
        noteText=data.getNoteText();
        noteColor=data.getNoteColor();

        // Get Elements
        actionTitle=findViewById(R.id.actionTitle);
        backActionBtn=findViewById(R.id.backActionButton);
        saveActionBtn=findViewById(R.id.saveActionButton);
        noteEditText=findViewById(R.id.noteEditText);

        // Set Toolbar Title
        actionTitle.setText(data.getNoteName());

        // Set Saved Data
        noteEditText.setText(data.getNoteText());

        // Button Click Events
        backActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Preferences
                String prefData=getPreferenceString(getSPreferences(), "APP_NOTES");
                // Get Change Data
                String newNoteText=noteEditText.getText().toString();
                // Convert to String & Update Data
                String oldData=gson.toJson(data);

                APP_NOTES.get(dataPosition).setNoteText(newNoteText);
                String newData=gson.toJson(APP_NOTES.get(dataPosition));
                // Change Data
                String updateNewPreference=prefData.replace(oldData,newData);
                // Save Preference
                setPreferenceString(getSPreferences(), "APP_NOTES", updateNewPreference);
                /*
                // Convert Get Values to NoteData
                NoteData convertNewData=new NoteData(noteId,noteName,noteEditText.getText().toString(),noteColor);
                String newData=gson.toJson(convertNewData);
                String oldData=gson.toJson(data);
                // Change Data
                String updateNewPreference=prefData.replace(oldData,newData);
                // Save Preference
                setPreferenceString(getSPreferences(), "APP_NOTES", updateNewPreference);
                // Update Array
                APP_NOTES.remove(dataPosition);
                APP_NOTES.add(dataPosition,convertNewData);
                 */
                // Show Toast Message
                Toast.makeText(getContext(), getString(R.string.note_saved_text), Toast.LENGTH_SHORT).show();
            }
        });
    }
}