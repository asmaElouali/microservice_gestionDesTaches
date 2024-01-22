package com.example.microservicenote.repository;

import com.example.microservicenote.entity.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @AfterEach
    void tearDown() {
        noteRepository.deleteAll();
    }

    @Test
    public void testFindByIdUser() {
        // Given
        Long userId = 1L;
        Note note1 = new Note( "Body 1", "Category 1", "Title 1", userId);
        Note note2 = new Note("Body 2", "Category 2", "Title 2", userId);
        noteRepository.save(note1);
        noteRepository.save(note2);

        // When
        List<Note> notes = noteRepository.findAllByIdUser(userId);

        // Then
        assertEquals(2, notes.size());
    }

    @Test
    public void testFindByUserId() {
        // Given
        Long userId = 2L;

        // When
        List<Note> notes = noteRepository.findAllByIdUser(userId);

        // Then
        assertEquals(0, notes.size());
    }
}
