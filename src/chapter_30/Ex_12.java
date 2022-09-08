package chapter_30;

public class Ex_12 {
    public static void main (String[] args) {
        System.out.println(sumDigits(-96384109));
    }

    public static int sumDigits(long n) {
        return String.valueOf(Math.abs(n))
                .chars().map(e -> e - '0').reduce(0, Integer::sum);
    }
}
