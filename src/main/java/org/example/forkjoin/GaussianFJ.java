package org.example.forkjoin;

import org.example.forkjoin.batch.PivotBatched;
import org.example.forkjoin.singular.PivotSingular;

import java.util.concurrent.ForkJoinPool;

import static org.example.utils.GaussUtils.backSubstitute;

public class GaussianFJ {
    public double[] solveSystem(double[][] matrix, int rowsPerThread) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long startS = System.currentTimeMillis();
        pool.invoke(new PivotRecur(matrix, rowsPerThread));
        long timeS = System.currentTimeMillis() - startS;
        System.out.println("Time for elimination FJ - " + timeS);

        return backSubstitute(matrix);
    }
}
