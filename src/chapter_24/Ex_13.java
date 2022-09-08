package chapter_24;

import chapter_22.ImprovedFibonacci;

import java.util.Iterator;

public class Ex_13 {
    public static void main(String[] args) {
        FibonacciIterator iterator = new FibonacciIterator(120_000);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}

class FibonacciIterator implements Iterator<Long> {
    private final long LIMIT;
    private long fibIndex = 0;
    private long nextFib = ImprovedFibonacci.fib(fibIndex);

    public FibonacciIterator(long limit) {
        this.LIMIT = limit;
    }

    @Override
    public boolean hasNext() {
        return nextFib <= LIMIT;
    }

    @Override
    public Long next() {
        long returningFib = nextFib;
        nextFib = ImprovedFibonacci.fib(++fibIndex);
        return returningFib;
    }
}

