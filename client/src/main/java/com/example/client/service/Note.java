package com.example.client.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {


    private String body;

    private String category;

    private String title;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
