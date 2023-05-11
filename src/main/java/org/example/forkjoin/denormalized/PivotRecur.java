package org.example.forkjoin.denormalized;

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
            new Recurring(Arrays.copyOfRange(matrix, i, matrix.length), matrix[i-1], i, rowsPerThread).fork().join();
        }
    }
}
