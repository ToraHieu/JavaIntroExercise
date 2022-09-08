package testingGround;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HBoxGrowAndShrink extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        int[] points = {1, 2, 3, 4, 5};

        // All view use the same data for simplicity
        CustomPane view1 = new CustomPane(points);
        CustomPane view2 = new CustomPane(points);
        CustomPane view3 = new CustomPane(points);
        CustomPane view4 = new CustomPane(points);

        HBox hBox = new HBox(view1, view2, view3, view4);
        hBox.setSpacing(10);

        HBox.setHgrow(view1, Priority.ALWAYS);
        HBox.setHgrow(view2, Priority.ALWAYS);
        HBox.setHgrow(view3, Priority.ALWAYS);
        HBox.setHgrow(view4, Priority.ALWAYS);

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class CustomPane extends Pane {
        private int[] points; // Data
        private Rectangle[] columns;

        public CustomPane(int[] points) {
            this.points = points;
            columns = new Rectangle[points.length];

            for (int i = 0; i < columns.length; i++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(null);
                rectangle.setStroke(Color.BLACK);

                columns[i] = rectangle;
            }
            getChildren().addAll(columns);

            setOnResize();
            // BG color to differentiate each view
            setBackground(new Background(
                    new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
                            CornerRadii.EMPTY, Insets.EMPTY)));

            setPrefSize(100, 100);
        }

        private void setOnResize() {
            heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    double unit = newValue.doubleValue() / columns.length;
                    for (int i = 0; i < columns.length; i++) {
                        columns[i].setHeight(points[i] * unit);
                        columns[i].setY(newValue.doubleValue() - columns[i].getHeight());
                    }
                }
            });

            widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    double unit = newValue.doubleValue() / columns.length;
                    for (int i = 0; i < columns.length; i++) {
                        columns[i].setWidth(unit);
                        columns[i].setX(unit * i);
                    }
                }
            });
        }
    }
}
