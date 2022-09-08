package chapter_30;

public class Ex_14 {
    public static int count(String str, char a) {
        return str.chars().reduce(0, (e1, e2) -> a == e2 ? e1+1 : e1);
    }
}
