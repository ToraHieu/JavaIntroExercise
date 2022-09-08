package chapter_23;

public class Ex_07 {
    /** Heap sort method */
    public static <E extends Comparable<E>> void heapSort(E[] list) {
        // Create a Heap of integers
        MinHeap<E> heap = new MinHeap<E>();

        // Add elements to the heap
        for (E e : list) heap.add(e);

        // Remove elements from the heap
        for (int i =0; i < list.length; i++)
            list[i] = heap.remove();
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        heapSort(list);
        for (Integer integer : list) System.out.print(integer + " ");
    }
}

class MinHeap<E extends Comparable<E>> {
    java.util.ArrayList<E> list = new java.util.ArrayList<>();

    /** Create a default heap */
    public MinHeap() {
    }

    /** Create a heap from an array of objects */
    public MinHeap(E[] objects) {
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
            // Swap if the current object is smaller than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) < 0) {
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
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) > 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is greater than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) > 0) {
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
