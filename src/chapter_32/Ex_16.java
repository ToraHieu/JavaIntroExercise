package chapter_32;

import chapter_08.Ex_05;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Ex_16 {
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
        d = parallelAddMatrix(a, b);
        long endTime = System.currentTimeMillis();
        System.out.println("Number of processors is " +
                Runtime.getRuntime().availableProcessors());
        System.out.println("Time with " + (endTime - startTime)
                + " milliseconds");


        startTime = System.currentTimeMillis();
        c = Ex_05.addMatrix(a, b);
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential time is " +
                (endTime - startTime) + " milliseconds");
    }

    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length)
            return null;

        int rows = a.length, column = a[0].length;
        double[][] c = new double[rows][column];

        RecursiveAction mainTask = new AddMatrixAction(a, b, c, 0, rows, 0, column);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
        return c;
    }

    private static class AddMatrixAction extends RecursiveAction {
        private static final int THRESHOLD = 1_000;
        private final double[][] a, b, c;
        private final int rowLow, rowHigh, colLow, colHigh;

        public AddMatrixAction(double[][] a, double[][] b, double[][] c,
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
                addMatrix(a, b, c, rowLow, rowHigh, colLow, colHigh);
            } else {
                int rowMid = rowLow + (rowHigh - rowLow) / 2;
                int colMid = colLow + (colHigh - colLow) / 2;
                invokeAll(new AddMatrixAction(a, b, c, rowLow, rowMid, colLow, colMid),
                        new AddMatrixAction(a, b, c, rowMid, rowHigh, colMid, colHigh));
            }
        }
    }

    /** Calculate the addition of matrix a and matrix b to matrix c from low to high */
    public static void addMatrix(double[][] a, double[][] b, double[][] c,
                                 int rowLow, int rowHigh, int colLow, int colHigh) {
        for (int i = rowLow; i < rowHigh; i++) {
            for (int j = colLow; j < colHigh; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
    }
}
