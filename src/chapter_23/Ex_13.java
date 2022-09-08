package chapter_23;

import java.util.Arrays;

import static chapter_23.BubbleSort.bubbleSort;
import static chapter_23.Ex_12.radixSort;
import static chapter_23.HeapSort.heapSort;
import static chapter_23.InsertionSort.insertionSort;
import static chapter_23.MergeSort.mergeSort;

/** (Execution time for sorting)
 * Write a program that obtains the execution time of selection sort, bubble sort, merge sort,
 * quick sort, heap sort, and radix sort for input size 60,000 120,000 180,000 240,000 300,000 360,000.
 * Use a non-recursive version of Quick sort in this exercise.
 * */
public class Ex_13 {
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        // Create an auxiliary stack
        int[] stack = new int[last - first + 1];

        // Initialize top of stack
        int top = -1;

        // push initial values of first and last to stack
        stack[++top] = first;
        stack[++top] = last;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop last and first
            last = stack[top--];
            first = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(list, first, last);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > first) {
                stack[++top] = first;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < last) {
                stack[++top] = p + 1;
                stack[++top] = last;
            }
        }
    }

    /** Partition the array list[first..last] */
    public static int partition(int[] list, int first, int last) {
        int middle = first + (last - first) / 2;
        // Choose the median element as the pivot
        int pivot = median(list[first], list[middle], list[last]);
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

    /** The main method */
    public static void main(String[] args) {
        // Initialize the input
        int[][] input = new int[6][];
        for (int i = 0; i < input.length; i++) {
            input[i] = new int[60_000 * (i + 1)];
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = (int) (Math.random() * 1_000);
            }
        }

        // input.length is number of test case, 6 is number of algorithms tested
        long[][] output = new long[input.length][6];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                // Copy the original input for each algorithm
                int[] data = new int[input[i].length];
                System.arraycopy(input[i], 0, data, 0, data.length);

                long startTime, endTime;

                // I could have place the startTime, endTime and execution time (output) outside of the switch statement
                // but I thought it may affect the result ever so slightly,
                // especially at the heapSort case because it requires extra step converting the int[] to Integer[].
                switch (j) {
                    case 0: {
                        startTime = System.currentTimeMillis();
                        insertionSort(data);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                    case 1: {
                        startTime = System.currentTimeMillis();
                        bubbleSort(data);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                    case 2: {
                        startTime = System.currentTimeMillis();
                        mergeSort(data);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                    case 3: {
                        startTime = System.currentTimeMillis();
                        quickSort(data);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                    case 4: {
                        Integer[] list = Arrays.stream( data ).boxed().toArray( Integer[]::new );
                        startTime = System.currentTimeMillis();
                        heapSort(list);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                    case 5: {
                        startTime = System.currentTimeMillis();
                        radixSort(data);
                        endTime = System.currentTimeMillis();
                        output[i][j] = endTime - startTime;
                        break;
                    }
                }
            }
        }

        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                "Array size", "Insertion Sort", "Bubble Sort", "Merge Sort", "Quick Sort", "Heap Sort", "Radix Sort");
        for (int i = 0; i < output.length; i++) {
            System.out.printf("%20d%20d%20d%20d%20d%20d%20d\n",
                    input[i].length, output[i][0], output[i][1], output[i][2], output[i][3], output[i][4], output[i][5]);
        }
    }

    /** Returns the median of three integers */
    public static int median(int first, int middle, int last) {
        return Math.max(Math.min(first, middle),
                Math.min(Math.max(first, middle), last));
    }
}
