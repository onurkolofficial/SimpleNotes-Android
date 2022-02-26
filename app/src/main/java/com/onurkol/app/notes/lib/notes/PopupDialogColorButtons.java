package com.onurkol.app.notes.lib.notes;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.lib.ContextManager;

import java.lang.ref.WeakReference;

public class PopupDialogColorButtons {
    static int defaultNoteColor;

    static WeakReference<ImageButton> white, red, pink, purple, deeppurple, indigo,
            blue, lightblue, cyan, teal, green, lightgreen, lime, yellow, amber,
            orange, deeporange, brown, grey, bluegrey;

    public static void initColorButtons(Dialog dialog, Integer defaultColor){
        Context context=ContextManager.getManager().getContext();

        white = new WeakReference<>(dialog.findViewById(R.id.dialogColorWhiteButton));
        red = new WeakReference<>(dialog.findViewById(R.id.dialogColorRedButton));
        pink = new WeakReference<>(dialog.findViewById(R.id.dialogColorPinkButton));
        purple = new WeakReference<>(dialog.findViewById(R.id.dialogColorPurpleButton));
        deeppurple = new WeakReference<>(dialog.findViewById(R.id.dialogColorDeepPurpleButton));
        indigo = new WeakReference<>(dialog.findViewById(R.id.dialogColorIndigoButton));
        blue = new WeakReference<>(dialog.findViewById(R.id.dialogColorBlueButton));
        lightblue = new WeakReference<>(dialog.findViewById(R.id.dialogColorLightBlueButton));
        cyan = new WeakReference<>(dialog.findViewById(R.id.dialogColorCyanButton));
        teal = new WeakReference<>(dialog.findViewById(R.id.dialogColorTealButton));
        green = new WeakReference<>(dialog.findViewById(R.id.dialogColorGreenButton));
        lightgreen = new WeakReference<>(dialog.findViewById(R.id.dialogColorLightGreenButton));
        lime = new WeakReference<>(dialog.findViewById(R.id.dialogColorLimeButton));
        yellow = new WeakReference<>(dialog.findViewById(R.id.dialogColorYellowButton));
        amber = new WeakReference<>(dialog.findViewById(R.id.dialogColorAmberButton));
        orange = new WeakReference<>(dialog.findViewById(R.id.dialogColorOrangeButton));
        deeporange = new WeakReference<>(dialog.findViewById(R.id.dialogColorDeepOrangeButton));
        brown = new WeakReference<>(dialog.findViewById(R.id.dialogColorBrownButton));
        grey = new WeakReference<>(dialog.findViewById(R.id.dialogColorGreyButton));
        bluegrey = new WeakReference<>(dialog.findViewById(R.id.dialogColorBlueGreyButton));

        // First Dialog
        if(defaultColor==null)
            defaultNoteColor = ContextCompat.getColor(context, R.color.cardColorWhite);
        else
            defaultNoteColor = defaultColor;

        white.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorWhite);
            checkColorPoint(defaultNoteColor);
        });
        red.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorRed);
            checkColorPoint(defaultNoteColor);
        });
        pink.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorPink);
            checkColorPoint(defaultNoteColor);
        });
        purple.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorPurple);
            checkColorPoint(defaultNoteColor);
        });
        deeppurple.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorDeepPurple);
            checkColorPoint(defaultNoteColor);
        });
        indigo.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorIndigo);
            checkColorPoint(defaultNoteColor);
        });
        blue.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorBlue);
            checkColorPoint(defaultNoteColor);
        });
        lightblue.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorLightBlue);
            checkColorPoint(defaultNoteColor);
        });
        cyan.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorCyan);
            checkColorPoint(defaultNoteColor);
        });
        teal.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorTeal);
            checkColorPoint(defaultNoteColor);
        });
        green.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorGreen);
            checkColorPoint(defaultNoteColor);
        });
        lightgreen.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorLightGreen);
            checkColorPoint(defaultNoteColor);
        });
        lime.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorLime);
            checkColorPoint(defaultNoteColor);
        });
        yellow.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorYellow);
            checkColorPoint(defaultNoteColor);
        });
        amber.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorAmber);
            checkColorPoint(defaultNoteColor);
        });
        orange.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorOrange);
            checkColorPoint(defaultNoteColor);
        });
        deeporange.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorDeepOrange);
            checkColorPoint(defaultNoteColor);
        });
        brown.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorBrown);
            checkColorPoint(defaultNoteColor);
        });
        grey.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorGrey);
            checkColorPoint(defaultNoteColor);
        });
        bluegrey.get().setOnClickListener(view -> {
            defaultNoteColor=ContextCompat.getColor(context,R.color.cardColorBlueGrey);
            checkColorPoint(defaultNoteColor);
        });
    }

    public static int getDefaultNoteColor(){
        return defaultNoteColor;
    }

    public static void checkColorPoint(int color) {
        Context context = ContextManager.getManager().getContext();

        white.get().setImageDrawable(null);red.get().setImageDrawable(null);pink.get().setImageDrawable(null);purple.get().setImageDrawable(null);
        deeppurple.get().setImageDrawable(null);indigo.get().setImageDrawable(null);blue.get().setImageDrawable(null);lightblue.get().setImageDrawable(null);
        cyan.get().setImageDrawable(null);teal.get().setImageDrawable(null);green.get().setImageDrawable(null);lightgreen.get().setImageDrawable(null);
        lime.get().setImageDrawable(null);yellow.get().setImageDrawable(null);amber.get().setImageDrawable(null);orange.get().setImageDrawable(null);
        deeporange.get().setImageDrawable(null);brown.get().setImageDrawable(null);grey.get().setImageDrawable(null);bluegrey.get().setImageDrawable(null);

        if(color== ContextCompat.getColor(context,R.color.cardColorWhite))
            white.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorRed))
            red.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorPink))
            pink.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorPurple))
            purple.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorDeepPurple))
            deeppurple.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorIndigo))
            indigo.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorBlue))
            blue.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorLightBlue))
            lightblue.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorCyan))
            cyan.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorTeal))
            teal.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorGreen))
            green.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorLightGreen))
            lightgreen.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorLime))
            lime.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorYellow))
            yellow.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorAmber))
            amber.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorOrange))
            orange.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorDeepOrange))
            deeporange.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorBrown))
            brown.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorGrey))
            grey.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
        else if(color==ContextCompat.getColor(context,R.color.cardColorBlueGrey))
            bluegrey.get().setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24));
    }
}
