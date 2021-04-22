package com.onurkol.app.notes.popups;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.EditNoteActivity;
import com.onurkol.app.notes.data.NoteData;

import java.lang.ref.WeakReference;

import static com.onurkol.app.notes.activity.MainActivity.noteListViewWeak;
import static com.onurkol.app.notes.data.PreferenceData.APP_NOTES;
import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceString;

public class PopupLockUnlockOpenNote {

    static NoteData editData;
    static boolean LockMode;
    static int notePosition;
    static String eventMode;

    static WeakReference<ImageButton> test;

    // Gson
    static Gson gson=new Gson();

    public static void showLockUnlockOpenDialog(int editPosition, boolean mode){
        // Lock/Unlock or Open Note
        LockMode=mode;
        notePosition=editPosition;
        // Set Data
        editData=APP_NOTES.get(editPosition);
        // Call Class
        lockUnlockOpenDialogClass();
    }

    private static void lockUnlockOpenDialogClass(){
        Context context=getContext();
        Activity activity=(Activity)context;

        // Elements (static is MemoryLeak)
        EditText dialogLockUnlockOpenPassword;
        TextView dialogLockUnlockOpenTitle,dialogLockUnlockOpenText;
        Button dialogCancelButton, dialogLockUnlockOpenButton;

        final Dialog noteLockUnlockOpen = new Dialog(activity);
        noteLockUnlockOpen.setContentView(R.layout.popup_lock_unlock_note);
        // Dialog Main Elements
        dialogLockUnlockOpenPassword = noteLockUnlockOpen.findViewById(R.id.dialogLockUnlockOpenPassword);
        dialogLockUnlockOpenTitle = noteLockUnlockOpen.findViewById(R.id.dialogLockUnlockOpenTitle);
        dialogLockUnlockOpenText = noteLockUnlockOpen.findViewById(R.id.dialogLockUnlockOpenText);
        dialogCancelButton = noteLockUnlockOpen.findViewById(R.id.dialogCancelButton);
        dialogLockUnlockOpenButton = noteLockUnlockOpen.findViewById(R.id.dialogLockUnlockOpenButton);

        String notePassword=editData.getNotePassword();
        // Check Mode
        if(LockMode){
            // Check Lock/Unlock
            if(notePassword==null || notePassword.equals("null")){
                // Set Lock Dialog
                dialogLockUnlockOpenTitle.setText(context.getString(R.string.lock_note_text));
                dialogLockUnlockOpenText.setText(context.getString(R.string.lock_for_password_text));
                dialogLockUnlockOpenButton.setText(context.getString(R.string.lock_text));
                eventMode="lock";
                // Show Dialog
                noteLockUnlockOpen.show();
            }
            else{
                // Set Unlock Dialog
                dialogLockUnlockOpenTitle.setText(context.getString(R.string.unlock_note_text));
                dialogLockUnlockOpenText.setText(context.getString(R.string.unlock_for_password_text));
                dialogLockUnlockOpenButton.setText(context.getString(R.string.unlock_text));
                eventMode="unlock";
                // Show Dialog
                noteLockUnlockOpen.show();
            }
        }
        else{
            // Check Open Password.
            if(notePassword==null || notePassword.equals("null")){
                // No Dialog.
                startEditNoteActivity(context);
            }
            else{
                // Set Open to Password Dialog.
                dialogLockUnlockOpenTitle.setText(context.getString(R.string.open_note_text));
                dialogLockUnlockOpenText.setText(context.getString(R.string.open_for_password_text));
                dialogLockUnlockOpenButton.setText(context.getString(R.string.open_text));
                eventMode="open";
                // Show Dialog
                noteLockUnlockOpen.show();
            }
        }

        // Button Events
        // Cancel Button
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteLockUnlockOpen.dismiss();
            }
        });

        dialogLockUnlockOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check Password
                String getPassword=dialogLockUnlockOpenPassword.getText().toString();
                if(getPassword.isEmpty() || getPassword.equals("")){
                    showDialog(context, context.getString(R.string.empty_password_text));
                }
                else{
                    // Check Modes
                    if(eventMode.equals("lock")){
                        // Get Default Data
                        String oldData=gson.toJson(editData);
                        // Set Password
                        editData.setNotePassword(getPassword);
                        // Convert & Save Preference
                        String newData=gson.toJson(editData);
                        // Get Preferences
                        String prefData=getPreferenceString(getSPreferences(), "APP_NOTES");
                        // Change Data
                        String updateNewPreference=prefData.replace(oldData,newData);
                        // Save Preference
                        setPreferenceString(getSPreferences(), "APP_NOTES", updateNewPreference);
                        // Refresh ListView
                        noteListViewWeak.get().invalidateViews();
                        // Show Message
                        Toast.makeText(context, context.getString(R.string.success_lock_text), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (getPassword.equals(notePassword)) {
                            if (eventMode.equals("unlock")) {
                                // Get Default Data
                                String oldData = gson.toJson(editData);
                                // Set Password
                                editData.setNotePassword("null");
                                // Convert & Save Preference
                                String newData = gson.toJson(editData);
                                // Get Preferences
                                String prefData = getPreferenceString(getSPreferences(), "APP_NOTES");
                                // Change Data
                                String updateNewPreference = prefData.replace(oldData, newData);
                                // Save Preference
                                setPreferenceString(getSPreferences(), "APP_NOTES", updateNewPreference);
                                // Refresh ListView
                                noteListViewWeak.get().invalidateViews();
                                // Show Message
                                Toast.makeText(context, context.getString(R.string.success_unlock_text), Toast.LENGTH_SHORT).show();
                            }
                            else if (eventMode.equals("open"))
                                startEditNoteActivity(context);
                        }
                        else
                            showDialog(context, context.getString(R.string.wrong_password_text));
                    }
                }
                noteLockUnlockOpen.dismiss();
            }
        });
    }

    private static void startEditNoteActivity(Context context){
        Intent editNoteIntent=new Intent(context, EditNoteActivity.class);
        editNoteIntent.putExtra("NOTE_DATA_POSITION", notePosition);
        context.startActivity(editNoteIntent);
    }

    private static void showDialog(Context context, String message){
        // Show Alert Dialog
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setMessage(message);
        dialog.setPositiveButton(R.string.ok_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Dismiss
            }
        });
        dialog.setCancelable(true);
        dialog.create().show();
    }
}
