package chapter_11;

public class CkPts_22 {
    public static void main(String[] agrs) {
        A a = new A(3);
        System.out.println(a);
    }

}

class A extends B {
    public A(int t) {
        System.out.println("A's contructor");
    }
}

class B {
    public B() {
        System.out.println("B's contructor");
    }
}
