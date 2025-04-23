package com.zad2oro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "performances")
public class Performance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "spectacle_id")
    private Spectacle spectacle;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "performance")
    private List<Reservation> reservations;
}
