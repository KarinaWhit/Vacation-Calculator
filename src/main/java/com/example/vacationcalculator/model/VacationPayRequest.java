package com.example.vacationcalculator.model;

import jakarta.validation.constraints.Positive;  // Правильный импорт для Spring Boot 3.x
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Request for vacation pay calculation")
public class VacationPayRequest {
    @Positive(message = "Average salary must be positive")
    @Schema(description = "Average salary for the last 12 months", example = "50000.0")
    private double averageSalary;

    @Positive(message = "Vacation days must be positive")
    @Schema(description = "Number of vacation days", example = "14")
    private int vacationDays;

    @Schema(description = "Start date of vacation (yyyy-MM-dd)", example = "2024-07-01")
    private LocalDate startDate;

    @Schema(description = "End date of vacation (yyyy-MM-dd)", example = "2024-07-14")
    private LocalDate endDate;
}