package com.zad2oro.entity;

import com.zad2oro.utils.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
