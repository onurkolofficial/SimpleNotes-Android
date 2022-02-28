package com.onurkol.app.notes.widgets.noteEditWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class Service extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ViewFactory(this.getApplicationContext(), intent);
    }
}
