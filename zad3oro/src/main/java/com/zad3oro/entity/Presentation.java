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
@Table(name = "presentations")
public class Presentation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "presenter_id")
    private Participant presenter;

    @ManyToOne
    @JoinColumn(name = "conference_room_id")
    private ConferenceRoom conferenceRoom;

    @ManyToMany
    @JoinTable(
            name = "presentation_participants",
            joinColumns = @JoinColumn(name = "presentation_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> participants;
}
