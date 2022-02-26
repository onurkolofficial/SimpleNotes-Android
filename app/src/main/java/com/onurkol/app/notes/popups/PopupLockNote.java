package com.onurkol.app.notes.popups;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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

public class PopupLockNote {
    static String notePassword;

    public static void Show(int position) {
        Context context=ContextManager.getManager().getContext();
        Activity activity=(Activity)context;

        EditText dialogLockUnlockOpenPassword;
        TextView dialogLockUnlockOpenTitle, dialogLockUnlockOpenText;
        Button dialogCancelButton, dialogLockUnlockOpenButton;

        final Dialog lockNoteDialog = new Dialog(activity);
        lockNoteDialog.setContentView(R.layout.popup_lock_unlock_note);
        lockNoteDialog.getWindow().setLayout(MATCH_PARENT,WRAP_CONTENT);

        ArrayList<NoteData> noteList=NoteManager.getManager().getNoteList();
        NoteData editData=noteList.get(position);

        dialogLockUnlockOpenPassword = lockNoteDialog.findViewById(R.id.dialogLockUnlockOpenPassword);
        dialogLockUnlockOpenTitle = lockNoteDialog.findViewById(R.id.dialogLockUnlockOpenTitle);
        dialogLockUnlockOpenText = lockNoteDialog.findViewById(R.id.dialogLockUnlockOpenText);
        dialogCancelButton = lockNoteDialog.findViewById(R.id.dialogCancelButton);
        dialogLockUnlockOpenButton = lockNoteDialog.findViewById(R.id.dialogLockUnlockOpenButton);

        dialogLockUnlockOpenTitle.setText(context.getString(R.string.lock_note_text));
        dialogLockUnlockOpenText.setText(context.getString(R.string.lock_for_password_text));
        dialogLockUnlockOpenButton.setText(context.getString(R.string.lock_text));

        dialogCancelButton.setOnClickListener(view -> lockNoteDialog.dismiss());
        dialogLockUnlockOpenButton.setOnClickListener(view -> {
            notePassword=dialogLockUnlockOpenPassword.getText().toString();
            if(notePassword.isEmpty())
                notePassword=null;

            editData.setNotePassword(notePassword);

            noteList.set(position, editData);

            NoteManager.getManager().saveNoteListInPreference(noteList);
            NoteManager.getManager().syncNoteListDataOnPreference();

            MainActivity.onUpdateNoteListSet();
            lockNoteDialog.dismiss();
        });

        lockNoteDialog.show();
    }
}
