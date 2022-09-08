package chapter_20;

import java.util.Stack;

public class Ex_23 {
    public static void main(String[] args) {
        // Stimulate the input
        args = new String[]{"3 + (4 + 5) * ((3) + 5) + 4 * 5"};

        // Check number of arguments passed
        if (args.length != 1) {
            System.out.println("Usage: java EvaluateExpression \"expression\"");
            System.exit(1);
        }

        try {
            System.out.println(evaluateExpression(args[0]));
        } catch (Exception ex) {
            System.out.println("Wrong expression: " + args[0]);
            ex.printStackTrace();
        }
    }

    /** Modified to make all operators (+ - * /) have equal precedence */
    public static int evaluateExpression(String expression) throws Exception {
        // Create operandStack to store operands
        Stack<Integer> operandStack = new Stack<>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<>();

        // Insert blanks around (, ), +, -, /, and *
        expression = insertBlanks(expression);

        // Extract operands and operators
        String[] tokens = expression.split(" ");

        // determine if the current can be an Operand
        boolean canBeOperand = true;

        // Phase 1: Scan tokens
        for (String token: tokens) {
            if (!(token.length() == 0)) { // Not a blank space
                // Check if this is an operator
                if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*' || token.charAt(0) == '/') {
                    // Process a +, -, *, / in the top of the operator stack
                    if (!operatorStack.isEmpty() && operatorStack.peek() != '(' && operandStack.size() >= 2) {
                        processAnOperator(operandStack, operatorStack);
                    }
                    // Push the + or - operator into the operator stack
                    operatorStack.push(token.charAt(0));
                    canBeOperand = true;
                } else if (token.trim().charAt(0) == '(') {
                    operatorStack.push('(');
                    canBeOperand = true;
                } else if (token.trim().charAt(0) == ')') {
                    // Process all the operators in the stack until seeing
                    while (operatorStack.peek() != '(') {
                        processAnOperator(operandStack, operatorStack);
                    }
                    operatorStack.pop(); // Pop the '(' symbol from the stack
                } else { // An operand scanned
                    // Push an operand to the stack
                    if (!canBeOperand)
                        throw new Exception("Wrong expression");
                    operandStack.push(new Integer(token));
                    // The next token can't be an Operand
                    canBeOperand = false;
                }
            }
        }

        // Phase 2: Process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    /** Process one operator: Take an operator from operatorStack and
     * apply it on the operands in the operandStack.
     * Need guarantee that the an operation is possible.*/
    public static void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        if (op == '+')
            operandStack.push(op2 + op1);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '*')
            operandStack.push(op2 * op1);
        else if (op == '/')
            operandStack.push(op2 / op1);
    }

    public static String insertBlanks(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '*' || s.charAt(i) == '/')
                result += " " + s.charAt(i) + " ";
            else
                result += s.charAt(i);
        }
        return result;
    }
}
