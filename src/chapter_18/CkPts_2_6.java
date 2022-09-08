package chapter_18;

public class CkPts_2_6 {
    public static void main(String[] args) {
        System.out.print(mySum(5));
    }

    public static int mySum(int n) {
        if (n > 0)
            return n + mySum(n - 1);
        else
            return 0;
    }
}
