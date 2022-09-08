package chapter_10;

public class Queue {
    private int[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 8;
    
    public Queue() {
        elements = new int[DEFAULT_CAPACITY];
    }
    
    public void enqueue(int v) {
        if (size >= elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = v;
    }
    
    public int dequeue() {
        int removed_Element = elements[0];
        int[] temp = new int[elements.length];
        System.arraycopy(elements, 1, temp, 0, --size);
        elements = temp;
        return removed_Element;
    }
    
    public boolean empty() {
        return size == 0;
    }
    
    public int getSize() {
        return size;
    }

}
