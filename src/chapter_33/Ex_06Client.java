package chapter_33;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Ex_06Client extends Application implements Ex_06Constants {
    private TextField tfName = new TextField();
    private TextField tfStreet = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfZip = new TextField();

    private Button btAdd = new Button("Add");
    private Button btFirst = new Button("First");
    private Button btNext = new Button("Next");
    private Button btPrevious = new Button("Previous");
    private Button btLast = new Button("Last");

    // Input and output streams from/to server
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;

    // Session is still connected
    private boolean isConnecting = false;

    // Waiting for user to send request to server
    private boolean isWaiting = false;

    private int request;

    @Override
    public void start(Stage primaryStage) {
        Insets insetsPane = new Insets(5);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(insetsPane);
        gridPane.setHgap(5);
        gridPane.setVgap(2);
        gridPane.addColumn(0, new Label("Name"), new Label("Street"), new Label("City"));
        gridPane.add(tfName, 1, 0, 5, 1);
        gridPane.add(tfStreet, 1, 1, 5, 1);
        gridPane.addRow(2, tfCity, new Label("State"), tfState, new Label("Zip"), tfZip);

        tfState.setPrefColumnCount(4);
        tfZip.setPrefColumnCount(5);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btAdd, btFirst, btNext, btPrevious, btLast);
        hBox.setPadding(insetsPane);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        Insets insetsButton = new Insets(5, 20, 5, 20);
        btAdd.setPadding(insetsButton);
        btFirst.setPadding(insetsButton);
        btNext.setPadding(insetsButton);
        btPrevious.setPadding(insetsButton);
        btLast.setPadding(insetsButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        btAdd.setOnAction(event -> handleRequest(ADD));
        btFirst.setOnAction(event -> handleRequest(FIRST));
        btNext.setOnAction(event -> handleRequest(NEXT));
        btPrevious.setOnAction(event -> handleRequest(PREVIOUS));
        btLast.setOnAction(event -> handleRequest(LAST));

        primaryStage.setOnCloseRequest(event -> {
            isConnecting = false;
            handleRequest(DISCONNECT);
        });

        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 3306);
                System.out.println("Server connected");
                toServer = new ObjectOutputStream(socket.getOutputStream());
                fromServer = new ObjectInputStream(socket.getInputStream());
                isConnecting = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("Reading first status from server");
                int firstObjectStatus = fromServer.readInt();
                System.out.println(firstObjectStatus);
                if (firstObjectStatus == LIST_EMPTY) {
                    System.out.println("Nothing to initialize");
                }
                if (firstObjectStatus == LIST_NOT_EMPTY) {
                    setAddress((StudentAddress) fromServer.readObject());
                } else if (firstObjectStatus != LIST_EMPTY) {
                    System.out.println("Incorrect contract: " + firstObjectStatus);
                }

                System.out.println("Proceed to maintain connection");
                while (isConnecting) {
                    try {
                        waitForUserRequest();
                        toServer.writeInt(request);
                        toServer.flush();
                        System.out.println("Writing " + request);
                        if (request == DISCONNECT) {
                            break;
                        } else if (request == ADD) {
                            toServer.writeObject(new StudentAddress(
                                    tfName.getText(), tfStreet.getText(),
                                    tfCity.getText(), tfState.getText(), tfZip.getText()));
                            toServer.flush();
                        } else {
                            StudentAddress address = (StudentAddress) fromServer.readObject();
                            if (address != null) setAddress(address);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                toServer.close();
                fromServer.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /** Wait for the user to make a request */
    private void waitForUserRequest() throws InterruptedException {
        while (isConnecting && isWaiting) {
            Thread.sleep(500);
            System.out.println("Waiting");
        }

        isWaiting = true;
    }

    public void setAddress(StudentAddress address) {
        Platform.runLater(() -> {
            tfName.setText(address.getName());
            tfStreet.setText(address.getStreet());
            tfCity.setText(address.getCity());
            tfState.setText(address.getState());
            tfZip.setText(address.getZip());
        });
    }

    private void handleRequest(int request) {
        this.request = request;
        isWaiting = false;
    }
}
