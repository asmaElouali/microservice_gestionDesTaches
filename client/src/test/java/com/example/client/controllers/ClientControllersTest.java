package com.example.client.controllers;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import com.example.client.service.ClientService;
import com.example.client.service.FullUserResponce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
class ClientControllersTest {


    private MockMvc mockMvc;
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientControllers(clientRepository,clientService)).build();
    }

    @Test
    void testFindAll() throws Exception {
        when(clientRepository.findAll()).thenReturn(List.of(new Client(), new Client()));

        mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testFindById() throws Exception {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(java.util.Optional.of(new Client()));

        mockMvc.perform(MockMvcRequestBuilders.get("/client/{id}", clientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindByNom() throws Exception {
        String nom = "John";
        when(clientRepository.findByNom(nom)).thenReturn(new Client());

        mockMvc.perform(MockMvcRequestBuilders.get("/client/nom/{nom}", nom))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddClient() throws Exception {
        Client client = new Client();
        client.setNom("John");
        client.setEmail("john@example.com");
        when(clientRepository.save(client)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nom\": \"John\", \"email\": \"john@example.com\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testfindAllUsers() throws Exception {
        Long userId = 1L;
        FullUserResponce fullUserResponce = new FullUserResponce();
        when(clientService.findUsersWithNotes(userId)).thenReturn(fullUserResponce);

        mockMvc.perform(MockMvcRequestBuilders.get("/with-note/{user-id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}