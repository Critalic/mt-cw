package org.example.forkjoin.threadOriented;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class PivotRecur extends RecursiveAction {
    private final double[][] matrix;
    private final int threads;
    private final int minimalRowsThread;

    public PivotRecur(double[][] matrix, int threads, int minimalRowsThread) {
        this.matrix = matrix;
        this.threads = threads;
        this.minimalRowsThread = minimalRowsThread;
    }

    @Override
    protected void compute() {
        for (int i = 1; i <= matrix.length; i++) {
//            normalize(i - 1);

            int rowsPerThread = Math.floorDiv(matrix.length, threads);
            new Recurring(Arrays.copyOfRange(matrix, i, matrix.length), matrix[i-1].clone(), i-1,
                    Math.max(rowsPerThread, minimalRowsThread)).invoke();
        }
    }

    private void normalize(int row) {
        double pivot = matrix[row][row];
        for (int j = row; j < matrix[row].length; j++) {
            matrix[row][j] /= pivot;
        }
        matrix[row][row] = 1;
    }
}
