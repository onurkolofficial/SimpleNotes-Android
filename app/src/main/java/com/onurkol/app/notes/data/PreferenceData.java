package com.onurkol.app.notes.data;

import android.content.SharedPreferences;

import com.onurkol.app.notes.R;

import java.util.ArrayList;
import java.util.List;

import static com.onurkol.app.notes.tools.ContextTool.getContext;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.INTEGER_NULL;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.STRING_NULL;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceInteger;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceString;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceInteger;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceString;

public class PreferenceData {
    static SharedPreferences prefs;

    // Default Settings
    // Settings: {1} ON | {0} OFF
    private static final int DEF_APP_LINE_NUMBER=0;

    // Themes: {0} LIGHT | {1} DARK
    private static final int DEF_APP_THEME=0;

    // Lists
    public static final List<String> THEME_LIST=new ArrayList<>();
    public static List<NoteData> APP_NOTES=new ArrayList<>();

    public static void setupAppThemes(){
        // Themes
        THEME_LIST.add(0,getContext().getString(R.string.theme_light_text));
        THEME_LIST.add(1,getContext().getString(R.string.theme_dark_text));
    }

    public static void startPreferenceData(){
        prefs=getSPreferences();
        // Desktop Mode
        if(getPreferenceInteger(prefs, "APP_LINE_NUMBER")==INTEGER_NULL)
            setPreferenceInteger(prefs,"APP_LINE_NUMBER",DEF_APP_LINE_NUMBER);
        // Theme
        if(getPreferenceInteger(prefs, "APP_THEME")==INTEGER_NULL)
            setPreferenceInteger(prefs,"APP_THEME",DEF_APP_THEME);
        // Notes
        if(getPreferenceString(prefs, "APP_NOTES")==null)
            setPreferenceString(prefs,"APP_NOTES",STRING_NULL);
    }

    public static int getCurrentApplicationTheme(){
        prefs=getSPreferences();
        int getAppTheme=getPreferenceInteger(prefs,"APP_THEME");
        if(getAppTheme==0)
            return R.style.LightTheme;
        else if(getAppTheme==1)
            return R.style.DarkTheme;
        else
            return R.style.LightTheme; // Default
    }
}
