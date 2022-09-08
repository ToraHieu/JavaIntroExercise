package chapter_20;

import java.util.LinkedList;

public class Ex_06 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        // Will take A.LOT of time, reduce the number down to thousands
        for (int i = 0; i < 10000000; i++)
            list.add(i);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++)
            list.get(i);
        long endTime = System.currentTimeMillis();
        System.out.print("Traverse time using index is " + (endTime - startTime));

        int x;
        startTime = System.currentTimeMillis();
        for (int i: list) {
            x = i;
        }
        endTime = System.currentTimeMillis();
        System.out.print("Traverse time using iterator is " + (endTime - startTime));
    }
}
