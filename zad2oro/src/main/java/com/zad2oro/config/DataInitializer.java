package com.zad2oro.config;

import com.zad2oro.entity.*;
import com.zad2oro.repository.*;
import com.zad2oro.utils.ReservationStatus;
import com.zad2oro.utils.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final HallRepository hallRepository;
    private final PerformanceRepository performanceRepository;
    private final ParticipantRepository participantRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final SpectacleRepository spectacleRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Hall hall1 = createHall("Duża Scena", 200);
            Hall hall2 = createHall("Mała Scena", 100);

            Participant participant1 = createParticipant("jan_kowalski");
            Participant participant2 = createParticipant("anna_nowak");
            Participant participant3 = createParticipant("piotr_wiśniewski");
            Participant participant4 = createParticipant("maria_zielińska");
            Participant participant5 = createParticipant("tomasz_kowalczyk");

            Spectacle spectacle1 = createSpectacle("Hamlet");
            Spectacle spectacle2 = createSpectacle("Romeo i Julia");

            Performance performance1 = createPerformance(spectacle1, LocalDate.of(2025, 6, 15), LocalTime.of(19, 0), hall1);
            Performance performance2 = createPerformance(spectacle1, LocalDate.of(2025, 6, 16), LocalTime.of(20, 0), hall1);
            Performance performance3 = createPerformance(spectacle1, LocalDate.of(2025, 6, 17), LocalTime.of(20, 0), hall2);
            Performance performance4 = createPerformance(spectacle1, LocalDate.of(2025, 6, 17), LocalTime.of(18, 0), hall2);
            Performance performance5 = createPerformance(spectacle2, LocalDate.of(2025, 6, 18), LocalTime.of(20, 0), hall2);
            Performance performance6 = createPerformance(spectacle2, LocalDate.of(2025, 6, 19), LocalTime.of(20, 0), hall2);
            Performance performance7 = createPerformance(spectacle2, LocalDate.of(2025, 6, 19), LocalTime.of(20, 0), hall2);

            Reservation reservation1 = createReservation(performance1, participant1, ReservationStatus.CONFIRMED);
            Reservation reservation2 = createReservation(performance2, participant1, ReservationStatus.CONFIRMED);
            Reservation reservation3 = createReservation(performance5, participant1, ReservationStatus.CONFIRMED);
            Reservation reservation4 = createReservation(performance1, participant2, ReservationStatus.CONFIRMED);
            Reservation reservation5 = createReservation(performance1, participant3, ReservationStatus.CONFIRMED);
            Reservation reservation6 = createReservation(performance1, participant4, ReservationStatus.CONFIRMED);
            Reservation reservation7 = createReservation(performance1, participant5, ReservationStatus.CONFIRMED);
            Reservation reservation8 = createReservation(performance5, participant5, ReservationStatus.CONFIRMED);

            createTicket(reservation1, TicketType.NORMAL);
            createTicket(reservation2, TicketType.NORMAL);
            createTicket(reservation3, TicketType.NORMAL);
            createTicket(reservation4, TicketType.SENIOR);
            createTicket(reservation5, TicketType.STUDENT);
            createTicket(reservation6, TicketType.STUDENT);
            createTicket(reservation7, TicketType.SENIOR);
            createTicket(reservation8, TicketType.SENIOR);
        };
    }

    private Hall createHall(String name, int seats) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setSeats(seats);
        return hallRepository.save(hall);
    }

    private Participant createParticipant(String login) {
        Participant participant = new Participant();
        participant.setLogin(login);
        return participantRepository.save(participant);
    }

    private Spectacle createSpectacle(String title) {
        return spectacleRepository.save(new Spectacle().withTitle(title));
    }

    private Performance createPerformance(Spectacle spectacle, LocalDate date, LocalTime time, Hall hall) {
        return performanceRepository.save(new Performance().withSpectacle(spectacle).withDate(date).withTime(time).withHall(hall));
    }

    private Reservation createReservation(Performance performance, Participant participant, ReservationStatus status) {
        return reservationRepository.save(new Reservation().withPerformance(performance).withParticipant(participant).withReservationStatus(status));
    }

    private Ticket createTicket(Reservation reservation, TicketType ticketType) {
        return ticketRepository.save(new Ticket().withReservation(reservation).withTicketType(ticketType));
    }
}