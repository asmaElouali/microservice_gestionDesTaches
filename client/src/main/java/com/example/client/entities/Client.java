package com.example.client.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String password;
    private String email;
    private String phone;
    private String country;
    private String address;
    private String gender;


    public Client(long l, String number, String mail, String number1, String morocco, String marrakech, String femal) {
    }
}
