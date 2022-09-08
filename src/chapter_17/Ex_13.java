package chapter_17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Ex_13 extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        Label lblStatus = new Label("If the base file is named temp.txt with three pieces,\n" +
                "temp.txt.1, temp.txt.2 and temp.txt.3 are combined into temp.txt");
        lblStatus.setWrapText(true);
        lblStatus.setPrefHeight(40);

        TextField tfFile = new TextField();
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
        gridPane.addRow(0, new Text("Enter a file:"), tfFile);
        gridPane.addRow(1, new Text("Specify the number of smaller files:"), tfNumberOfPieces);

        Button btnStart = new Button("Start");
        btnStart.setOnAction(event -> {
            int numberOfPieces;
            try {
                numberOfPieces = Integer.parseInt(tfNumberOfPieces.getText());
            } catch (NumberFormatException e) {
                lblStatus.setText("Please type in a valid number of smaller pieces (>=0).");
                return;
            }

            // The smaller files and target file
            String[] files = new String[numberOfPieces + 1];
            for (int i = 0; i < numberOfPieces; i++) {
                files[i] = tfFile.getText() + "." + (i + 1);
                if (!new File(files[i]).exists()) {
                    lblStatus.setText("One orr more of the files can't be found.\nPlease verify inputs and try again.");
                    return;
                }
            }

            files[numberOfPieces] = tfFile.getText();
            try {
                Ex_12.main(files);
            } catch (IOException e) {
                lblStatus.setText("File combined unsuccessfully.");
                e.printStackTrace();
                return;
            }
            lblStatus.setText("File combined successfully.");
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
