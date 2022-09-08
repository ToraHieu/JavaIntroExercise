package chapter_35;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Ex_02 extends Application {
    private Button first, next, prior, last, insert, delete, update;
    private TextField lastName, firstName, mi, street, city, state, zip, telephone, email;
    private Text status = new Text();
    private ResultSet resultSet;

    @Override
    public void start(Stage primaryStage) {
        initVar();
        initDB();

        HBox buttonBox = new HBox(2, first, next, prior, last, insert, delete, update);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setSpacing(4);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
                buttonBox,
                new HBox(2, new Label("Last Name"), lastName, new Label("First Name"), firstName, new Label("MI"), mi),
                new HBox(2, new Label("Street"), street),
                new HBox(2, new Label("City"), city, new Label("State"), state, new Label("ZIP"), zip),
                new HBox(2, new Label("Telephone"), telephone),
                new HBox(2, new Label("Email"), email),
                status
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_02");
        primaryStage.show();

        // Fill the field with first result if any
        try {
            if (resultSet.next()) {
                updateFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        first.setOnAction(e -> {
            try {
                if (resultSet.first())
                    updateFields();
                else {
                    status.setText("No result");
                    clearFields();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        next.setOnAction(e -> {
            try {
                if (resultSet.next())
                    updateFields();
                else {
                    status.setText("No result");
                    clearFields();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        prior.setOnAction(e -> {
            try {
                if (resultSet.previous())
                    updateFields();
                else {
                    status.setText("No result");
                    clearFields();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        last.setOnAction(e -> {
            try {
                if (resultSet.last())
                    updateFields();
                else {
                    status.setText("No result");
                    clearFields();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        insert.setOnAction(e -> {
            try {
                resultSet.last();
                resultSet.moveToInsertRow();
                resultSet.updateString(1, lastName.getText());
                resultSet.updateString(2, firstName.getText());
                resultSet.updateString(3, mi.getText());
                resultSet.updateString(4, street.getText());
                resultSet.updateString(5, city.getText());
                resultSet.updateString(6, state.getText());
                resultSet.updateString(7, zip.getText());
                resultSet.updateString(8, telephone.getText());
                resultSet.updateString(9, email.getText());

                resultSet.insertRow();
                status.setText("Insert completed. Current row number: " + resultSet.getRow());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        delete.setOnAction(e -> {
            try {
                resultSet.deleteRow();
                clearFields();

                status.setText("Record deleted");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        update.setOnAction(e -> {
            try {
                resultSet.updateString(1, lastName.getText());
                resultSet.updateString(2, firstName.getText());
                resultSet.updateString(3, mi.getText());
                resultSet.updateString(4, street.getText());
                resultSet.updateString(5, city.getText());
                resultSet.updateString(6, state.getText());
                resultSet.updateString(7, zip.getText());
                resultSet.updateString(8, telephone.getText());
                resultSet.updateString(9, email.getText());

                resultSet.updateRow();

                status.setText("Update completed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initVar() {
        first = new Button("First");
        next = new Button("Next");
        prior = new Button("Prior");
        last = new Button("Last");
        insert = new Button("Insert");
        delete = new Button("Delete");
        update = new Button("Update");

        lastName = new TextField();
        lastName.setPrefColumnCount(8);
        firstName = new TextField();
        firstName.setPrefColumnCount(8);
        mi = new TextField();
        mi.setPrefColumnCount(1);
        street = new TextField();
        city = new TextField();
        state = new TextField();
        state.setPrefColumnCount(2);
        zip = new TextField();
        zip.setPrefColumnCount(5);
        telephone = new TextField();
        email = new TextField();
    }

    private void initDB() {
        {
            try {
                // Establish a connection
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                System.out.println("Database connected");

                String query = "SELECT lastname, firstname, mi, street, city, state, zip, telephone, email FROM Address";
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultSet = statement.executeQuery(query);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateFields() {
        try {
            lastName.setText(resultSet.getString(1));
            firstName.setText(resultSet.getString(2));
            mi.setText(resultSet.getString(3));
            street.setText(resultSet.getString(4));
            city.setText(resultSet.getString(5));
            state.setText(resultSet.getString(6));
            zip.setText(resultSet.getString(7));
            telephone.setText(resultSet.getString(8));
            email.setText(resultSet.getString(9));

            status.setText("Current row number: " + resultSet.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void clearFields() {
        lastName.setText(null);
        firstName.setText(null);
        mi.setText(null);
        street.setText(null);
        city.setText(null);
        state.setText(null);
        zip.setText(null);
        telephone.setText(null);
        email.setText(null);
    }
}
