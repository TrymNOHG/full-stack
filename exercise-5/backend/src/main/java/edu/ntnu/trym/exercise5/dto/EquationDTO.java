package edu.ntnu.trym.exercise5.dto;

import lombok.Builder;

@Builder
public record EquationDTO(String expression, double result) {
}
