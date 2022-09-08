package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex_10Server extends Application {
    private final int port = 3310;
    private ServerSocket serverSocket;
    private TextArea textArea;

    private final Lock lock = new ReentrantLock();
    private final Condition newMessage = lock.newCondition();
    private final StringBuffer chatLog = new StringBuffer();
    private final StringBuffer newChat = new StringBuffer();

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        Scene scene = new Scene(textArea, 350, 150);
        primaryStage.setTitle("Ex_10 Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                Platform.runLater(() ->
                        textArea.appendText("Server started at " + new Date() + '\n'));
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        Platform.runLater(() ->
                                textArea.appendText("Connection accepted at " + new Date() + '\n'));
                        ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());

                        Thread outputThread = new Thread(new HandleAClientOutput(toClient));
                        new Thread(new HandleAClientInput(fromClient, toClient, outputThread)).start();
                        outputThread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Main server thread closed");
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

    class HandleAClientInput implements Runnable {
        ObjectInputStream fromClient;
        ObjectOutputStream toClient;
        Thread outputThread;

        public HandleAClientInput(ObjectInputStream fromClient, ObjectOutputStream toClient, Thread outputThread) {
            this.fromClient = fromClient;
            this.toClient = toClient;
            this.outputThread = outputThread;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int requestValue = fromClient.readInt();
                    System.out.println("Read: " + requestValue);
                    Ex_10RequestCode request = Ex_10RequestCode.get(requestValue);
                    if (request == Ex_10RequestCode.DISCONNECT) {
                        break;
                    }
                    if (request == Ex_10RequestCode.REQUEST_ALL_MESSAGE) {
                        toClient.writeObject(chatLog.toString());
                        toClient.flush();
                    } else if (request == Ex_10RequestCode.SEND_MESSAGE) {
                        String message = (String) fromClient.readObject();
                        newChat.delete(0, newChat.length());
                        newChat.append(message);
                        chatLog.append(message).append('\n');
                        lock.lock();
                        newMessage.signalAll();
                        lock.unlock();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("HandleAClientInput closed");
            outputThread.interrupt();
        }
    }

    class HandleAClientOutput implements Runnable {
        ObjectOutputStream toClient;

        public HandleAClientOutput(ObjectOutputStream toClient) {
            this.toClient = toClient;
        }

        @Override
        public void run() {
                while (true) {
                    lock.lock();
                    try {
                        newMessage.await();
                        toClient.writeObject(newChat.toString());
                        toClient.flush();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
                System.out.println("HandleAClientOutput closed");
            }
        }
    }

