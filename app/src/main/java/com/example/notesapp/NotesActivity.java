package com.example.notesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.entity.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class NotesActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    private MyRecyclerViewAdapter adapter;
    private ArrayList<Note> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        recyclerViewInit();

        adapter.setNotes(data);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_activity_menu, menu); // inflate toolbar with our menu layout
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionAdd) {

            LayoutInflater layoutInflater = this.getLayoutInflater();

            final View view = layoutInflater.inflate(R.layout.add_dialog_layout, null);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("NEW NOTE");
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Enter text :");
            final EditText etAddNote = view.findViewById(R.id.etAddNote);


            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.getNotes().add(0, new Note(UUID.randomUUID(), etAddNote.getText().toString(), getFormattedDate()));
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


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("task_list", json);
        editor.apply();
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task_list", null);
        Type type = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data = gson.fromJson(json, type);
        if (data == null) {
            data = new ArrayList<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData();

    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy.", Locale.getDefault());
        return sdf.format(new Date());
    }
}