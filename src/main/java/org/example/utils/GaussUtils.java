package org.example.utils;

public class GaussUtils {
    public static void normalize(int row, double[][] matrix) {
        double pivot = matrix[row][row];
        for (int j = row; j < matrix[row].length; j++) {
            matrix[row][j] /= pivot;
        }
        matrix[row][row] = 1;
    }

    public static double[] backSubstitute(double[][] matrix) {
        double[] response = new double[matrix.length];
        for (int i = matrix.length-1; i >= 0; i--) {
            double sum = 0;
            for (int j = i+1; j < matrix.length; j++) {
                sum += matrix[i][j] * response[j];
            }
            response[i] = (matrix[i][matrix.length] - sum) / matrix[i][i];
        }
        return response;
    }
}
