package com.onurkol.app.notes.data;

public class NoteData {
    private String mNoteID;
    private String mNoteName;
    private String mNoteText;
    private int mNoteColor;
    private String mNotePassword;

    public NoteData(String NoteID, String NoteName, String NoteText, String NotePassword, int NoteColor){
        this.mNoteID=NoteID;
        this.mNoteName=NoteName;
        this.mNoteText=NoteText;
        this.mNoteColor=NoteColor;
        this.mNotePassword=NotePassword;
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
    public String getNotePassword(){
        return mNotePassword;
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
    public void setNotePassword(String notePassword){
        mNotePassword=notePassword;
    }
}
