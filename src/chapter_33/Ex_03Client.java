package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ex_03Client extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField
                tfRate = new TextField(),
                tfYear = new TextField(),
                tfLoan = new TextField();
        Button button = new Button("Submit");

        GridPane gridPane = new GridPane();
        gridPane.addColumn(0, new Text("Annual Interest Rate"), new Text("Number Of Years"), new Text("Loan Amount"));
        gridPane.addColumn(1, tfRate, tfYear, tfLoan);
        gridPane.add(button, 2, 1);

        TextArea textArea = new TextArea();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(gridPane);
        borderPane.setCenter(textArea);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Ex_01 Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(event -> {
            new Thread(() -> {
                double rate, loan;
                int year;
                try {
                    rate = Double.parseDouble(tfRate.getText());
                    year = Integer.parseInt(tfYear.getText());
                    loan = Double.parseDouble(tfLoan.getText());
                    try (
                            Socket socket = new Socket("localhost",  3301);
                            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
                            DataInputStream fromServer = new DataInputStream(socket.getInputStream())
                    ) {
                        toServer.writeDouble(rate);
                        toServer.writeInt(year);
                        toServer.writeDouble(loan);
                        double monthlyPayment = fromServer.readDouble();
                        double totalPayment = fromServer.readDouble();
                        Platform.runLater(() -> textArea.appendText("Annual Interest Rate: " + rate
                                + "\nNumber of Years: " + year
                                + "\nLoan Amount: " + loan
                                + "\nMonthly Payment: " + monthlyPayment
                                + "\nTotal Payment: " + totalPayment
                                + "\n"
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    Platform.runLater(() -> textArea.appendText("Invalid input"));
                }
            }).start();
        });
    }
}
