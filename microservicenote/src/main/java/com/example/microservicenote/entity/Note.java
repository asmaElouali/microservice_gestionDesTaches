package com.example.microservicenote.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="tbl_notes")
public class Note {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String body;

    @Column
    private String category;

    @Column
    private String title;

    @Column(name="created_at",nullable=false, updatable=false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "id_user")
    private Long idUser;

    public Note( String s, String s1, String s2, Long userId) {
        this.body = s;
        this.category = s1;
        this.title = s2;
        this.idUser = userId;}
}
