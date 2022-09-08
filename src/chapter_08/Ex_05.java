package chapter_08;

public class Ex_05 {
    public static void main(String[] args) {
        double[][] a = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        double[][] b = {{0, 2.0, 4.0},
                {1, 4.5, 2.2},
                {1.1, 4.3, 5.2}};

        double[][] c = addMatrix(a, b);

        assert c != null;
        for (double[] doubles : c) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }

    public static double[][] addMatrix(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length)
            return null;
        int rows = a.length, column = a[0].length;
        double[][] c = new double[rows][column];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }

        return c;
    }
}
