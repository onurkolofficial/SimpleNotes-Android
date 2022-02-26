package com.onurkol.app.notes.activity.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.fragments.settings.SettingsAboutFragment;
import com.onurkol.app.notes.fragments.settings.SettingsThemeFragment;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.ContextManager;

public class SettingsThemeActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView settingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContextManager.Build(this);

        super.onCreate(savedInstanceState);
        AppDataManager.loadApplicationData();
        setContentView(R.layout.activity_settings_theme);

        backButton=findViewById(R.id.backButton);
        settingName=findViewById(R.id.settingName);

        settingName.setText(getString(R.string.themes_text));

        backButton.setOnClickListener(view -> finish());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settingsThemesFragmentContent,new SettingsThemeFragment()).commit();
    }
}