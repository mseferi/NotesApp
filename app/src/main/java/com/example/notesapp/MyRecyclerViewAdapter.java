package com.example.notesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.entity.Note;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Note> notes;
    private final LayoutInflater mInflater;
    public RecyclerView rvNotes;


    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notes_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvNote.setText(note.getNoteText());
        holder.tvTime.setText(note.getNoteTime());

        holder.ibDelete.setOnClickListener((View v) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setCancelable(true);
            builder.setTitle("DELETE");
            builder.setMessage("Are you sure you want to delete this note?");
            builder.setPositiveButton("Confirm",
                    (dialog, which) -> {
                        notes.remove(note);  // remove the item from list
                        MyRecyclerViewAdapter.this.notifyDataSetChanged();
                    });
            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote;
        TextView tvTime;
        ImageButton ibDelete;
        LinearLayout linLayoutRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNote = itemView.findViewById(R.id.tvNote);
            tvTime = itemView.findViewById(R.id.tvTime);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            linLayoutRow = itemView.findViewById(R.id.linLayoutRow);


        }
    }

}
