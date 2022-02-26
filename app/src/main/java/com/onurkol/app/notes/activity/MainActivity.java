package com.onurkol.app.notes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.onurkol.app.notes.R;
import com.onurkol.app.notes.adapters.NoteListAdapter;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppDataManager;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;
import com.onurkol.app.notes.popups.PopupNewNote;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements AppData {
    public static boolean isCreated,
            isConfigChanged=false,
            onUpdateNoteOnSettings=false;

    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 11;

    NoteManager noteManager;

    TextView toolbarTitle;
    ListView noteListView;
    ImageButton closeAppButton,settingsButton;
    LinearLayout noNoteLayout;
    FloatingActionButton newNoteButton;

    NoteListAdapter noteListAdapter;
    static WeakReference<NoteListAdapter> noteListAdapterStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContextManager.BuildBase(this);

        super.onCreate(savedInstanceState);
        AppDataManager.loadApplicationData();
        setContentView(R.layout.activity_main);

        noteManager=NoteManager.getManager();

        toolbarTitle=findViewById(R.id.toolbarTitle);
        noteListView=findViewById(R.id.noteListView);
        closeAppButton=findViewById(R.id.closeAppButton);
        settingsButton=findViewById(R.id.settingsButton);
        noNoteLayout=findViewById(R.id.View_No_Note);
        newNoteButton=findViewById(R.id.newNoteButton);

        toolbarTitle.setText(getString(R.string.app_name));

        closeAppButton.setOnClickListener(view -> System.exit(0));
        settingsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
        newNoteButton.setOnClickListener(v -> PopupNewNote.Show());

        noteListAdapter=new NoteListAdapter(this,noteListView,NOTE_LIST);
        noteListView.setAdapter(noteListAdapter);
        noteListAdapterStatic=new WeakReference<>(noteListAdapter);

        if(noteManager.getNoteList().size()>0) {
            noNoteLayout.setVisibility(View.GONE);
            noteManager.syncNoteListDataOnPreference();
        }

        // Ads (StartApp)
        String getAppId=getString(R.string.startapp_app_id);
        StartAppSDK.init(this, getAppId);
        StartAppAd.disableSplash();

        final Banner appBanner=findViewById(R.id.startAppBanner);
        appBanner.setBannerListener(new BannerListener() {
            @Override
            public void onReceiveAd(View view) {
                appBanner.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailedToReceiveAd(View view) {
                appBanner.setVisibility(View.GONE);
            }
            @Override
            public void onImpression(View view) {}
            @Override
            public void onClick(View view) {}
        });
        appBanner.setVisibility(View.GONE);

        isCreated=true;
    }

    public static void onUpdateNoteList(){
        updateListClass(false);
    }
    public static void onUpdateNoteListSet(){
        updateListClass(true);
    }

    private static void updateListClass(boolean isSet){
        Activity $this=ContextManager.getManager().getContextActivity();

        ListView noteListView=$this.findViewById(R.id.noteListView);
        LinearLayout noNoteLayout=$this.findViewById(R.id.View_No_Note);

        if(isSet)
            noteListAdapterStatic.get().notifyDataSetChanged();
        noteListView.invalidateViews();
        if(NoteManager.getManager().getNoteList().size()>0)
            noNoteLayout.setVisibility(View.GONE);
        else
            noNoteLayout.setVisibility(View.VISIBLE);
    }

    public static void startNoteEditActivity(Context context, int position){
        Intent editNoteIntent=new Intent(context, NoteEditActivity.class);
        editNoteIntent.putExtra(KEY_EXTRA_NOTE_POSITION, position);
        context.startActivity(editNoteIntent);
    }

    @Override
    protected void onResume() {
        ContextManager.BuildBase(this);

        if(isConfigChanged) {
            AppDataManager.loadApplicationData();
            recreate();
            isConfigChanged=false;
        }
        if(onUpdateNoteOnSettings) {
            onUpdateNoteListSet();
            onUpdateNoteOnSettings=false;
        }
        super.onResume();
    }

    @Override
    protected void onStart() {
        // Create App Update Manager
        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(result -> {
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/)){
                try {
                    mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.FLEXIBLE, MainActivity.this, RC_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (result.installStatus() == InstallStatus.DOWNLOADED){
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            }
        });
        super.onStart();
    }
    private void popupSnackbarForCompleteUpdate() {
        Snackbar snackbar = Snackbar.make(
                findViewById(R.id.appMainLayout),
                getString(R.string.update_new_version_available),
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.install_text), view -> {
            if (mAppUpdateManager != null)
                mAppUpdateManager.completeUpdate();
        });
        snackbar.setActionTextColor(ContextCompat.getColor(this,R.color.primary));
        snackbar.show();
    }
    InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED){
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            } else if (state.installStatus() == InstallStatus.INSTALLED){
                if (mAppUpdateManager != null)
                    mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                // Show Message
                Toast.makeText(MainActivity.this, getString(R.string.update_install_completed), Toast.LENGTH_SHORT).show();
            }
            /* else {
                //Log.e(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
            } */
        }
    };
}