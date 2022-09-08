package chapter_19;

import chapter_13.Complex;

public class Ex_11 {
    public static void main(String[] args) {
        // Create two Rational arrays m1 and m2
        Complex[][] m1 = new Complex[3][3];
        Complex[][] m2 = new Complex[3][3];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                m1[i][j] = new Complex(i + 1, j + 5);
                m2[i][j] = new Complex(i + 1, j + 6);
            }
        }

        // Create an instance of ComplexMatrix
        ComplexMatrix complexMatrix = new ComplexMatrix();

        System.out.println("m1 + m2 is");
        ModifiedGenericMatrix.printResult(m1, m2, complexMatrix.addMatrix(m1, m2), '+');

        System.out.println("\nm1 * m2 is");
        ModifiedGenericMatrix.printResult(m1, m2, complexMatrix.mulitplyMatrix(m1, m2), '*');
    }
}
