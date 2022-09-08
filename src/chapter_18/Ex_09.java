package chapter_18;

public class Ex_09 {
    public static void reverseDisplay(String value) {
        int l = value.length();
        if (l == 0)
            return;
        System.out.print(value.charAt(--l));
        reverseDisplay(value.substring(0, l));
    }

    public static void main(String[] args) {
        reverseDisplay("abcd");
    }
}
