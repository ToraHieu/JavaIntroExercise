package chapter_18;

import java.util.Arrays;

public class Ex_16 {
    public static int pos(int[] a, int l, int r) {
//        if (l == r)
//            return l;
//        int i;
//        // Search for the element a[i] that is less than a[l] (a.k.a a[k])
//        for (i = l; i <= r; i++) {
//            if (a[i] < a[l]) { // a[k] > a[i]
//                // Swap a[l+1] with a[i]
//                swap(a, l+1, i)
//
//                // Swap a[l] with a[l+1]
//                swap(a, l, l+1);
//                break;
//            }
//        }
//        return pos(a, i, r);
//        // Holds up. I think I kinda implemented it in the Loop way.
//        // Just a few tweak and it will work perfectly fine as a Loop.
//        // Oh well, as long as it works, I guess?
//        // Nevermind, I implemented a proper recursive method... probably. The idea is the same anyway.

        return pos(a, l, r, l);
    }

    public static int pos(int[] a, int left, int right, int currentIndex) {
        if (currentIndex == right)
            return left; // left is our rank to be returned
        if (a[left] > a[currentIndex]) {
            // Swap a[left + 1] with a[currentIndex]
            swap(a, left + 1, currentIndex);

            // Swap a[left] with a[left + 1]
            swap(a, left, left + 1);
            left++; // Update left
        }
        return pos(a, left, right, currentIndex + 1);
    }

    public static void main(String[] args) {
        int[] a = {8,7,4,1,9,6,2,5,3,0};
        int k = pos(a, 2, 7);
        System.out.println(Arrays.toString(a) + " k: " + k);
    }

    private static void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
}
