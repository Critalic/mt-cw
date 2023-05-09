package org.example.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class PivotRecur extends RecursiveAction {
    private final double[][] matrix;
    private final int rowsPerThread;

    public PivotRecur(double[][] matrix, int rowsPerThread) {
        this.matrix = matrix;
        this.rowsPerThread = rowsPerThread;
    }

    @Override
    protected void compute() {
        for (int i = 1; i <= matrix.length; i++) {
            normalize(i - 1);

            new Recurring(Arrays.copyOfRange(matrix, i, matrix.length), matrix[i-1], i, rowsPerThread).fork().join();
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
