package com.example.notesapp.entity;

import java.util.Date;
import java.util.UUID;

public class Note {
    private UUID noteId;
    private String note;
    private Date noteTime;

    public Note(UUID noteId, String note, Date noteTime) {
        this.noteId = noteId;
        this.note = note;
        this.noteTime = noteTime;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Date noteTime) {
        this.noteTime = noteTime;
    }
}
