package com.example.client.repositories;

import com.example.client.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository underTest;
    @Test
    void testFindByNom() {
        //given
        Client client = new Client(1L,"1234","asma@gmail.com","0654363704","morocco","marrakech","femal");
        //when
        underTest.save(client);
        //then
        underTest.findByNom(client.getNom());
    }
}