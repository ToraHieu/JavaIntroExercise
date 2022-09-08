package chapter_18;

public class Ex_21 {
    public static String dec2Bin(int value) {
        return dec2Bin(value, "");
    }

    private static String dec2Bin(int value, String result) {
        result = (value % 2 == 0 ? "0" : "1").concat(result);
        value /= 2;
        if (value == 0)
            return result;
        return dec2Bin(value, result);
    }

    public static void main(String[] args) {
        System.out.print(dec2Bin(10));
    }
}
