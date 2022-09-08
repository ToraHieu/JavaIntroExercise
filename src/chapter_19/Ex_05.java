package chapter_19;

public class Ex_05 {
}

class Triplet<E> {
    private E o1, o2, o3;

    public Triplet(E o1, E o2, E o3) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
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

    public E getO3() {
        return o3;
    }

    public void setO3(E o3) {
        this.o3 = o3;
    }
}
