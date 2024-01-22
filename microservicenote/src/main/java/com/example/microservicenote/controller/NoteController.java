package com.example.microservicenote.controller;

import com.example.microservicenote.entity.Note;
import com.example.microservicenote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Managment")
public class NoteController {


    private final NoteService noteservice;
    @Autowired
    public NoteController(NoteService someDependency) {
        this.noteservice = someDependency;
    }
    @GetMapping("/notee")
    public List<Note> getNotes(){
        return noteservice.readAll();
    }

    @PostMapping("/notee")
    public Note saveNote(@RequestBody Note note) {
        return noteservice.saveNote(note);
    }

    @GetMapping("/notee/{id}")
    public Note readNote(@PathVariable(name = "id") Long id) {
        return noteservice.readNote(id);
    }

    @DeleteMapping("/notee/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable(name = "id") Long id) {
        noteservice.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/notee")
    public Note Update(@RequestBody Note note) {
        return noteservice.saveNote(note);
    }
    @GetMapping("/client/{user-id}")
    public List<Note> getNotes(@PathVariable("user-id") Long userId){
        return noteservice.readAllNoteByUser(userId);
    }

}
