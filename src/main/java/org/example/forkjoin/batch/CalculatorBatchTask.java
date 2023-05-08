package org.example.forkjoin.batch;

import org.example.model.CalculatorResult;

import java.util.concurrent.RecursiveTask;

public class CalculatorBatchTask extends RecursiveTask<CalculatorResult> {
    private final double[][] matrix;
    private final double[] pivot;
    private final int start;
    private final int end;
    private final int iteration;

    public CalculatorBatchTask(double[][] matrix, double[] pivot, int start, int end, int iteration) {
        this.matrix = matrix;
        this.pivot = pivot;
        this.start = start;
        this.end = end;
        this.iteration = iteration;
    }

    @Override
    protected CalculatorResult compute() {
        for (double[] row : matrix) {
            double scale = row[iteration - 1];
            for (int j = 0; j < row.length; j++) {
                row[j] -= pivot[j] * scale;
            }
        }
        return new CalculatorResult(start, end, matrix);
    }
}
