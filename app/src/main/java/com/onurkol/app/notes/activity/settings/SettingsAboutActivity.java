package com.onurkol.app.notes.activity.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.fragments.settings.SettingsAboutFragment;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.ContextManager;

public class SettingsAboutActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView settingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContextManager.Build(this);

        super.onCreate(savedInstanceState);
        AppDataManager.loadApplicationData();
        setContentView(R.layout.activity_settings_about);

        backButton=findViewById(R.id.backButton);
        settingName=findViewById(R.id.settingName);

        settingName.setText(getString(R.string.about_text));

        backButton.setOnClickListener(view -> finish());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settingsAboutFragmentContent,new SettingsAboutFragment()).commit();
    }
}