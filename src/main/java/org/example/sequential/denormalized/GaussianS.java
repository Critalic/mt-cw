package org.example.sequential.denormalized;

import static org.example.utils.GaussUtils.backSubstitute;

public class GaussianS {
    public double[] solveSystem(double[][] matrix) {
        long startS = System.currentTimeMillis();

        for (int i = 0; i < matrix.length; i++) {
            for (int r = i + 1; r < matrix.length; r++) {
                double scale = matrix[r][i];
                double divisor = matrix[i][i];
                for (int j = i; j < matrix[r].length; j++) {
                    matrix[r][j] -= matrix[i][j] / divisor * scale;
                }
            }
        }
        long timeS = System.currentTimeMillis() - startS;
        System.out.println("Time for elimination Sequential - " + timeS);

        return backSubstitute(matrix);
    }
}
