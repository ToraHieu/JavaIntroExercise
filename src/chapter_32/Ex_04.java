package chapter_32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex_04 {
    public static void main(String[] args) {
        IntWrapper asyncSum = new IntWrapper(0);
        ExecutorService asyncExecutor = Executors.newCachedThreadPool();
        SyncIntWrapper syncSum = new SyncIntWrapper(0);

//        CompletableFuture<?>[] asyncFutures = new CompletableFuture[1000];
//        for (int i = 0; i < 1000; i++) {
//            asyncFutures[i] = CompletableFuture.runAsync(new AddSum(asyncSum), asyncExecutor);
//        }
//        CompletableFuture c = CompletableFuture.allOf(asyncFutures);
//        while (!c.isDone()) {
//
//        }
//        System.out.println(asyncSum);

        List<Future<?>> asyncFutures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<?> f = asyncExecutor.submit(new AddSum(asyncSum));
            asyncFutures.add(f);
        }

        for (int i = 0; i < 1000; i++) {
            new Thread(new AddSum(syncSum)).start();
        }

        for(Future<?> future : asyncFutures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        asyncExecutor.shutdown();
        System.out.println("Async: " + asyncSum);
        System.out.println("Sync: " + syncSum);

    }

    private static class AddSum implements Runnable {
        IntWrapper sum;

        public AddSum(IntWrapper sum) {
            this.sum = sum;
        }

        @Override
        public void run() {
            sum.add();
        }
    }

    private static class IntWrapper {
        Integer integer;

        public IntWrapper(Integer integer) {
            this.integer = integer;
        }

        public void add() {
            integer += 1;
        }

        @Override
        public String toString() {
            return String.valueOf(integer);
        }
    }

    private static class SyncIntWrapper extends IntWrapper {
        public SyncIntWrapper(Integer integer) {
            super(integer);
        }

        public synchronized void add() {
            integer += 1;
        }

        @Override
        public String toString() {
            return String.valueOf(integer);
        }
    }
}
