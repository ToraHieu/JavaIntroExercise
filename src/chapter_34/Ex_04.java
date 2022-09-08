package chapter_34;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Ex_04 extends Application {
    private final TextField ssnTf = new TextField();
    private final Button showBtn = new Button("Show Grade");
    private final Text statusTxt = new Text();
    private final TextArea textArea = new TextArea();
    private PreparedStatement statement;

    @Override
    public void start(Stage primaryStage) {
        initDB();
        HBox topBox = new HBox();
        topBox.getChildren().addAll(new Label("SSN"), ssnTf, showBtn);
        topBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(topBox);
        pane.setCenter(textArea);
        pane.setBottom(statusTxt);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_04");
        primaryStage.show();

        showBtn.setOnAction(e -> showHandler());
    }

    private void initDB() {
        {
            try {
                // Establish a connection
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                System.out.println("Database connected");

                String viewQuery = "SELECT firstName, mi, lastName, title, grade \n" +
                        "FROM Enrollment e \n" +
                        "INNER JOIN Student s \n" +
                        "\tON s.ssn = e.ssn \n" +
                        "INNER JOIN Course c \n" +
                        "\tON c.courseId = e.courseId \n" +
                        "WHERE e.ssn = ?";
                statement = connection.prepareStatement(viewQuery);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void showHandler() {
        try {
            statement.setString(1, ssnTf.getText().trim());
            ResultSet resultSet = statement.executeQuery();
            int rowCount = 0;
            while (resultSet.next()) {
                textArea.appendText(String.format("%s %s %s's grade on course %s is %s\n",
                        resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)));
                rowCount++;
            }

            statusTxt.setText(rowCount + (rowCount == 1 ? " course" : " courses") + " found");
        } catch (SQLException e) {
            e.printStackTrace();
            statusTxt.setText("Error " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
