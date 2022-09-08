package chapter_23;

public class Ex_20 {
    /** The method for sorting the numbers */
    public static void mergeSort(int[] list) {
        mergeSort(list, 0, list.length - 1);
    }

    /** A helper method that recursively merge sort*/
    private static void mergeSort(int[] list, int first, int last) {
        if (first < last) {
            // Merge sort the first half
            mergeSort(list, first, first + (last - first) / 2);

            // Merge sort the second half
            mergeSort(list, first + (last - first) / 2 + 1, last);

            // Merge the first half and second half into the list
            merge(list, first, last);
        }
    }

    /** Merge two sorted lists */
    private static void merge(int[] list, int first, int last) {
        int current1 = first; // Current index in firstHalf
        int current2 = first + (last - first) / 2 + 1; // Current index in secondHalf
        int current3 = 0; // Current index in temp

        int[] temp = new int[last - first + 1];

        while(current1 <= first + (last - first) / 2 && current2 <= last) {
            if (list[current1] < list[current2])
                temp[current3++] = list[current1++];
            else
            temp[current3++] = list[current2++];
        }

        while (current1 <= first + (last - first) / 2)
            temp[current3++] = list[current1++];

        while (current2 <= last)
            temp[current3++] = list[current2++];

        System.arraycopy(temp, 0, list, first, temp.length);
    }

    /** A test method */
    public static void main(String[] args) {
        int[] list = {4, 2, 3, 2, 5, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(list);
        for (int value : list) System.out.print(value + " ");
    }
}
