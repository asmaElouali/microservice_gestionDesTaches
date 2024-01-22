package com.example.microservicenote.controller;

import com.example.microservicenote.entity.Note;
import com.example.microservicenote.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void testGetNotes() throws Exception {
        List<Note> notes = Arrays.asList(new Note(), new Note());
        when(noteService.readAll()).thenReturn(notes);

        mockMvc.perform(MockMvcRequestBuilders.get("/Managment/notee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(notes.size()));
    }

    @Test
    void testSaveNote() throws Exception {
        Note note = new Note();
        when(noteService.saveNote(any())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders.post("/Managment/notee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(note.getId()));

        verify(noteService, times(1)).saveNote(any());
    }

    @Test
    void testReadNote() throws Exception {
        Note note = new Note();
        when(noteService.readNote(anyLong())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders.get("/Managment/notee/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(note.getId()));

        verify(noteService, times(1)).readNote(anyLong());
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Managment/notee/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(noteService, times(1)).delete(anyLong());
    }

    @Test
    void testUpdate() throws Exception {
        Note note = new Note();
        when(noteService.saveNote(any())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders.put("/Managment/notee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(note.getId()));

        verify(noteService, times(1)).saveNote(any());
    }

    @Test
    void testGetNotesByUserId() throws Exception {
        List<Note> notes = Arrays.asList(new Note(), new Note());
        when(noteService.readAllNoteByUser(anyLong())).thenReturn(notes);

        mockMvc.perform(MockMvcRequestBuilders.get("/Managment/client/{user-id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(notes.size()));

        verify(noteService, times(1)).readAllNoteByUser(anyLong());
    }
}
