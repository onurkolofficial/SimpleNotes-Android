package com.onurkol.app.notes.activity;

import static com.onurkol.app.notes.lib.AppPreferenceManager.INTEGER_NULL;
import static com.onurkol.app.notes.tools.CharLimiter.Limit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.edittext.SimpleNotesEditText;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;
import com.onurkol.app.notes.tools.DateManager;

import java.util.ArrayList;

public class NoteEditActivity extends AppCompatActivity implements AppData {

    NoteManager noteManager;

    ImageButton backButton, saveButton;
    TextView noteTitle;
    SimpleNotesEditText noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContextManager.Build(this);

        super.onCreate(savedInstanceState);
        AppDataManager.loadApplicationData();
        setContentView(R.layout.activity_note_edit);

        noteManager=NoteManager.getManager();
        NoteData data=null;
        int dataPosition=0;

        ArrayList<NoteData> noteList=noteManager.getNoteList();

        int getIntentNoteId=getIntent().getIntExtra(KEY_EXTRA_NOTE_ID,INTEGER_NULL);
        if(getIntentNoteId==INTEGER_NULL){
            dataPosition = getIntent().getIntExtra(KEY_EXTRA_NOTE_INDEX, INTEGER_NULL);
            data = noteManager.getNoteList().get(dataPosition);
        }
        else{
            int position=0;
            for(NoteData note : NoteManager.getManager().getNoteList()) {
                if(note.getNoteId()==getIntentNoteId){
                    dataPosition = position;
                    data = noteManager.getNoteList().get(position);
                    break;
                }
                position++;
            }
        }
        final NoteData finalData = data;
        final int finalDataPosition = dataPosition;

        backButton=findViewById(R.id.backButton);
        saveButton=findViewById(R.id.saveNoteButton);
        noteTitle=findViewById(R.id.noteTitle);
        noteText=findViewById(R.id.noteEditText);

        noteTitle.setText(Limit(data.getNoteTitle(),18));
        noteText.setText(data.getNoteText());

        backButton.setOnClickListener(view -> finish());
        saveButton.setOnClickListener(view -> {
            String getNewText=noteText.getText().toString();
            String getEditDate=DateManager.getDateTime();

            finalData.setNoteText(getNewText);
            finalData.setNoteEditDate(getEditDate);

            noteList.set(finalDataPosition, finalData);

            noteManager.saveNoteListInPreference(noteList);
            noteManager.syncNoteListDataOnPreference();

            MainActivity.onUpdateNoteOnSettings=true;
            Toast.makeText(this, getString(R.string.note_saved_text), Toast.LENGTH_SHORT).show();
        });

    }
}