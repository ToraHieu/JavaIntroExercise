package chapter_18;

public class CkPts_2_5 {
    public static void main(String[] args) {
        System.out.print(myPower(0.5, 3));
    }

    public static double myPower(double x, int n) {
        if (n > 0)
            return x * myPower(x, n - 1);
        else
            return 1;
    }
}
