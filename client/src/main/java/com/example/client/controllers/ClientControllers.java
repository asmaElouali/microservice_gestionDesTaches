package com.example.client.controllers;
import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import com.example.client.service.ClientService;
import com.example.client.service.FullUserResponce;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class ClientControllers {

    @Autowired
    ClientRepository clientRepository ;

    @Autowired
    ClientService clientService ;
    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Client findById (@PathVariable(name = "id") Long id) throws Exception{

         return this.clientRepository.findById(id).orElseThrow(()-> new
                Exception("Client inexistant"));
    }
    @GetMapping("/client/nom/{nom}")
    public ResponseEntity<Client> findByNom(@PathVariable(name = "nom") String nom) {
        Client client = this.clientRepository.findByNom(nom);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/client")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/with-note/{user-id}")
    public ResponseEntity<FullUserResponce> findAllUsers(@PathVariable("user-id") Long UserId){
         return ResponseEntity.ok(clientService.findUsersWithNotes(UserId));
    }

}
