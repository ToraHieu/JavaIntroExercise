package chapter_16;

public class CkPts_4_1 {
    public static void main(String[] args) {
        CkPts_4_1 test = new CkPts_4_1();
        test.new B().start();
    }

    class A {
        public void start() {
            System.out.print(getP());
        }

        public int getP() {
            return 1;
        }
    }

    class B extends A {
        public int getP() {
            return 2 + super.getP();
        }
    }
}
