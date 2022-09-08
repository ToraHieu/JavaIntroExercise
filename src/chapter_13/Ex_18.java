package chapter_13;

public class Ex_18 {
    /** This will take some times and gives out errors. Use Rational_15 (defined in Ex_15) will solve the problem.*/
    public static void main(String[] args) {
        Rational_15 sum = new Rational_15(0, 1);
        for (int i = 1; i < 100; i++) {
            sum = sum.add(new Rational_15(i, i + 1));
        }
        System.out.print(sum.toString());
    }
}
