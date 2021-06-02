package com.example.notesapp.entity;

import java.util.Date;
import java.util.UUID;

public class Note {
    private UUID noteId;
    private String noteText;
    private Date noteTime;

    public Note(UUID noteId, String noteText, Date noteTime) {
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

    public Date getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Date noteTime) {
        this.noteTime = noteTime;
    }
}
