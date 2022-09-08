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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddressBook extends Application {
    // Specify the size of five string fields in the record
    final static int NAME_SIZE = 32;
    final static int STREET_SIZE = 32;
    final static int CITY_SIZE = 20;
    final static int STATE_SIZE = 2;
    final static int ZIP_SIZE = 5;
    final static int RECORD_SIZE = NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE;

    // Access address.dat using RandomAccessFile
    private RandomAccessFile raf;

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

    @Override
    public void start(Stage primaryStage) {
        // Open or create random access file
        try {
            raf = new RandomAccessFile("address.dat", "rw");
        }
        catch(IOException ex) {
            System.out.print("Error: " + ex);
            System.exit(0);
        }

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

        btAdd.setOnAction(e -> writeAddress());
        btFirst.setOnAction(e -> {
            try {
                if (raf.length() > 0) readAddress(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btNext.setOnAction(e -> {
            try {
                long currentPosition = raf.getFilePointer();
                if (currentPosition < raf.length())
                    readAddress(currentPosition);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btPrevious.setOnAction(e -> {
            try {
                long currentPosition = raf.getFilePointer();
                if (currentPosition - 2 * RECORD_SIZE > 0)
                    readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
                else
                    readAddress(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btLast.setOnAction(e -> {
            try {
                long lastPosition = raf.length();
                if (lastPosition > 0)
                    readAddress(lastPosition - 2 * RECORD_SIZE);
                else
                    readAddress(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        try {
            if (raf.length() > 0) readAddress(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Write a record at the end of the file */
    private void writeAddress() {
        try {
            raf.seek(raf.length());
            FixedLengthStringIO.writeFixedLengthString(tfName.getText(), NAME_SIZE, raf);
            FixedLengthStringIO.writeFixedLengthString(tfStreet.getText(), STREET_SIZE , raf);
            FixedLengthStringIO.writeFixedLengthString(tfCity.getText(), CITY_SIZE, raf);
            FixedLengthStringIO.writeFixedLengthString(tfState.getText(), STATE_SIZE , raf);
            FixedLengthStringIO.writeFixedLengthString(tfZip.getText(), ZIP_SIZE , raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Read a record at the specified position */
    private void readAddress(long position) throws IOException {
        raf.seek(position);
        String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
        String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
        String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
        String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
        String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);

        tfName.setText(name);
        tfStreet.setText(street);
        tfCity.setText(city);
        tfState.setText(state);
        tfZip.setText(zip);
    }
}

class FixedLengthStringIO {
    /** Read fixed number of characters from a DataInput stream */
    public static String readFixedLengthString(int size, DataInput in) throws IOException {
        char[] chars = new char[size];

        for (int i = 0; i < size; i++) {
            chars[i] = in.readChar();
        }
        return new String(chars);
    }

    /** Write fixed number of characters to a DataOutput stream */
    public static void writeFixedLengthString(String s, int size, DataOutput out) throws IOException {
        char[] chars = new char[size];

        // Fill the array with characters from the string
        s.getChars(0, Math.min(s.length(), size), chars, 0);
        for (int i = Math.min(s.length(), size); i < chars.length; i++)
            chars[i] = ' ';

        out.writeChars(new String(chars));
    }
}