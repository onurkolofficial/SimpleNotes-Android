package com.onurkol.app.notes.tools;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.onurkol.app.notes.R;

public class ClassicAlertDialog {
    public static void Alert(Context context, String message){
        // Show Alert Dialog
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setMessage(message);
        dialog.setPositiveButton(R.string.ok_text, (dialogInterface, i) -> {
            // Dismiss
        });
        dialog.setCancelable(true);
        dialog.create().show();
    }
}
