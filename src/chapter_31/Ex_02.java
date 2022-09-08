package chapter_31;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class Ex_02 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Random random = new Random();
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            columnConstraints.setHalignment(HPos.CENTER);
            RowConstraints rowConstraints = new RowConstraints(50);
            rowConstraints.setValignment(VPos.CENTER);
            gridPane.getColumnConstraints().add(columnConstraints);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < 9; i++) {
            int point = random.nextInt(3);
            // 0 = 0, 1 = x, 2 = none
            if (point != 2) {
                gridPane.add(new ImageView(point == 0 ? "image/o.gif" : "image/x.gif"), i / 3, i % 3);
            }
        }

        HBox pane = new HBox();
        pane.getChildren().add(gridPane);
        pane.setAlignment(Pos.CENTER);
        pane.getStyleClass().add("border");

        Scene scene = new Scene(pane, 250, 150);
        scene.getStylesheets().add("chapter_31/mystyle.css");
        primaryStage.setTitle("Ex_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
