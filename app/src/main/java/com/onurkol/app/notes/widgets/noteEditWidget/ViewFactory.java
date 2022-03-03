package com.onurkol.app.notes.widgets.noteEditWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.widgets.NoteEditWidgetConfigureActivity;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.notes.NoteManager;

public class ViewFactory implements RemoteViewsService.RemoteViewsFactory, AppData {
    private Context context;
    private int appWidgetId;

    public ViewFactory(Context context, Intent intent){
        this.context=context;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget_scrollable_text);

        int widgetNoteIndex=NoteEditWidgetConfigureActivity.loadWidgetDataInt(context, appWidgetId, WIDGET_KEY_NOTE_INDEX);

        String updatedWidgetText=NoteManager.getManager().getNoteList().get(widgetNoteIndex).getNoteText();
        int updatedWidgetColor=NoteManager.getManager().getNoteList().get(widgetNoteIndex).getNoteColor();
        int widgetTextColor;
        if(updatedWidgetColor==context.getColor(R.color.cardColorWhite) ||
                updatedWidgetColor==context.getColor(R.color.cardColorYellow) ||
                updatedWidgetColor==context.getColor(R.color.cardColorLime) ||
                updatedWidgetColor==context.getColor(R.color.cardColorAmber))
            widgetTextColor=context.getColor(R.color.black);
        else
            widgetTextColor=context.getColor(R.color.white);

        views.setTextColor(R.id.widgetNoteText, widgetTextColor);

        views.setTextViewText(R.id.widgetNoteText, updatedWidgetText);

        Intent intent = new Intent();
        views.setOnClickFillInIntent(R.id.widgetNoteText, intent);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
