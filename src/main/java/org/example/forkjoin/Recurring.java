package org.example.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class Recurring extends RecursiveAction {
    private final double[][] matrix;
    private final int iteration;
    private final double[] pivot;
    private final int threshold;

    public Recurring(double[][] matrix, double[] pivot, int iteration, int threshold) {
        this.matrix = matrix;
        this.iteration = iteration;
        this.pivot = pivot;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (matrix.length > threshold) {
            int split = Math.ceilDiv(matrix.length, 2);
            Recurring f1 = new Recurring(
                    Arrays.copyOfRange(matrix, 0, split), pivot.clone(), iteration, threshold);
            Recurring f2 = new Recurring(
                    Arrays.copyOfRange(matrix, split, matrix.length), pivot.clone(), iteration, threshold);
            f1.fork();
            f2.fork();

            f1.join();
            f2.join();
        } else {
            for (double[] row : matrix) {
                double scale = row[iteration - 1];
                for (int j = 0; j < row.length; j++) {
                    row[j] -= pivot[j] * scale;
                }
            }
        }
    }
}
