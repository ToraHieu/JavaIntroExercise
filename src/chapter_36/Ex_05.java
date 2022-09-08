package chapter_36;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class Ex_05 extends Application {
    @Override
    public void start(Stage stage) {
        Button button = new Button("Display Loan Schedule");
        HBox hBox = new HBox(5, new Label("Enter Loan Amount, Number of Years, and Annual Interest Rate"), button);

        TextField tfLoan = new TextField(), tfYears = new TextField(), tfRate = new TextField();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.addRow(0, new Label("Loan Amount"), tfLoan);
        gridPane.addRow(1, new Label("Number of Years"), tfYears);
        gridPane.addRow(2, new Label("Annual Interest Rate"), tfRate);

        TextArea textArea = new TextArea();

        VBox root = new VBox(5, hBox, gridPane, textArea);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ex_05");
        stage.show();

        button.setOnAction(e -> {
            double loan = Double.parseDouble(tfLoan.getText());
            double interestRate = Double.parseDouble(tfRate.getText()) / 1200;
            double numberOfYears = Double.parseDouble(tfYears.getText());

            double monthlyPayment = loan * interestRate/
                    (1 - (Math.pow(1 / (1 + interestRate), numberOfYears * 12)));
            double totalPayment = monthlyPayment * numberOfYears * 12;

            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

            textArea.appendText("Monthly Payment: " + format.format(monthlyPayment));
            textArea.appendText("\nTotal Payment: " + format.format(totalPayment));

            textArea.appendText(String.format("\n\n%-8s%12s%12s%12s", "Payment#", "Interest", "Principal", "Balance"));
            double interest, principal, balance = loan;

            for (int i = 1; i < numberOfYears * 12; i++) {
                interest = interestRate * balance;
                principal = monthlyPayment - interest;
                balance = balance - principal;
                textArea.appendText(String.format("\n%-8d%-16s%-16s%-12s", i, format.format(interest), format.format(principal), format.format(balance)));
            }
            interest = interestRate * balance;
            principal = monthlyPayment - interest;
            balance = balance - principal;
            textArea.appendText(String.format("\n%-8d%-16s%-12s%-12s", (int) (numberOfYears * 12), format.format(interest), format.format(principal), format.format(balance)));
        });
    }
}
