package com.onurkol.app.notes.widgets;

import static com.onurkol.app.notes.lib.AppPreferenceManager.INTEGER_NULL;
import static com.onurkol.app.notes.tools.CharLimiter.Limit;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.NoteEditActivity;
import com.onurkol.app.notes.activity.widgets.NoteEditWidgetConfigureActivity;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.ContextManager;
import com.onurkol.app.notes.lib.notes.NoteManager;
import com.onurkol.app.notes.widgets.noteEditWidget.Service;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NoteEditWidgetConfigureActivity NoteEditWidgetConfigureActivity}
 */
public class NoteEditWidget extends AppWidgetProvider implements AppData {
    public static final String EXTRA_ITEM = "com.onurkol.app.notes.widgets.NoteEditWidget.EXTRA_ITEM";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        int widgetNoteIndex=NoteEditWidgetConfigureActivity.loadWidgetDataInt(WIDGET_KEY_NOTE_INDEX);

        if(widgetNoteIndex!=INTEGER_NULL){
            String updatedWidgetTitle = NoteManager.getManager().getNoteList().get(widgetNoteIndex).getNoteTitle();
            int updatedWidgetColor = NoteManager.getManager().getNoteList().get(widgetNoteIndex).getNoteColor();

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.note_edit_widget);
            views.setTextViewText(R.id.noteWidgetTitleTextView, Limit(updatedWidgetTitle, 14));

            int widgetTextColor;
            if (updatedWidgetColor == context.getColor(R.color.cardColorWhite) ||
                    updatedWidgetColor == context.getColor(R.color.cardColorYellow) ||
                    updatedWidgetColor == context.getColor(R.color.cardColorLime) ||
                    updatedWidgetColor == context.getColor(R.color.cardColorAmber))
                widgetTextColor = context.getColor(R.color.black);
            else
                widgetTextColor = context.getColor(R.color.white);

            views.setTextColor(R.id.noteWidgetTitleTextView, widgetTextColor);
            views.setInt(R.id.widgetBaseLayout, "setBackgroundColor", updatedWidgetColor);
            views.setInt(R.id.refreshNoteWidgetButton, "setBackgroundColor", context.getColor(R.color.transparent));
            views.setInt(R.id.refreshNoteWidgetButton, "setImageResource", R.drawable.ic_baseline_refresh_24);
            views.setInt(R.id.refreshNoteWidgetButton, "setColorFilter", widgetTextColor);

            Intent noteEditWidgetService = new Intent(context, Service.class);
            noteEditWidgetService.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            noteEditWidgetService.setData(Uri.parse(noteEditWidgetService.toUri(Intent.URI_INTENT_SCHEME)));

            views.setRemoteAdapter(R.id.scrollableTextListView, noteEditWidgetService);

            Intent activityIntent = new Intent(context, NoteEditActivity.class);
            // Set the action for the intent.
            // When the user touches a particular view.
            activityIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            activityIntent.putExtra(KEY_EXTRA_NOTE_POSITION, widgetNoteIndex);
            activityIntent.setData(Uri.parse(activityIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, activityIntent,
                    PendingIntent.FLAG_IMMUTABLE);
            views.setPendingIntentTemplate(R.id.scrollableTextListView, pendingIntent);

            // Widget Refresh Button
            Intent intentSync = new Intent(context, NoteEditWidget.class);
            intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            // IF IDS: .EXTRA_APPWIDGET_IDS, new int[]{ appWidgetId }
            // IF ID:  .EXTRA_APPWIDGET_ID, appWidgetId
            intentSync.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent pendingSync = PendingIntent.getBroadcast(context, 0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.refreshNoteWidgetButton, pendingSync);

            //if (intentSync.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE))
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.scrollableTextListView);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Bundle extras = intent.getExtras();
        if(extras!=null) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), NoteEditWidget.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if(!ContextManager.isContext()) {
            ContextManager.Build(context);
            Toast.makeText(context, context.getString(R.string.error_context_again), Toast.LENGTH_SHORT).show();
        }
        else{
            // There may be multiple widgets active, so update all of them
            for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}