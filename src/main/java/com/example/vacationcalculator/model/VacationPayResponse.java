package com.example.vacationcalculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(description = "Response with calculated vacation pay")
public class VacationPayResponse {
    @Schema(description = "Calculated vacation pay amount", example = "23890.45")
    private double vacationPay;

    public VacationPayResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }
}