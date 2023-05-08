package org.example.forkjoin.singular;

import org.example.model.CalculatorResult;

import java.util.concurrent.RecursiveTask;

public class CalculatorSingularTask extends RecursiveTask<CalculatorResult> {
    private final double[] row;
    private final double[] pivot;
    private final int iteration;

    public CalculatorSingularTask(double[] row, double[] pivot, int iteration) {
        this.row = row;
        this.pivot = pivot;
        this.iteration = iteration;
    }

    @Override
    protected CalculatorResult compute() {
        double scale = row[iteration - 1];
        for (int j = 0; j < row.length; j++) {
            row[j] -= pivot[j] * scale;
        }

        return null;
    }
}
