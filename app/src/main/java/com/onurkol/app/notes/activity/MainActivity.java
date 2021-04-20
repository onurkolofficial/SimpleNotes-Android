package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onurkol.app.notes.R;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.lang.ref.WeakReference;

import static com.onurkol.app.notes.data.PreferenceData.startPreferenceData;
import static com.onurkol.app.notes.fragments.SettingsFragment.setApplicationTheme;
import static com.onurkol.app.notes.popups.PopupEditNewNote.showNewNoteDialog;
import static com.onurkol.app.notes.tools.ContextTool.setContext;
import static com.onurkol.app.notes.tools.NoteManager.buildNoteList;
import static com.onurkol.app.notes.tools.NoteManager.getNoteDataList;

public class MainActivity extends AppCompatActivity {

    TextView toolbarTitle;
    ImageButton toolbarExitBtn,toolbarSettingsBtn;
    FloatingActionButton newNoteBtn;
    public static WeakReference<ListView> noteListViewWeak;
    ListView noteListView;
    public static WeakReference<LinearLayout> noNoteLayoutWeak;
    LinearLayout noNoteLayout;

    Intent settingsIntent;

    Banner startAppBanner1;

    public static Boolean isThemeChanged=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Current Activity Context
        setContext(this);
        // Load Preference Data
        startPreferenceData();
        // Load Theme
        setApplicationTheme(this);
        // Create View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ads Initialize
        String getAppId=getString(R.string.startapp_app_id);
        StartAppSDK.init(this, getAppId, false);
        // Disable Startapp Splash Screen.
        StartAppAd.disableSplash();
        // Get Ads Element & Set Listener
        startAppBanner1=findViewById(R.id.startAppBanner1);
        // Set Listener
        startAppBanner1.setBannerListener(new BannerListener() {
            @Override
            public void onReceiveAd(View view) {
                startAppBanner1.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailedToReceiveAd(View view) {
                startAppBanner1.setVisibility(View.GONE);
            }
            @Override
            public void onImpression(View view) {}
            @Override
            public void onClick(View view) {}
        });
        // Hide Default
        startAppBanner1.setVisibility(View.GONE);

        // Get Elements
        noNoteLayoutWeak=new WeakReference<>(findViewById(R.id.noNewNoteLayout));
        noNoteLayout=noNoteLayoutWeak.get();
        noteListViewWeak=new WeakReference<>(findViewById(R.id.noteListView));
        noteListView=noteListViewWeak.get();
        toolbarTitle=findViewById(R.id.applicationToolbarTitle);
        toolbarExitBtn=findViewById(R.id.exitAppButton);
        toolbarSettingsBtn=findViewById(R.id.settingsButton);
        newNoteBtn=findViewById(R.id.newNoteButton);

        // Set Toolbar Title
        toolbarTitle.setText(getString(R.string.app_name));

        // Exit Button
        toolbarExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        toolbarSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsIntent=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        // New Note Button
        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewNoteDialog();
            }
        });

        // Init ...
        buildNoteList(noteListView);
    }

    @Override
    protected void onResume() {
        if(isThemeChanged){
            isThemeChanged=false;
            this.recreate();
            // Fixed theme change to reset layout issue.
            getNoteDataList();
        }
        // Fixed back activity to set context
        setContext(this);
        super.onResume();
    }
}