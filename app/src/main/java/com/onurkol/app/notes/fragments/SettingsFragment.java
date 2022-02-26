package com.onurkol.app.notes.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.MainActivity;
import com.onurkol.app.notes.activity.settings.SettingsAboutActivity;
import com.onurkol.app.notes.activity.settings.SettingsLanguageActivity;
import com.onurkol.app.notes.activity.settings.SettingsThemeActivity;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppPreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat implements AppData {

    Preference themesPref,langsPref,aboutPref;
    CheckBoxPreference lineNumbersPref,dateInfoPref;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings,rootKey);

        AppPreferenceManager prefManager=AppPreferenceManager.getInstance();

        int settingLineNumbers=prefManager.getInt(KEY_NOTE_LINE_NUMBERS);
        int settingDateInfo=prefManager.getInt(KEY_NOTE_DATE_INFO);

        dateInfoPref=findPreference("dateInfoPref");
        lineNumbersPref=findPreference("lineNumberPref");
        themesPref=findPreference("themesPref");
        langsPref=findPreference("languagesPref");
        aboutPref=findPreference("aboutPref");

        lineNumbersPref.setChecked(settingLineNumbers==1);
        dateInfoPref.setChecked(settingDateInfo==1);

        lineNumbersPref.setOnPreferenceClickListener(preference -> {
            int newValue=((settingLineNumbers==1) ? 0 : ((settingLineNumbers==0) ? 1 : 0));
            prefManager.setPreference(KEY_NOTE_LINE_NUMBERS, newValue);
            return false;
        });
        dateInfoPref.setOnPreferenceClickListener(preference -> {
            int newValue=((settingDateInfo==1) ? 0 : ((settingDateInfo==0) ? 1 : 0));
            prefManager.setPreference(KEY_NOTE_DATE_INFO, newValue);
            MainActivity.onUpdateNoteOnSettings=true;
            return false;
        });
        themesPref.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(getActivity(), SettingsThemeActivity.class));
            return false;
        });
        langsPref.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(getActivity(), SettingsLanguageActivity.class));
            return false;
        });
        aboutPref.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(getActivity(), SettingsAboutActivity.class));
            return false;
        });
    }

}
