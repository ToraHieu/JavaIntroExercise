package chapter_24;

import java.util.Comparator;

public class Ex_06 {
    public static void main(String[] args) {
        Patient patient1 = new Patient("John", 2);
        Patient patient2 = new Patient("Jim", 1);
        Patient patient3 = new Patient("Tim", 5);
        Patient patient4 = new Patient("Cindy", 7);

        PriorityQueue<Patient> priorityQueue
                = new PriorityQueue<>((o1, o2) -> o2.priority - o1.priority);
        priorityQueue.enqueue(patient1);
        priorityQueue.enqueue(patient2);
        priorityQueue.enqueue(patient3);
        priorityQueue.enqueue(patient4);

        while (priorityQueue.getSize() > 0)
            System.out.print(priorityQueue.dequeue() + " ");
    }

    static class Patient implements Comparable<Patient> {
        private String name;
        private int priority;

        public Patient(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return name + "(priority:" + priority + ")";
        }

        public int compareTo(Patient o) {
            return this.priority - o.priority;
        }
    }
}

class PriorityQueue<E> {
    private HeapWithComparator<E> heap;

    public PriorityQueue(Comparator<? super E> comparator) {
        heap = new HeapWithComparator<>(comparator);
    }

    public void enqueue(E newObject) {
        heap.add(newObject);
    }

    public E dequeue() {
        return heap.remove();
    }

    public int getSize() {
        return heap.getSize();
    }

    /** Copied from Ex_05.HeapWithComparator */
    private static class HeapWithComparator<E> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private Comparator<? super E> comparator; // For comparing elements

        /** Create a default heap with a comparator*/
        public HeapWithComparator(Comparator<? super E> comparator) {
            this.comparator = comparator;
        }

        /** Create a heap from an array of objects with a comparator */
        @SuppressWarnings("unused")
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
}
