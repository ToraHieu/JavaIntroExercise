package chapter_32;

import chapter_08.Ex_05;
import chapter_08.Ex_06;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Ex_17 {
    public static void main(String[] args) {
        final int ROW_LENGTH = 2_000, COL_LENGTH = 2_000;
        double[][] a = new double[ROW_LENGTH][COL_LENGTH];
        double[][] b = new double[ROW_LENGTH][COL_LENGTH];
        double[][] c = new double[ROW_LENGTH][COL_LENGTH];
        double[][] d = new double[ROW_LENGTH][COL_LENGTH];

        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COL_LENGTH; j++) {
                a[i][j] = i + j;
                b[i][j] = a[i][j] * 2;
            }
        }

        long startTime = System.currentTimeMillis();
        d = parallelMultiplyMatrix(a, b);
        long endTime = System.currentTimeMillis();
        System.out.println("Number of processors is " +
                Runtime.getRuntime().availableProcessors());
        System.out.println("Time with " + (endTime - startTime)
                + " milliseconds");


        startTime = System.currentTimeMillis();
        multiplyMatrix(a, b, c, 0, ROW_LENGTH, 0, COL_LENGTH);
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential time is " +
                (endTime - startTime) + " milliseconds");
    }

    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length)
            return null;

        int rows = a.length, column = a[0].length;
        double[][] c = new double[rows][column];

        RecursiveAction mainTask = new MultiplyMatrixAction(a, b, c, 0, rows, 0, column);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
        return c;
    }

    private static class MultiplyMatrixAction extends RecursiveAction {
        private static final int THRESHOLD = 1_000;
        private final double[][] a, b, c;
        private final int rowLow, rowHigh, colLow, colHigh;

        public MultiplyMatrixAction(double[][] a, double[][] b, double[][] c,
                                    int rowLow, int rowHigh, int colLow, int colHigh) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.rowLow = rowLow;
            this.rowHigh = rowHigh;
            this.colLow = colLow;
            this. colHigh = colHigh;
        }

        @Override
        protected void compute() {
            if ((rowHigh - rowLow) * (colHigh - colLow)  <= THRESHOLD) {
                multiplyMatrix(a, b, c, rowLow, rowHigh, colLow, colHigh);
            } else {
                int rowMid = rowLow + (rowHigh - rowLow) / 2;
                int colMid = colLow + (colHigh - colLow) / 2;
                invokeAll(new MultiplyMatrixAction(a, b, c, rowLow, rowMid, colLow, colMid),
                        new MultiplyMatrixAction(a, b, c, rowMid, rowHigh, colMid, colHigh),
                        new MultiplyMatrixAction(a, b, c, rowMid, rowHigh, colLow, colMid),
                        new MultiplyMatrixAction(a, b, c, rowLow, rowMid, colMid, colHigh));
            }
        }
    }

    /** Calculate the addition of matrix a and matrix b to matrix c from low to high */
    public static void multiplyMatrix(double[][] a, double[][] b, double[][] c,
                                      int rowLow, int rowHigh, int colLow, int colHigh) {
        int n = b.length;
        for (int i = rowLow; i < rowHigh; i++) {
            for (int j = colLow; j < colHigh; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum+= a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
    }
}
