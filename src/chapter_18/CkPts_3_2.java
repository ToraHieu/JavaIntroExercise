package chapter_18;

public class CkPts_3_2 {
    public static void main(String[] args) {
        // This will cause a StackOverflowError
        CkPts_3_2 test = new CkPts_3_2();
        System.out.println(test.toString());
    }
    public CkPts_3_2() {
        CkPts_3_2 test = new CkPts_3_2();
    }
}
