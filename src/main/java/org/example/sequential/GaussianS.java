package org.example.sequential;

import static org.example.utils.GaussUtils.backSubstitute;

public class GaussianS {
    public double[] solveSystem(double[][] matrix) {
        long startS = System.currentTimeMillis();

        for (int i = 0; i < matrix.length; i++) {
            normalize(i, matrix);

            for (int r = i+1; r < matrix.length; r++) {
                double scale = matrix[r][i];
                for (int j = i; j < matrix[r].length; j++) {
                    matrix[r][j] -= matrix[i][j] * scale;
                }
            }
        }
        long timeS = System.currentTimeMillis() - startS;
        System.out.println("Time for elimination Sequential - " + timeS);

        return backSubstitute(matrix);
    }

    private void normalize(int row, double[][] matrix) {
        double pivot = matrix[row][row];
        for (int j = row; j < matrix[row].length; j++) {
            matrix[row][j] /= pivot;
        }
        matrix[row][row] = 1;
    }
}
