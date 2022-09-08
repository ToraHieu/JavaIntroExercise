package testingGround;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ObservableListIndices extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblSelected = new Label();

        ObservableList<String> items = FXCollections.observableArrayList(
                "1st", "3rd", "2nd", "4th");
        ListView<String> listView = new ListView<>();
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.getSelectionModel().getSelectedIndices()
                .addListener((ListChangeListener<? super Integer>) observable -> {

        });


        listView.getSelectionModel().selectedIndexProperty().addListener(observable -> {
            ObservableList<String> selected = listView.getSelectionModel().getSelectedItems();
            StringBuilder sb = new StringBuilder("Selected items:");
            for (String s: selected) {
                sb.append(" ").append(s);
            }
            lblSelected.setText(sb.toString());

//                ObservableList<Integer> indices = listView.getSelectionModel().getSelectedIndices();
//                if (indices.size() != 0) {
//                    StringBuilder stringBuilder = new StringBuilder("Selected items are");
//                    indices.forEach(index -> stringBuilder.append(" " + items.get(index)));
//                    lblSelected.setText(stringBuilder.toString());
//                }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblSelected);
        borderPane.setCenter(listView);

        Scene scene = new Scene(borderPane, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
