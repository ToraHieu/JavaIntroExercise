package chapter_33;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex_10Client extends Application {
    private final TextField tfName = new TextField();
    private final TextField tfMessage = new TextField();
    private final TextArea textArea = new TextArea();

    private final Lock lock = new ReentrantLock();
    private final Condition userInput = lock.newCondition();

    // Input and output streams from/to server
    private Socket socket;
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;

    private Ex_10RequestCode requestCode;

    @Override
    public void start(Stage primaryStage) {
        textArea.setWrapText(true);
        textArea.setEditable(false);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.addRow(0, new Label("Name"), tfName);
        gridPane.addRow(1, new Label("Enter text"), tfMessage);

        VBox vBox = new VBox(5, gridPane, textArea);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_10 Client");
        primaryStage.show();

        tfMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                requestCode = Ex_10RequestCode.SEND_MESSAGE;
                lock.lock();
                userInput.signal();
                lock.unlock();
            }
        });

        primaryStage.setOnCloseRequest(event -> {
            requestCode = Ex_10RequestCode.DISCONNECT;
            lock.lock();
            userInput.signal();
            lock.unlock();
        });

        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3310);
                toServer = new ObjectOutputStream(socket.getOutputStream());
                fromServer = new ObjectInputStream(socket.getInputStream());

                requestCode = Ex_10RequestCode.REQUEST_ALL_MESSAGE;
                toServer.writeInt(requestCode.getValue());
                toServer.flush();

                String previousMessages = (String) fromServer.readObject();
                updateNewMessage(previousMessages);
                new Thread(new HandleUserInput()).start();
                new Thread(new HandleNewMessage()).start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateNewMessage(String string) {
        textArea.appendText(string);
    }

    class HandleUserInput implements Runnable {
        @Override
        public void run() {
            try {
                while (requestCode != Ex_10RequestCode.DISCONNECT) {
                    lock.lock();
                    System.out.println("About to wait");
                    userInput.await();
                    System.out.println("Finish waiting");
                    toServer.writeInt(requestCode.getValue());
                    String message = tfName.getText() + ": " + tfMessage.getText();
                    toServer.writeObject(message);
                    toServer.flush();
                    lock.unlock();
                }
                // Disconnecting
                toServer.writeInt(requestCode.getValue());
                toServer.flush();
                toServer.close();
                fromServer.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class HandleNewMessage implements Runnable {
        @Override
        public void run() {
            while (requestCode != Ex_10RequestCode.DISCONNECT) {
                try {
                    updateNewMessage(fromServer.readObject() + "\n");

                } catch (EOFException eofException) {
                    // EOF means server end is somehow dead and need to disconnect
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
