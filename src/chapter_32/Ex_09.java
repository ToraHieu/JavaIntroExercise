package chapter_32;

import java.util.HashSet;
import java.util.Iterator;

public class Ex_09 {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                set.add(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                Iterator<Integer> iterator = set.iterator();
                while (iterator.hasNext()) {
                    iterator.next();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
