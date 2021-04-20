package com.onurkol.app.notes.popups;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.onurkol.app.notes.R;
import com.onurkol.app.notes.data.NoteData;

import java.lang.ref.WeakReference;

import static com.onurkol.app.notes.activity.MainActivity.noNoteLayoutWeak;
import static com.onurkol.app.notes.activity.MainActivity.noteListViewWeak;
import static com.onurkol.app.notes.controller.NoteController.addNewNote;
import static com.onurkol.app.notes.data.PreferenceData.APP_NOTES;
import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.RandomTool.random;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceString;

public class PopupEditNewNote {
    public static int defaultNoteColor;
    public static String defaultNoteName;

    static NoteData editData;
    static boolean NewNote;

    static WeakReference<ImageButton> white, red, pink, purple, deeppurple, indigo, blue, lightblue, cyan, teal, green, lightgreen, lime, yellow, amber, orange, deeporange, brown, grey, bluegrey;

    // Gson
    static Gson gson=new Gson();

    public static void showNewNoteDialog(){
        // Create New Note
        NewNote=true;
        // Call Class
        newNoteDialogClass();
    }

    public static void showNewNoteDialog(int editPosition){
        // Edit Note
        NewNote=false;
        // Set Data
        editData=APP_NOTES.get(editPosition);
        // Call Class
        newNoteDialogClass();
    }

