package com.example.microservicenote.service;

import com.example.microservicenote.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> readAll();
    Note saveNote(Note note);
    Note readNote(Long id);
    void delete(Long id);
    List<Note> readAllNoteByUser(Long userId);
}
