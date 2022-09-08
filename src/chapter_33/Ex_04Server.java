package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex_04Server extends Application {
    ServerSocket serverSocket;

    @Override
    public void start(Stage primaryStage) {
        // Text area for displaying contents
        TextArea ta = new TextArea();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Ex_04 Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        new Thread(() -> {
            try {
                RandomAccessFile raf = new RandomAccessFile("src/chapter_33/ClientCount.dat", "rw");
                int count = 0;
                if (raf.length() != 0) {
                    count = raf.readInt();
                }
                try {
                    serverSocket = new ServerSocket(3304);
                    Platform.runLater(() ->
                            ta.appendText("Server started at " + new Date() + '\n'));
                    while (true) {
                        try (
                                Socket socket = serverSocket.accept();
                                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream())
                        ) {
                            outputToClient.writeInt(++count);
                            final int finalCount = count;
                            Platform.runLater(() -> {
                                ta.appendText("Accept client number " + finalCount
                                        + "\nClient IP /" + socket.getInetAddress().getHostAddress() + "\n");
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Server closed
                raf.seek(0);
                raf.writeInt(count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        primaryStage.setOnCloseRequest(event -> {
          if (serverSocket != null) {
              try {
                  serverSocket.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        });
    }
}
