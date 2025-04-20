package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.model.VacationPayRequest;
import com.example.vacationcalculator.model.VacationPayResponse;
import com.example.vacationcalculator.service.VacationPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate; // Добавьте этот импорт

@Tag(name = "Vacation Pay Calculator", description = "API for calculating vacation pay")
@RestController
@RequestMapping("/api/v1")
public class VacationPayController {
    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @Operation(summary = "Calculate vacation pay",
            description = "Calculate vacation pay based on average salary and vacation days")
    @GetMapping("/calculate")
    public ResponseEntity<VacationPayResponse> calculateVacationPay(
            @Parameter(description = "Average salary for 12 months", required = true)
            @RequestParam double averageSalary,

            @Parameter(description = "Number of vacation days")
            @RequestParam(required = false) Integer vacationDays,

            @Parameter(description = "Start date of vacation (format: yyyy-MM-dd)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @Parameter(description = "End date of vacation (format: yyyy-MM-dd)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        VacationPayRequest request = new VacationPayRequest();
        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays != null ? vacationDays : 0);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        double vacationPay = vacationPayService.calculateVacationPay(request);
        return ResponseEntity.ok(new VacationPayResponse(vacationPay));
    }

    @PostMapping("/calculate")
    public ResponseEntity<VacationPayResponse> calculateVacationPayPost(
            @Valid @RequestBody VacationPayRequest request) {
        double vacationPay = vacationPayService.calculateVacationPay(request);
        return ResponseEntity.ok(new VacationPayResponse(vacationPay));
    }
}