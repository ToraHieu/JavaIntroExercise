package chapter_20;

import java.util.Stack;

public class Ex_14 {
    public static void main(String[] args) {
        // Stimulate input
        args = new String[]{"1 2 + 3 * 4 -"};

        Stack<Integer> operandStack = new Stack<>();
        String[] tokens = args[0].split(" ");

        // Evaluate the expression
        for (String s : tokens) {
            // Encounter operator
            if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/') {
                processAnOperator(operandStack, s.charAt(0));
            } else { // Encounter operand
                operandStack.push(Integer.parseInt(s));
            }
        }
        System.out.print("The value of the expression " + args[0] + " is " + operandStack.pop());
    }

    /** Process one operator: Take an operator from operatorStack and
     * apply it on the operands in the operandStack */
    // Copied from EvaluateExpression class
    public static void processAnOperator(Stack<Integer> operandStack, char operator) {
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        if (operator == '+')
            operandStack.push(op2 + op1);
        else if (operator == '-')
            operandStack.push(op2 - op1);
        else if (operator == '*')
            operandStack.push(op2 * op1);
        else if (operator == '/')
            operandStack.push(op2 / op1);
    }
}
