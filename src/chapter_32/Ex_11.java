package chapter_32;

public class Ex_11 {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (obj1) {
                try {
                    Thread.sleep(100);
                    synchronized (obj2) {
                        System.out.println("Thread 1 sync");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (obj2) {
                try {
                    Thread.sleep(100);
                    synchronized (obj1) {
                        System.out.println("Thread 2 sync");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
