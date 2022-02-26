package com.onurkol.app.notes.lib.core;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {
    private static ThemeManager instance=null;

    private ThemeManager(){}

    public static synchronized ThemeManager getInstance(){
        if(instance==null)
            instance=new ThemeManager();
        return instance;
    }

    public void setAppTheme(int Theme){
        if(Theme==0)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else if(Theme==1)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else if(Theme==2)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }
}
