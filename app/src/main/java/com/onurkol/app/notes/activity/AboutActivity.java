package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onurkol.app.notes.BuildConfig;
import com.onurkol.app.notes.R;

import static com.onurkol.app.notes.fragments.SettingsFragment.setApplicationTheme;

public class AboutActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView settingName,versionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load Theme
        setApplicationTheme(this);
        // Create View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Get Application Version
        String appVersion= BuildConfig.VERSION_NAME+"-"+BuildConfig.VERSION_CODE;

        // Get Elements
        backButton=findViewById(R.id.backSettingsButton);
        settingName=findViewById(R.id.settingsTitle);
        versionInfo=findViewById(R.id.versionInfo);

        // Set Toolbar Title
        settingName.setText(getString(R.string.about_text));

        // Button Click Events
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close This Activity
                finish();
            }
        });

        // Set About Text
        versionInfo.setText(appVersion);
    }
}