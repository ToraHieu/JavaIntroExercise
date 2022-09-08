package chapter_26;

import chapter_25.BST;

import java.util.Random;

public class Ex_02 {
    private static final int LENGTH = 600_000;

    public static void main(String[] args) {
        int[] arr = new int[LENGTH], secondArr = new int[LENGTH], thirdArr = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            arr[i] = secondArr[i] = thirdArr[i] = i;

        }
        shuffleArray(arr);
        shuffleArray(secondArr);
        shuffleArray(thirdArr);

        BST<Integer> bst = new BST<>();
        AVLTree<Integer> avlTree = new AVLTree<>();

        long startTime, stopTime;
        startTime = System.nanoTime();
        for (int i: arr) {
            bst.insert(i);
        }
        for (int i: secondArr) {
            bst.search(i);
        }
        for (int i: thirdArr) {
            bst.search(i);
        }
        for (int i: arr) {
            bst.search(i);
        }
//        for (int i: thirdArr) {
//            bst.delete(i);
//        }
        stopTime = System.nanoTime();

        System.out.println("BST runtime: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i: arr) {
            avlTree.insert(i);
        }
        for (int i: secondArr) {
            avlTree.search(i);
        }
        for (int i: thirdArr) {
            avlTree.search(i);
        }
        for (int i: arr) {
            avlTree.search(i);
        }
//        for (int i: thirdArr) {
//            avlTree.delete(i);
//        }
        stopTime = System.nanoTime();

        System.out.println("AVLT runtime: " + (stopTime - startTime));
    }

    static void shuffleArray(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
