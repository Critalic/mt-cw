package org.example.forkjoin.normalized;

import java.util.concurrent.ForkJoinPool;

import static org.example.utils.GaussUtils.backSubstitute;

public class GaussianFJ {
    public double[] solveSystem(double[][] matrix, int threads, int minimalThreadRows) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long startS = System.currentTimeMillis();
        pool.invoke(new PivotRecur(matrix, threads, minimalThreadRows));
        long timeS = System.currentTimeMillis() - startS;
        System.out.println("Time for elimination FJ - " + timeS);

        return backSubstitute(matrix);
    }
}
