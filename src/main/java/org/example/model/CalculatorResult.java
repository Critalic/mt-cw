package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculatorResult {
    private final int start;
    private final int end;
    private final double[][] matrix;

}
