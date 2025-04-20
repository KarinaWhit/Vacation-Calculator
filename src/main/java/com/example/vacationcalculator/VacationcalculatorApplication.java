package com.example.vacationcalculator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Vacation Pay Calculator API",
		version = "1.0",
		description = "API for calculating vacation pay amounts"
))
class VacationCalculatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(VacationCalculatorApplication.class, args);
	}
}