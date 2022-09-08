package chapter_35;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_01 extends Application {
    private final Stage connectScene = new ConnectWindow();
    private final Label lblStatus = new Label("Connect and execute batch");
    private final Button btnConnect = new Button("Connect to Database");
    private final TextArea textArea = new TextArea();
    private final Button btnBatch = new Button("Batch Update");
    private final Button btnNonBatch = new Button("Non-Batch Update");

    protected Statement statement;

    @Override
    public void start(Stage primaryStage) {
        connectScene.initModality(Modality.WINDOW_MODAL);
        connectScene.initOwner(primaryStage);

        StackPane spacer = new StackPane(lblStatus);

        HBox topPane = new HBox(spacer, btnConnect);
        topPane.setAlignment(Pos.CENTER);
        btnConnect.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomPane = new HBox(btnBatch, btnNonBatch);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topPane);
        borderPane.setCenter(textArea);
        borderPane.setBottom(bottomPane);

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnConnect.setOnAction(event -> connectScene.show());

        double[][] temps = new double[1000][3];
        for (int i = 0; i < temps.length; i++) {
            for (int j = 0; j < temps[i].length; j++) {
                temps[i][j] = Math.random();
            }
        }

        btnBatch.setOnAction(e -> {
            try {
                long start = System.nanoTime();
                for (double[] temp : temps) {
                    statement.addBatch("INSERT INTO Temp values " +
                            "(" + temp[0] + ", " + temp[1] + ", " + temp[2] + ");");
                }
                statement.executeBatch();
                long end = System.nanoTime();
                lblStatus.setText("Batch update succeed");
                textArea.appendText("Batch update completed\nThe elapsed time is " + (end - start) + " nano seconds\n\n");
            } catch (SQLException ex) {
                ex.printStackTrace();
                lblStatus.setText("Batch update failed");
            }
        });

        btnNonBatch.setOnAction(e -> {
            try {
                long start = System.nanoTime();
                for (double[] temp : temps) {
                    statement.execute("INSERT INTO Temp values " +
                            "(" + temp[0] + ", " + temp[1] + ", " + temp[2] + ");");
                }
                long end = System.nanoTime();
                textArea.appendText("Non-Batch update completed\nThe elapsed time is " + (end - start) + " nano seconds\n\n");
            } catch (SQLException ex) {
                ex.printStackTrace();
                lblStatus.setText("Non-Batch update failed");
            }
        });
    }

    private class ConnectWindow extends Stage {
        private final TextField tfURL;
        private final TextField tfUser;
        private final PasswordField tfPass;
        private final Text status;

        ConnectWindow() {
            super();
            status = new Text("Enter");

            tfURL = new TextField("jdbc:mysql://localhost/javabook");
            tfUser = new TextField("scott");
            tfPass = new PasswordField();

            Button btnConnect = new Button("Connect to DB");
            Button btnClose = new Button("Close Dialog");

            GridPane gridPane = new GridPane();
            gridPane.addRow(0, new Label("Database URL"), tfURL);
            gridPane.addRow(1, new Label("Username"), tfUser);
            gridPane.addRow(2, new Label("Password"), tfPass);

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(status);
            borderPane.setCenter(gridPane);
            borderPane.setBottom(btnConnect);
            BorderPane.setAlignment(btnConnect, Pos.CENTER_RIGHT);

            VBox vBox = new VBox(borderPane, btnClose);
            vBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vBox);
            setTitle("Connect to DB");
            setScene(scene);

            btnClose.setOnAction(e -> close());

            btnConnect.setOnAction(e -> {
                try {
                    System.out.println(tfURL.getText().trim());
                    System.out.println(tfUser.getText());
                    System.out.println(tfPass.getText());
                    Connection connection = DriverManager.getConnection(
                            tfURL.getText().trim(), tfUser.getText(), tfPass.getText());
                    statement = connection.createStatement();
                    status.setText("Database is connected");
                } catch (SQLException ex) {
                    status.setText("Error: check the fields");
                    ex.printStackTrace();
                }
            });
        }
    }
}
