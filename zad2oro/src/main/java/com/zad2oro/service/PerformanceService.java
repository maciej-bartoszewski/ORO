package com.zad2oro.service;

import com.zad2oro.dto.ParticipantDto;
import com.zad2oro.dto.PerformanceDto;
import com.zad2oro.repository.PerformanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Service
public class PerformanceService {
    private PerformanceRepository performanceRepository;

    public Page<PerformanceDto> getPerformancesByHallId(Long hallId, Pageable pageable) {
        return performanceRepository.findPerformancesByHallId(hallId, pageable)
                .map(performance -> new PerformanceDto(
                        performance.getId(),
                        performance.getDate(),
                        performance.getTime(),
                        performance.getSpectacle().getTitle(),
                        performance.getHall().getName()
                ));
    }

    public Page<PerformanceDto> getPerformancesBySpectacleId(Long spectacleId, Pageable pageable) {
        return performanceRepository.findPerformancesBySpectacleId(spectacleId, pageable)
                .map(performance -> new PerformanceDto(
                        performance.getId(),
                        performance.getDate(),
                        performance.getTime(),
                        performance.getSpectacle().getTitle(),
                        performance.getHall().getName()
                ));
    }

    public Page<PerformanceDto> getPerformancesBySpectacleTitle(String title, Pageable pageable) {
        return performanceRepository.findPerformancesBySpectacleTitle(title, pageable)
                .map(performance -> new PerformanceDto(
                        performance.getId(),
                        performance.getDate(),
                        performance.getTime(),
                        performance.getSpectacle().getTitle(),
                        performance.getHall().getName()
                ));
    }

    public Page<ParticipantDto> getParticipantsByPerformanceId(Long performanceId, Pageable pageable) {
        return performanceRepository.findParticipantsByPerformanceId(performanceId, pageable)
                .map(participant -> new ParticipantDto(
                        participant.getId(),
                        participant.getLogin()
                ));
    }

    public Page<PerformanceDto> getPerformancesByParticipantId(Long participantId, Pageable pageable) {
        return performanceRepository.findPerformancesByParticipantId(participantId, pageable)
                .map(performance -> new PerformanceDto(
                        performance.getId(),
                        performance.getDate(),
                        performance.getTime(),
                        performance.getSpectacle().getTitle(),
                        performance.getHall().getName()
                ));
    }

    public Page<PerformanceDto> getPerformancesByParticipantLogin(String participantLogin, Pageable pageable) {
        return performanceRepository.findPerformancesByParticipantLogin(participantLogin, pageable)
                .map(performance -> new PerformanceDto(
                        performance.getId(),
                        performance.getDate(),
                        performance.getTime(),
                        performance.getSpectacle().getTitle(),
                        performance.getHall().getName()
                ));
    }

    public Long countOccupiedSeatsByHallIdAndPerformanceTime(Long hallId, LocalDate date, LocalTime time) {
        return performanceRepository.countOccupiedSeatsByHallIdAndPerformanceTime(hallId, date, time);
    }

    public Long countHallsBySpectacleId(Long spectacleId) {
        return performanceRepository.countHallsBySpectacleId(spectacleId);
    }

    public Long countTicketsByUserInDateRange(Long participantId, LocalDate startDate, LocalDate endDate) {
        return performanceRepository.countTicketsByUserInDateRange(participantId, startDate, endDate);
    }
}
