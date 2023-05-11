package org.example.utils;

import java.util.Random;

public class MatrixGenerator {
    Random random = new Random();

    public double[][] generate(int size, int upperLimit) {
        double[][] response = new double[size - 1][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                response[i][j] = random.nextInt(upperLimit) + 2;
            }
        }
        return response;
    }
}
