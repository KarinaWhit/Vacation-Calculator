package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VacationPayController.class)
class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationPayService vacationPayService;

    @Test
    void calculateVacationPaySimple() throws Exception {
        when(vacationPayService.calculateVacationPay(any()))
                .thenReturn(14334.47);

        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "30000")
                        .param("vacationDays", "14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(14334.47));
    }

    @Test
    void calculateVacationPayWithDates() throws Exception {
        when(vacationPayService.calculateVacationPay(any()))
                .thenReturn(10238.91);

        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "30000")
                        .param("startDate", "2024-05-01")
                        .param("endDate", "2024-05-14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(10238.91));
    }
}