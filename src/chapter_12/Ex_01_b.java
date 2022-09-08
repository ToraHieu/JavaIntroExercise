package chapter_12;

public class Ex_01_b {
    /** Main method */
    public static void main(String[] args) {
      // Check number of strings passed
      if (args.length != 3) {
        System.out.println(
          "Usage: java Calculator operand1 operator operand2");
        System.exit(0);
      }
      if (!isNumeric(args[0])) {
          System.out.println("Wrong input: " + args[0]);
          System.exit(1);
      }
      if (!isNumeric(args[2])) {
          System.out.println("Wrong input: " + args[2]);
          System.exit(2);
      }
      
      // The result of the operation
      int result = 0;

      // Determine the operator
      switch (args[1].charAt(0)) { 
        case '+': result = Integer.parseInt(args[0]) + 
                           Integer.parseInt(args[2]);
                  break;
        case '-': result = Integer.parseInt(args[0]) -
                           Integer.parseInt(args[2]);
                  break;
        case '.': result = Integer.parseInt(args[0]) *
                           Integer.parseInt(args[2]);
                  break;
        case '/': result = Integer.parseInt(args[0]) /
                           Integer.parseInt(args[2]);
      }

      // Display result
      System.out.println(args[0] + ' ' + args[1] + ' ' + args[2]
        + " = " + result);
    }
    
    public static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') 
                return false;
        }
        return true;
    }
}
