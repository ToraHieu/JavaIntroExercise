package chapter_13;
/** Copied and modified from Calculator.java in chapter 7 */
public class Ex_16 {
    /** Main method */
    public static void main(String[] args) {
        // Check number of strings passed
        if (args.length != 3) {
            System.out.println("Usage: operand1 operator operand2");
            System.exit(0);
        }

        // The result of the operation
        Rational result = null;
        
        // Getting the rational numbers
        String[] s1 = args[0].split("/");
        Rational r1 = new Rational(Long.parseLong(s1[0]), Long.parseLong(s1[1]));
        String[] s2 = args[2].split("/");
        Rational r2 = new Rational(Long.parseLong(s2[0]), Long.parseLong(s2[1]));
        

        // Determine the operator
        switch (args[1].charAt(0)) {
        case '+':
            result = r1.add(r2);
            break;
        case '-':
            result = r1.subtract(r2);
            break;
        case '.':
            result = r1.multiply(r2);
            break;
        case '/':
            result = r1.divide(r2);
        }

        // Display result
        System.out.println(args[0] + ' ' + args[1] + ' ' + args[2] + " = " + result.toString());
    }
}
