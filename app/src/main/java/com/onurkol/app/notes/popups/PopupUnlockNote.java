package com.onurkol.app.notes.popups;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import static com.onurkol.app.notes.tools.ClassicAlertDialog.Alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.MainActivity;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;

import java.util.ArrayList;

public class PopupUnlockNote {
    static String notePassword;

    public static void Show(int position) {
        Context context=ContextManager.getManager().getContext();
        Activity activity=(Activity)context;

        EditText dialogLockUnlockOpenPassword;
        TextView dialogLockUnlockOpenTitle, dialogLockUnlockOpenText;
        Button dialogCancelButton, dialogLockUnlockOpenButton;

        final Dialog unlockNoteDialog = new Dialog(activity);
        unlockNoteDialog.setContentView(R.layout.popup_lock_unlock_note);
        unlockNoteDialog.getWindow().setLayout(MATCH_PARENT,WRAP_CONTENT);

        ArrayList<NoteData> noteList=NoteManager.getManager().getNoteList();
        NoteData editData=noteList.get(position);

        dialogLockUnlockOpenPassword = unlockNoteDialog.findViewById(R.id.dialogLockUnlockOpenPassword);
        dialogLockUnlockOpenTitle = unlockNoteDialog.findViewById(R.id.dialogLockUnlockOpenTitle);
        dialogLockUnlockOpenText = unlockNoteDialog.findViewById(R.id.dialogLockUnlockOpenText);
        dialogCancelButton = unlockNoteDialog.findViewById(R.id.dialogCancelButton);
        dialogLockUnlockOpenButton = unlockNoteDialog.findViewById(R.id.dialogLockUnlockOpenButton);

        dialogLockUnlockOpenTitle.setText(context.getString(R.string.unlock_note_text));
        dialogLockUnlockOpenText.setText(context.getString(R.string.unlock_for_password_text));
        dialogLockUnlockOpenButton.setText(context.getString(R.string.unlock_text));

        dialogCancelButton.setOnClickListener(view -> unlockNoteDialog.dismiss());
        dialogLockUnlockOpenButton.setOnClickListener(view -> {
            notePassword=dialogLockUnlockOpenPassword.getText().toString();
            if(notePassword.equals(editData.getNotePassword())){
                editData.setNotePassword(null);

                noteList.set(position, editData);

                NoteManager.getManager().saveNoteListInPreference(noteList);
                NoteManager.getManager().syncNoteListDataOnPreference();

                MainActivity.onUpdateNoteListSet();
            }
            else{
                Alert(context,context.getString(R.string.wrong_password_text));
            }
            unlockNoteDialog.dismiss();
        });

        unlockNoteDialog.show();
    }
}
