package com.onurkol.app.notes.popups;

import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.checkColorPoint;
import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.getDefaultNoteColor;
import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.initColorButtons;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.MainActivity;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;

import java.lang.ref.WeakReference;

public class PopupNewNote {
    static String defaultNoteTitle;

    public static void Show() {
        Context context=ContextManager.getManager().getContext();
        Activity activity=(Activity)context;

        EditText dialogTitleEditText;
        TextView dialogEditNewNoteTitle;
        Button dialogCancelButton, dialogNoteAddButton;

        final Dialog newNoteDialog = new Dialog(activity);
        newNoteDialog.setContentView(R.layout.popup_edit_new_note);

        dialogTitleEditText = newNoteDialog.findViewById(R.id.dialogNewNoteTitle);
        dialogCancelButton = newNoteDialog.findViewById(R.id.dialogCancelButton);
        dialogNoteAddButton = newNoteDialog.findViewById(R.id.dialogNoteAddButton);
        dialogEditNewNoteTitle = newNoteDialog.findViewById(R.id.dialogEditNewNoteTitle);

        initColorButtons(newNoteDialog,null);

        dialogEditNewNoteTitle.setText(context.getString(R.string.dialog_new_note_name_title));
        dialogNoteAddButton.setText(context.getString(R.string.add_text));
        defaultNoteTitle = context.getString(R.string.default_note_title);

        checkColorPoint(getDefaultNoteColor());

        dialogCancelButton.setOnClickListener(view -> newNoteDialog.dismiss());
        dialogNoteAddButton.setOnClickListener(view -> {
            String noteTitle;
            int noteColor=getDefaultNoteColor();

            if (dialogTitleEditText.getText().toString().equals(""))
                noteTitle = defaultNoteTitle;
            else
                noteTitle = dialogTitleEditText.getText().toString();

            NoteManager.getManager().newNote(noteTitle,null,null,noteColor,null);

            MainActivity.onUpdateNoteList();
            newNoteDialog.dismiss();
        });

        newNoteDialog.show();
    }


}
