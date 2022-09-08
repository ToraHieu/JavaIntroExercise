package chapter_20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.*;

/* Contains modified methods from EvaluateExpression */
public class Ex_13 extends Application {
    private ArrayList<Image> cards = new ArrayList<>();
    private Label lbStatus = new Label();
    private Button btnShuffle = new Button("Shuffle");
    private Button btnVerify = new Button("Verify");
    private TextField tfExpression = new TextField();
    private ImageView[] imageViews = new ImageView[4];
    private Integer[] usedCards = new Integer[4]; // Store the number of the used cards

    @Override
    public void start(Stage primaryStage) {
        // Preparing
        initialize();
        shuffledCards(); // Shuffle the cards the first time

        lbStatus.setWrapText(true);
        btnShuffle.setMinWidth(80);

        HBox topBox = new HBox(5);
        topBox.getChildren().addAll(lbStatus, btnShuffle);
        topBox.setAlignment(Pos.BOTTOM_RIGHT);
        topBox.setPadding(new Insets(5));

        HBox centerBox = new HBox(10);
        centerBox.getChildren().addAll(imageViews);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(10));

        HBox bottomBox = new HBox(5);
        bottomBox.getChildren().addAll(new Label("Enter an expression: "), tfExpression, btnVerify);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(5));

        BorderPane pane = new BorderPane();
        pane.setTop(topBox);
        pane.setCenter(centerBox);
        pane.setBottom(bottomBox);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Ex_13");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnShuffle.setOnAction(e -> shuffledCards());
        btnVerify.setOnAction(e -> verifyExpression());
    }

    /*
    BEWARE!!!
    BELOW IS HELL OF A MESS!!!
    EVEN THE AUTHOUR (me) DOESN'T KNOW WHAT KIND OF SHIT IS GOING DOWN THERE ANYMORE.
     */

    private void initialize() {
        for (int i = 1; i <= 52; i++) {
            cards.add(new Image("image/card/" + i + ".png"));
        }
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView();
        }
        for (int i = 0; i < usedCards.length; i++) {
            usedCards[i] = -1; // No card allocated yet
        }
    }

    private void shuffledCards() {
        for (int i = 0; i < imageViews.length; i++) {
            int randomIndex;
            do {
                randomIndex = (int)(Math.random() * cards.size());
            }
            while (Arrays.asList(usedCards).contains(randomIndex + 1));
            // Get the image
            Image image = cards.get(randomIndex);
            imageViews[i].setImage(image);
            usedCards[i] = randomIndex + 1;
        }
        lbStatus.setText("The result is shown here");
    }

    private void verifyExpression() {
        try {
            String expression = tfExpression.getText();
            if (evaluateExpression(expression) == 24) {
                lbStatus.setText("Correct");
            } else {
                lbStatus.setText("Incorrect");
            }
        } catch (Exception e) {
            lbStatus.setText(e.getMessage());
        }
    }

    private boolean containsAllUsedCard(String[] tokens) {
        // Get the number of the cards used
        Integer[] usedNumber = new Integer[usedCards.length];
        for (int i = 0; i < usedCards.length; i++) {
            usedNumber[i] = usedCards[i] % 13 == 0 ? 13 : usedCards[i] % 13; // If the card is a King, the value is 13
        }

        // Create operandStack to store operands
        Stack<Integer> operandStack = new Stack<>();

        for (String token : tokens) {
            if (! (token.length() == 0)) // Not a Blank space
                if (Character.isDigit(token.charAt(0))) {
                    // Is a number
                    operandStack.add(Integer.parseInt(token));
                }
        }
        Collections.sort(operandStack);
        List<Integer> list = Arrays.asList(usedNumber);
        Collections.sort(list);

        return list.equals(operandStack);
    }

    public int evaluateExpression(String expression) throws Exception {
        // Create operandStack to store operands
        Stack<Integer> operandStack = new Stack<>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<>();

        // Insert blanks around (, ), +, -, /, and *
        expression = insertBlanks(expression);

        // Extract operands and operators
        String[] tokens = expression.split(" ");

        // Check if the operands match the number of the used cards
        if (!containsAllUsedCard(tokens)) {
            throw new Exception("The number in the expression don't match the number in the list");
        }

        // determine if the current can be an Operand
        boolean canBeOperand = true;

        // Phase 1: Scan tokens
        for (String token: tokens) {
            if (token.length() == 0) // Blank space
                continue; // Back to the while loop to extract the next token
            else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                // Process all +, -, *, / in the top of the operator stack
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the + or - operator into the operator stack
                operatorStack.push(token.charAt(0));
                canBeOperand = true;
            }
            else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                // Process all *, / in the top of the operator stack
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the * or / operator into the operator stack
                operatorStack.push(token.charAt(0));
                canBeOperand = true;
            }
            else if (token.trim().charAt(0) == '(') {
                operatorStack.push('(');
                canBeOperand = true;
            }
            else if (token.trim().charAt(0) == ')') {
                // Process all the operators in the stack until seeing
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.pop(); // Pop the '(' symbol from the stack
            }
            else { // An operand scanned
                // Push an operand to the stack
                if (!canBeOperand)
                    throw new Exception("Wrong expression");
                operandStack.push(new Integer(token));
                // The next token can't be an Operand
                canBeOperand = false;
            }
        }

        // Phase 2: Process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    /** Process one operator: Take an operator from operatorStack and
     * apply it on the operands in the operandStack */
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
