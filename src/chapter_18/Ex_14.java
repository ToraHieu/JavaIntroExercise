package chapter_18;

public class Ex_14 {
    public static void main(String[] args) {
        System.out.print(even(2019));
    }

    public static boolean odd(int x) {
        if (x == 1)
            return true;
        if (x == 0)
            return false;
        --x;
        --x;
        return odd(x);
    }

    public static boolean even(int x) {
        return !odd(x);
    }
}
