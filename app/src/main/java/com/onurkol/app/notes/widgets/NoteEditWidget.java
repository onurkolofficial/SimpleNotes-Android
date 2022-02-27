package com.onurkol.app.notes.widgets;

import static com.onurkol.app.notes.tools.CharLimiter.Limit;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.widgets.NoteEditWidgetConfigureActivity;
import com.onurkol.app.notes.interfaces.AppData;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NoteEditWidgetConfigureActivity NoteEditWidgetConfigureActivity}
 */
public class NoteEditWidget extends AppWidgetProvider implements AppData {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        String widgetTitle = NoteEditWidgetConfigureActivity.loadWidgetDataString(context, appWidgetId, AppData.WIDGET_KEY_NOTE_TITLE);
        String widgetText = NoteEditWidgetConfigureActivity.loadWidgetDataString(context, appWidgetId, AppData.WIDGET_KEY_NOTE_TEXT);
        int widgetColor = NoteEditWidgetConfigureActivity.loadWidgetDataInt(context, appWidgetId, AppData.WIDGET_KEY_NOTE_COLOR);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.note_edit_widget);
        views.setTextViewText(R.id.noteWidgetTitleTextView, Limit(widgetTitle,24));
        views.setTextViewText(R.id.noteWidgetTextView, widgetText);

        views.setInt(R.id.widgetBaseLayout, "setBackgroundColor", widgetColor);

        int widgetTextColor;
        if(widgetColor==context.getColor(R.color.cardColorWhite) ||
                widgetColor==context.getColor(R.color.cardColorYellow) ||
                widgetColor==context.getColor(R.color.cardColorLime) ||
                widgetColor==context.getColor(R.color.cardColorAmber))
            widgetTextColor=context.getColor(R.color.black);
        else
            widgetTextColor=context.getColor(R.color.white);

        views.setTextColor(R.id.noteWidgetTitleTextView, widgetTextColor);
        views.setTextColor(R.id.noteWidgetTextView, widgetTextColor);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            NoteEditWidgetConfigureActivity.deleteWidgetData(context, appWidgetId, AppData.WIDGET_KEY_NOTE_TITLE);
            NoteEditWidgetConfigureActivity.deleteWidgetData(context, appWidgetId, AppData.WIDGET_KEY_NOTE_TEXT);
        }
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