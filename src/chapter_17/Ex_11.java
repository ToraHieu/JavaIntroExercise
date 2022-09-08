package chapter_17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class Ex_11 extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        Label lblStatus = new Label("If you split a file named temp.txt into 3 smaller files,\n" +
                "the three smaller files are temp.txt.1, temp.txt.2, temp.txt.3.");
        lblStatus.setWrapText(true);
        lblStatus.setPrefHeight(40);

        TextField tfSourceFile = new TextField();
        TextField tfNumberOfPieces = new TextField();
        /*
        // Using TextFormatter to enforce decimal input for the tfNumberOfPieces, copied from StackOverflow: a/31043122/7514121
        tfNumberOfPieces.setTextFormatter(new TextFormatter<>(c -> {
            DecimalFormat format = new DecimalFormat( "#" );

            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));*/

        GridPane gridPane = new GridPane();
        gridPane.addRow(0, new Text("Enter a file:"), tfSourceFile);
        gridPane.addRow(1, new Text("Specify the number of smaller files:"), tfNumberOfPieces);

        Button btnStart = new Button("Start");
        btnStart.setOnAction(event -> {
            File file = new File(tfSourceFile.getText());
            if (!file.exists()) {
                lblStatus.setText("File does not exist, please try again.");
                return;
            }

            try {
                Integer.parseInt(tfNumberOfPieces.getText());
            } catch (NumberFormatException e) {
                lblStatus.setText("Please type in a valid number of smaller pieces (>=0).");
                return;
            }

            try {
                Ex_10.main(new String[]{file.getName(), tfNumberOfPieces.getText()});
            } catch (IOException e) {
                lblStatus.setText("Split file unsuccessfully.");
                e.printStackTrace();
                return;
            }
            lblStatus.setText("Split file successfully.");
        });

        pane.setTop(lblStatus);
        pane.setCenter(gridPane);
        pane.setBottom(btnStart);
        pane.setPadding(new Insets(5));
        BorderPane.setAlignment(btnStart, Pos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
