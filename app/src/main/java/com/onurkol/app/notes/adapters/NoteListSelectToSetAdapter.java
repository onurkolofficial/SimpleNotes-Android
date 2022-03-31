package com.onurkol.app.notes.adapters;

import static com.onurkol.app.notes.tools.CharLimiter.Limit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.widgets.NoteEditWidgetConfigureActivity;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.popups.PopupSetWidgetNote;

import java.util.List;

public class NoteListSelectToSetAdapter extends ArrayAdapter<NoteData> implements AppData {
    private final LayoutInflater inflater;
    private ViewHolder holder;
    private static List<NoteData> noteData;


    public NoteListSelectToSetAdapter(Context context, ListView NoteListView, List<NoteData> NoteData) {
        super(context, 0, NoteData);
        noteData=NoteData;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_note_list_set_widget, null);
            holder = new ViewHolder();
            holder.noteNameWidgetText = convertView.findViewById(R.id.noteNameWidgetText);
            holder.noteSetWidgetButton = convertView.findViewById(R.id.noteSetWidgetButton);
            holder.noteLockStatus = convertView.findViewById(R.id.noteLockStatus);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }

        final NoteData currentData=noteData.get(position);

        int textColor,
                background=currentData.getNoteColor();

        if(background==ContextCompat.getColor(getContext(),R.color.cardColorWhite) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorYellow) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorLime) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorAmber))
            textColor=ContextCompat.getColor(getContext(),R.color.black);
        else
            textColor=ContextCompat.getColor(getContext(),R.color.white);

        holder.noteSetWidgetButton.setCardBackgroundColor(background);
        holder.noteLockStatus.setColorFilter(background);
        holder.noteNameWidgetText.setTextColor(textColor);

        holder.noteNameWidgetText.setText(Limit(currentData.getNoteTitle(),24));

        String notePassword=currentData.getNotePassword();
        if(notePassword==null)
            holder.noteLockStatus.setVisibility(View.INVISIBLE);
        else
            holder.noteLockStatus.setVisibility(View.VISIBLE);

        holder.noteSetWidgetButton.setOnClickListener(view -> {
            if(currentData.getNotePassword()==null)
                NoteEditWidgetConfigureActivity.acceptWidgetDataOnClick(getContext(), currentData.getNoteId());
            else
                PopupSetWidgetNote.Show(position, currentData.getNoteId());
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView noteNameWidgetText;
        CardView noteSetWidgetButton;
        ImageView noteLockStatus;
    }
}
