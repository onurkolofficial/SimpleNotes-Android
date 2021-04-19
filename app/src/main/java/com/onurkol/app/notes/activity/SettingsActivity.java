package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.fragments.SettingsFragment;

import static com.onurkol.app.notes.fragments.SettingsFragment.setApplicationTheme;
import static com.onurkol.app.notes.tools.ContextTool.setContext;

public class SettingsActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView settingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Current Activity Context
        setContext(this);
        // Load Theme
        setApplicationTheme(this);
        // Create View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Get Elements
        backButton=findViewById(R.id.backSettingsButton);
        settingName=findViewById(R.id.settingsTitle);

        // Set Toolbar Title
        settingName.setText(getString(R.string.settings_text));

        // Button Click Events
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close This Activity
                finish();
            }
        });

        // Get Fragment
        getSupportFragmentManager().beginTransaction().add(R.id.settingsFragmentContent,new SettingsFragment()).commit();
    }
}