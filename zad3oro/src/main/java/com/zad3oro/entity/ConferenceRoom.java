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
@Table(name = "conference_rooms")
public class ConferenceRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "conferenceRoom")
    private List<Presentation> presentations;
}
