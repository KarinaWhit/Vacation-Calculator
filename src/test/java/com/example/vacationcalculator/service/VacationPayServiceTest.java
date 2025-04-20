package com.example.vacationcalculator.service;

import com.example.vacationcalculator.model.VacationPayRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VacationPayServiceTest {

    private VacationPayService vacationPayService;

    @BeforeEach
    void setUp() {
        vacationPayService = new VacationPayService();
    }
    @Test
    void calculateSimpleVacationPay() {
        VacationPayRequest request = new VacationPayRequest();
        request.setAverageSalary(30000);
        request.setVacationDays(14);

        double result = vacationPayService.calculateVacationPay(request);
        assertEquals(14334.47, result, 0.01);
    }
}