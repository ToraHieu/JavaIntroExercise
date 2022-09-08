package chapter_20;

import java.util.Stack;

public class Ex_12 {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>(1, 2, 3, 4, 5);
        while (!myStack.empty())
            System.out.print(myStack.pop() + " ");
    }
}

class MyStack<E> extends Stack<E> {
    @SafeVarargs
    @SuppressWarnings("WeakerAccess")
    public MyStack(E... es) {
        for (E e: es)
            push(e);
    }
}