package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ex_06Server extends Application implements Ex_06Constants {
    private static final int MAX_CONNECTION = 2;
    private int numberOfConnections = 0;
    private final int port = 3306;
    private ServerSocket serverSocket;
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        Scene scene = new Scene(textArea, 350, 150);
        primaryStage.setTitle("Ex_06 Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                Platform.runLater(() ->
                        textArea.appendText("Server started at " + new Date() + '\n'));
                File file = new File("src/chapter_33/StudentAddress.dat");
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));

                List<StudentAddress> addressList;
                try {
                    addressList = (List<StudentAddress>) inputStream.readObject();
                } catch (ClassNotFoundException | EOFException e) {
                    e.printStackTrace();
                    System.out.println("File is empty");
                    addressList = new ArrayList<>();
                }
                inputStream.close();
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        Platform.runLater(() ->
                                textArea.appendText("Connection accepted at " + new Date() + '\n'));
                        new Thread(new HandleAClient(addressList, socket)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Server is closed, write list to file
                    System.out.println("Server closed. Writing to file.");
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
                    outputStream.writeObject(addressList);
                    outputStream.close();
                }
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

    static class HandleAClient implements Runnable {
        int index = 0;
        List<StudentAddress> list;
        Socket socket;

        public HandleAClient(List<StudentAddress> list, Socket socket) {
            this.list = list;
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Communicate with client");
            try {
                ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Sending first status to client");
                if (list.size() == 0) {
                    toClient.writeInt(LIST_EMPTY);
                } else {
                    toClient.writeInt(LIST_NOT_EMPTY);
                    toClient.writeObject(list.get(0));
                }
                toClient.flush();

                ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
                System.out.println("Proceed to maintain connection");
                while (true) {
                    System.out.println("Reading: ");
                    int status = fromClient.readInt();
                    System.out.println(status);
                    if (status == DISCONNECT) {
                        toClient.close();
                        fromClient.close();
                        break;
                    }
                    if (status == ADD) {
                        try {
                            list.add((StudentAddress) fromClient.readObject());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        switch (status) {
                            case FIRST:
                                index = 0;
                                break;
                            case NEXT:
                                index++;
                                break;
                            case PREVIOUS:
                                index--;
                                break;
                            case LAST:
                                index = list.size() - 1;
                                break;
                        }
                        if (index < list.size() && index >= 0) {
                            toClient.writeObject(list.get(index));
                            toClient.flush();
                        } else {
                            toClient.writeObject(null);
                            if (index >= list.size()) {
                                index = list.size() - 1;
                            } else if (index < 0) {
                                index = 0;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
