package com.zad2oro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class PerformanceDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String spectacleName;
    private String hallName;
}
