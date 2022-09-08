package chapter_18;

public class Ex_12 {
    public static void reverseDisplay(String value) {
        reverseDisplay(value, value.length() - 1);
    }

    private static void reverseDisplay(String value, int high) {
        if (high == 0) {
            System.out.println(value.charAt(high));
            return;
        }
        System.out.print(value.charAt(high));
        reverseDisplay(value, high - 1);
    }

    public static void main(String[] args) {
        reverseDisplay("abcd");
    }
}
