package chapter_32;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex_10 {
    public static void main(String[] args) {
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                set.add(i);
                System.out.println("Add " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (set.size() != 100) {
                synchronized (set) {
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        System.out.println("Read: " + iterator.next());
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
