package chapter_13;

import java.util.ArrayList;

/**
 * I don't understand what the exercise mean. What's there to deep copy? I'll
 * just check the solution file. Brb. Ohhh, so you have to implement the
 * Cloneable interface and deep copy with it.
 */
public class Ex_08 {
    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push("s1");
        stack1.push("s2");
        stack1.push("s");
        
        MyStack stack2 = (MyStack) stack1.clone();
        stack2.pop();
        stack2.pop();
        
        System.out.println(stack1.getSize() + " " + stack2.getSize());
    }
}

class MyStack implements Cloneable {
    private ArrayList<Object> list = new ArrayList<Object>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        return list.get(getSize() - 1);
    }

    public Object pop() {
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public void push(Object o) {
        list.add(o);
    }

    @Override /** Override the toString in the Object class */
    public String toString() {
        return "stack: " + list.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            MyStack cloneStack = (MyStack) super.clone();
            cloneStack.list = (ArrayList<Object>)(this.list.clone());
            return cloneStack;
        } catch (CloneNotSupportedException ex) {
            return null;
        }

    }
}