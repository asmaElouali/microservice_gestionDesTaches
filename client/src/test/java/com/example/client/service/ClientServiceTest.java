package com.example.client.service;

import com.example.client.client.UserClient;
import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private  UserClient userClient;
    private ClientService underTest;

    private  AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ClientService(clientRepository,userClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testFindUsersWithNotes() {
        //given
        Long userId = 1L;
        Client client = Client.builder().nom("Test").email("test@example.com").build();
        List<Note> notes = Arrays.asList(new Note(), new Note());

        when(clientRepository.findById(userId)).thenReturn(Optional.of(client));
        when(userClient.findAllNotesByUser(userId)).thenReturn(notes);

        //when
        FullUserResponce result = underTest.findUsersWithNotes(userId);

        //then
        verify(clientRepository).findById(userId);
        verify(userClient).findAllNotesByUser(userId);
        assertEquals("Test", result.getNom());
        assertEquals(notes, result.getNotes());
    }
}