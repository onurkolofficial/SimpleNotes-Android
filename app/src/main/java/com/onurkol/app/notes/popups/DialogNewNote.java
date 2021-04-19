package com.onurkol.app.notes.popups;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import com.onurkol.app.notes.R;

import static com.onurkol.app.notes.activity.MainActivity.noNoteLayoutWeak;
import static com.onurkol.app.notes.activity.MainActivity.noteListViewWeak;
import static com.onurkol.app.notes.controller.NoteController.addNewNote;
import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.RandomTool.random;

public class DialogNewNote {
    public static int defaultNoteColor;
    public static String defaultNoteName;

    public static void showNewNoteDialog() {
        Context context=getContext();
        Activity activity=(Activity)context;

        defaultNoteName = context.getString(R.string.default_note_name);
        defaultNoteColor = context.getResources().getColor(R.color.cardColorWhite);
        final EditText dialogNameEditText;
        Button dialogCancelButton, dialogNoteAddButton;
        final ImageButton white, red, pink, purple, deeppurple, indigo, blue, lightblue, cyan, teal, green, lightgreen, lime, yellow, amber, orange, deeporange, brown, grey, bluegrey;
        final Dialog newNoteDialog = new Dialog(activity);
        newNoteDialog.setContentView(R.layout.dialog_new_note);
        // Dialog Main Elements
        dialogNameEditText = newNoteDialog.findViewById(R.id.dialogNewNoteName);
        dialogCancelButton = newNoteDialog.findViewById(R.id.dialogNoteCancelButton);
        dialogNoteAddButton = newNoteDialog.findViewById(R.id.dialogNoteAddButton);
        // Color Buttons
        white = newNoteDialog.findViewById(R.id.dialogColorWhiteButton);
        red = newNoteDialog.findViewById(R.id.dialogColorRedButton);
        pink = newNoteDialog.findViewById(R.id.dialogColorPinkButton);
        purple = newNoteDialog.findViewById(R.id.dialogColorPurpleButton);
        deeppurple = newNoteDialog.findViewById(R.id.dialogColorDeepPurpleButton);
        indigo = newNoteDialog.findViewById(R.id.dialogColorIndigoButton);
        blue = newNoteDialog.findViewById(R.id.dialogColorBlueButton);
        lightblue = newNoteDialog.findViewById(R.id.dialogColorLightBlueButton);
        cyan = newNoteDialog.findViewById(R.id.dialogColorCyanButton);
        teal = newNoteDialog.findViewById(R.id.dialogColorTealButton);
        green = newNoteDialog.findViewById(R.id.dialogColorGreenButton);
        lightgreen = newNoteDialog.findViewById(R.id.dialogColorLightGreenButton);
        lime = newNoteDialog.findViewById(R.id.dialogColorLimeButton);
        yellow = newNoteDialog.findViewById(R.id.dialogColorYellowButton);
        amber = newNoteDialog.findViewById(R.id.dialogColorAmberButton);
        orange = newNoteDialog.findViewById(R.id.dialogColorOrangeButton);
        deeporange = newNoteDialog.findViewById(R.id.dialogColorDeepOrangeButton);
        brown = newNoteDialog.findViewById(R.id.dialogColorBrownButton);
        grey = newNoteDialog.findViewById(R.id.dialogColorGreyButton);
        bluegrey = newNoteDialog.findViewById(R.id.dialogColorBlueGreyButton);

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
                String noteName;
                int noteColor=defaultNoteColor;
                newNoteDialog.dismiss();

                if(dialogNameEditText.getText().toString().equals(""))
                    noteName=defaultNoteName;
                else
                    noteName=dialogNameEditText.getText().toString();

                String noteID=random(20);

                // Add Note
                addNewNote(noteID,noteName,null,noteColor);
                // Refresh ListView
                noteListViewWeak.get().invalidateViews();
                // Hide No Note Layout
                noNoteLayoutWeak.get().setVisibility(View.GONE);
            }
        });
        
        // Color Buttons
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorWhite);
                white.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorRed);
                red.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                white.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorPink);
                pink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);white.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorPurple);
                purple.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);white.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        deeppurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorDeepPurple);
                deeppurple.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);white.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        indigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorIndigo);
                indigo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                white.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBlue);
                blue.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);white.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        lightblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLightBlue);
                lightblue.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);white.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorCyan);
                cyan.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);white.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        teal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorTeal);
                teal.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                white.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorGreen);
                green.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);white.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        lightgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLightGreen);
                lightgreen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);white.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        lime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorLime);
                lime.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);white.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorYellow);
                yellow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                white.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorAmber);
                amber.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);white.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorOrange);
                orange.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);white.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        deeporange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorDeepOrange);
                deeporange.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);white.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBrown);
                brown.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                white.setImageDrawable(null);grey.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorGrey);
                grey.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);white.setImageDrawable(null);bluegrey.setImageDrawable(null);
            }
        });
        bluegrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultNoteColor=context.getResources().getColor(R.color.cardColorBlueGrey);
                bluegrey.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
                red.setImageDrawable(null);pink.setImageDrawable(null);purple.setImageDrawable(null);deeppurple.setImageDrawable(null);
                indigo.setImageDrawable(null);blue.setImageDrawable(null);lightblue.setImageDrawable(null);cyan.setImageDrawable(null);
                teal.setImageDrawable(null);green.setImageDrawable(null);lightgreen.setImageDrawable(null);lime.setImageDrawable(null);
                yellow.setImageDrawable(null);amber.setImageDrawable(null);orange.setImageDrawable(null);deeporange.setImageDrawable(null);
                brown.setImageDrawable(null);grey.setImageDrawable(null);white.setImageDrawable(null);
            }
        });


        // Show Dialog
        newNoteDialog.show();
    }
}
