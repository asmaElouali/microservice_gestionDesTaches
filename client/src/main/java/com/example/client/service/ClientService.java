package com.example.client.service;

import com.example.client.client.UserClient;
import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserClient userClient;
    public FullUserResponce findUsersWithNotes(Long userId) {
        var client = clientRepository.findById(userId)
                .orElse(Client.builder().nom("Not found")
                        .email("Not found")
                .build());
        var note =  userClient.findAllNotesByUser(userId); // find all notes
        return FullUserResponce.builder().nom(client.getNom()).password(client.getPassword()).notes(note).build();
    }
}
