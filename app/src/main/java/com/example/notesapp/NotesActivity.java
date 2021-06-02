package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.notesapp.entity.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.zip.Inflater;

public class NotesActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    private MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewInit();

        adapter.setNotes(dataPopulation());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_activity_menu, menu); // inflate toolbar with our menu layout
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.actionAdd){

            LayoutInflater layoutInflater = this.getLayoutInflater();

            final View view = layoutInflater.inflate(R.layout.add_dialog_layout, null);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("NEW NOTE");
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Enter text :");
            final EditText etAddNote = (EditText) view.findViewById(R.id.etAddNote);


            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.getNotes().add(0, new Note(UUID.randomUUID(), etAddNote.getText().toString(), Calendar.getInstance().getTime()));
                    adapter.notifyDataSetChanged();

                }
            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });


            alertDialog.setView(view);
            alertDialog.show();


        }

        return super.onOptionsItemSelected(item);
    }

    public void recyclerViewInit() {
        adapter = new MyRecyclerViewAdapter(this);
        RecyclerView rvNotes = findViewById(R.id.rvNotes);
        adapter.rvNotes = rvNotes;
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider_in_row)));
        rvNotes.addItemDecoration(divider);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);
    }

    public ArrayList<Note> dataPopulation() {
        ArrayList<Note> data = new ArrayList<>();
        data.add(new Note(UUID.randomUUID(), "Bilješka", Calendar.getInstance().getTime()));
        data.add(new Note(UUID.randomUUID(), "Mateo", Calendar.getInstance().getTime()));
        data.add(new Note(UUID.randomUUID(), "AAAAAAAAAAABBBBBBBBBBBBBBBBBCCCCCCCCCCCCCCCDDDDDDDDEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEFFFFFFFFFFFFFFFFFFFFFFFFFFFFHHHHHHHHHHHHHHHHHH", Calendar.getInstance().getTime()));

        return data;
    }


}