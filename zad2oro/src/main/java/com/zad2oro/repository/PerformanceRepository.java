package com.zad2oro.repository;

import com.zad2oro.entity.Participant;
import com.zad2oro.entity.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query("select distinct p from Performance p where p.hall.id = :hallId")
    Page<Performance> findPerformancesByHallId(Long hallId, Pageable pageable);

    @Query("select distinct p from Performance p where p.spectacle.id = :spectacleId")
    Page<Performance> findPerformancesBySpectacleId(Long spectacleId, Pageable pageable);

    @Query("select distinct p from Performance p where p.spectacle.title = :title")
    Page<Performance> findPerformancesBySpectacleTitle(String title, Pageable pageable);

    @Query("select distinct r.participant from Reservation r where r.performance.id = :performanceId")
    Page<Participant> findParticipantsByPerformanceId(Long performanceId, Pageable pageable);

    @Query("select distinct r.performance from Reservation r where r.participant.id = :participantId")
    Page<Performance> findPerformancesByParticipantId(Long participantId, Pageable pageable);

    @Query("select distinct r.performance from Reservation r where r.participant.login = :participantLogin")
    Page<Performance> findPerformancesByParticipantLogin(String participantLogin, Pageable pageable);

    @Query("select count(distinct r) from Reservation r where r.performance.hall.id = :hallId and r.performance.date = :date and r.performance.time = :time")
    Long countOccupiedSeatsByHallIdAndPerformanceTime(Long hallId, LocalDate date, LocalTime time);

    @Query("select count(distinct p.hall) from Performance p where p.spectacle.id = :spectacleId")
    Long countHallsBySpectacleId(Long spectacleId);

    @Query("select count(distinct r) from Reservation r where r.participant.id = :participantId and r.performance.date >= :startDate and r.performance.date <= :endDate")
    Long countTicketsByUserInDateRange(Long participantId, LocalDate startDate, LocalDate endDate);
}
