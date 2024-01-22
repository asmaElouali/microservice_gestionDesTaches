package com.example.microservicenote.service;

import com.example.microservicenote.entity.Note;
import com.example.microservicenote.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceimplTest {

    @Mock private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceimpl underTest;

    @Captor
    private ArgumentCaptor<Note> noteArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new NoteServiceimpl(noteRepository);
    }



    @Test
    void canReadAll() {
        //when
        underTest.readAll();
        //then
        verify(noteRepository).findAll();
    }

    @Test
    void canSaveNote() {
        //given
        Long userId = 1L;
        Note note1 = new Note( "Body 1", "Category 1", "Title 1", userId);

        // when
        underTest.saveNote(note1);

        //then
        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteArgumentCaptor.capture());

        Note capturedNote = noteArgumentCaptor.getValue();

        assertThat(capturedNote).isEqualTo(note1);
    }

    @Test
    void canReadNote() {
        Long id = 1L;
        // Mock the behavior of noteRepository.findById to return an empty result
        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method under test and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> underTest.readNote(id));
        //then
        verify(noteRepository).findById(id);
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;
        Long userId = 1L;
        Note note = new Note("Body 1", "Category 1", "Title 1", userId);

        // Mock the behavior of noteRepository.findById to return the note
        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        // When
        underTest.delete(id);
        // Then
        verify(noteRepository).findById(id);
        verify(noteRepository).delete(note);
    }

    @Test
    void testReadAllNoteByUser() {
        // Créez un ID fictif pour le test
        Long userId = 1L;

        // Créez une liste fictive de notes pour simuler le comportement du repository
        List<Note> mockNotes = Arrays.asList(new Note(), new Note());

        // Définissez le comportement du repository lorsque la méthode findAllByIdUser est appelée avec l'ID fictif
        when(noteRepository.findAllByIdUser(userId)).thenReturn(mockNotes);

        // Appelez la méthode du service avec l'ID fictif
        List<Note> result = underTest.readAllNoteByUser(userId);

        // Vérifiez si la méthode du repository a été appelée avec l'ID fictif
        verify(noteRepository, times(1)).findAllByIdUser(userId);

        // Vérifiez si le résultat renvoyé par le service est le même que la liste fictive de notes
        assertEquals(mockNotes, result);
    }
}