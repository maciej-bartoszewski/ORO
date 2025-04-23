package com.zad2oro.controller;

import com.zad2oro.dto.ParticipantDto;
import com.zad2oro.dto.PerformanceDto;
import com.zad2oro.service.PerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@RequestMapping("/performances")
@RestController
public class PerformanceController {
    private PerformanceService performanceService;

    @GetMapping("/halls/{hallId}")
    public ResponseEntity<Page<PerformanceDto>> getPerformancesByHallId(@PathVariable Long hallId, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getPerformancesByHallId(hallId, pageable));
    }

    @GetMapping("/spectacles/{spectacleId}")
    public ResponseEntity<Page<PerformanceDto>> getPerformancesBySpectacleId(@PathVariable Long spectacleId, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getPerformancesBySpectacleId(spectacleId, pageable));
    }

    @GetMapping("/spectacles/title/{spectacleTitle}")
    public ResponseEntity<Page<PerformanceDto>> getPerformancesBySpectacleTitle(@PathVariable String spectacleTitle, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getPerformancesBySpectacleTitle(spectacleTitle, pageable));
    }

    @GetMapping("/{performanceId}/participants")
    public ResponseEntity<Page<ParticipantDto>> getParticipantsByPerformanceId(@PathVariable Long performanceId, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getParticipantsByPerformanceId(performanceId, pageable));
    }

    @GetMapping("/participants/{participantId}")
    public ResponseEntity<Page<PerformanceDto>> getPerformancesByParticipantId(@PathVariable Long participantId, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getPerformancesByParticipantId(participantId, pageable));
    }

    @GetMapping("/participants/login/{participantLogin}")
    public ResponseEntity<Page<PerformanceDto>> getPerformancesByParticipantLogin(@PathVariable String participantLogin, Pageable pageable) {
        return ResponseEntity.ok(performanceService.getPerformancesByParticipantLogin(participantLogin, pageable));
    }

    @GetMapping("/halls/{hallId}/{date}/{time}/occupied-seats")
    public ResponseEntity<Long> countOccupiedSeatsByHallIdAndPerformanceTime(@PathVariable Long hallId, @PathVariable String date, @PathVariable String time) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd:MM:yyyy"));
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        return ResponseEntity.ok(performanceService.countOccupiedSeatsByHallIdAndPerformanceTime(hallId, localDate, localTime));
    }

    @GetMapping("/spectacles/{spectacleId}/halls")
    public ResponseEntity<Long> countHallsBySpectacleId(@PathVariable Long spectacleId) {
        return ResponseEntity.ok(performanceService.countHallsBySpectacleId(spectacleId));
    }

    @GetMapping("/participants/{participantId}/tickets/{startDate}/{endDate}")
    public ResponseEntity<Long> countTicketsByUserInDateRange(@PathVariable Long participantId, @PathVariable String startDate, @PathVariable String endDate) {
        LocalDate localStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd:MM:yyyy"));
        LocalDate localEndDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd:MM:yyyy"));
        return ResponseEntity.ok(performanceService.countTicketsByUserInDateRange(participantId, localStartDate, localEndDate));
    }
}