    public static void newNoteDialogClass() {
        Context context=getContext();
        Activity activity=(Activity)context;

        // Elements (static is MemoryLeak)
        EditText dialogNameEditText;
        TextView dialogEditNewNoteTitle;
        Button dialogCancelButton, dialogNoteAddButton;

        final Dialog newNoteDialog = new Dialog(activity);
        newNoteDialog.setContentView(R.layout.popup_edit_new_note);
        // Dialog Main Elements
        dialogNameEditText = newNoteDialog.findViewById(R.id.dialogNewNoteName);
        dialogCancelButton = newNoteDialog.findViewById(R.id.dialogNoteCancelButton);
        dialogNoteAddButton = newNoteDialog.findViewById(R.id.dialogNoteAddButton);
        dialogEditNewNoteTitle = newNoteDialog.findViewById(R.id.dialogEditNewNoteTitle);

        // Color Buttons
        white = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorWhiteButton));
        red = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorRedButton));
        pink = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorPinkButton));
        purple = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorPurpleButton));
        deeppurple = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorDeepPurpleButton));
        indigo = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorIndigoButton));
        blue = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorBlueButton));
        lightblue = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorLightBlueButton));
        cyan = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorCyanButton));
        teal = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorTealButton));
        green = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorGreenButton));
        lightgreen = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorLightGreenButton));
        lime = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorLimeButton));
        yellow = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorYellowButton));
        amber = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorAmberButton));
        orange = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorOrangeButton));
        deeporange = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorDeepOrangeButton));
        brown = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorBrownButton));
        grey = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorGreyButton));
        bluegrey = new WeakReference<>(newNoteDialog.findViewById(R.id.dialogColorBlueGreyButton));

        if(NewNote) {
            // Set Texts
            dialogEditNewNoteTitle.setText(context.getString(R.string.dialog_new_note_name_title));
            dialogNoteAddButton.setText(context.getString(R.string.add_text));

            defaultNoteName = context.getString(R.string.default_note_name);
            defaultNoteColor = context.getResources().getColor(R.color.cardColorWhite);
        }
        else{
            // Set Title
            dialogEditNewNoteTitle.setText(context.getString(R.string.dialog_edit_note_name_title));
            dialogNoteAddButton.setText(context.getString(R.string.apply_text));

            // Get Data
            defaultNoteName = editData.getNoteName();
            defaultNoteColor = editData.getNoteColor();

            dialogNameEditText.setText(defaultNoteName);
        }
        checkColorPoint(defaultNoteColor);

        // Button Events
        // Cancel Button
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNoteDialog.dismiss();
            }
        });

        // Add Button
        dialogNoteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NewNote) {
                    // Add New Note
                    String noteName;
                    int noteColor = defaultNoteColor;

                    if (dialogNameEditText.getText().toString().equals(""))
                        noteName = defaultNoteName;
                    else
                        noteName = dialogNameEditText.getText().toString();

                    String noteID = random(20);
                    // Add Note
                    addNewNote(noteID, noteName, null, noteColor);
                    // Refresh ListView
                    noteListViewWeak.get().invalidateViews();
                    // Hide No Note Layout
                    noNoteLayoutWeak.get().setVisibility(View.GONE);
                }
                else{
                    // Change Note
                    String newNoteName;
                    int newNoteColor;

                    if (dialogNameEditText.getText().toString().equals(""))
                        newNoteName = context.getString(R.string.default_note_name);
                    else
                        newNoteName = dialogNameEditText.getText().toString();

                    newNoteColor=defaultNoteColor;
                    // Get Default Data
                    String oldData=gson.toJson(editData);
                    // Change Data
                    editData.setNoteName(newNoteName);
                    editData.setNoteColor(newNoteColor);
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
                }
                newNoteDialog.dismiss();
            }
        });
        
        // Color Buttons
        white.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorWhite);
                checkColorPoint(defaultNoteColor);
            }
        });
        red.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorRed);
                checkColorPoint(defaultNoteColor);
            }
        });
        pink.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorPink);
                checkColorPoint(defaultNoteColor);
            }
        });
        purple.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorPurple);
                checkColorPoint(defaultNoteColor);
            }
        });
        deeppurple.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorDeepPurple);
                checkColorPoint(defaultNoteColor);
            }
        });
        indigo.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorIndigo);
                checkColorPoint(defaultNoteColor);
            }
        });
        blue.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBlue);
                checkColorPoint(defaultNoteColor);
            }
        });
        lightblue.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLightBlue);
                checkColorPoint(defaultNoteColor);
            }
        });
        cyan.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorCyan);
                checkColorPoint(defaultNoteColor);
            }
        });
        teal.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorTeal);
                checkColorPoint(defaultNoteColor);
            }
        });
        green.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorGreen);
                checkColorPoint(defaultNoteColor);
            }
        });
        lightgreen.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLightGreen);
                checkColorPoint(defaultNoteColor);
            }
        });
        lime.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLime);
                checkColorPoint(defaultNoteColor);
            }
        });
        yellow.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorYellow);
                checkColorPoint(defaultNoteColor);
            }
        });
        amber.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorAmber);
                checkColorPoint(defaultNoteColor);
            }
        });
        orange.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorOrange);
                checkColorPoint(defaultNoteColor);
            }
        });
        deeporange.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorDeepOrange);
                checkColorPoint(defaultNoteColor);
            }
        });
        brown.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBrown);
                checkColorPoint(defaultNoteColor);
            }
        });
        grey.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorGrey);
                checkColorPoint(defaultNoteColor);
            }
        });
        bluegrey.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBlueGrey);
                checkColorPoint(defaultNoteColor);
            }
        });
        // Show Dialog
        newNoteDialog.show();
    }

    private static void checkColorPoint(int color){
        Context context=getContext();

        // Clear All Drawable
        white.get().setImageDrawable(null);red.get().setImageDrawable(null);pink.get().setImageDrawable(null);purple.get().setImageDrawable(null);
        deeppurple.get().setImageDrawable(null);indigo.get().setImageDrawable(null);blue.get().setImageDrawable(null);lightblue.get().setImageDrawable(null);
        cyan.get().setImageDrawable(null);teal.get().setImageDrawable(null);green.get().setImageDrawable(null);lightgreen.get().setImageDrawable(null);
        lime.get().setImageDrawable(null);yellow.get().setImageDrawable(null);amber.get().setImageDrawable(null);orange.get().setImageDrawable(null);
        deeporange.get().setImageDrawable(null);brown.get().setImageDrawable(null);grey.get().setImageDrawable(null);bluegrey.get().setImageDrawable(null);

        if(color==context.getResources().getColor(R.color.cardColorWhite))
            white.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorRed))
            red.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorPink))
            pink.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorPurple))
            purple.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorDeepPurple))
            deeppurple.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorIndigo))
            indigo.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorBlue))
            blue.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorLightBlue))
            lightblue.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorCyan))
            cyan.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorTeal))
            teal.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorGreen))
            green.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorLightGreen))
            lightgreen.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorLime))
            lime.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorYellow))
            yellow.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorAmber))
            amber.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorOrange))
            orange.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorDeepOrange))
            deeporange.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorBrown))
            brown.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorGrey))
            grey.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==context.getResources().getColor(R.color.cardColorBlueGrey))
            bluegrey.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
    }
}
