package org.example;

import org.example.forkjoin.threadOriented.GaussianFJ;
import org.example.sequential.GaussianS;
import org.example.utils.MatrixGenerator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GaussianFJ gaussianFJ = new GaussianFJ();
        GaussianS gaussianS = new GaussianS();
        double[][] matrix = new MatrixGenerator().generate(2000, 5);
        double[][] matrix2  = deepClone(matrix);
//        print(matrix);



        long startFJ = System.currentTimeMillis();
        double[] res1 = gaussianFJ.solveSystem(matrix, 3, 1000);
        long timeFJ = System.currentTimeMillis() - startFJ;
        System.out.println("Time for FJ - " + timeFJ);
//        print(matrix);

        long startS = System.currentTimeMillis();
        double[] res2 = gaussianS.solveSystem(matrix2);
        long timeS = System.currentTimeMillis() - startS;
        System.out.println("Time for Sequential - " + timeS);
//        print(matrix2);

        System.out.println(Arrays.equals(res1, res2));
    }

    private static void print(double[][] matrix) {
        Arrays.stream(matrix).forEach(row -> {
            Arrays.stream(row).forEach(i -> System.out.print(i + ", "));
            System.out.println();
        });
        System.out.println();
        System.out.println("---------------");
        System.out.println();
    }

    private static double[][] deepClone(double[][] matrix) {
        double[][] m1 = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < m1.length; i++) {
            m1[i] = matrix[i].clone();
        }
        return m1;
    }
}