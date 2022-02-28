package com.onurkol.app.notes.activity.widgets;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.onurkol.app.notes.adapters.NoteListSelectToSetAdapter;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppPreferenceManager;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;
import com.onurkol.app.notes.widgets.NoteEditWidget;
import com.onurkol.app.notes.R;
import com.onurkol.app.notes.databinding.NoteEditWidgetConfigureBinding;

/**
 * The configuration screen for the {@link NoteEditWidget NoteEditWidget} AppWidget.
 */
public class NoteEditWidgetConfigureActivity extends Activity implements AppData {
    public static int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    ListView noteListView;
    ImageButton backButton;
    TextView settingName;
    LinearLayout noNoteLayout;

    private NoteEditWidgetConfigureBinding binding;

    public NoteEditWidgetConfigureActivity() {
        super();
    }

    public static void saveWidgetDataInt(String Name, int Value) {
        AppPreferenceManager.getInstance().setPreference(Name, Value);
    }

    public static int loadWidgetDataInt(String Name) {
        return AppPreferenceManager.getInstance().getInt(Name);
    }

    public static void acceptWidgetDataOnClick(Context context, int Index){
        NoteEditWidgetConfigureActivity.saveWidgetDataInt(WIDGET_KEY_NOTE_INDEX, Index);

        // It is the responsibility of the configuration activity to update the app widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        NoteEditWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        ((Activity)context).setResult(RESULT_OK, resultValue);
        ((Activity)context).finish();
    }

    @Override
    public void onCreate(Bundle icicle) {
        ContextManager.Build(this);
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        binding = NoteEditWidgetConfigureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View toolbarView=binding.noteListToolbar.getRoot().getRootView();

        noteListView = binding.noteListView;
        backButton = toolbarView.findViewById(R.id.backButton);
        settingName = toolbarView.findViewById(R.id.settingName);
        noNoteLayout = binding.noNoteLayout;

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        settingName.setText(getString(R.string.widget_note_edit_select_text));

        backButton.setOnClickListener(view -> finish());

        noteListView.setAdapter(new NoteListSelectToSetAdapter(this, noteListView, NoteManager.getManager().getNoteList()));

        if(NoteManager.getManager().getNoteList().size()>0)
            noNoteLayout.setVisibility(View.GONE);
        else
            noNoteLayout.setVisibility(View.VISIBLE);
    }
}