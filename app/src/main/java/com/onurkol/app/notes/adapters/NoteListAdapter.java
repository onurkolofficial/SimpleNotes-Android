package com.onurkol.app.notes.adapters;

import static com.onurkol.app.notes.tools.CharLimiter.Limit;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.onurkol.app.notes.R;
import com.onurkol.app.notes.activity.MainActivity;
import com.onurkol.app.notes.data.NoteData;
import com.onurkol.app.notes.interfaces.AppData;
import com.onurkol.app.notes.lib.AppPreferenceManager;
import com.onurkol.app.notes.lib.notes.NoteManager;
import com.onurkol.app.notes.popups.PopupEditNote;
import com.onurkol.app.notes.popups.PopupLockNote;
import com.onurkol.app.notes.popups.PopupOpenNote;
import com.onurkol.app.notes.popups.PopupUnlockNote;

import java.util.List;

public class NoteListAdapter extends ArrayAdapter<NoteData> implements AppData {
    private final LayoutInflater inflater;
    private ViewHolder holder;
    private static List<NoteData> noteData;
    private final ListView noteListView;
    private Context mContext;

    public NoteListAdapter(Context context, ListView NoteListView, List<NoteData> NoteData){
        super(context,0,NoteData);
        noteData=NoteData;
        noteListView=NoteListView;
        inflater=LayoutInflater.from(context);
        mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.item_note_list, null);
            holder=new ViewHolder();
            holder.noteNameText=convertView.findViewById(R.id.noteNameText);
            holder.createdDateText=convertView.findViewById(R.id.createdDateText);
            holder.createdDateValue=convertView.findViewById(R.id.createdDateValue);
            holder.lastEditDateText=convertView.findViewById(R.id.lastEditDateText);
            holder.lastEditDateValue=convertView.findViewById(R.id.lastEditDateValue);
            holder.noteLockUnlockButton=convertView.findViewById(R.id.noteLockUnlockButton);
            holder.noteEditButton=convertView.findViewById(R.id.noteEditButton);
            holder.noteDeleteButton=convertView.findViewById(R.id.noteDeleteButton);
            holder.noteOpenLayoutButton=convertView.findViewById(R.id.noteOpenLayoutButton);
            holder.noteLockStatus=convertView.findViewById(R.id.noteLockStatus);
            holder.noteDateInfo=convertView.findViewById(R.id.noteDateInfo);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        final NoteData currentData=noteData.get(position);
        final AppPreferenceManager prefManager=AppPreferenceManager.getInstance();

        int showDateInfo=prefManager.getInt(KEY_NOTE_DATE_INFO);

        int textColor,
                background=currentData.getNoteColor();

        if(background==ContextCompat.getColor(getContext(),R.color.cardColorWhite) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorYellow) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorLime) ||
                background==ContextCompat.getColor(getContext(),R.color.cardColorAmber))
            textColor=ContextCompat.getColor(getContext(),R.color.black);
        else
            textColor=ContextCompat.getColor(getContext(),R.color.white);
        holder.noteOpenLayoutButton.setCardBackgroundColor(background);
        holder.noteNameText.setTextColor(textColor);
        holder.createdDateText.setTextColor(textColor);
        holder.createdDateValue.setTextColor(textColor);
        holder.lastEditDateText.setTextColor(textColor);
        holder.lastEditDateValue.setTextColor(textColor);
        holder.noteDeleteButton.setColorFilter(textColor);
        holder.noteEditButton.setColorFilter(textColor);
        holder.noteLockStatus.setColorFilter(background);
        holder.noteLockUnlockButton.setColorFilter(textColor);

        holder.noteNameText.setText(Limit(currentData.getNoteTitle(),19));
        holder.createdDateValue.setText(currentData.getNoteCreateDate());
        String editDateText;
        if(currentData.getNoteEditDate()!=null)
            editDateText=currentData.getNoteEditDate();
        else
            editDateText="-";
        holder.lastEditDateValue.setText(editDateText);

        String notePassword=currentData.getNotePassword();
        if(notePassword==null){
            holder.noteLockStatus.setVisibility(View.INVISIBLE);
            holder.noteLockUnlockButton.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_baseline_lock_24));
        }
        else{
            holder.noteLockStatus.setVisibility(View.VISIBLE);
            holder.noteLockUnlockButton.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_round_lock_open_24));
        }

        if(showDateInfo==1)
            holder.noteDateInfo.setVisibility(View.VISIBLE);
        else
            holder.noteDateInfo.setVisibility(View.GONE);

        holder.noteDeleteButton.setOnClickListener(view -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage(getContext().getString(R.string.sure_delete_note_text))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(getContext().getString(R.string.yes_text), (dialogInterface, i) -> {
                        NoteManager.getManager().deleteNote(position);

                        MainActivity.onUpdateNoteList();
                    })
                    .setNegativeButton(getContext().getString(R.string.no_text), null)
                    .show();
        });

        holder.noteEditButton.setOnClickListener(view -> PopupEditNote.Show(position));
        holder.noteLockUnlockButton.setOnClickListener(v -> {
            if(currentData.getNotePassword()==null)
                PopupLockNote.Show(position);
            else
                PopupUnlockNote.Show(position);
        });
        holder.noteOpenLayoutButton.setOnClickListener(v -> {
            if(currentData.getNotePassword()==null)
                MainActivity.startNoteEditActivity(getContext(),position);
            else
                PopupOpenNote.Show(position);
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView noteNameText,
                createdDateText,
                lastEditDateText,
                createdDateValue,
                lastEditDateValue;
        ImageButton noteLockUnlockButton,
                noteEditButton,
                noteDeleteButton;
        ImageView noteLockStatus;
        CardView noteOpenLayoutButton;
        LinearLayout noteDateInfo;
    }
}
