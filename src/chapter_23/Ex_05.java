package chapter_23;

import java.util.Comparator;

/** Generic heap sort)
 * Write the following two generic methods using heap sort.
 * The first method sorts the elements using the Comparable interface
 * and the second uses the Comparator interface.
 */
public class Ex_05 {
    public static <E extends Comparable<E>> void heapSort(E[] list) {
        heapSort(list, Comparator.naturalOrder());
    }

    public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
        // Create a Heap of integers
        HeapWithComparator<E> heap = new HeapWithComparator<>(comparator);

        // Add elements to the heap
        for (E e : list) heap.add(e);

        // Remove elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        heapSort(list);

        String[] list2 = {"abc", "acb", "aaaa", "Abc", "s", "xyz"};
        heapSort(list2, String::compareToIgnoreCase);
        for (int value : list) System.out.print(value + " ");
        for (String value : list2) System.out.print(value + " ");
    }
}

class HeapWithComparator<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    private Comparator<? super E> comparator; // For comparing elements

    /** Create a default heap with a comparator*/
    public HeapWithComparator(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /** Create a heap from an array of objects with a comparator */
    public HeapWithComparator(E[] objects, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (E object : objects) {
            add(object);
        }
    }

    /** Add a new object into the heap */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (comparator.compare(list.get(currentIndex),
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }
            else
                break; // the tree is a heap now

            currentIndex = parentIndex;
        }
    }

    /** Remove the root from the heap */
    public E remove() {
        if (list.size() == 0) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (comparator.compare(list.get(maxIndex),
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (comparator.compare(list.get(currentIndex),
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else
                break; // The tree is a heap
        }

        return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return list.size();
    }
}
