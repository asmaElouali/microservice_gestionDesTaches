package com.example.microservicenote.service;

import com.example.microservicenote.entity.Note;
import com.example.microservicenote.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NoteServiceimpl  implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> readAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note readNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Note is not present for the id"+id) );
    }

    @Override
    public void delete(Long id) {
        Note existingNote=readNote(id);
        noteRepository.delete(existingNote);
    }

    @Override
    public List<Note> readAllNoteByUser(Long userId) {
        return noteRepository.findAllByIdUser(userId);
    }

}
