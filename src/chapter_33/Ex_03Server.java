package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex_03Server extends Application {
    private final int port = 3301;
    private ServerSocket serverSocket;

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        Scene scene = new Scene(textArea, 350, 150);
        primaryStage.setTitle("Ex_01 Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                Platform.runLater(() -> textArea.appendText(new Date() +
                        ": Server started on port " + port + "\n"));
                while (true) {
                    try (
                            Socket socket = serverSocket.accept();
                            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream())
                    ) {
                        Platform.runLater(() ->
                                textArea.appendText("Connection accepted at " + new Date() + '\n'));

                        // Receive radius from the client
                        double monthlyInterest, loan, rate;
                        int year;
                        try {
                            rate = inputFromClient.readDouble();
                            monthlyInterest = rate / 12 / 100;
                            year = inputFromClient.readInt();
                            loan = inputFromClient.readDouble();

                            double monthlyPayment = (loan * monthlyInterest) /
                                    (1 - (1 / Math.pow(1 + monthlyInterest, year * 12)));
                            double totalPayment = monthlyPayment * year * 12;

                            outputToClient.writeDouble(monthlyPayment);
                            outputToClient.writeDouble(totalPayment);
                            Platform.runLater(() -> textArea.appendText("Annual Interest Rate: " + rate
                                    + "\nNumber of Years: " + year
                                    + "\nLoan Amount: " + loan
                                    + "\nMonthly Payment: " + monthlyPayment
                                    + "\nTotal Payment: " + totalPayment
                                    + "\n"
                            ));
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        primaryStage.setOnCloseRequest(event -> {
            try {
                serverSocket.close();
                textArea.appendText("Server closing");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
