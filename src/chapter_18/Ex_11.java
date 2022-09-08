package chapter_18;

import java.util.Arrays;

public class Ex_11 {
    public static void displayPermutations(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size;) {
            arr[i] = ++i;
        }
        displayPermutations(arr, 0, arr.length - 1);
    }

    private static void displayPermutations(int[] arr, int l, int r) {
        if (l == r)
            System.out.println(Arrays.toString(arr));
        else {
            for (int i = l; i <= r; i++) {
                swap(arr, l, i);
                displayPermutations(arr, l + 1, r);
                swap(arr, l, i);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        displayPermutations(5);
    }
}
