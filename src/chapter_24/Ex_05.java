package chapter_24;

import java.util.LinkedList;

public class Ex_05 {
    public static void main(String[] args) {
        // Create a queue
        InheritanceGenericQueue<String> queue = new InheritanceGenericQueue<>();

        // Add elements to the queue
        queue.enqueue("Tom"); // Add it to the queue
        System.out.println("(7) " + queue);

        queue.enqueue("Susan"); // Add it to the queue
        System.out.println("(8) " + queue);

        queue.enqueue("Kim"); // Add it to the queue
        queue.enqueue("Michael"); // Add it to the queue
        System.out.println("(9) " + queue);

        // Remove elements from the queue
        System.out.println("(10) " + queue.dequeue());
        System.out.println("(11) " + queue.dequeue());
        System.out.println("(12) " + queue);
    }
}
class InheritanceGenericQueue<E> extends LinkedList<E> {
    public void enqueue(E e) {
        addLast(e);
    }

    public E dequeue() {
        return removeFirst();
    }

    public int getSize() {
        return size();
    }
}


