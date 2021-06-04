package com.example.notesapp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Note {
    private UUID noteId;
    private String noteText;
    private String noteTime;

    public Note(UUID noteId, String noteText, String noteTime) {
        this.noteId = noteId;
        this.noteText = noteText;
        this.noteTime = noteTime;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }


    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy.", Locale.getDefault());
        return sdf.format(new Date());
    }
}
