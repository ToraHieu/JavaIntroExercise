package chapter_18;

public class Ex_10 {
    private static int count = 0;
    public static int count(String str, char a) {
        int i = str.indexOf(a);
        if (i < 0) {
            return count;
        }
        count++;
        return count(str.substring(i + 1), a);
    }

    public static void main(String[] args) {
        System.out.print(count("Welcome", 'e'));
    }
}
