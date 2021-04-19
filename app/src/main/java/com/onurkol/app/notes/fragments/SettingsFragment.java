package com.onurkol.app.notes.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.AboutActivity;
import com.onurkol.app.notes.tools.ContextTool;

import static com.onurkol.app.notes.activity.MainActivity.isThemeChanged;
import static com.onurkol.app.notes.data.PreferenceData.THEME_LIST;
import static com.onurkol.app.notes.data.PreferenceData.setupAppThemes;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getPreferenceInteger;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.getSPreferences;
import static com.onurkol.app.notes.tools.SharedPreferenceManager.setPreferenceInteger;

public class SettingsFragment extends PreferenceFragmentCompat {
    ListPreference themesPref;
    CheckBoxPreference lineNumPref;
    Preference aboutPref;
    Intent aboutIntent;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Set Resource
        setPreferencesFromResource(R.xml.settings_preference, rootKey);

        // Get Preferences
        aboutPref=findPreference("show_about");
        lineNumPref=findPreference("line_numbers_check");
        themesPref=findPreference("app_themes_list");

        // Check Themes
        if(THEME_LIST.size()<=0)
            setupAppThemes();

        // Add Themes
        CharSequence[] themeEntries=new String[THEME_LIST.size()];
        CharSequence[] themeEntryValues=new String[THEME_LIST.size()];

        for(int i=0; i<THEME_LIST.size(); i++){
            themeEntries[i]=THEME_LIST.get(i);
            themeEntryValues[i]=String.valueOf(i);
        }
        themesPref.setEntries(themeEntries);
        themesPref.setEntryValues(themeEntryValues);

        // Set Default Values
        // Theme
        themesPref.setValue(String.valueOf(getPreferenceInteger(getSPreferences(), "APP_THEME")));
        // Line Numbers
        int lineNumberData=getPreferenceInteger(getSPreferences(), "APP_LINE_NUMBER");
        lineNumPref.setChecked(lineNumberData==1);

        // Preference Click/Change Events
        aboutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Start Web Setting Activity
                startActivity(new Intent(getActivity(), AboutActivity.class));
                return false;
            }
        });
        themesPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                // Convert Object to Integer
                int convertNewValue=Integer.parseInt(newValue.toString());
                // Save Preference
                setPreferenceInteger(getSPreferences(), "APP_THEME",convertNewValue);
                // Set Value
                themesPref.setValue(String.valueOf(convertNewValue));
                // Change Theme
                setApplicationTheme(getActivity());
                // Save Value
                isThemeChanged=true;
                // Recreate View
                getActivity().recreate();

                return false;
            }
        });
        lineNumPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Get Value
                int getValue=getPreferenceInteger(getSPreferences(), "APP_LINE_NUMBER");
                // Set & Save New Value
                int newValue=((getValue==1) ? 0 : ((getValue==0) ? 1 : 0));
                setPreferenceInteger(getSPreferences(),"APP_LINE_NUMBER", newValue);

                return false;
            }
        });
    }

    public static void setApplicationTheme(Context context){
        // Set Theme
        int getAppTheme=getPreferenceInteger(getSPreferences(), "APP_THEME");
        if(getAppTheme==0)
            context.setTheme(R.style.LightTheme);
        else if(getAppTheme==1)
            context.setTheme(R.style.DarkTheme);
    }
}