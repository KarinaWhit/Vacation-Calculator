package com.example.vacationcalculator.service;

import com.example.vacationcalculator.model.VacationPayRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationPayService {
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private final Set<LocalDate> holidays;

    public VacationPayService() {
        this.holidays = initializeHolidays();
    }

    private Set<LocalDate> initializeHolidays() {
        Set<LocalDate> holidays = new HashSet<>();
        // Fixed Russian holidays (can be extended)
        holidays.add(LocalDate.of(2024, 1, 1));  // New Year
        holidays.add(LocalDate.of(2024, 1, 2));
        holidays.add(LocalDate.of(2024, 1, 7));  // Christmas
        holidays.add(LocalDate.of(2024, 2, 23)); // Defender of the Fatherland Day
        holidays.add(LocalDate.of(2024, 3, 8));  // International Women's Day
        holidays.add(LocalDate.of(2024, 5, 1));  // Spring and Labor Day
        holidays.add(LocalDate.of(2024, 5, 9));  // Victory Day
        holidays.add(LocalDate.of(2024, 6, 12)); // Russia Day
        holidays.add(LocalDate.of(2024, 11, 4)); // National Unity Day
        return holidays;
    }

    public double calculateVacationPay(VacationPayRequest request) {
        validateRequest(request);

        if (request.getStartDate() != null && request.getEndDate() != null) {
            return calculateWithDates(request.getAverageSalary(), request.getStartDate(), request.getEndDate());
        } else {
            return calculateSimple(request.getAverageSalary(), request.getVacationDays());
        }
    }

    private void validateRequest(VacationPayRequest request) {
        if (request.getStartDate() != null && request.getEndDate() != null
                && request.getStartDate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }
    }

    private double calculateSimple(double averageSalary, int vacationDays) {
        return roundToTwoDecimals(averageSalary / AVERAGE_DAYS_IN_MONTH * vacationDays);
    }

    private double calculateWithDates(double averageSalary, LocalDate startDate, LocalDate endDate) {
        int workingDays = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            if (!isHolidayOrWeekend(date)) {
                workingDays++;
            }
            date = date.plusDays(1);
        }

        if (workingDays == 0) {
            throw new IllegalArgumentException("No working days in the specified period");
        }

        return calculateSimple(averageSalary, workingDays);
    }

    private boolean isHolidayOrWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY
                || holidays.contains(date);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}