package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.fragments.SettingsFragment;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.ContextManager;

public class SettingsActivity extends AppCompatActivity {
    public static boolean isConfigChanged=false;

    ImageButton backButton;
    TextView settingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContextManager.Build(this);

        super.onCreate(savedInstanceState);
        AppDataManager.loadApplicationData();
        setContentView(R.layout.activity_settings);

        backButton=findViewById(R.id.backButton);
        settingName=findViewById(R.id.settingName);

        settingName.setText(getString(R.string.settings_text));

        backButton.setOnClickListener(view -> finish());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settingsFragmentContent,new SettingsFragment()).commit();
    }

    @Override
    protected void onResume() {
        ContextManager.Build(this);

        if(isConfigChanged) {
            MainActivity.isConfigChanged=true;
            AppDataManager.loadApplicationData();
            recreate();
            isConfigChanged=false;
        }
        super.onResume();
    }
}