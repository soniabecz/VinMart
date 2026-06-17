package com.example.catalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vinyls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vinyl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artist;

    private String title;

    private String genre;

    private Integer releaseYear;

    private Double price;

    private Integer stock;
}