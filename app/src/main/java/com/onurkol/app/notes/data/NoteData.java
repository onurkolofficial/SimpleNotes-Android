package com.onurkol.app.notes.data;

public class NoteData {
    private String mNoteID;
    private String mNoteName;
    private String mNoteText;
    private int mNoteColor;

    public NoteData(String NoteID, String NoteName, String NoteText, int NoteColor){
        this.mNoteID=NoteID;
        this.mNoteName=NoteName;
        this.mNoteText=NoteText;
        this.mNoteColor=NoteColor;
    }

    public String getNoteID(){
        return mNoteID;
    }
    public String getNoteName(){
        return mNoteName;
    }
    public String getNoteText(){
        return mNoteText;
    }
    public int getNoteColor(){
        return mNoteColor;
    }

    public void setNoteName(String noteName){
        mNoteName=noteName;
    }
    public void setNoteText(String noteText){
        mNoteText=noteText;
    }
    public void setNoteColor(int noteColor){
        mNoteColor=noteColor;
    }
}
