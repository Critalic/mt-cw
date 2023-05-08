package org.example.forkjoin.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class PivotBatched extends RecursiveTask<double[][]> {
    private final double[][] matrix;
    private final int rowsPerThread;

    public PivotBatched(double[][] matrix, int rowsPerThread) {
        this.matrix = matrix;
        this.rowsPerThread = rowsPerThread;
    }

    @Override
    protected double[][] compute() {
        for (int i = 1; i <= matrix.length; i++) {
            normalize(i - 1);

            List<CalculatorBatchTask> calculators = new ArrayList<>();
            int remainder = (matrix.length - i) % rowsPerThread;
            int repetitions = Math.floorDiv((matrix.length - i), rowsPerThread);

            for (int r = 0; r < repetitions; r++) {
                int start = i + r * rowsPerThread;
                int end = r == repetitions - 1 ? i + (r + 1) * rowsPerThread + remainder : i + (r + 1) * rowsPerThread;
                CalculatorBatchTask calculator = new CalculatorBatchTask(
                        Arrays.copyOfRange(matrix, i, end),
                        matrix[i - 1],
                        start, end, i);
                calculator.fork();
                calculators.add(calculator);
            }
            if (repetitions == 0 && remainder > 0) {
                int end = i + remainder;
                CalculatorBatchTask calculator = new CalculatorBatchTask(
                        Arrays.copyOfRange(matrix, i, end),
                        matrix[i - 1],
                        i, end, i);
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
