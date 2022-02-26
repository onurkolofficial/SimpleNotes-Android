package com.onurkol.app.notes.lib.notes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.interfaces.notes.NoteManagerInterface;
import com.onurkol.app.notes.lib.AppPreferenceManager;
import com.onurkol.app.notes.tools.DateManager;
import com.onurkol.app.notes.tools.ListToJson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class NoteManager implements NoteManagerInterface, AppData {
    private static WeakReference<NoteManager> instanceStatic;
    private AppPreferenceManager prefManager;

    static Gson gson=new Gson();

    private NoteManager(){
        prefManager=AppPreferenceManager.getInstance();
    }

    public static synchronized NoteManager getManager(){
        if(instanceStatic==null || instanceStatic.get()==null)
            instanceStatic=new WeakReference<>(new NoteManager());
        return instanceStatic.get();
    }

    @Override
    public void newNote(String NoteTitle, String NoteText, String NoteEditDate, int NoteColor, String NotePassword) {
        if(NOTE_LIST.size()<=0)
            syncNoteListDataOnPreference();

        String noteCreateDate=DateManager.getDateTime();
        NOTE_LIST.add(0,new NoteData(NoteTitle,NoteText,noteCreateDate,NoteEditDate,NoteColor,NotePassword));
        saveNoteListInPreference(NOTE_LIST);
    }

    @Override
    public void deleteNote(int Index) {
        NOTE_LIST.remove(Index);
        saveNoteListInPreference(NOTE_LIST);
    }

    @Override
    public void deleteAllNotes() {
        NOTE_LIST.clear();
        saveNoteListInPreference(NOTE_LIST);
    }

    @Override
    public void saveNoteListInPreference(ArrayList<NoteData> NoteList) {
        String listData;
        if(NoteList.size()<=0)
            listData="";
        else
            listData=ListToJson.getJson(NoteList);

        prefManager.setPreference(KEY_NOTE_LIST,listData);
    }

    @Override
    public ArrayList<NoteData> getNoteList() {
        if(prefManager==null)
            prefManager=AppPreferenceManager.getInstance();
        ArrayList<NoteData> newList=new ArrayList<>();

        String getSavedHistoriesString=prefManager.getString(KEY_NOTE_LIST);
        if(getSavedHistoriesString!=null && !getSavedHistoriesString.equals(""))
            newList=gson.fromJson(getSavedHistoriesString, new TypeToken<ArrayList<NoteData>>(){}.getType());

        return newList;
    }

    @Override
    public String getNoteListJson() {
        return gson.toJson(getNoteList());
    }

    @Override
    public void syncNoteListDataOnPreference() {
        NOTE_LIST.clear();
        NOTE_LIST.addAll(getNoteList());
    }
}
