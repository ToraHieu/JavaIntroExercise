package chapter_35;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;

public class Ex_05 extends Application {
    // Connection to the database
    private Connection connection;

    // Statement to execute SQL commands
    private Statement statement;

    // Text area to enter SQL commands
    private TextArea taSQLCommand = new TextArea();

    // Text area to display results from SQL commands
    private TableView tvSQLResult = new TableView();
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();

    // DBC info for a database connection
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();
    private ComboBox<String> cboURL = new ComboBox<>();

    private Button btExecuteSQL = new Button("Execute SQL Command");
    private Button btClearSQLCommand = new Button("Clear");
    private Button btConnectDB = new Button("Connect to Database");
    private Button btClearSQLResult = new Button("Clear Result");
    private Label lblConnectionStatus
            = new Label("No connection now");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        cboURL.getItems().addAll(FXCollections.observableArrayList(
                "jdbc:mysql://localhost/javabook",
                "jdbc:mysql://liang.armstrong.edu/javabook",
                "jdbc:odbc:exampleMDBDataSource",
                "jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"));
        cboURL.getSelectionModel().selectFirst();

        // Create UI for connecting to the database
        GridPane gridPane = new GridPane();
        gridPane.add(cboURL, 1, 0);
        gridPane.add(tfUsername, 1, 2);
        gridPane.add(pfPassword, 1, 3);
        gridPane.add(new Label("Database URL"), 0, 0);
        gridPane.add(new Label("Username"), 0, 2);
        gridPane.add(new Label("Password"), 0, 3);

        HBox hBoxConnection = new HBox();
        hBoxConnection.getChildren().addAll(
                lblConnectionStatus, btConnectDB);
        hBoxConnection.setAlignment(Pos.CENTER_RIGHT);

        VBox vBoxConnection = new VBox(5);
        vBoxConnection.getChildren().addAll(
                new Label("Enter Database Information"),
                gridPane, hBoxConnection);

        gridPane.setStyle("-fx-border-color: black;");

        HBox hBoxSQLCommand = new HBox(5);
        hBoxSQLCommand.getChildren().addAll(
                btClearSQLCommand, btExecuteSQL);
        hBoxSQLCommand.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPaneSqlCommand = new BorderPane();
        borderPaneSqlCommand.setTop(
                new Label("Enter an SQL Command"));
        borderPaneSqlCommand.setCenter(
                new ScrollPane(taSQLCommand));
        borderPaneSqlCommand.setBottom(
                hBoxSQLCommand);

        HBox hBoxConnectionCommand = new HBox(10);
        hBoxConnectionCommand.getChildren().addAll(
                vBoxConnection, borderPaneSqlCommand);

        BorderPane borderPaneExecutionResult = new BorderPane();
        borderPaneExecutionResult.setTop(
                new Label("SQL Execution Result"));
        borderPaneExecutionResult.setCenter(tvSQLResult);
        borderPaneExecutionResult.setBottom(btClearSQLResult);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxConnectionCommand);
        borderPane.setCenter(borderPaneExecutionResult);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 670, 400);
        primaryStage.setTitle("SQLClient"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btConnectDB.setOnAction(e -> connectToDB());
        btExecuteSQL.setOnAction(e -> executeSQL());
        btClearSQLCommand.setOnAction(e -> taSQLCommand.setText(null));
        btClearSQLResult.setOnAction(e -> clearTable());
    }

    /** Connect to DB */
    private void connectToDB() {
        connection = null;

        // Get database information from the user input
        String url = cboURL.getSelectionModel().getSelectedItem();
        String username = tfUsername.getText().trim();
        String password = pfPassword.getText().trim();

        // Connection to the database
        try {
            connection = DriverManager.getConnection(
                    url, username, password);
            lblConnectionStatus.setText("Connected to " + url);
        }
        catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
    }

    /** Execute SQL commands */
    private void executeSQL() {
        if (connection != null) {
            String sqlCommands = taSQLCommand.getText().trim();
            String[] commands = sqlCommands.replace('\n', ' ').split(";");

            for (String aCommand: commands) {
                if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
                    processSQLSelect(aCommand);
                }
                else {
                    processSQLNonSelect(aCommand);
                }
            }
        }
    }

    /** Execute SQL SELECT commands */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void processSQLSelect(String sqlCommand) {
        try {
            // Get a new statement for the current connection
            statement = connection.createStatement();

            // Execute a SELECT SQL command
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            clearTable();

            // Add column names
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tvSQLResult.getColumns().add(col);
            }

            // Add data
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            tvSQLResult.setItems(data);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** Execute SQL DDL, and modification commands */
    private void processSQLNonSelect(String sqlCommand) {
        try {
            // Get a new statement for the current connection
            statement = connection.createStatement();

            // Execute a non-SELECT SQL command
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearTable() {
        data.clear();
        tvSQLResult.getColumns().clear();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
