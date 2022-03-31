package com.onurkol.app.notes.interfaces.notes;

import com.onurkol.app.notes.data.NoteData;

import java.util.ArrayList;

public interface NoteManagerInterface {
    void newNote(String NoteTitle, String NoteText, String NoteEditDate, int NoteColor, String NotePassword);
    void deleteNote(int Index);
    void deleteAllNotes();
    void saveNoteListInPreference(ArrayList<NoteData> NoteList);

    ArrayList<NoteData> getNoteList();
    String getNoteListJson();

    void syncNoteListDataOnPreference();

    void fixedAutoDataInNotes();
}
