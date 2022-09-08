package chapter_19;

public class Ex_06 {
}

class Association<E1, E2> {
    private E1 o1;
    private E2 o2;

    public Association(E1 o1, E2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public E1 getO1() {
        return o1;
    }

    public void setO1(E1 o1) {
        this.o1 = o1;
    }

    public E2 getO2() {
        return o2;
    }

    public void setO2(E2 o2) {
        this.o2 = o2;
    }
}

class Transition<E1, E2, E3> {
    private E1 o1;
    private E2 o2;
    private E3 o3;

    public Transition(E1 o1, E2 o2, E3 o3) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
    }

    public E1 getO1() {
        return o1;
    }

    public void setO1(E1 o1) {
        this.o1 = o1;
    }

    public E2 getO2() {
        return o2;
    }

    public void setO2(E2 o2) {
        this.o2 = o2;
    }

    public E3 getO3() {
        return o3;
    }

    public void setO3(E3 o3) {
        this.o3 = o3;
    }
}
