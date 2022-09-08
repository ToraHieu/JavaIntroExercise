package chapter_12;

public class Ex_01_a {
    /** Main method */
    public static void main(String[] args) {
        // Check number of strings passed
        if (args.length != 3) {
            System.out.println("Usage: java Calculator operand1 operator operand2");
            System.exit(0);
        }

        // The result of the operation
        int result = 0;
        try {
            // Determine the operator
            switch (args[1].charAt(0)) {
            case '+':
                result = Integer.parseInt(args[0]) + Integer.parseInt(args[2]);
                break;
            case '-':
                result = Integer.parseInt(args[0]) - Integer.parseInt(args[2]);
                break;
            case '.':
                result = Integer.parseInt(args[0]) * Integer.parseInt(args[2]);
                break;
            case '/':
                result = Integer.parseInt(args[0]) / Integer.parseInt(args[2]);
            }

            // Display result
            System.out.println(args[0] + ' ' + args[1] + ' ' + args[2] + " = " + result);
        } catch (NumberFormatException e) {
            String message = "Wrong Input: " + e.getMessage().substring(e.getMessage().indexOf('\"') + 1, e.getMessage().lastIndexOf('\"')); 
                    
            System.out.println(message);
        }
    }
}
