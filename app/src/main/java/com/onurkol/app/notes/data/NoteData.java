package com.onurkol.app.notes.data;

public class NoteData {
    private String mGetNoteTitle,
            mGetNoteText,
            mGetNoteCreateDate,
            mGetNoteEditDate,
            mGetNotePassword;
    private int mGetNoteColor;

    public NoteData(String noteTitle,String noteText, String noteCreateDate, String noteEditDate, int noteColor, String notePassword){
        mGetNoteTitle=noteTitle;
        mGetNoteText=noteText;
        mGetNoteCreateDate=noteCreateDate;
        mGetNoteEditDate=noteEditDate;
        mGetNoteColor=noteColor;
        mGetNotePassword=notePassword;
    }

    public String getNoteTitle(){
        return mGetNoteTitle;
    }
    public String getNoteText(){
        return mGetNoteText;
    }
    public String getNoteCreateDate() {
        return mGetNoteCreateDate;
    }
    public String getNoteEditDate() {
        return mGetNoteEditDate;
    }
    public String getNotePassword() {
        return mGetNotePassword;
    }
    public int getNoteColor() {
        return mGetNoteColor;
    }

    public void setNoteTitle(String newNoteTitle){
        mGetNoteTitle=newNoteTitle;
    }
    public void setNoteText(String newNoteText){
        mGetNoteText=newNoteText;
    }
    public void setNoteEditDate(String newNoteEditDate) {
        mGetNoteEditDate=newNoteEditDate;
    }
    public void setNotePassword(String newNotePassword) {
        mGetNotePassword=newNotePassword;
    }
    public void setNoteColor(int newNoteColor) {
        mGetNoteColor=newNoteColor;
    }
}
