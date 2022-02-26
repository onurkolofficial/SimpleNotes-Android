package com.onurkol.app.notes.fragments.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.SettingsActivity;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.AppPreferenceManager;

public class SettingsLanguageFragment extends PreferenceFragmentCompat implements AppData {

    CheckBoxPreference systemLangPref,enLangPref,trLangPref;

    AppPreferenceManager prefManager;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings_language,rootKey);

        prefManager=AppPreferenceManager.getInstance();

        systemLangPref=findPreference("systemLangPref");
        enLangPref=findPreference("englishLangPref");
        trLangPref=findPreference("turkishLangPref");

        systemLangPref.setOnPreferenceClickListener(preference -> {
            changeLanguage(0);
            return false;
        });
        enLangPref.setOnPreferenceClickListener(preference -> {
            changeLanguage(1);
            return false;
        });
        trLangPref.setOnPreferenceClickListener(preference -> {
            changeLanguage(2);
            return false;
        });

        int language_id=prefManager.getInt(KEY_APP_LANGUAGE);
        checkboxChecked(language_id);
    }

    private void changeLanguage(int langId){
        checkboxChecked(langId);

        prefManager.setPreference(KEY_APP_LANGUAGE,langId);

        // <BUG> 'recreate' & 'loadApplicationData'
        getActivity().recreate();
        AppDataManager.loadApplicationData();
        SettingsActivity.isConfigChanged=true;
    }

    private void checkboxChecked(int langId){
        systemLangPref.setChecked(langId==0);
        enLangPref.setChecked(langId==1);
        trLangPref.setChecked(langId==2);
    }
}
