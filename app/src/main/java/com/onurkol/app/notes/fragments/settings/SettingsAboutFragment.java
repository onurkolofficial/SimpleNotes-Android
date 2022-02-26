package com.onurkol.app.notes.fragments.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.onurkol.app.notes.BuildConfig;
import com.onurkol.app.notes.R;

public class SettingsAboutFragment extends PreferenceFragmentCompat {
    Preference appVersionPref,androidVersionPref,openDevWebPref;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings_about,rootKey);

        String appVersion=BuildConfig.VERSION_NAME+"-"+BuildConfig.VERSION_CODE;
        String androidVersion=Build.VERSION.RELEASE+" - API "+Build.VERSION.SDK_INT;
        String developerWebPage="https://onurkolofficial.epizy.com/en";

        appVersionPref=findPreference("appVersionPref");
        androidVersionPref=findPreference("androidVersionPref");
        openDevWebPref=findPreference("devWebPref");

        appVersionPref.setSummary(appVersion);
        androidVersionPref.setSummary(androidVersion);

        openDevWebPref.setOnPreferenceClickListener(preference -> {
            openWebActivity(developerWebPage);
            return false;
        });
    }

    public void openWebActivity(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
