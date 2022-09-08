package chapter_19;

public class Ex_03 {
}

class Pair<E> {
    private E o1, o2;
    public Pair(E o1, E o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public E getO1() {
        return o1;
    }

    public void setO1(E o1) {
        this.o1 = o1;
    }

    public E getO2() {
        return o2;
    }

    public void setO2(E o2) {
        this.o2 = o2;
    }
}
