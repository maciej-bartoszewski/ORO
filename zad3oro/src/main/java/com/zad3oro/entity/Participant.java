package com.zad3oro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participants")
public class Participant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String country;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "participants")
    private List<Presentation> presentations;
}
