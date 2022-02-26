package com.onurkol.app.notes.popups;

import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.checkColorPoint;
import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.getDefaultNoteColor;
import static com.onurkol.app.notes.lib.notes.PopupDialogColorButtons.initColorButtons;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.MainActivity;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;

import java.util.ArrayList;

public class PopupEditNote {
    static String editNoteTitle, defaultNoteTitle;

    public static void Show(int position) {
        Context context=ContextManager.getManager().getContext();
        Activity activity=(Activity)context;

        EditText dialogTitleEditText;
        TextView dialogEditNewNoteTitle;
        Button dialogCancelButton, dialogNoteAddButton;

        final Dialog editNoteDialog = new Dialog(activity);
        editNoteDialog.setContentView(R.layout.popup_edit_new_note);

        ArrayList<NoteData> noteList=NoteManager.getManager().getNoteList();
        NoteData editData=noteList.get(position);

        dialogTitleEditText = editNoteDialog.findViewById(R.id.dialogNewNoteTitle);
        dialogCancelButton = editNoteDialog.findViewById(R.id.dialogCancelButton);
        dialogNoteAddButton = editNoteDialog.findViewById(R.id.dialogNoteAddButton);
        dialogEditNewNoteTitle = editNoteDialog.findViewById(R.id.dialogEditNewNoteTitle);

        initColorButtons(editNoteDialog, editData.getNoteColor());

        dialogEditNewNoteTitle.setText(context.getString(R.string.dialog_edit_note_name_title));
        dialogNoteAddButton.setText(context.getString(R.string.apply_text));
        editNoteTitle = editData.getNoteTitle();
        defaultNoteTitle = context.getString(R.string.default_note_title);

        checkColorPoint(editData.getNoteColor());

        dialogTitleEditText.setText(editNoteTitle);

        dialogCancelButton.setOnClickListener(view -> editNoteDialog.dismiss());
        dialogNoteAddButton.setOnClickListener(view -> {

            String noteTitle;
            int noteColor=getDefaultNoteColor();

            if (dialogTitleEditText.getText().toString().equals(""))
                noteTitle = defaultNoteTitle;
            else
                noteTitle = dialogTitleEditText.getText().toString();

            editData.setNoteColor(noteColor);
            editData.setNoteTitle(noteTitle);

            noteList.set(position,editData);

            NoteManager.getManager().saveNoteListInPreference(noteList);
            NoteManager.getManager().syncNoteListDataOnPreference();

            MainActivity.onUpdateNoteListSet();
            editNoteDialog.dismiss();
        });

        editNoteDialog.show();
    }
}
