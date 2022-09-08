package chapter_11;

import java.util.ArrayList;

public class Ex_09 {
    public static void main(String[] agrs) {
        int[][] m = createRandomMatrix(4);
        printMatrix(m);
        
        ArrayList<Integer> maxRows = findMaxRows(m);
        ArrayList<Integer> maxColumns = findMaxColumns(m);
        System.out.println(maxRows.toString() +"\n" + maxColumns.toString());
    }
    
    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static int[][] createRandomMatrix(int n) {
        int[][] m = new int[n][n];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (int) (Math.random() * 2);
            }
        }
        
        return m;
    }
    
    public static ArrayList<Integer> findMaxRows(int[][] m) {
        ArrayList<Integer> result = new ArrayList<>();
        int sum; 
        int currentMax = 0;
        for (int i = 0; i < m.length; i++) {
            sum = 0;
            for (int j = 0; j <m[i].length; j++) {
                sum+= m[i][j];
            }
            if (sum > currentMax) {
                result.clear();
                result.add(i);
                currentMax = sum;
            } else if (sum == currentMax) {
                result.add(i);
            }
        }
        return result;
    }
    
    public static ArrayList<Integer> findMaxColumns(int[][] m) {
        ArrayList<Integer> result = new ArrayList<>();
        int sum; 
        int currentMax = 0;
        for (int i = 0; i < m[0].length; i++) {
            sum = 0;
            for (int j = 0; j <m.length; j++) {
                sum+= m[j][i];
            }
            if (sum > currentMax) {
                result.clear();
                result.add(i);
                currentMax = sum;
            } else if (sum == currentMax) {
                result.add(i);
            }
        }
        return result;
    }

}
