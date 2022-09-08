package chapter_24;

import java.util.ArrayList;
import java.util.Comparator;

public class Ex_17 {
    public static final int ARRAY_SIZE = 5_000_000;
    /* The higher this is, the less likely dequeue mid run happen.
    * Realistically, dequeue should happen fairly often during enqueue process,
    * so the lower number represent a more realistic use-case,
    * proving that MyPriorityQueue uses a Heap to be more efficient than PriorityUsingSortedArrayList.
    * In other word, PriorityUsingSortedArrayList is more efficient
    * when a huge amount of enqueue and dequeue is called consecutively, e.g. 1m enqueue and then 1m dequeue.
    * However, in an edge case where DEQUEUE_CHANCE_DIVIDER is too small, e.g. 0-2, PriorityUsingSortedArrayList
    * is probably more efficient due to it using ArrayList thus having a smaller overhead to MyPriorityQueue.
    * */
    public static final int DEQUEUE_CHANCE_DIVIDER = 10;

    public static void main(String[] args) {
        long startTime, endTime;
        Integer[] integers = new Integer[ARRAY_SIZE];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = (int) (Math.random() * ARRAY_SIZE);
        }
        PriorityUsingSortedArrayList<Integer> priorityUsingSortedArrayList = new PriorityUsingSortedArrayList<>();
        MyPriorityQueue<Integer> myPriorityQueue = new MyPriorityQueue<>();

        startTime = System.currentTimeMillis();
        for (Integer i : integers) {
            priorityUsingSortedArrayList.enqueue(i);
            if ((int) (Math.random() * DEQUEUE_CHANCE_DIVIDER) == 0) {
                int dequeueNumber = (int) (Math.random() * priorityUsingSortedArrayList.getSize());
                for (int j = 0; j < dequeueNumber; j++) {
                    priorityUsingSortedArrayList.dequeue();
                }
            }
        }
        while (priorityUsingSortedArrayList.getSize() > 0) {
            priorityUsingSortedArrayList.dequeue();
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n PriorityUsingSortedArrayList time: " + (endTime - startTime) + "\n");


        startTime = System.currentTimeMillis();
        for (Integer i : integers) {
            myPriorityQueue.enqueue(i);
            if ((int) (Math.random() * DEQUEUE_CHANCE_DIVIDER) == 0) {
                int dequeueNumber = (int) (Math.random() * myPriorityQueue.getSize());
                for (int j = 0; j < dequeueNumber; j++) {
                    myPriorityQueue.dequeue();
                }
            }
        }
        while (myPriorityQueue.getSize() > 0) {
            myPriorityQueue.dequeue();
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n MyPriorityQueue time: " + (endTime - startTime) + "\n");
    }
}

class PriorityUsingSortedArrayList<E extends Comparable<E>> {
    private boolean needSort = false;
    private ArrayList<E> list = new ArrayList<>();

    public void enqueue(E newObject) {
        list.add(newObject);
        needSort = true;
    }

    public E dequeue() {
        if (needSort) {
            needSort = false;
            list.sort(Comparator.naturalOrder());
        }
        return list.remove(getSize() - 1);
    }

    public int getSize() {
        return list.size();
    }
}