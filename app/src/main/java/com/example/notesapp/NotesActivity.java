package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.notesapp.entity.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

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