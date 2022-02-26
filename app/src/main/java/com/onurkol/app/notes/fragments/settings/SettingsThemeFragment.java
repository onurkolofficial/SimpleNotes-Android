package com.onurkol.app.notes.fragments.settings;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.AppPreferenceManager;

public class SettingsThemeFragment extends PreferenceFragmentCompat implements AppData {

    CheckBoxPreference systemThemePref,lightThemePref,darkThemePref;

    AppPreferenceManager prefManager;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings_theme,rootKey);

        prefManager=AppPreferenceManager.getInstance();

        systemThemePref=findPreference("systemThemePref");
        lightThemePref=findPreference("lightThemePref");
        darkThemePref=findPreference("darkThemePref");

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
            systemThemePref.setVisible(false);

        systemThemePref.setOnPreferenceClickListener(preference -> {
            systemThemePref.setChecked(true);
            lightThemePref.setChecked(false);
            darkThemePref.setChecked(false);

            prefManager.setPreference(KEY_APP_THEME,2);

            AppDataManager.loadApplicationData();
            return false;
        });
        lightThemePref.setOnPreferenceClickListener(preference -> {
            systemThemePref.setChecked(false);
            lightThemePref.setChecked(true);
            darkThemePref.setChecked(false);

            prefManager.setPreference(KEY_APP_THEME,0);

            AppDataManager.loadApplicationData();
            return false;
        });
        darkThemePref.setOnPreferenceClickListener(preference -> {
            systemThemePref.setChecked(false);
            lightThemePref.setChecked(false);
            darkThemePref.setChecked(true);

            prefManager.setPreference(KEY_APP_THEME,1);

            AppDataManager.loadApplicationData();
            return false;
        });

        int theme_id=prefManager.getInt(KEY_APP_THEME);
        systemThemePref.setChecked(theme_id==2);
        lightThemePref.setChecked(theme_id==0);
        darkThemePref.setChecked(theme_id==1);
    }
}
