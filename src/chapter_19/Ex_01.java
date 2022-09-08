package chapter_19;

import java.util.Arrays;

public class Ex_01 {
    public static void main(String[] args) {
        ArrayGenericStack<String> list = new ArrayGenericStack<>();
        System.out.println(list.getSize() + " isEmpty: " + list.isEmpty());
        list.push("Hello");
        System.out.println(list.peek());
        System.out.println(list.toString());
        list.push("World");
        System.out.println(list.getSize() + " isEmpty: " + list.isEmpty());
        System.out.println(list.peek());
        System.out.println(list.toString());
        System.out.println(list.pop());
        System.out.println(list.peek());
        System.out.println(list.toString());
        System.out.println(list.getSize() + " isEmpty: " + list.isEmpty());
    }
}

@SuppressWarnings("unchecked")
class ArrayGenericStack<E> {
    private int numberOfObject = 0;
    private E[] list = (E[]) new Object[1];

    public int getSize() {
        return numberOfObject;
    }

    public E peek() {
        return list[numberOfObject - 1];
    }

    public void push(E o) {
        if (numberOfObject == list.length) {
            E[] temp = (E[]) new Object[list.length * 2];
            System.arraycopy(list, 0, temp, 0, numberOfObject);
            list = temp;
        }
        list[numberOfObject++] = o;
    }

    public E pop() {
        --numberOfObject;
        if (numberOfObject == 0)
            return null;
        E o = list[numberOfObject];
        list[numberOfObject] = null;
        return o;
    }

    public boolean isEmpty() {
        return numberOfObject == 0;
    }

    @Override
    public String toString() {
        E[] temp = (E[]) new Object[numberOfObject];
        System.arraycopy(list, 0,temp, 0, numberOfObject);
        return "stack: " + Arrays.toString(temp);
    }
}


