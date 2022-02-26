package com.onurkol.app.notes.lib;

import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.core.LanguageManager;
import com.onurkol.app.notes.lib.core.ThemeManager;

import java.lang.ref.WeakReference;

public class AppDataManager implements AppData {
    static WeakReference<AppPreferenceManager> prefManagerStatic;

    public static void loadApplicationData(){
        prefManagerStatic=new WeakReference<>(AppPreferenceManager.getInstance());
        loadPreferenceData();
    }

    private static void loadPreferenceData(){
        if(prefManagerStatic.get().getInt(KEY_APP_THEME)==AppPreferenceManager.INTEGER_NULL)
            prefManagerStatic.get().setPreference(KEY_APP_THEME, DEFAULT_APP_THEME);
        if(prefManagerStatic.get().getInt(KEY_APP_LANGUAGE)==AppPreferenceManager.INTEGER_NULL)
            prefManagerStatic.get().setPreference(KEY_APP_LANGUAGE, DEFAULT_APP_LANGUAGE);
        if(prefManagerStatic.get().getInt(KEY_NOTE_LINE_NUMBERS)==AppPreferenceManager.INTEGER_NULL)
            prefManagerStatic.get().setPreference(KEY_NOTE_LINE_NUMBERS, DEFAULT_NOTE_LINE_NUMBERS);
        if(prefManagerStatic.get().getInt(KEY_NOTE_DATE_INFO)==AppPreferenceManager.INTEGER_NULL)
            prefManagerStatic.get().setPreference(KEY_NOTE_DATE_INFO, DEFAULT_NOTE_DATE_INFO);
        if(prefManagerStatic.get().getString(KEY_NOTE_LIST)==null)
            prefManagerStatic.get().setPreference(KEY_NOTE_LIST, "");
        initView();
    }

    private static void initView(){
        // Load Theme
        ThemeManager.getInstance().setAppTheme(prefManagerStatic.get().getInt(KEY_APP_THEME));
        // Load Language
        LanguageManager.getInstance().setAppLanguage(prefManagerStatic.get().getInt(KEY_APP_LANGUAGE));
    }
}
