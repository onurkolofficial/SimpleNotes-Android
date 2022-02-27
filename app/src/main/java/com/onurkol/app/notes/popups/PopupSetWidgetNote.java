package com.onurkol.app.notes.popups;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.onurkol.app.notes.tools.ClassicAlertDialog.Alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.widgets.NoteEditWidgetConfigureActivity;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;

import java.util.ArrayList;

public class PopupSetWidgetNote {
    static String notePassword;
    public static void Show(int position) {
        Context context= ContextManager.getManager().getContext();
        Activity activity=(Activity)context;

        EditText dialogLockUnlockOpenPassword;
        TextView dialogLockUnlockOpenTitle, dialogLockUnlockOpenText;
        Button dialogCancelButton, dialogLockUnlockOpenButton;

        final Dialog openLockedNoteDialog = new Dialog(activity);
        openLockedNoteDialog.setContentView(R.layout.popup_lock_unlock_note);
        openLockedNoteDialog.getWindow().setLayout(MATCH_PARENT,WRAP_CONTENT);

        ArrayList<NoteData> noteList= NoteManager.getManager().getNoteList();
        NoteData editData=noteList.get(position);

        dialogLockUnlockOpenPassword = openLockedNoteDialog.findViewById(R.id.dialogLockUnlockOpenPassword);
        dialogLockUnlockOpenTitle = openLockedNoteDialog.findViewById(R.id.dialogLockUnlockOpenTitle);
        dialogLockUnlockOpenText = openLockedNoteDialog.findViewById(R.id.dialogLockUnlockOpenText);
        dialogCancelButton = openLockedNoteDialog.findViewById(R.id.dialogCancelButton);
        dialogLockUnlockOpenButton = openLockedNoteDialog.findViewById(R.id.dialogLockUnlockOpenButton);

        dialogLockUnlockOpenTitle.setText(context.getString(R.string.set_note_text));
        dialogLockUnlockOpenText.setText(context.getString(R.string.set_for_password_text));
        dialogLockUnlockOpenButton.setText(context.getString(R.string.set_text));

        dialogCancelButton.setOnClickListener(view -> openLockedNoteDialog.dismiss());
        dialogLockUnlockOpenButton.setOnClickListener(view -> {
            notePassword=dialogLockUnlockOpenPassword.getText().toString();
            if(notePassword.equals(editData.getNotePassword()))
                NoteEditWidgetConfigureActivity.acceptWidgetDataOnClick(context,
                        editData.getNoteTitle(),
                        editData.getNoteText(),
                        editData.getNoteColor());
            else
                Alert(context,context.getString(R.string.wrong_password_text));

            openLockedNoteDialog.dismiss();
        });

        openLockedNoteDialog.show();
    }
}
