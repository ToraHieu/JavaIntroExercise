package chapter_19;

import java.util.ArrayList;

public class Ex_02 {
    public static void main(String[] args) {
        InheritanceGenericStack<String> stack = new InheritanceGenericStack<>();
        // Stimulate user's input because it's a pain to type every time I run.
        String[] s = new String[5];
        s[0] = "Hello World";
        s[1] = "Hello there.";
        s[2] = "General Kenobi!!!!";
        s[3] = "*Light sabers intensifies*";
        s[4] = "I haven't seen any Star war movie, though.";

        for (int i = 0; i < 5; i++) {
           stack.push(s[i]);
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + " ");
        }
    }
}

class InheritanceGenericStack<E> extends ArrayList<E> {
    public int getSize() {
        return size();
    }

    public E peek() {
        return get(size() - 1);
    }

    public void push(E o) {
        add(o);
    }

    public E pop() {
        E o = get(size() - 1);
        remove(o);
        return o;
    }

    public boolean isEmpty() {
        return isEmpty();
    }

    @Override
    public String toString() {
        return "stack: " + toString();
    }
}
