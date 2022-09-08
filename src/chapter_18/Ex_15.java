package chapter_18;

public class Ex_15 {
    public static int count(String str, char a) {
        return count(str, a, 0);
    }

    public static int count(String str, char a, int high) {
        if (high == str.length())
            return 0;
        int isChar = str.charAt(high) == a ? 1 : 0;

        return isChar + count(str, a, high + 1);
    }

    public static void main(String[] args) {
        System.out.print(count("Welcome", 'e'));
    }
}
