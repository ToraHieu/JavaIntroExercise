package chapter_31;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex_21 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Tab iTab = new Tab("Integer Operations");
        iTab.setContent(getCalculatorPane(true));
        Tab rTab = new Tab("Rational Operations");
        rTab.setContent(getCalculatorPane(false));
        TabPane tabPane = new TabPane(iTab, rTab);

        Scene scene = new Scene(tabPane, 350, 150);
        primaryStage.setTitle("Ex_21"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    private void perform(boolean isInteger, char operator, TextField tfNumber1, TextField tfNumber2, TextField tfResult) {
        double number1 = Double.parseDouble(tfNumber1.getText());
        double number2 = Double.parseDouble(tfNumber2.getText());

        double result = 0;
        switch (operator) {
            case '+': result = number1 + number2; break;
            case '-': result = number1 - number2; break;
            case '*': result = number1 * number2; break;
            case '/': result = number1 / number2; break;
        }

        tfResult.setText(String.valueOf(isInteger ? (int) (result) : result));
    }

    private Pane getCalculatorPane(boolean isInteger) {
        TextField tfNumber1 = new TextField();
        TextField tfNumber2 = new TextField();
        TextField tfResult = new TextField();

        HBox hBox1 = new HBox(5);
        tfNumber1.setPrefColumnCount(2);
        tfNumber2.setPrefColumnCount(2);
        tfResult.setPrefColumnCount(2);
        hBox1.getChildren().addAll(new Label("Number 1:"), tfNumber1,
                new Label("Number 2:"), tfNumber2, new Label("Result:"),
                tfResult);
        hBox1.setAlignment(Pos.CENTER);

        HBox hBox2 = new HBox(5);
        Button btAdd = new Button("Add");
        Button btSubtract = new Button("Subtract");
        Button btMultiply = new Button("Multiply");
        Button btDivide = new Button("Divide");
        hBox2.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
        hBox2.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(new Text(isInteger ? "Integer Calculation" : "Rational Calculation"),
                hBox1, hBox2);

        // Handle button actions
        btAdd.setOnAction(e -> perform(isInteger, '+', tfNumber1, tfNumber2, tfResult));
        btSubtract.setOnAction(e -> perform(isInteger, '-', tfNumber1, tfNumber2, tfResult));
        btMultiply.setOnAction(e -> perform(isInteger, '*', tfNumber1, tfNumber2, tfResult));
        btDivide.setOnAction(e -> perform(isInteger, '/', tfNumber1, tfNumber2, tfResult));

        return vBox;
    }
}
