package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Ex_04Client extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text("Connecting to Server");
        StackPane stackPane = new StackPane(text);
        Scene scene = new Scene(stackPane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EX_04 Client");
        primaryStage.show();

        Thread thread = new Thread(() -> {
            while (true) {
                try (
                        Socket socket = new Socket("localhost",  3304);
                        DataInputStream fromServer = new DataInputStream(socket.getInputStream())
                ) {
                    int number = fromServer.readInt();
                    Platform.runLater(() -> text.setText("You are visitor " + number));
                    break;
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        thread.start();
        primaryStage.setOnCloseRequest(e -> thread.interrupt());
    }
}
