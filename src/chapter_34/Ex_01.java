package chapter_34;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class Ex_01 extends Application {
    private static final int SPACING = 2;
    private Text textStatus;
    private TextField tfID, tfLastName, tfFirstName, tfMI, tfAddress, tfCity, tfState, tfTelephone;
    private Button btnView, btnInsert, btnUpdate, btnClear;
    private PreparedStatement viewStatement, insertStatement, updateStatement;

    @Override
    public void start(Stage primaryStage) {
        initVar();
        initDB();

        VBox centerBox = new VBox(SPACING);
        centerBox.getChildren().add(new HBox(SPACING, new Label("ID"), tfID));
        centerBox.getChildren().add(new HBox(SPACING, 
                new Label("Last Name"), tfLastName, new Label("First Name"), tfFirstName, new Label("MI"), tfMI));
        centerBox.getChildren().add(new HBox(SPACING, new Label("Address"), tfAddress));
        centerBox.getChildren().add(new HBox(SPACING, new Label("City"), tfCity, new Label("State"), tfState));
        centerBox.getChildren().add(new HBox(SPACING, new Label("Telephone"), tfTelephone));

        HBox bottomBox = new HBox(SPACING, btnView, btnInsert, btnUpdate, btnClear);
        bottomBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(SPACING, textStatus, centerBox, bottomBox);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Ex_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnView.setOnAction(e -> viewEventHandler());
        btnInsert.setOnAction(e -> insertEventHandler());
        btnUpdate.setOnAction(e -> updateEventHandler());
        btnClear.setOnAction(e -> clearEventHandler());
    }

    private void initVar() {
        textStatus = new Text("Status");

        tfID = new TextField();
        tfLastName = new TextField();
        tfLastName.setPrefColumnCount(8);
        tfFirstName = new TextField();
        tfFirstName.setPrefColumnCount(8);
        tfMI = new TextField();
        tfMI.setPrefColumnCount(1);
        tfAddress = new TextField();
        tfCity = new TextField();
        tfState = new TextField();
        tfTelephone = new TextField();

        btnView = new Button("View");
        btnInsert = new Button("Insert");
        btnUpdate = new Button("Update");
        btnClear = new Button("Clear");
    }

    private void initDB() {
            try {
                // Establish a connection
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                System.out.println("Database connected");

                String viewQuery = "SELECT lastName, firstName, mi, address, city, state, telephone from Staff " +
                        "WHERE id = ?";
                viewStatement = connection.prepareStatement(viewQuery);

                String insertQuery = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                insertStatement = connection.prepareStatement(insertQuery);

                String updateQuery = "UPDATE Staff SET " +
                        "lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ? " +
                        "WHERE id = ?";
                updateStatement = connection.prepareStatement(updateQuery);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    private void viewEventHandler() {
        try {
            viewStatement.setString(1, tfID.getText());
            ResultSet resultSet = viewStatement.executeQuery();

            if (resultSet.next()) {
                tfLastName.setText(Objects.toString(resultSet.getString(1), ""));
                tfFirstName.setText(Objects.toString(resultSet.getString(2), ""));
                tfMI.setText(Objects.toString(resultSet.getString(3), ""));
                tfAddress.setText(Objects.toString(resultSet.getString(4), ""));
                tfCity.setText(Objects.toString(resultSet.getString(5), ""));
                tfState.setText(Objects.toString(resultSet.getString(6), ""));
                tfTelephone.setText(Objects.toString(resultSet.getString(7), ""));
                
                textStatus.setText("Record found.");
            } else {
                textStatus.setText("Not found with ID : " + tfID.getText());
                clearEventHandler();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            textStatus.setText("Error " + e.getErrorCode() + ": " + e.getMessage());
        }
    }

    private void insertEventHandler() {
        try {
            insertStatement.setString(1, tfID.getText());
            insertStatement.setString(2, tfLastName.getText());
            insertStatement.setString(3, tfFirstName.getText());
            insertStatement.setString(4, tfMI.getText());
            insertStatement.setString(5, tfAddress.getText());
            insertStatement.setString(6, tfCity.getText());
            insertStatement.setString(7, tfState.getText());
            insertStatement.setString(8, tfTelephone.getText());

            int result = insertStatement.executeUpdate();
            if (result > 0) {
                textStatus.setText("Insert successful");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062) 
                textStatus.setText("Record with this ID already exists");
            else
                textStatus.setText("Error " + e.getErrorCode() + ": " + e.getMessage());
        }
    }

    private void updateEventHandler() {
        try {
            updateStatement.setString(8, tfID.getText());
            updateStatement.setString(1, tfLastName.getText());
            updateStatement.setString(2, tfFirstName.getText());
            updateStatement.setString(3, tfMI.getText());
            updateStatement.setString(4, tfAddress.getText());
            updateStatement.setString(5, tfCity.getText());
            updateStatement.setString(6, tfState.getText());
            updateStatement.setString(7, tfTelephone.getText());

            int result = updateStatement.executeUpdate();
            if (result > 0) {
                textStatus.setText("Update successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062)
                textStatus.setText("Record with this ID already exists");
            else
                textStatus.setText("Error " + e.getErrorCode() + ": " + e.getMessage());
        }
    }

    private void clearEventHandler() {
        tfID.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
    }
}
