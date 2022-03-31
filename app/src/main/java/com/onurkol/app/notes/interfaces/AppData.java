package com.onurkol.app.notes.interfaces;

import com.onurkol.app.notes.data.NoteData;

import java.util.ArrayList;

public interface AppData {
    String KEY_APP_THEME="Data.App.Settings.Theme",
            KEY_APP_LANGUAGE="Data.App.Settings.Language",
            KEY_NOTE_LINE_NUMBERS="Data.App.Settings.LineNumbers",
            KEY_NOTE_DATE_INFO="Data.App.Settings.DateInfo",
            KEY_NOTE_LIST="Data.App.Note.List",
            KEY_EXTRA_NOTE_ID="Data.App.Note.IdFromWidgetExtra",
            KEY_EXTRA_NOTE_INDEX="Data.App.Note.IndexFromWidgetExtra";

    // Default Values
    int DEFAULT_APP_THEME=0,
            DEFAULT_APP_LANGUAGE=0,
            DEFAULT_NOTE_LINE_NUMBERS=0,
            DEFAULT_NOTE_DATE_INFO=1;

    String WIDGET_KEY_NOTE_TITLE=".Widget.Data.Note.Title",
            WIDGET_KEY_NOTE_TEXT=".Widget.Data.Note.Text",
            WIDGET_KEY_NOTE_COLOR=".Widget.Data.Note.Color",
            WIDGET_KEY_NOTE_PASSWORD=".Widget.Data.Note.Password",
            WIDGET_KEY_NOTE_INDEX=".Widget.Data.Note.Index",
            WIDGET_KEY_NOTE_ID=".Widget.Data.Note.Id";

    // Lists
    ArrayList<NoteData> NOTE_LIST=new ArrayList<>();
}
