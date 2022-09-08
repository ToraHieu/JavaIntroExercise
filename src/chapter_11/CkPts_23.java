package chapter_11;

public class CkPts_23 {
    public static void main(String[] agrs) {
        new E();
        new F();
    }

}

class E {
    int i = 7;
    
    public E() {
        setI(20);
        System.out.println("i from E is " + i);
    }
    
    public void setI(int i) {
        this.i = 2 * i;
    }
}

class F extends E {
    public F() {
        System.out.println("i from F is " + i);
    }
    
    
    public void setI(int i) {
        this.i = 3 * i;
    }
}
