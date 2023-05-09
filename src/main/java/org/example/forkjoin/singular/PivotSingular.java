package org.example.forkjoin.singular;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class PivotSingular extends RecursiveTask<double[][]> {
    private final double[][] matrix;
    private final int rowsPerThread;

    public PivotSingular(double[][] matrix, int rowsPerThread) {
        this.matrix = matrix;
        this.rowsPerThread = rowsPerThread;
    }

    @Override
    protected double[][] compute() {
        for (int i = 1; i <= matrix.length; i++) {
            normalize(i - 1);

            List<CalculatorSingularTask> calculators = new ArrayList<>();

            for (int r = i; r < matrix.length-i; r++) {
                CalculatorSingularTask calculator = new CalculatorSingularTask(
                        matrix[r],
                        matrix[i - 1].clone(), i);
                calculator.fork();
                calculators.add(calculator);
            }
            calculators.forEach(ForkJoinTask::join);
        }
        return matrix;
    }

    private void normalize(int row) {
        double pivot = matrix[row][row];
        for (int j = row; j < matrix[row].length; j++) {
            matrix[row][j] /= pivot;
        }
        matrix[row][row] = 1;
    }
}
