package chapter_16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ex_16 extends Application {
    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> cboMode = new ComboBox<>();
        cboMode.getItems().addAll("SINGLE", "MULTIPLE");
        cboMode.setValue("SINGLE");
        Label lblMode = new Label("Choose Selection Mode:");
        lblMode.setGraphic(cboMode);
        lblMode.setContentDisplay(ContentDisplay.RIGHT);
        lblMode.setGraphicTextGap(5);
        lblMode.setPadding(new Insets(5));

        ObservableList<String> items = FXCollections.observableArrayList(
                "China", "Japan", "Korea", "India", "Malaysia", "Vietnam", "Thailand", "Laos");
        ListView<String> lvCountries = new ListView<>();
        lvCountries.setItems(items);
        lvCountries.setPrefHeight(100);

        Label lblSelected = new Label("No items selected");
        lblSelected.setPadding(new Insets(5));

        BorderPane pane = new BorderPane();
        pane.setTop(lblMode);
        pane.setCenter(lvCountries);
        pane.setBottom(lblSelected);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Ex_16");
        primaryStage.setScene(scene);
        primaryStage.show();

        cboMode.setOnAction(e -> lvCountries.getSelectionModel().setSelectionMode(SelectionMode.valueOf(cboMode.getValue())));
        lvCountries.getSelectionModel().selectedIndexProperty().addListener(observable -> {
            ObservableList<Integer> indices = lvCountries.getSelectionModel().getSelectedIndices();
            if (indices.size() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                if (indices.size() != 1) {
                    stringBuilder.append("Selected items are");
                } else {
                    stringBuilder.append("Selected item is");
                }
                indices.forEach(i -> stringBuilder.append(" " + i));
                lblSelected.setText(stringBuilder.toString());
            } else {
                lblSelected.setText("No items selected");
            }
        });
    }
}
